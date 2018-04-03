// Generated from src/main/mc/parser/MC.g4 by ANTLR 4.6

	package mc.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MCParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MCVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MCParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MCParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(MCParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#vardecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardecl(MCParser.VardeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#primitivetype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitivetype(MCParser.PrimitivetypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#varlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarlist(MCParser.VarlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#varname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarname(MCParser.VarnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#funcdecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncdecl(MCParser.FuncdeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#functype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctype(MCParser.FunctypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#paralist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParalist(MCParser.ParalistContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#paradecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParadecl(MCParser.ParadeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(MCParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#blockbody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockbody(MCParser.BlockbodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#declpart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclpart(MCParser.DeclpartContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#stmtpart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtpart(MCParser.StmtpartContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(MCParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#ifstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfstmt(MCParser.IfstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#ifelse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfelse(MCParser.IfelseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#stmtif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtif(MCParser.StmtifContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#ifnoelse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfnoelse(MCParser.IfnoelseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#whilestmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhilestmt(MCParser.WhilestmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#forstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForstmt(MCParser.ForstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#breakstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakstmt(MCParser.BreakstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#continuestmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinuestmt(MCParser.ContinuestmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#returnstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnstmt(MCParser.ReturnstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MCParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#naexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNaexpression(MCParser.NaexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#operand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperand(MCParser.OperandContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(MCParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#funcall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncall(MCParser.FuncallContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#arglist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArglist(MCParser.ArglistContext ctx);
}