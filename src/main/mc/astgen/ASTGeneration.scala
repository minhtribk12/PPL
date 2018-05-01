package mc.astgen
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.ParserRuleContext
import java.io.{PrintWriter,File}
import org.antlr.v4.runtime.ANTLRFileStream
import mc.utils._
import scala.collection.JavaConverters._
import org.antlr.v4.runtime.tree._
import mc.parser._
import mc.parser.MCParser._

class ASTGeneration extends MCBaseVisitor[Any] {

  override def visitProgram(ctx:ProgramContext) = 
    Program(ctx.declaration().asScala.toList.foldRight(List[Decl]())((x,y) => x.accept(this).asInstanceOf[List[Decl]]:::y))

  override def visitDeclaration(ctx:DeclarationContext) = ctx.getChild(0).accept(this)

  override def visitVardecl(ctx:VardeclContext) = {
    ctx.varlist().varname().asScala.toList.map(x => if(x.getChildCount() == 1) VarDecl(Id(x.ID.getText),
                                                                            ctx.primitivetype.accept(this).asInstanceOf[Type])
                                                      else VarDecl(Id(x.ID.getText),
                                                            ArrayType(IntLiteral(x.INTLIT.getText.toInt),
                                                            ctx.primitivetype.accept(this).asInstanceOf[Type])))
  }

  override def visitPrimitivetype(ctx:PrimitivetypeContext) = {
    if (ctx.INTTYPE != null) IntType
    else if (ctx.FLOATTYPE != null) FloatType
    else if (ctx.BOOLTYPE != null) BoolType
    else StringType
  }

  override def visitFuncdecl(ctx:FuncdeclContext) = {
    List(FuncDecl(Id(ctx.ID.getText), ctx.paralist.accept(this).asInstanceOf[List[VarDecl]],
            ctx.functype.accept(this).asInstanceOf[Type],
            ctx.block.accept(this).asInstanceOf[Stmt])).asInstanceOf[List[Decl]]
  }

  override def visitParalist(ctx:ParalistContext) = ctx.paradecl().asScala.toList.map(_.accept(this))

  override def visitParadecl(ctx:ParadeclContext) = {
    if(ctx.getChildCount == 2) VarDecl(Id(ctx.ID.getText), ctx.primitivetype.accept(this).asInstanceOf[Type])
    else VarDecl(Id(ctx.ID.getText), ArrayPointerType(ctx.primitivetype.accept(this).asInstanceOf[Type]))
  }

  override def visitFunctype(ctx:FunctypeContext) = {
    if(ctx.getChildCount == 1){
      if (ctx.VOIDTYPE != null) VoidType
      else{
        if(ctx.primitivetype().INTTYPE != null) IntType
        else if (ctx.primitivetype().FLOATTYPE != null) FloatType
        else if (ctx.primitivetype().BOOLTYPE != null) BoolType
        else StringType
      }
    }
    else ArrayPointerType(ctx.primitivetype.accept(this).asInstanceOf[Type])
  }

  override def visitBlock(ctx:BlockContext) = ctx.blockbody.accept(this)

  override def visitBlockbody(ctx:BlockbodyContext) = Block(ctx.declpart.accept(this).asInstanceOf[List[Decl]], ctx.stmtpart.accept(this).asInstanceOf[List[Stmt]])

  override def visitDeclpart(ctx:DeclpartContext) = ctx.vardecl.asScala.toList.foldRight(List[Decl]())((x,y) => x.accept(this).asInstanceOf[List[Decl]]:::y)

  override def visitStmtpart(ctx:StmtpartContext) = ctx.stmt.asScala.toList.map(_.accept(this))

  override def visitStmt(ctx:StmtContext) = if(ctx.getChild(0) != null) ctx.getChild(0).accept(this)

  override def visitIfstmt(ctx:IfstmtContext) = ctx.getChild(0).accept(this)

  override def visitIfelse(ctx:IfelseContext) = If(ctx.expression.accept(this).asInstanceOf[Expr],
                                                ctx.stmtif.accept(this).asInstanceOf[Stmt],
                                                Some(ctx.stmt.accept(this).asInstanceOf[Stmt]))

  override def visitIfnoelse(ctx:IfnoelseContext) = If(ctx.expression.accept(this).asInstanceOf[Expr],
                                                ctx.stmt.accept(this).asInstanceOf[Stmt],None)   

  override def visitStmtif(ctx:StmtifContext) = if(ctx.getChild(0) != null) ctx.getChild(0).accept(this)

  override def visitWhilestmt(ctx:WhilestmtContext) = Dowhile(ctx.stmt.asScala.toList.map(_.accept(this).asInstanceOf[Stmt]),
                                                      ctx.expression.accept(this).asInstanceOf[Expr])

  override def visitForstmt(ctx:ForstmtContext) = For(ctx.expression(0).accept(this).asInstanceOf[Expr],
                                                      ctx.expression(1).accept(this).asInstanceOf[Expr],
                                                      ctx.expression(2).accept(this).asInstanceOf[Expr],
                                                      ctx.stmt.accept(this).asInstanceOf[Stmt]) 

  override def visitBreakstmt(ctx:BreakstmtContext) = Break

  override def visitContinuestmt(ctx:ContinuestmtContext) = Continue

  override def visitReturnstmt(ctx:ReturnstmtContext) = {
    if(ctx.getChildCount == 3) Return(Some(ctx.expression.accept(this).asInstanceOf[Expr]))
    else Return(None)
  }

