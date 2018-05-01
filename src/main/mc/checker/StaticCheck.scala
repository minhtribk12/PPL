package mc.checker

/**
 * @author nhphung
  * Student name: Nguyen Minh Tri
 * Student ID: 1770026
 */

import mc.parser._
import mc.utils._
import java.io.{File, PrintWriter}

//import mc.codegen.Val
import org.antlr.v4.runtime.ANTLRFileStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree._

import scala.collection.JavaConverters._


trait Utils {
	def lookup[T](name:String,lst:List[T],func:T=>String):Option[T] = lst match {
    	case List() => None
    	case head::tail => if (name == func(head)) Some(head) else lookup(name,tail,func)
  	}
	
	
}

class StaticChecker(ast:AST) extends BaseVisitor with Utils {
    
    def check() = visit(ast,null)

    def tostring(decl:Decl) : String = {
		if (decl.isInstanceOf[VarDecl]) {
			decl.asInstanceOf[VarDecl].variable.name 
		}
		else {
			decl.asInstanceOf[FuncDecl].name.name
		}
    }

	val builtInFunc = List(
					FuncDecl(Id("getInt"), List[VarDecl](), IntType, Block(List[Decl](), List[Stmt]())),
					FuncDecl(Id("putInt"), List[VarDecl](VarDecl(Id("i"), IntType)), VoidType, Block(List[Decl](), List[Stmt]())),
					FuncDecl(Id("putIntLn"), List[VarDecl](VarDecl(Id("i"), IntType)), VoidType, Block(List[Decl](), List[Stmt]())),
					FuncDecl(Id("getFloat"), List[VarDecl](), IntType, Block(List[Decl](), List[Stmt]())),
					FuncDecl(Id("putFloat"), List[VarDecl](VarDecl(Id("f"), FloatType)), VoidType, Block(List[Decl](), List[Stmt]())),
					FuncDecl(Id("putFloatLn"), List[VarDecl](VarDecl(Id("f"), FloatType)), VoidType, Block(List[Decl](), List[Stmt]())),
					FuncDecl(Id("putBool"), List[VarDecl](VarDecl(Id("b"), BoolType)), VoidType, Block(List[Decl](), List[Stmt]())),
					FuncDecl(Id("putBoolLn"), List[VarDecl](VarDecl(Id("b"), BoolType)), VoidType, Block(List[Decl](), List[Stmt]())),
					FuncDecl(Id("putString"), List[VarDecl](VarDecl(Id("s"), StringType)), VoidType, Block(List[Decl](), List[Stmt]())),
					FuncDecl(Id("putStringLn"), List[VarDecl](VarDecl(Id("s"), StringType)), VoidType, Block(List[Decl](), List[Stmt]())),
					FuncDecl(Id("putLn"), List[VarDecl](), VoidType, Block(List[Decl](), List[Stmt]()))).asInstanceOf[List[Decl]]

	def checkID(id:Decl, idlist:List[Decl], level:Int) = {
		val name = if(id.isInstanceOf[FuncDecl]) id.asInstanceOf[FuncDecl].name.name else id.asInstanceOf[VarDecl].variable.name
		if (lookup(name, builtInFunc, tostring) != None) {
			if(id.isInstanceOf[FuncDecl]) throw Redeclared(Function, name)
            else if (level == 1) throw Redeclared(Parameter, name)
            else throw Redeclared(Variable, name)
        }
		else if (lookup(name, idlist, tostring) != None) {
			if(id.isInstanceOf[FuncDecl]) throw Redeclared(Function, name)
            else if (level == 1) throw Redeclared(Parameter, name)
            else throw Redeclared(Variable, name)
        }
	}
	def checkIDexist(name:String, idlist:List[Decl]) = {
		val builtFunc = lookup(name, builtInFunc, tostring)
		if (builtFunc != None) builtFunc
		else{
			var func = lookup(name, idlist, tostring)
			if (func != None) func
			else None
		}
	}

