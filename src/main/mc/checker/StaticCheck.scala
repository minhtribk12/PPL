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
		val checkFunc = declList.foldLeft(builtInFunc)((a,b) => 
			{
				if(b.isInstanceOf[FuncDecl] == true)
				{
					checkID(b, a, 0)
				}
				b.asInstanceOf[Decl] :: a.asInstanceOf[List[Decl]]
			}
		)
		val allfunc = checkFunc.filter(_.isInstanceOf[FuncDecl])
        val checkList = declList.foldLeft(List(List[Decl]()))((a,b) => b.accept(this, List(a, 0, allfunc)).asInstanceOf[List[List[Decl]]])
		val funcList = checkList.asInstanceOf[List[List[Decl]]].flatten.filter(_.isInstanceOf[FuncDecl]).asInstanceOf[List[FuncDecl]]
		val listFuncall = VarDecl(Id("main"), IntType) :: checkList.asInstanceOf[List[List[Decl]]].flatten.filter(_.isInstanceOf[VarDecl]).asInstanceOf[List[VarDecl]]
		lookup("main",funcList, tostring) match {
			case Some(x) => if(x.returnType != VoidType) throw NoEntryPoint
			case None => throw NoEntryPoint
		}
		funcList.map(x => {
			lookup(x.name.name,listFuncall, tostring) match {
				case Some(f) => {}
				case None => throw UnreachableFunction(x.name.name)
			}
		})
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
		val env = c.asInstanceOf[List[Any]]
        val idlist = env(0).asInstanceOf[List[List[Decl]]]
		val level = env(1).asInstanceOf[Int]
		val allfunc = env(2).asInstanceOf[List[FuncDecl]]
		var currentlist = idlist.asInstanceOf[List[List[Decl]]](0).asInstanceOf[List[Decl]]
		val newList = ast.asInstanceOf[Decl] :: currentlist
		val funcList = newList.asInstanceOf[List[Decl]] :: idlist.drop(1).asInstanceOf[List[List[Decl]]]
		val newEmptyList = List[Decl]() :: funcList
		val paramList = ast.param.foldLeft(newEmptyList)((a, b) => b.accept(this, List(a, 1, List[Any](), false, List[Decl]())).asInstanceOf[List[List[Decl]]])
		val body = ast.body.accept(this, List(paramList, 2, List[Any](), false, List[Decl](), allfunc)).asInstanceOf[List[Any]]
		if ((ast.returnType != VoidType) && body(2).asInstanceOf[List[Any]].filter(_.isInstanceOf[List[Any]]).isEmpty) throw FunctionNotReturn(ast.name.name)
		val returnlist = body(4).asInstanceOf[List[Decl]].foldLeft(newList)((a,b) => b.asInstanceOf[Decl] :: a)
		return returnlist.asInstanceOf[List[Decl]] :: idlist.drop(1).asInstanceOf[List[List[Decl]]]
    }

	override def visitBlock(ast: Block, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val idlist = env(0).asInstanceOf[List[List[Decl]]]
		val level = env(1).asInstanceOf[Int]
		val loopcheck = env(3).asInstanceOf[Boolean]
		val listfuncall = env(4).asInstanceOf[List[Decl]]
		val allfunc = env(5).asInstanceOf[List[FuncDecl]]
		val newEnv = ast.decl.foldLeft(idlist)((a, b) => b.accept(this, List(a, level)).asInstanceOf[List[List[Decl]]])
		val envchecklist = env(2).asInstanceOf[List[Any]]
		if (!envchecklist.filter(_.isInstanceOf[Boolean]).isEmpty || !envchecklist.filter(_.isInstanceOf[List[Any]]).isEmpty) throw UnreachableStatement(ast)
		val blockEnv = List(newEnv.asInstanceOf[List[List[Decl]]], level, List[Any](), loopcheck, listfuncall, allfunc)
		val returnEnv = ast.stmt.foldLeft(blockEnv)((thisEnv,x) => 
			if (x.isInstanceOf[Block]) {
				x.accept(this, List(List[Decl]() :: thisEnv(0).asInstanceOf[List[List[Decl]]], thisEnv(1).asInstanceOf[Int] + 1, thisEnv(2), thisEnv(3), thisEnv(4), thisEnv(5))).asInstanceOf[List[Any]]
			}
			else 
			{
				x.accept(this, thisEnv).asInstanceOf[List[Any]]
			}
		)
		return List(env(0), env(1), returnEnv(2), env(3), returnEnv(4), env(5))
	}
	override def visitIf(ast: If, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val level = env(1).asInstanceOf[Int]
		val idlist = env(0).asInstanceOf[List[List[Decl]]]
		val loopcheck = env(3).asInstanceOf[Boolean]
		val listfuncall = env(4).asInstanceOf[List[Decl]]
		val allfunc = env(5).asInstanceOf[List[FuncDecl]]
		val envchecklist = env(2).asInstanceOf[List[Any]]
		if (!envchecklist.filter(_.isInstanceOf[Boolean]).isEmpty || !envchecklist.filter(_.isInstanceOf[List[Any]]).isEmpty) throw UnreachableStatement(ast)
		val ifexp = ast.expr.accept(this, c).asInstanceOf[List[Any]]
		if (ifexp(6).asInstanceOf[Type] != BoolType) throw TypeMismatchInStatement(ast)
		val checkList = ast.elseStmt match {
			case Some(x) => {
				val checkReturnif = if (ast.thenStmt.isInstanceOf[Block]) {
					ast.thenStmt.accept(this, List(List[Decl]() :: env(0).asInstanceOf[List[List[Decl]]], env(1).asInstanceOf[Int] + 1, env(2), env(3), List[Decl](), env(5)))
				}
				else
				{		
					ast.thenStmt.accept(this, List(env(0), env(1) , env(2), env(3), List[Decl](), env(5)))
				}
				val checkReturnelse = if (x.isInstanceOf[Block]) {
					x.accept(this, List(List[Decl]() :: env(0).asInstanceOf[List[List[Decl]]], env(1).asInstanceOf[Int] + 1, env(2), env(3), List[Decl](), env(5)))
				}
				else {
					x.accept(this, List(env(0), env(1) , env(2), env(3), List[Decl](), env(5)))
				}
				val funcallif = checkReturnif.asInstanceOf[List[Any]](4).asInstanceOf[List[Decl]]
				val funcallelse = checkReturnelse.asInstanceOf[List[Any]](4).asInstanceOf[List[Decl]]
				val funcall = ifexp(4).asInstanceOf[List[Decl]]:::funcallif:::funcallelse.asInstanceOf[List[Decl]]
				if (!checkReturnif.isInstanceOf[Type] && !checkReturnelse.isInstanceOf[Type])
					if (!checkReturnif.asInstanceOf[List[Any]](2).asInstanceOf[List[Any]].isEmpty && !checkReturnelse.asInstanceOf[List[Any]](2).asInstanceOf[List[Any]].isEmpty)
					{
						return List(env(0), env(1), checkReturnif.asInstanceOf[List[Any]](2), env(3), funcall, env(5))
					}
					
					else return List(env(0), env(1), env(2), env(3), funcall, env(5))
				else return List(env(0), env(1), env(2), env(3), funcall, env(5))
			}
			case None => {
				val checkReturnif = if (ast.thenStmt.isInstanceOf[Block]) {
					ast.thenStmt.accept(this, List(List[Decl]() :: env(0).asInstanceOf[List[List[Decl]]], env(1).asInstanceOf[Int] + 1, env(2), env(3), env(4), env(5)))
				}
				else
				{		
					ast.thenStmt.accept(this, List(env(0), env(1) , env(2), env(3), List[Decl](), env(5)))
				}
				val funcallif = ifexp(4).asInstanceOf[List[Decl]]:::checkReturnif.asInstanceOf[List[Any]](4).asInstanceOf[List[Decl]]
				return List(env(0), env(1), env(2), env(3), funcallif, env(5))
			}
		}
	}

	override def visitFor(ast: For, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val forexp1 = ast.expr1.accept(this, List(env(0), env(1) , env(2), env(3), List[Decl](), env(5))).asInstanceOf[List[Any]]
		val forexp2 = ast.expr2.accept(this, List(env(0), env(1) , env(2), env(3), List[Decl](), env(5))).asInstanceOf[List[Any]]
		val forexp3 = ast.expr3.accept(this, List(env(0), env(1) , env(2), env(3), List[Decl](), env(5))).asInstanceOf[List[Any]]
		if (forexp1(6).asInstanceOf[Type] != IntType) throw TypeMismatchInStatement(ast)
		if (forexp2(6).asInstanceOf[Type] != BoolType) throw TypeMismatchInStatement(ast)
		if (forexp3(6).asInstanceOf[Type] != IntType) throw TypeMismatchInStatement(ast)
		val level = env(1).asInstanceOf[Int]
		val idlist = env(0).asInstanceOf[List[List[Decl]]]
		val envchecklist = c.asInstanceOf[List[Any]](2).asInstanceOf[List[Any]]
		if (!envchecklist.filter(_.isInstanceOf[Boolean]).isEmpty || !envchecklist.filter(_.isInstanceOf[List[Any]]).isEmpty) throw UnreachableStatement(ast)
		val returnCheck = if (ast.loop.isInstanceOf[Block]) {
			ast.loop.accept(this, List(List[Decl]() :: env(0).asInstanceOf[List[List[Decl]]], env(1).asInstanceOf[Int] + 1, env(2), true, env(4), env(5)))
		}
		else 
		{
			ast.loop.accept(this, List(env(0), env(1), env(2), true, env(4), env(5)))
		}
		val listfuncall = forexp1(4).asInstanceOf[List[Decl]] ::: forexp2(4).asInstanceOf[List[Decl]] ::: forexp3(4).asInstanceOf[List[Decl]] ::: returnCheck.asInstanceOf[List[Any]](4).asInstanceOf[List[Decl]]
		return List(env(0), env(1), returnCheck.asInstanceOf[List[Any]](2).asInstanceOf[List[Any]].filter(_.isInstanceOf[List[Any]]), env(3), listfuncall, env(5))
	}
	
	override def visitDowhile(ast: Dowhile, c:Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val idlist = env(0).asInstanceOf[List[List[Decl]]]
		val level = env(1).asInstanceOf[Int]
		val envchecklist = env(2).asInstanceOf[List[Any]]
		if (!envchecklist.filter(_.isInstanceOf[Boolean]).isEmpty || !envchecklist.filter(_.isInstanceOf[List[Any]]).isEmpty) throw UnreachableStatement(ast)
		val dowhileexp = ast.exp.accept(this, c).asInstanceOf[List[Any]]
		if (dowhileexp(6).asInstanceOf[Type] != BoolType) throw TypeMismatchInStatement(ast)
		val newEnv = List(env(0), env(1), List[Any](), env(3), env(4), env(5))
		val returnList = ast.sl.foldLeft(newEnv)((thisEnv,x) => 
			if (x.isInstanceOf[Block]) {
				x.accept(this, List(List[Decl]()::thisEnv(0).asInstanceOf[List[List[Decl]]], thisEnv(1).asInstanceOf[Int] + 1, thisEnv(2), true, thisEnv(4), thisEnv(5))).asInstanceOf[List[Any]]
			}
			else 
			{
				x.accept(this, List(thisEnv(0), thisEnv(1), thisEnv(2), true, thisEnv(4), thisEnv(5))).asInstanceOf[List[Any]]
			}
		)
		val returncheck = returnList(2).asInstanceOf[List[Any]].foldLeft(env(2).asInstanceOf[List[Any]])((a,b) => b :: a)
		return List(env(0), env(1), returncheck.asInstanceOf[List[Any]].filter(_.isInstanceOf[List[Any]]), env(3), dowhileexp(4).asInstanceOf[List[Decl]]:::returnList(4).asInstanceOf[List[Any]], env(5))
	}

	override def visitBreak(ast: Break.type, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val envchecklist = c.asInstanceOf[List[Any]](2).asInstanceOf[List[Any]]
		if (!envchecklist.filter(_.isInstanceOf[Boolean]).isEmpty || !envchecklist.filter(_.isInstanceOf[List[Any]]).isEmpty) throw UnreachableStatement(ast)
		if (env(3) == null) throw BreakNotInLoop
		else if (env(3).asInstanceOf[Boolean] == false) throw BreakNotInLoop
		else return List(env(0), env(1), true :: env(2).asInstanceOf[List[Any]], env(3), env(4), env(5))
	}
	override def visitContinue(ast: Continue.type, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val envchecklist = c.asInstanceOf[List[Any]](2).asInstanceOf[List[Any]]
		if (!envchecklist.filter(_.isInstanceOf[Boolean]).isEmpty || !envchecklist.filter(_.isInstanceOf[List[Any]]).isEmpty) throw UnreachableStatement(ast)
		if (env(3) == null) throw ContinueNotInLoop
		else if (env(3).asInstanceOf[Boolean] == false) throw ContinueNotInLoop
		else return List(env(0), env(1), true :: env(2).asInstanceOf[List[Any]], env(3), env(4), env(5))
	}
	override def visitReturn(ast: Return, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val envchecklist = c.asInstanceOf[List[Any]](2).asInstanceOf[List[Any]]
		if (!envchecklist.filter(_.isInstanceOf[Boolean]).isEmpty || !envchecklist.filter(_.isInstanceOf[List[Any]]).isEmpty) throw UnreachableStatement(ast)
		val expEnv = env(0).asInstanceOf[List[List[Decl]]].flatten.filter(_.isInstanceOf[FuncDecl]).asInstanceOf[List[FuncDecl]]
		val funcType = expEnv(0).asInstanceOf[FuncDecl].returnType
		val returnEnv = ast.expr match {
			case None => VoidType
			case Some(x) => x.accept(this, c).asInstanceOf[List[Any]]
		}
		var returnType = if(returnEnv.isInstanceOf[Type]) returnEnv else returnEnv.asInstanceOf[List[Any]](6).asInstanceOf[Type]
		if (funcType == VoidType) {
			if (returnType != VoidType) throw TypeMismatchInStatement(ast)
		}
		else if (funcType == IntType) {
			if (returnType != IntType) throw TypeMismatchInStatement(ast)
		}
		else if (funcType == FloatType) {
			if (returnType != IntType && returnType != FloatType) throw TypeMismatchInStatement(ast)
		}
		else if (funcType == BoolType) {	
			if (returnType != BoolType) throw TypeMismatchInStatement(ast)
		}
		else if (funcType.isInstanceOf[ArrayPointerType]) {
			val returntype = if(returnType.isInstanceOf[ArrayType]) returnType.asInstanceOf[ArrayType].eleType else returnType.asInstanceOf[ArrayPointerType].eleType
			if (funcType.asInstanceOf[ArrayPointerType].eleType != returntype) throw TypeMismatchInStatement(ast)
		}
		else throw TypeMismatchInStatement(ast)
		val returnfunc = if(returnEnv.isInstanceOf[List[Any]]) returnEnv.asInstanceOf[List[Any]](4) else env(4)
		return List(env(0), env(1), List(true, returnType) :: env(2).asInstanceOf[List[Any]], env(3), returnfunc, env(5))
	}

	override def visitCallExpr(ast: CallExpr, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val envchecklist = env(2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		val funclist = env(5).asInstanceOf[List[FuncDecl]]
		val func = checkIDexist(ast.method.name, funclist) match {
			case None => null
			case Some(d) => d.asInstanceOf[FuncDecl]
		}
		if(func == null) throw Undeclared(Function, ast.method.name)
		if (ast.params.length != func.param.length) throw TypeMismatchInExpression(ast)
		else {
			func.param.zip(ast.params).map{case (x, y) => {
				val lt = x.varType
				val rt = y.accept(this, c).asInstanceOf[List[Any]](6).asInstanceOf[Type]
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
				else if (lt.isInstanceOf[ArrayPointerType] || lt.isInstanceOf[ArrayType]){	
					val leftexptype = if(lt.isInstanceOf[ArrayPointerType]) lt.asInstanceOf[ArrayPointerType].eleType else lt.asInstanceOf[ArrayType].eleType
					if (rt.isInstanceOf[ArrayPointerType] || rt.isInstanceOf[ArrayType]) {
						val rightexptype = if(rt.isInstanceOf[ArrayPointerType]) rt.asInstanceOf[ArrayPointerType].eleType else rt.asInstanceOf[ArrayType].eleType
						if (leftexptype != rightexptype) throw TypeMismatchInExpression(ast)
					}
					else throw TypeMismatchInExpression(ast)
				}
				else throw TypeMismatchInExpression(ast)
			}}
		}
		val newfunclist = VarDecl(Id(func.asInstanceOf[FuncDecl].name.name), IntType) :: env(4).asInstanceOf[List[Decl]]
		return List(env(0), env(1), env(2), env(3), newfunclist, env(5), func.returnType)
	}

	override def visitBinaryOp(ast: BinaryOp, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val envchecklist = env(2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		val leftexp = ast.left.accept(this, env).asInstanceOf[List[Any]]
		val rightexp = ast.right.accept(this, List(env(0), env(1), env(2), env(3), List[Decl](), env(5))).asInstanceOf[List[Any]]
		val lt = leftexp(6).asInstanceOf[Type]
		val rt = rightexp(6).asInstanceOf[Type]
		val funclist = rightexp(4).asInstanceOf[List[Decl]]:::leftexp(4).asInstanceOf[List[Decl]]
		if (ast.op == "+" || ast.op == "-" || ast.op == "*" || ast.op == "/") {
			if (lt == IntType) {
				if (rt == IntType) List(env(0), env(1), env(2), env(3), funclist, env(5), IntType)
				else if (rt == FloatType) List(env(0), env(1), env(2), env(3), funclist, env(5), FloatType)
				else throw TypeMismatchInExpression(ast)
			}
			else if (lt == FloatType) {
				if (rt == IntType || rt == FloatType) List(env(0), env(1), env(2), env(3), funclist, env(5), FloatType)
				else throw TypeMismatchInExpression(ast)
			}
			else throw TypeMismatchInExpression(ast)
		}
		else if (ast.op == "<" || ast.op == "<=" || ast.op == ">" || ast.op == ">=") {
			if ((lt == IntType || lt == FloatType) && (rt == IntType || rt == FloatType)) List(env(0), env(1), env(2), env(3), funclist, env(5), BoolType)
			else throw TypeMismatchInExpression(ast)
		}
		else if (ast.op == "==" || ast.op == "!=") {
			if (lt == BoolType) {
				if (rt == BoolType) List(env(0), env(1), env(2), env(3), funclist, env(5), BoolType)
				else throw TypeMismatchInExpression(ast)
			}
			else if (lt == IntType)
			{
				if (rt == IntType) List(env(0), env(1), env(2), env(3), funclist, env(5), BoolType)
				else throw TypeMismatchInExpression(ast)
			}
			else throw TypeMismatchInExpression(ast)
		}
		else if (ast.op == "&&" || ast.op == "||") {
			if (lt == BoolType && rt == BoolType) List(env(0), env(1), env(2), env(3), funclist, env(5), BoolType)
			else throw TypeMismatchInExpression(ast)
		}
		else {	
			if (lt == IntType) {
				if (rt == IntType) List(env(0), env(1), env(2), env(3), funclist, env(5), IntType)
				else throw TypeMismatchInExpression(ast)
			}
			else if (lt == FloatType) {
				if (rt == IntType || rt == FloatType) List(env(0), env(1), env(2), env(3), funclist, env(5), FloatType)
				else throw TypeMismatchInExpression(ast)
			}
			else if (lt == BoolType) {
				if (rt == BoolType) List(env(0), env(1), env(2), env(3), funclist, env(5), BoolType)
				else throw TypeMismatchInExpression(ast)
			}
			else if (lt == StringType) {
				if (rt == StringType) List(env(0), env(1), env(2), env(3), funclist, env(5), StringType)
				else throw TypeMismatchInExpression(ast)
			}
			else throw TypeMismatchInExpression(ast)
		}
	}
	
	override def visitUnaryOp(ast: UnaryOp, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val envchecklist = env(2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		val bodyexp = ast.body.accept(this, c).asInstanceOf[List[Any]]
		val tmp = bodyexp(6).asInstanceOf[Type]
		if (ast.op == "!") {
			if (tmp == BoolType) List(env(0), env(1), env(2), env(3), bodyexp(4), env(5), BoolType)
			else throw TypeMismatchInExpression(ast)
		}
		else { 	
			if (tmp == FloatType || tmp == IntType) List(env(0), env(1), env(2), env(3), bodyexp(4), env(5), tmp) else throw TypeMismatchInExpression(ast)
		}
	}

	override def visitId(ast: Id, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val envchecklist = env(2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		val varlist = env(0).asInstanceOf[List[List[Decl]]].flatten.filter(_.isInstanceOf[VarDecl])
		val vartype = checkIDexist(ast.name, varlist) match {
			case None => throw Undeclared(Identifier, ast.name)
			case Some(d) => d.asInstanceOf[VarDecl].varType.asInstanceOf[Type]
		}
		return List(env(0), env(1), env(2), env(3), env(4), env(5), vartype)
	}

	override def visitArrayCell(ast: ArrayCell, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		val envchecklist = env(2).asInstanceOf[List[Any]]
		if (envchecklist.filter(_.isInstanceOf[Boolean]).exists(_ == true) || envchecklist.filter(_.isInstanceOf[List[Any]]).exists(_.asInstanceOf[List[Any]](0).asInstanceOf[Boolean] == true)) throw UnreachableStatement(ast)
		val arrexp = ast.arr.accept(this, env).asInstanceOf[List[Any]]
		val idxexp = ast.idx.accept(this, List(env(0), env(1), env(2), env(3), List[Decl](), env(5))).asInstanceOf[List[Any]]
		val funclist = idxexp(4).asInstanceOf[List[Decl]]:::arrexp(4).asInstanceOf[List[Decl]]
		val e1 = arrexp(6).asInstanceOf[Type]
		val e2 = idxexp(6).asInstanceOf[Type]
		if (!e1.isInstanceOf[ArrayType] && !e1.isInstanceOf[ArrayPointerType])
			throw TypeMismatchInExpression(ast)
		if (e2 != IntType)
			throw TypeMismatchInExpression(ast)
		val returntype = if(e1.isInstanceOf[ArrayType]) e1.asInstanceOf[ArrayType].eleType else e1.asInstanceOf[ArrayPointerType].eleType
		return return List(env(0), env(1), env(2), env(3), funclist, env(5), returntype)
	}

	override def visitIntType(ast: IntType.type, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		List(env(0), env(1), env(2), env(3), env(4), env(5), IntType) 
	}
  	override def visitFloatType(ast: FloatType.type, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		List(env(0), env(1), env(2), env(3), env(4), env(5), FloatType)
	}
  	override def visitBoolType(ast: BoolType.type, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		List(env(0), env(1), env(2), env(3), env(4), env(5), BoolType)
	}
	override def visitStringType(ast: StringType.type, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		List(env(0), env(1), env(2), env(3), env(4), env(5), StringType)
	}
	override def visitVoidType(ast: VoidType.type, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		List(env(0), env(1), env(2), env(3), env(4), env(5), VoidType)
	}
	override def visitArrayType(ast: ArrayType, c: Any): Any =  {
		val env = c.asInstanceOf[List[Any]]
		List(env(0), env(1), env(2), env(3), env(4), env(5), ArrayType(ast.dimen, ast.eleType))
	}
	override def visitArrayPointerType(ast:ArrayPointerType, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		List(env(0), env(1), env(2), env(3), env(4), env(5), ArrayPointerType(ast.eleType))
	}
	override def visitIntLiteral(ast: IntLiteral, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		List(env(0), env(1), env(2), env(3), env(4), env(5), IntType)
	}
	override def visitFloatLiteral(ast: FloatLiteral, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		List(env(0), env(1), env(2), env(3), env(4), env(5), FloatType)
	}
	override def visitStringLiteral(ast: StringLiteral, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		List(env(0), env(1), env(2), env(3), env(4), env(5), StringType)
	}
	override def visitBooleanLiteral(ast: BooleanLiteral, c: Any): Any = {
		val env = c.asInstanceOf[List[Any]]
		List(env(0), env(1), env(2), env(3), env(4), env(5), BoolType)
	}
}