  // override def visitExpression(ctx:ExpressionContext) = {
  //   if(ctx.getChildCount() == 1) ctx.operand.accept(this)
  //   else if (ctx.getChildCount == 2) UnaryOp(ctx.getChild(0).getText, ctx.getChild(1).accept(this).asInstanceOf[Expr])
  //   else if (ctx.getChildCount == 3){
  //     if(ctx.LB != null) ctx.getChild(1).accept(this)
  //     else BinaryOp(ctx.getChild(1).getText, ctx.getChild(0).accept(this).asInstanceOf[Expr], ctx.getChild(2).accept(this).asInstanceOf[Expr])
  //   }
  //   else ArrayCell(ctx.getChild(0).accept(this).asInstanceOf[Expr], ctx.getChild(2).accept(this).asInstanceOf[Expr])
  // }

  // override def visitNaexpression(ctx:NaexpressionContext) = {
  //   if(ctx.getChildCount() == 1) ctx.operand.accept(this)
  //   else if (ctx.getChildCount == 2) UnaryOp(ctx.getChild(0).getText, ctx.getChild(1).accept(this).asInstanceOf[Expr])
  //   else if (ctx.getChildCount == 3){
  //     if(ctx.LB != null) ctx.getChild(1).accept(this)
  //     else BinaryOp(ctx.getChild(1).getText, ctx.getChild(0).accept(this).asInstanceOf[Expr], ctx.getChild(2).accept(this).asInstanceOf[Expr])
  //   }
  // }

  override def visitExpression(ctx:ExpressionContext) = {
    if (ctx.getChildCount() == 1) ctx.getChild(0).accept(this)
    else BinaryOp(ctx.getChild(1).getText, ctx.getChild(0).accept(this).asInstanceOf[Expr], ctx.getChild(2).accept(this).asInstanceOf[Expr])
  }

  override def visitExp1(ctx:Exp1Context) = {
    if (ctx.getChildCount() == 1) ctx.getChild(0).accept(this)
    else BinaryOp(ctx.getChild(1).getText, ctx.getChild(0).accept(this).asInstanceOf[Expr], ctx.getChild(2).accept(this).asInstanceOf[Expr])
  }

  override def visitExp2(ctx:Exp2Context) = {
    if (ctx.getChildCount() == 1) ctx.getChild(0).accept(this)
    else BinaryOp(ctx.getChild(1).getText, ctx.getChild(0).accept(this).asInstanceOf[Expr], ctx.getChild(2).accept(this).asInstanceOf[Expr])
  }

  override def visitExp3(ctx:Exp3Context) = {
    if (ctx.getChildCount() == 1) ctx.getChild(0).accept(this)
    else BinaryOp(ctx.getChild(1).getText, ctx.getChild(0).accept(this).asInstanceOf[Expr], ctx.getChild(2).accept(this).asInstanceOf[Expr])
  }

  override def visitExp4(ctx:Exp4Context) = {
    if (ctx.getChildCount() == 1) ctx.getChild(0).accept(this)
    else BinaryOp(ctx.getChild(1).getText, ctx.getChild(0).accept(this).asInstanceOf[Expr], ctx.getChild(2).accept(this).asInstanceOf[Expr])
  }

  override def visitExp5(ctx:Exp5Context) = {
    if (ctx.getChildCount() == 1) ctx.getChild(0).accept(this)
    else BinaryOp(ctx.getChild(1).getText, ctx.getChild(0).accept(this).asInstanceOf[Expr], ctx.getChild(2).accept(this).asInstanceOf[Expr])
  }

  override def visitExp6(ctx:Exp6Context) = {
    if (ctx.getChildCount() == 1) ctx.getChild(0).accept(this)
    else BinaryOp(ctx.getChild(1).getText, ctx.getChild(0).accept(this).asInstanceOf[Expr], ctx.getChild(2).accept(this).asInstanceOf[Expr])
  }

  override def visitExp7(ctx:Exp7Context) = {
    if (ctx.getChildCount() == 1) ctx.getChild(0).accept(this)
    else UnaryOp(ctx.getChild(0).getText, ctx.getChild(1).accept(this).asInstanceOf[Expr])
  }

  override def visitExp8(ctx:Exp8Context) = {
    if (ctx.getChildCount() == 1) ctx.getChild(0).accept(this)
    else ArrayCell(ctx.getChild(0).accept(this).asInstanceOf[Expr], ctx.getChild(2).accept(this).asInstanceOf[Expr])
  }
  

  override def visitExp9(ctx:Exp9Context) = {
    if (ctx.getChildCount() == 1) ctx.getChild(0).accept(this)
    else ctx.getChild(1).accept(this)
  }

  override def visitOperand(ctx:OperandContext) = {
    if(ctx.literal != null) ctx.literal.accept(this)
    else if(ctx.ID != null) Id(ctx.ID.getText)
    else ctx.funcall.accept(this)
  }

  override def visitLiteral(ctx:LiteralContext) = {
    if(ctx.INTLIT != null) IntLiteral(ctx.INTLIT.getText.toInt)
    else if(ctx.FLOATLIT != null) FloatLiteral(ctx.FLOATLIT.getText.toFloat)
    else if(ctx.STRINGLIT != null) StringLiteral(ctx.STRINGLIT.getText)
    else BooleanLiteral(ctx.BOOLLIT.getText.toBoolean)
  }

  override def visitFuncall(ctx:FuncallContext) = CallExpr(Id(ctx.ID.getText), ctx.arglist.accept(this).asInstanceOf[List[Expr]])

  override def visitArglist(ctx:ArglistContext) = ctx.expression().asScala.toList.map(_.accept(this).asInstanceOf[Expr])
}