    override def visitProgram(ast: Program, c: Any): Any = {
        val declList = ast.decl.asInstanceOf[List[Decl]]
        val checkList = declList.foldLeft(List(List[Decl]()))((a,b) => b.accept(this, List(a, 0)).asInstanceOf[List[List[Decl]]])
		val funcList = checkList.asInstanceOf[List[List[Decl]]].flatten.filter(_.isInstanceOf[FuncDecl]).asInstanceOf[List[FuncDecl]]
		lookup("main",funcList, tostring) match {
			case Some(x) => if(x.returnType != VoidType) throw NoEntryPoint
			case None => throw NoEntryPoint
		}
        null
    }

    override def visitVarDecl(ast: VarDecl, c: Any): Any = {
		val idlist = c.asInstanceOf[List[Any]](0).asInstanceOf[List[List[Decl]]]
		val varname = ast.variable.name
		val level = c.asInstanceOf[List[Any]](1).asInstanceOf[Int]
		var currentlist = idlist.asInstanceOf[List[List[Decl]]](0).asInstanceOf[List[Decl]]
		checkID(ast, currentlist, level)
		val newList = ast.asInstanceOf[Decl] :: currentlist
		return newList.asInstanceOf[List[Decl]] :: idlist.drop(1).asInstanceOf[List[List[Decl]]]
    }

    override def visitFuncDecl(ast: FuncDecl, c: Any): Any = {
        val idlist = c.asInstanceOf[List[Any]](0).asInstanceOf[List[List[Decl]]]
		val level = c.asInstanceOf[List[Any]](1).asInstanceOf[Int]
		var currentlist = idlist.asInstanceOf[List[List[Decl]]](0).asInstanceOf[List[Decl]]
		checkID(ast, currentlist, level)
		val newList = ast.asInstanceOf[Decl] :: currentlist
		val funcList = newList.asInstanceOf[List[Decl]] :: idlist.drop(1).asInstanceOf[List[List[Decl]]]
		val newEmptyList = List[Decl]() :: funcList
		val paramList = ast.param.foldLeft(newEmptyList)((a, b) => b.accept(this, List(a, 1, List[Any](), false)).asInstanceOf[List[List[Decl]]])
		val body = ast.body.accept(this, List(paramList, 2, List[Any](), false)).asInstanceOf[List[Any]]
		if ((ast.returnType != VoidType) && body.isEmpty) throw FunctionNotReturn(ast.name.name)
		return funcList
    }

	override def visitBlock(ast: Block, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val idlist = env(0).asInstanceOf[List[List[Decl]]]
		val level = env(1).asInstanceOf[Int]
		val loopcheck = env(3).asInstanceOf[Boolean]
		val newEnv = ast.decl.foldLeft(idlist)((a, b) => b.accept(this, List(a, level)).asInstanceOf[List[List[Decl]]])
		val envchecklist = env(2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		val returnList = ast.stmt.foldLeft(List[Any]())((list,x) => 
			if (x.isInstanceOf[Block]) {
				val tmpEnv1 = List[Decl]() :: newEnv.asInstanceOf[List[List[Decl]]]
				val returnCheck = x.accept(this, List(tmpEnv1.asInstanceOf[List[List[Decl]]], level.asInstanceOf[Int] + 1, list, loopcheck))
				returnCheck :: list.asInstanceOf[List[Any]]
			}
			else 
			{
				val returnCheck = x.accept(this, List(newEnv.asInstanceOf[List[List[Decl]]], level, list, loopcheck))
				returnCheck :: list.asInstanceOf[List[Any]]
			}
		)
		if (returnList.filter(_.isInstanceOf[List[Any]]).isEmpty) {
			if (level == 2) return List[Any]()
			else return false
		}
		else return returnList.filter(_.isInstanceOf[List[Any]]).head
	}
	
	override def visitIntType(ast: IntType.type, c: Any): Any = IntType 
  	override def visitFloatType(ast: FloatType.type, c: Any): Any = FloatType
  	override def visitBoolType(ast: BoolType.type, c: Any): Any = BoolType
	override def visitStringType(ast: StringType.type, c: Any): Any = StringType
	override def visitVoidType(ast: VoidType.type, c: Any): Any = VoidType
	override def visitArrayType(ast: ArrayType, c: Any): Any =  ArrayType(ast.dimen, ast.eleType)
	override def visitArrayPointerType(ast:ArrayPointerType, c: Any): Any = ArrayPointerType(ast.eleType)
	override def visitBinaryOp(ast: BinaryOp, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val envchecklist = env(2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		val lt = ast.left.accept(this, c).asInstanceOf[Type]
		val rt = ast.right.accept(this, c).asInstanceOf[Type]
		if (ast.op == "+" || ast.op == "-" || ast.op == "*" || ast.op == "/") {
			if (lt == IntType) {
				if (rt == IntType) IntType
				else if (rt == FloatType) FloatType
				else throw TypeMismatchInExpression(ast)
			}
			else if (lt == FloatType) {
				if (rt == IntType || rt == FloatType) FloatType
				else throw TypeMismatchInExpression(ast)
			}
			else throw TypeMismatchInExpression(ast)
		}
		else if (ast.op == "<" || ast.op == "<=" || ast.op == ">" || ast.op == ">=") {
			if ((lt == IntType || lt == FloatType) && (rt == IntType || rt == FloatType)) BoolType
			else throw TypeMismatchInExpression(ast)
		}
		else if (ast.op == "==" || ast.op == "!=") {
			if (lt == BoolType) {
				if (rt == BoolType) BoolType
				else throw TypeMismatchInExpression(ast)
			}
			else if (lt == IntType)
			{
				if (rt == IntType) BoolType
				else throw TypeMismatchInExpression(ast)
			}
			else throw TypeMismatchInExpression(ast)
		}
		else if (ast.op == "&&" || ast.op == "||") {
			if (lt == BoolType && rt == BoolType) BoolType
			else throw TypeMismatchInExpression(ast)
		}
		else {	
			if (lt == IntType) {
				if (rt == IntType) IntType
				else throw TypeMismatchInExpression(ast)
			}
			else if (lt == FloatType) {
				if (rt == IntType || rt == FloatType) FloatType
				else throw TypeMismatchInExpression(ast)
			}
			else if (lt == BoolType) {
				if (rt == BoolType) BoolType
				else throw TypeMismatchInExpression(ast)
			}
			else if (lt == StringType) {
				if (rt == StringType) StringType
				else throw TypeMismatchInExpression(ast)
			}
			else if (lt.isInstanceOf[ArrayPointerType]) {
				if (rt.isInstanceOf[ArrayPointerType]) {
					if (lt.asInstanceOf[ArrayPointerType].eleType == rt.asInstanceOf[ArrayPointerType].eleType)
						lt.asInstanceOf[ArrayPointerType]
					else throw TypeMismatchInExpression(ast)
				}
				else throw TypeMismatchInExpression(ast)
			}
			else throw TypeMismatchInExpression(ast)
		}
	}
	
	override def visitUnaryOp(ast: UnaryOp, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val envchecklist = env(2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		val tmp = ast.body.accept(this, c).asInstanceOf[Type]
		if (ast.op == "!") {
			if (tmp == BoolType) BoolType
			else throw TypeMismatchInExpression(ast)
		}
		else { 	
			if (tmp == FloatType || tmp == IntType) tmp else throw TypeMismatchInExpression(ast)
		}
	}
	
	override def visitCallExpr(ast: CallExpr, c: Any): Any = {
		val envchecklist = c.asInstanceOf[List[Any]](2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		val env = c.asInstanceOf[List[Any]](0).asInstanceOf[List[List[Decl]]].flatten.filter(_.isInstanceOf[FuncDecl])
		val func = checkIDexist(ast.method.name, env) match {
			case None => null
			case Some(d) => d.asInstanceOf[FuncDecl]
		}
		if(func == null) throw Undeclared(Function, ast.method.name)
		if (ast.params.length != func.param.length) throw TypeMismatchInExpression(ast)
		else {
			func.param.zip(ast.params).map{case (x, y) => {
				val lt = x.varType
				val rt = y.accept(this, c)
				if (lt == IntType) {
					if (rt != IntType) throw TypeMismatchInExpression(ast)
                }
				else if (lt == FloatType) {
						if (rt != IntType && rt != FloatType) throw TypeMismatchInExpression(ast)
				}
				else if (lt == BoolType) {
						if (rt != BoolType) throw TypeMismatchInExpression(ast)
				}
				else if (lt == StringType) {
					if (rt != StringType) throw TypeMismatchInExpression(ast)
				}
				else {	//(lt.isInstanceOf[ArrayPointerType])
					if (rt.isInstanceOf[ArrayPointerType]) {
							if (lt.asInstanceOf[ArrayPointerType].eleType != rt.asInstanceOf[ArrayPointerType].eleType)
								throw TypeMismatchInExpression(ast)
					}
					else throw TypeMismatchInExpression(ast)
				}
			}}
		}
		return func.returnType
	}

	override def visitId(ast: Id, c: Any): Any = {
		val envchecklist = c.asInstanceOf[List[Any]](2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		val env = c.asInstanceOf[List[Any]](0).asInstanceOf[List[List[Decl]]].flatten.filter(_.isInstanceOf[VarDecl])
		val vartype = checkIDexist(ast.name, env) match {
			case None => throw Undeclared(Identifier, ast.name)
			case Some(d) => d.asInstanceOf[VarDecl].varType.asInstanceOf[Type]
		}
		return vartype
	}

	override def visitArrayCell(ast: ArrayCell, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val envchecklist = env(2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		val e1 = ast.arr.accept(this, c).asInstanceOf[Type]
		val e2 = ast.idx.accept(this, c).asInstanceOf[Type]
		if (e1.isInstanceOf[ArrayType] == false)
			throw TypeMismatchInExpression(ast.asInstanceOf[Expr])
		if (e2 != IntType)
			throw TypeMismatchInExpression(ast.asInstanceOf[Expr])
		return e1.asInstanceOf[ArrayType].eleType
	}

	override def visitIf(ast: If, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val level = env(1).asInstanceOf[Int]
		val idlist = env(0).asInstanceOf[List[List[Decl]]]
		val loopcheck = env(3).asInstanceOf[Boolean]
		val envchecklist = c.asInstanceOf[List[Any]](2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		if (ast.expr.accept(this, c).asInstanceOf[Type] != BoolType) throw TypeMismatchInStatement(ast)
		val checkList = ast.elseStmt match {
			case Some(x) => {
					val checkReturnif = if (ast.thenStmt.isInstanceOf[Block]) {
						ast.thenStmt.accept(this, List(List[Decl]() :: idlist, level + 1, envchecklist, loopcheck))
					}
					else
					{		
						ast.thenStmt.accept(this, c)
					}
					val checkReturnelse = if (x.isInstanceOf[Block]) {
						x.accept(this, List(List[Decl]() :: idlist, level + 1, envchecklist, loopcheck))
					}
					else {
						x.accept(this, c)
					}
					if (checkReturnif.isInstanceOf[List[Any]] && checkReturnelse.isInstanceOf[List[Any]])
						if (checkReturnif.asInstanceOf[List[Any]](1).asInstanceOf[Type] == checkReturnelse.asInstanceOf[List[Any]](1).asInstanceOf[Type]) return checkReturnif.asInstanceOf[List[Any]]
						else return false
					else return false
				}
			case None => {
				val checkReturnif = if (ast.thenStmt.isInstanceOf[Block]) {
					ast.thenStmt.accept(this, List(List[Decl]() :: idlist, level + 1, envchecklist, loopcheck))
				}
				else
				{		
					ast.thenStmt.accept(this, c)
				}
				return false
			}
		}
	}
	override def visitFor(ast: For, c: Any): Any = {
		if (ast.expr1.accept(this, c).asInstanceOf[Type] != IntType) throw TypeMismatchInStatement(ast)
		if (ast.expr3.accept(this, c).asInstanceOf[Type] != IntType) throw TypeMismatchInStatement(ast)
		if (ast.expr2.accept(this, c).asInstanceOf[Type] != BoolType) throw TypeMismatchInStatement(ast)
		val level = c.asInstanceOf[List[Any]](1).asInstanceOf[Int]
		val idlist = c.asInstanceOf[List[Any]](0).asInstanceOf[List[List[Decl]]]
		val envchecklist = c.asInstanceOf[List[Any]](2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		val returnCheck = if (ast.loop.isInstanceOf[Block]) {
			ast.loop.accept(this, List(List[Decl]() :: idlist, level + 1, envchecklist, true))
		}
		else 
		{
			ast.loop.accept(this, List(idlist, level, envchecklist, true))
		}
		return returnCheck
	}
	override def visitBreak(ast: Break.type, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val envchecklist = c.asInstanceOf[List[Any]](2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		if (env(3) == null) throw BreakNotInLoop
		else if (env(3).asInstanceOf[Boolean] == false) throw BreakNotInLoop
		else return true
	}
	override def visitContinue(ast: Continue.type, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val envchecklist = c.asInstanceOf[List[Any]](2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		if (env(3) == null) throw ContinueNotInLoop
		else if (env(3).asInstanceOf[Boolean] == false) throw ContinueNotInLoop
		else return true
	}
	override def visitReturn(ast: Return, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val envchecklist = c.asInstanceOf[List[Any]](2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		val expEnv = env(0).asInstanceOf[List[List[Decl]]].flatten.filter(_.isInstanceOf[FuncDecl]).asInstanceOf[List[FuncDecl]]
		val funcType = expEnv(0).asInstanceOf[FuncDecl].returnType
		val returnType = ast.expr match {
			case None => VoidType
			case Some(x) => x.accept(this, c)
		}
		if (funcType == VoidType) {
			if (returnType != VoidType) throw TypeMismatchInStatement(ast)
		}
		else if (funcType == IntType) {
			if (returnType != IntType) throw TypeMismatchInStatement(ast)
		}
		else if (funcType == FloatType) {
			if (returnType != IntType && returnType != FloatType) throw TypeMismatchInStatement(ast)
		}
		else {	//(funcType == BoolType)
			if (returnType != BoolType) throw TypeMismatchInStatement(ast)
		}
		return List(true, funcType).asInstanceOf[List[Any]]
	}
	override def visitDowhile(ast: Dowhile, c:Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val idlist = env(0).asInstanceOf[List[List[Decl]]]
		val level = env(1).asInstanceOf[Int]
		val envchecklist = env(2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		if (ast.exp.accept(this, c).asInstanceOf[Type] != BoolType) throw TypeMismatchInStatement(ast)
		
		val returnList = ast.sl.foldLeft(List[Any]())((list,x) => 
			if (x.isInstanceOf[Block]) {
				val newEnv = List[Decl]() :: idlist.asInstanceOf[List[List[Decl]]]
				val returnCheck = x.accept(this, List(newEnv, level + 1, list, true))
				returnCheck :: list
			}
			else 
			{
				val returnCheck = x.accept(this, List(idlist, level, list, true))
				returnCheck :: list
			}
		)
		if (returnList.filter(_.isInstanceOf[List[Any]]).isEmpty) return false
		else returnList.filter(_.isInstanceOf[List[Any]])
	}
	override def visitIntLiteral(ast: IntLiteral, c: Any): Any = IntType
	override def visitFloatLiteral(ast: FloatLiteral, c: Any): Any = FloatType
	override def visitStringLiteral(ast: StringLiteral, c: Any): Any = StringType
	override def visitBooleanLiteral(ast: BooleanLiteral, c: Any): Any = BoolType
}
