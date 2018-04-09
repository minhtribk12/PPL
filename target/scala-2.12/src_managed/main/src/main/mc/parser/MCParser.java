// Generated from src/main/mc/parser/MC.g4 by ANTLR 4.6

	package mc.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MCParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LSB=1, RSB=2, CM=3, COMMENT=4, BOOLLIT=5, BOOLTYPE=6, INTTYPE=7, INTLIT=8, 
		FLOATTYPE=9, FLOATLIT=10, STRINGTYPE=11, STRINGLIT=12, VOIDTYPE=13, LB=14, 
		RB=15, LP=16, RP=17, SEMI=18, IF=19, ELSE=20, FOR=21, WHILE=22, DO=23, 
		BREAK=24, CONTINUE=25, RETURN=26, ADD=27, SUB=28, MUL=29, DIV=30, LT=31, 
		GT=32, LEQ=33, GEQ=34, EQ=35, NOT=36, OR=37, NEQ=38, ASSIGN=39, MOL=40, 
		AND=41, ID=42, WS=43, ILLEGAL_ESCAPE=44, UNCLOSE_STRING=45, ERROR_CHAR=46;
	public static final int
		RULE_program = 0, RULE_declaration = 1, RULE_vardecl = 2, RULE_primitivetype = 3, 
		RULE_varlist = 4, RULE_varname = 5, RULE_funcdecl = 6, RULE_functype = 7, 
		RULE_paralist = 8, RULE_paradecl = 9, RULE_block = 10, RULE_blockbody = 11, 
		RULE_declpart = 12, RULE_stmtpart = 13, RULE_stmt = 14, RULE_ifstmt = 15, 
		RULE_ifelse = 16, RULE_stmtif = 17, RULE_ifnoelse = 18, RULE_whilestmt = 19, 
		RULE_forstmt = 20, RULE_breakstmt = 21, RULE_continuestmt = 22, RULE_returnstmt = 23, 
		RULE_expression = 24, RULE_naexpression = 25, RULE_operand = 26, RULE_literal = 27, 
		RULE_funcall = 28, RULE_arglist = 29;
	public static final String[] ruleNames = {
		"program", "declaration", "vardecl", "primitivetype", "varlist", "varname", 
		"funcdecl", "functype", "paralist", "paradecl", "block", "blockbody", 
		"declpart", "stmtpart", "stmt", "ifstmt", "ifelse", "stmtif", "ifnoelse", 
		"whilestmt", "forstmt", "breakstmt", "continuestmt", "returnstmt", "expression", 
		"naexpression", "operand", "literal", "funcall", "arglist"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'['", "']'", "','", null, null, "'boolean'", "'int'", null, "'float'", 
		null, "'string'", null, "'void'", "'('", "')'", "'{'", "'}'", "';'", "'if'", 
		"'else'", "'for'", "'while'", "'do'", "'break'", "'continue'", "'return'", 
		"'+'", "'-'", "'*'", "'/'", "'<'", "'>'", "'<='", "'>='", "'=='", "'!'", 
		"'||'", "'!='", "'='", "'%'", "'&&'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LSB", "RSB", "CM", "COMMENT", "BOOLLIT", "BOOLTYPE", "INTTYPE", 
		"INTLIT", "FLOATTYPE", "FLOATLIT", "STRINGTYPE", "STRINGLIT", "VOIDTYPE", 
		"LB", "RB", "LP", "RP", "SEMI", "IF", "ELSE", "FOR", "WHILE", "DO", "BREAK", 
		"CONTINUE", "RETURN", "ADD", "SUB", "MUL", "DIV", "LT", "GT", "LEQ", "GEQ", 
		"EQ", "NOT", "OR", "NEQ", "ASSIGN", "MOL", "AND", "ID", "WS", "ILLEGAL_ESCAPE", 
		"UNCLOSE_STRING", "ERROR_CHAR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MC.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MCParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(60);
				declaration();
				}
				}
				setState(63); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLTYPE) | (1L << INTTYPE) | (1L << FLOATTYPE) | (1L << STRINGTYPE) | (1L << VOIDTYPE))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public VardeclContext vardecl() {
			return getRuleContext(VardeclContext.class,0);
		}
		public FuncdeclContext funcdecl() {
			return getRuleContext(FuncdeclContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		try {
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				vardecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				funcdecl();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VardeclContext extends ParserRuleContext {
		public PrimitivetypeContext primitivetype() {
			return getRuleContext(PrimitivetypeContext.class,0);
		}
		public VarlistContext varlist() {
			return getRuleContext(VarlistContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MCParser.SEMI, 0); }
		public VardeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vardecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitVardecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VardeclContext vardecl() throws RecognitionException {
		VardeclContext _localctx = new VardeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_vardecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			primitivetype();
			setState(70);
			varlist();
			setState(71);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimitivetypeContext extends ParserRuleContext {
		public TerminalNode INTTYPE() { return getToken(MCParser.INTTYPE, 0); }
		public TerminalNode FLOATTYPE() { return getToken(MCParser.FLOATTYPE, 0); }
		public TerminalNode BOOLTYPE() { return getToken(MCParser.BOOLTYPE, 0); }
		public TerminalNode STRINGTYPE() { return getToken(MCParser.STRINGTYPE, 0); }
		public PrimitivetypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitivetype; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitPrimitivetype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitivetypeContext primitivetype() throws RecognitionException {
		PrimitivetypeContext _localctx = new PrimitivetypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_primitivetype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLTYPE) | (1L << INTTYPE) | (1L << FLOATTYPE) | (1L << STRINGTYPE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarlistContext extends ParserRuleContext {
		public List<VarnameContext> varname() {
			return getRuleContexts(VarnameContext.class);
		}
		public VarnameContext varname(int i) {
			return getRuleContext(VarnameContext.class,i);
		}
		public List<TerminalNode> CM() { return getTokens(MCParser.CM); }
		public TerminalNode CM(int i) {
			return getToken(MCParser.CM, i);
		}
		public VarlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varlist; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitVarlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarlistContext varlist() throws RecognitionException {
		VarlistContext _localctx = new VarlistContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_varlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			varname();
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CM) {
				{
				{
				setState(76);
				match(CM);
				setState(77);
				varname();
				}
				}
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarnameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MCParser.ID, 0); }
		public TerminalNode LSB() { return getToken(MCParser.LSB, 0); }
		public TerminalNode INTLIT() { return getToken(MCParser.INTLIT, 0); }
		public TerminalNode RSB() { return getToken(MCParser.RSB, 0); }
		public VarnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varname; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitVarname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarnameContext varname() throws RecognitionException {
		VarnameContext _localctx = new VarnameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varname);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(ID);
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LSB) {
				{
				setState(84);
				match(LSB);
				setState(85);
				match(INTLIT);
				setState(86);
				match(RSB);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncdeclContext extends ParserRuleContext {
		public FunctypeContext functype() {
			return getRuleContext(FunctypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(MCParser.ID, 0); }
		public TerminalNode LB() { return getToken(MCParser.LB, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RB() { return getToken(MCParser.RB, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FuncdeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcdecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitFuncdecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncdeclContext funcdecl() throws RecognitionException {
		FuncdeclContext _localctx = new FuncdeclContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_funcdecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			functype();
			setState(90);
			match(ID);
			setState(91);
			match(LB);
			setState(92);
			paralist();
			setState(93);
			match(RB);
			setState(94);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctypeContext extends ParserRuleContext {
		public PrimitivetypeContext primitivetype() {
			return getRuleContext(PrimitivetypeContext.class,0);
		}
		public TerminalNode LSB() { return getToken(MCParser.LSB, 0); }
		public TerminalNode RSB() { return getToken(MCParser.RSB, 0); }
		public TerminalNode VOIDTYPE() { return getToken(MCParser.VOIDTYPE, 0); }
		public FunctypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functype; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitFunctype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctypeContext functype() throws RecognitionException {
		FunctypeContext _localctx = new FunctypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_functype);
		int _la;
		try {
			setState(102);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLTYPE:
			case INTTYPE:
			case FLOATTYPE:
			case STRINGTYPE:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(96);
				primitivetype();
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LSB) {
					{
					setState(97);
					match(LSB);
					setState(98);
					match(RSB);
					}
				}

				}
				}
				break;
			case VOIDTYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(101);
				match(VOIDTYPE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParalistContext extends ParserRuleContext {
		public List<ParadeclContext> paradecl() {
			return getRuleContexts(ParadeclContext.class);
		}
		public ParadeclContext paradecl(int i) {
			return getRuleContext(ParadeclContext.class,i);
		}
		public List<TerminalNode> CM() { return getTokens(MCParser.CM); }
		public TerminalNode CM(int i) {
			return getToken(MCParser.CM, i);
		}
		public ParalistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paralist; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitParalist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParalistContext paralist() throws RecognitionException {
		ParalistContext _localctx = new ParalistContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_paralist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLTYPE) | (1L << INTTYPE) | (1L << FLOATTYPE) | (1L << STRINGTYPE))) != 0)) {
				{
				setState(104);
				paradecl();
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CM) {
					{
					{
					setState(105);
					match(CM);
					setState(106);
					paradecl();
					}
					}
					setState(111);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParadeclContext extends ParserRuleContext {
		public PrimitivetypeContext primitivetype() {
			return getRuleContext(PrimitivetypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(MCParser.ID, 0); }
		public TerminalNode LSB() { return getToken(MCParser.LSB, 0); }
		public TerminalNode RSB() { return getToken(MCParser.RSB, 0); }
		public ParadeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paradecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitParadecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParadeclContext paradecl() throws RecognitionException {
		ParadeclContext _localctx = new ParadeclContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_paradecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			primitivetype();
			setState(115);
			match(ID);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LSB) {
				{
				setState(116);
				match(LSB);
				setState(117);
				match(RSB);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LP() { return getToken(MCParser.LP, 0); }
		public BlockbodyContext blockbody() {
			return getRuleContext(BlockbodyContext.class,0);
		}
		public TerminalNode RP() { return getToken(MCParser.RP, 0); }
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(LP);
			setState(121);
			blockbody();
			setState(122);
			match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockbodyContext extends ParserRuleContext {
		public DeclpartContext declpart() {
			return getRuleContext(DeclpartContext.class,0);
		}
		public StmtpartContext stmtpart() {
			return getRuleContext(StmtpartContext.class,0);
		}
		public BlockbodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockbody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitBlockbody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockbodyContext blockbody() throws RecognitionException {
		BlockbodyContext _localctx = new BlockbodyContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_blockbody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			declpart();
			setState(125);
			stmtpart();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclpartContext extends ParserRuleContext {
		public List<VardeclContext> vardecl() {
			return getRuleContexts(VardeclContext.class);
		}
		public VardeclContext vardecl(int i) {
			return getRuleContext(VardeclContext.class,i);
		}
		public DeclpartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declpart; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitDeclpart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclpartContext declpart() throws RecognitionException {
		DeclpartContext _localctx = new DeclpartContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_declpart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLTYPE) | (1L << INTTYPE) | (1L << FLOATTYPE) | (1L << STRINGTYPE))) != 0)) {
				{
				{
				setState(127);
				vardecl();
				}
				}
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtpartContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public StmtpartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtpart; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitStmtpart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtpartContext stmtpart() throws RecognitionException {
		StmtpartContext _localctx = new StmtpartContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_stmtpart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLLIT) | (1L << INTLIT) | (1L << FLOATLIT) | (1L << STRINGLIT) | (1L << LB) | (1L << LP) | (1L << IF) | (1L << FOR) | (1L << DO) | (1L << BREAK) | (1L << CONTINUE) | (1L << RETURN) | (1L << SUB) | (1L << NOT) | (1L << ID))) != 0)) {
				{
				{
				setState(133);
				stmt();
				}
				}
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public IfstmtContext ifstmt() {
			return getRuleContext(IfstmtContext.class,0);
		}
		public WhilestmtContext whilestmt() {
			return getRuleContext(WhilestmtContext.class,0);
		}
		public ForstmtContext forstmt() {
			return getRuleContext(ForstmtContext.class,0);
		}
		public BreakstmtContext breakstmt() {
			return getRuleContext(BreakstmtContext.class,0);
		}
		public ContinuestmtContext continuestmt() {
			return getRuleContext(ContinuestmtContext.class,0);
		}
		public ReturnstmtContext returnstmt() {
			return getRuleContext(ReturnstmtContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MCParser.SEMI, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_stmt);
		try {
			setState(149);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(139);
				ifstmt();
				}
				break;
			case DO:
				enterOuterAlt(_localctx, 2);
				{
				setState(140);
				whilestmt();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(141);
				forstmt();
				}
				break;
			case BREAK:
				enterOuterAlt(_localctx, 4);
				{
				setState(142);
				breakstmt();
				}
				break;
			case CONTINUE:
				enterOuterAlt(_localctx, 5);
				{
				setState(143);
				continuestmt();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 6);
				{
				setState(144);
				returnstmt();
				}
				break;
			case BOOLLIT:
			case INTLIT:
			case FLOATLIT:
			case STRINGLIT:
			case LB:
			case SUB:
			case NOT:
			case ID:
				enterOuterAlt(_localctx, 7);
				{
				setState(145);
				expression(0);
				setState(146);
				match(SEMI);
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 8);
				{
				setState(148);
				block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfstmtContext extends ParserRuleContext {
		public IfelseContext ifelse() {
			return getRuleContext(IfelseContext.class,0);
		}
		public IfnoelseContext ifnoelse() {
			return getRuleContext(IfnoelseContext.class,0);
		}
		public IfstmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifstmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitIfstmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfstmtContext ifstmt() throws RecognitionException {
		IfstmtContext _localctx = new IfstmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_ifstmt);
		try {
			setState(153);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				ifelse();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				ifnoelse();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfelseContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(MCParser.IF, 0); }
		public TerminalNode LB() { return getToken(MCParser.LB, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RB() { return getToken(MCParser.RB, 0); }
		public StmtifContext stmtif() {
			return getRuleContext(StmtifContext.class,0);
		}
		public TerminalNode ELSE() { return getToken(MCParser.ELSE, 0); }
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public IfelseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifelse; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitIfelse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfelseContext ifelse() throws RecognitionException {
		IfelseContext _localctx = new IfelseContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_ifelse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(IF);
			setState(156);
			match(LB);
			setState(157);
			expression(0);
			setState(158);
			match(RB);
			setState(159);
			stmtif();
			setState(160);
			match(ELSE);
			setState(161);
			stmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtifContext extends ParserRuleContext {
		public IfelseContext ifelse() {
			return getRuleContext(IfelseContext.class,0);
		}
		public WhilestmtContext whilestmt() {
			return getRuleContext(WhilestmtContext.class,0);
		}
		public ForstmtContext forstmt() {
			return getRuleContext(ForstmtContext.class,0);
		}
		public BreakstmtContext breakstmt() {
			return getRuleContext(BreakstmtContext.class,0);
		}
		public ContinuestmtContext continuestmt() {
			return getRuleContext(ContinuestmtContext.class,0);
		}
		public ReturnstmtContext returnstmt() {
			return getRuleContext(ReturnstmtContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MCParser.SEMI, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public StmtifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtif; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitStmtif(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtifContext stmtif() throws RecognitionException {
		StmtifContext _localctx = new StmtifContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_stmtif);
		try {
			setState(173);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(163);
				ifelse();
				}
				break;
			case DO:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
				whilestmt();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(165);
				forstmt();
				}
				break;
			case BREAK:
				enterOuterAlt(_localctx, 4);
				{
				setState(166);
				breakstmt();
				}
				break;
			case CONTINUE:
				enterOuterAlt(_localctx, 5);
				{
				setState(167);
				continuestmt();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 6);
				{
				setState(168);
				returnstmt();
				}
				break;
			case BOOLLIT:
			case INTLIT:
			case FLOATLIT:
			case STRINGLIT:
			case LB:
			case SUB:
			case NOT:
			case ID:
				enterOuterAlt(_localctx, 7);
				{
				setState(169);
				expression(0);
				setState(170);
				match(SEMI);
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 8);
				{
				setState(172);
				block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfnoelseContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(MCParser.IF, 0); }
		public TerminalNode LB() { return getToken(MCParser.LB, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RB() { return getToken(MCParser.RB, 0); }
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public IfnoelseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifnoelse; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitIfnoelse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfnoelseContext ifnoelse() throws RecognitionException {
		IfnoelseContext _localctx = new IfnoelseContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_ifnoelse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(IF);
			setState(176);
			match(LB);
			setState(177);
			expression(0);
			setState(178);
			match(RB);
			setState(179);
			stmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhilestmtContext extends ParserRuleContext {
		public TerminalNode DO() { return getToken(MCParser.DO, 0); }
		public TerminalNode WHILE() { return getToken(MCParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MCParser.SEMI, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public WhilestmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whilestmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitWhilestmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhilestmtContext whilestmt() throws RecognitionException {
		WhilestmtContext _localctx = new WhilestmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_whilestmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			match(DO);
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLLIT) | (1L << INTLIT) | (1L << FLOATLIT) | (1L << STRINGLIT) | (1L << LB) | (1L << LP) | (1L << IF) | (1L << FOR) | (1L << DO) | (1L << BREAK) | (1L << CONTINUE) | (1L << RETURN) | (1L << SUB) | (1L << NOT) | (1L << ID))) != 0)) {
				{
				{
				setState(182);
				stmt();
				}
				}
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(188);
			match(WHILE);
			setState(189);
			expression(0);
			setState(190);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForstmtContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(MCParser.FOR, 0); }
		public TerminalNode LB() { return getToken(MCParser.LB, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(MCParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(MCParser.SEMI, i);
		}
		public TerminalNode RB() { return getToken(MCParser.RB, 0); }
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public ForstmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forstmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitForstmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForstmtContext forstmt() throws RecognitionException {
		ForstmtContext _localctx = new ForstmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_forstmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(FOR);
			setState(193);
			match(LB);
			setState(194);
			expression(0);
			setState(195);
			match(SEMI);
			setState(196);
			expression(0);
			setState(197);
			match(SEMI);
			setState(198);
			expression(0);
			setState(199);
			match(RB);
			setState(200);
			stmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakstmtContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(MCParser.BREAK, 0); }
		public TerminalNode SEMI() { return getToken(MCParser.SEMI, 0); }
		public BreakstmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakstmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitBreakstmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakstmtContext breakstmt() throws RecognitionException {
		BreakstmtContext _localctx = new BreakstmtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_breakstmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			match(BREAK);
			setState(203);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContinuestmtContext extends ParserRuleContext {
		public TerminalNode CONTINUE() { return getToken(MCParser.CONTINUE, 0); }
		public TerminalNode SEMI() { return getToken(MCParser.SEMI, 0); }
		public ContinuestmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continuestmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitContinuestmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinuestmtContext continuestmt() throws RecognitionException {
		ContinuestmtContext _localctx = new ContinuestmtContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_continuestmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(CONTINUE);
			setState(206);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnstmtContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(MCParser.RETURN, 0); }
		public TerminalNode SEMI() { return getToken(MCParser.SEMI, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnstmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnstmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitReturnstmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnstmtContext returnstmt() throws RecognitionException {
		ReturnstmtContext _localctx = new ReturnstmtContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_returnstmt);
		try {
			setState(214);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(208);
				match(RETURN);
				setState(209);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(210);
				match(RETURN);
				setState(211);
				expression(0);
				setState(212);
				match(SEMI);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode LB() { return getToken(MCParser.LB, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RB() { return getToken(MCParser.RB, 0); }
		public TerminalNode ID() { return getToken(MCParser.ID, 0); }
		public TerminalNode LSB() { return getToken(MCParser.LSB, 0); }
		public TerminalNode RSB() { return getToken(MCParser.RSB, 0); }
		public List<NaexpressionContext> naexpression() {
			return getRuleContexts(NaexpressionContext.class);
		}
		public NaexpressionContext naexpression(int i) {
			return getRuleContext(NaexpressionContext.class,i);
		}
		public TerminalNode SUB() { return getToken(MCParser.SUB, 0); }
		public TerminalNode NOT() { return getToken(MCParser.NOT, 0); }
		public TerminalNode LT() { return getToken(MCParser.LT, 0); }
		public TerminalNode LEQ() { return getToken(MCParser.LEQ, 0); }
		public TerminalNode GT() { return getToken(MCParser.GT, 0); }
		public TerminalNode GEQ() { return getToken(MCParser.GEQ, 0); }
		public TerminalNode EQ() { return getToken(MCParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(MCParser.NEQ, 0); }
		public OperandContext operand() {
			return getRuleContext(OperandContext.class,0);
		}
		public TerminalNode DIV() { return getToken(MCParser.DIV, 0); }
		public TerminalNode MUL() { return getToken(MCParser.MUL, 0); }
		public TerminalNode MOL() { return getToken(MCParser.MOL, 0); }
		public TerminalNode ADD() { return getToken(MCParser.ADD, 0); }
		public TerminalNode AND() { return getToken(MCParser.AND, 0); }
		public TerminalNode OR() { return getToken(MCParser.OR, 0); }
		public TerminalNode ASSIGN() { return getToken(MCParser.ASSIGN, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 48;
		enterRecursionRule(_localctx, 48, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(217);
				match(LB);
				setState(218);
				expression(0);
				setState(219);
				match(RB);
				}
				break;
			case 2:
				{
				setState(221);
				match(ID);
				setState(222);
				match(LSB);
				setState(223);
				expression(0);
				setState(224);
				match(RSB);
				}
				break;
			case 3:
				{
				setState(226);
				naexpression(0);
				setState(227);
				match(LSB);
				setState(228);
				expression(0);
				setState(229);
				match(RSB);
				}
				break;
			case 4:
				{
				setState(231);
				_la = _input.LA(1);
				if ( !(_la==SUB || _la==NOT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(232);
				expression(9);
				}
				break;
			case 5:
				{
				setState(233);
				naexpression(0);
				setState(234);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT) | (1L << GT) | (1L << LEQ) | (1L << GEQ))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(235);
				naexpression(0);
				}
				break;
			case 6:
				{
				setState(237);
				naexpression(0);
				setState(238);
				_la = _input.LA(1);
				if ( !(_la==EQ || _la==NEQ) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(239);
				naexpression(0);
				}
				break;
			case 7:
				{
				setState(241);
				operand();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(261);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(259);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(244);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(245);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOL))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(246);
						expression(9);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(247);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(248);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(249);
						expression(8);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(250);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(251);
						match(AND);
						setState(252);
						expression(5);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(253);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(254);
						match(OR);
						setState(255);
						expression(4);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(256);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(257);
						match(ASSIGN);
						setState(258);
						expression(2);
						}
						break;
					}
					} 
				}
				setState(263);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NaexpressionContext extends ParserRuleContext {
		public TerminalNode LB() { return getToken(MCParser.LB, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RB() { return getToken(MCParser.RB, 0); }
		public List<NaexpressionContext> naexpression() {
			return getRuleContexts(NaexpressionContext.class);
		}
		public NaexpressionContext naexpression(int i) {
			return getRuleContext(NaexpressionContext.class,i);
		}
		public TerminalNode SUB() { return getToken(MCParser.SUB, 0); }
		public TerminalNode NOT() { return getToken(MCParser.NOT, 0); }
		public OperandContext operand() {
			return getRuleContext(OperandContext.class,0);
		}
		public TerminalNode DIV() { return getToken(MCParser.DIV, 0); }
		public TerminalNode MUL() { return getToken(MCParser.MUL, 0); }
		public TerminalNode MOL() { return getToken(MCParser.MOL, 0); }
		public TerminalNode ADD() { return getToken(MCParser.ADD, 0); }
		public TerminalNode AND() { return getToken(MCParser.AND, 0); }
		public TerminalNode OR() { return getToken(MCParser.OR, 0); }
		public TerminalNode ASSIGN() { return getToken(MCParser.ASSIGN, 0); }
		public NaexpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_naexpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitNaexpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NaexpressionContext naexpression() throws RecognitionException {
		return naexpression(0);
	}

	private NaexpressionContext naexpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		NaexpressionContext _localctx = new NaexpressionContext(_ctx, _parentState);
		NaexpressionContext _prevctx = _localctx;
		int _startState = 50;
		enterRecursionRule(_localctx, 50, RULE_naexpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
				{
				setState(265);
				match(LB);
				setState(266);
				expression(0);
				setState(267);
				match(RB);
				}
				break;
			case SUB:
			case NOT:
				{
				setState(269);
				_la = _input.LA(1);
				if ( !(_la==SUB || _la==NOT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(270);
				naexpression(7);
				}
				break;
			case BOOLLIT:
			case INTLIT:
			case FLOATLIT:
			case STRINGLIT:
			case ID:
				{
				setState(271);
				operand();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(291);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(289);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						_localctx = new NaexpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_naexpression);
						setState(274);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(275);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOL))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(276);
						naexpression(7);
						}
						break;
					case 2:
						{
						_localctx = new NaexpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_naexpression);
						setState(277);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(278);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(279);
						naexpression(6);
						}
						break;
					case 3:
						{
						_localctx = new NaexpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_naexpression);
						setState(280);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(281);
						match(AND);
						setState(282);
						naexpression(5);
						}
						break;
					case 4:
						{
						_localctx = new NaexpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_naexpression);
						setState(283);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(284);
						match(OR);
						setState(285);
						naexpression(4);
						}
						break;
					case 5:
						{
						_localctx = new NaexpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_naexpression);
						setState(286);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(287);
						match(ASSIGN);
						setState(288);
						naexpression(2);
						}
						break;
					}
					} 
				}
				setState(293);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class OperandContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode ID() { return getToken(MCParser.ID, 0); }
		public FuncallContext funcall() {
			return getRuleContext(FuncallContext.class,0);
		}
		public OperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operand; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitOperand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_operand);
		try {
			setState(297);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(294);
				literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(295);
				match(ID);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(296);
				funcall();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode INTLIT() { return getToken(MCParser.INTLIT, 0); }
		public TerminalNode FLOATLIT() { return getToken(MCParser.FLOATLIT, 0); }
		public TerminalNode STRINGLIT() { return getToken(MCParser.STRINGLIT, 0); }
		public TerminalNode BOOLLIT() { return getToken(MCParser.BOOLLIT, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLLIT) | (1L << INTLIT) | (1L << FLOATLIT) | (1L << STRINGLIT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncallContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MCParser.ID, 0); }
		public TerminalNode LB() { return getToken(MCParser.LB, 0); }
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public TerminalNode RB() { return getToken(MCParser.RB, 0); }
		public FuncallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitFuncall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncallContext funcall() throws RecognitionException {
		FuncallContext _localctx = new FuncallContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_funcall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			match(ID);
			setState(302);
			match(LB);
			setState(303);
			arglist();
			setState(304);
			match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArglistContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> CM() { return getTokens(MCParser.CM); }
		public TerminalNode CM(int i) {
			return getToken(MCParser.CM, i);
		}
		public ArglistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arglist; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitArglist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArglistContext arglist() throws RecognitionException {
		ArglistContext _localctx = new ArglistContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_arglist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLLIT) | (1L << INTLIT) | (1L << FLOATLIT) | (1L << STRINGLIT) | (1L << LB) | (1L << SUB) | (1L << NOT) | (1L << ID))) != 0)) {
				{
				setState(306);
				expression(0);
				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CM) {
					{
					{
					setState(307);
					match(CM);
					setState(308);
					expression(0);
					}
					}
					setState(313);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 24:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 25:
			return naexpression_sempred((NaexpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean naexpression_sempred(NaexpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\60\u013f\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\6\2@"+
		"\n\2\r\2\16\2A\3\3\3\3\5\3F\n\3\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\7"+
		"\6Q\n\6\f\6\16\6T\13\6\3\7\3\7\3\7\3\7\5\7Z\n\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\5\tf\n\t\3\t\5\ti\n\t\3\n\3\n\3\n\7\nn\n\n\f\n\16\n"+
		"q\13\n\5\ns\n\n\3\13\3\13\3\13\3\13\5\13y\n\13\3\f\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\16\7\16\u0083\n\16\f\16\16\16\u0086\13\16\3\17\7\17\u0089\n\17"+
		"\f\17\16\17\u008c\13\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\5\20\u0098\n\20\3\21\3\21\5\21\u009c\n\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23"+
		"\u00b0\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\7\25\u00ba\n\25\f"+
		"\25\16\25\u00bd\13\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\5\31\u00d9\n\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\5\32\u00f5\n\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u0106\n\32\f\32\16\32\u0109\13"+
		"\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u0113\n\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33"+
		"\u0124\n\33\f\33\16\33\u0127\13\33\3\34\3\34\3\34\5\34\u012c\n\34\3\35"+
		"\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\7\37\u0138\n\37\f\37\16"+
		"\37\u013b\13\37\5\37\u013d\n\37\3\37\2\4\62\64 \2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\64\668:<\2\t\5\2\b\t\13\13\r\r\4\2\36\36"+
		"&&\3\2!$\4\2%%((\4\2\37 **\3\2\35\36\6\2\7\7\n\n\f\f\16\16\u0152\2?\3"+
		"\2\2\2\4E\3\2\2\2\6G\3\2\2\2\bK\3\2\2\2\nM\3\2\2\2\fU\3\2\2\2\16[\3\2"+
		"\2\2\20h\3\2\2\2\22r\3\2\2\2\24t\3\2\2\2\26z\3\2\2\2\30~\3\2\2\2\32\u0084"+
		"\3\2\2\2\34\u008a\3\2\2\2\36\u0097\3\2\2\2 \u009b\3\2\2\2\"\u009d\3\2"+
		"\2\2$\u00af\3\2\2\2&\u00b1\3\2\2\2(\u00b7\3\2\2\2*\u00c2\3\2\2\2,\u00cc"+
		"\3\2\2\2.\u00cf\3\2\2\2\60\u00d8\3\2\2\2\62\u00f4\3\2\2\2\64\u0112\3\2"+
		"\2\2\66\u012b\3\2\2\28\u012d\3\2\2\2:\u012f\3\2\2\2<\u013c\3\2\2\2>@\5"+
		"\4\3\2?>\3\2\2\2@A\3\2\2\2A?\3\2\2\2AB\3\2\2\2B\3\3\2\2\2CF\5\6\4\2DF"+
		"\5\16\b\2EC\3\2\2\2ED\3\2\2\2F\5\3\2\2\2GH\5\b\5\2HI\5\n\6\2IJ\7\24\2"+
		"\2J\7\3\2\2\2KL\t\2\2\2L\t\3\2\2\2MR\5\f\7\2NO\7\5\2\2OQ\5\f\7\2PN\3\2"+
		"\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2S\13\3\2\2\2TR\3\2\2\2UY\7,\2\2VW\7"+
		"\3\2\2WX\7\n\2\2XZ\7\4\2\2YV\3\2\2\2YZ\3\2\2\2Z\r\3\2\2\2[\\\5\20\t\2"+
		"\\]\7,\2\2]^\7\20\2\2^_\5\22\n\2_`\7\21\2\2`a\5\26\f\2a\17\3\2\2\2be\5"+
		"\b\5\2cd\7\3\2\2df\7\4\2\2ec\3\2\2\2ef\3\2\2\2fi\3\2\2\2gi\7\17\2\2hb"+
		"\3\2\2\2hg\3\2\2\2i\21\3\2\2\2jo\5\24\13\2kl\7\5\2\2ln\5\24\13\2mk\3\2"+
		"\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2\2ps\3\2\2\2qo\3\2\2\2rj\3\2\2\2rs\3\2"+
		"\2\2s\23\3\2\2\2tu\5\b\5\2ux\7,\2\2vw\7\3\2\2wy\7\4\2\2xv\3\2\2\2xy\3"+
		"\2\2\2y\25\3\2\2\2z{\7\22\2\2{|\5\30\r\2|}\7\23\2\2}\27\3\2\2\2~\177\5"+
		"\32\16\2\177\u0080\5\34\17\2\u0080\31\3\2\2\2\u0081\u0083\5\6\4\2\u0082"+
		"\u0081\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2"+
		"\2\2\u0085\33\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0089\5\36\20\2\u0088"+
		"\u0087\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2"+
		"\2\2\u008b\35\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u0098\5 \21\2\u008e\u0098"+
		"\5(\25\2\u008f\u0098\5*\26\2\u0090\u0098\5,\27\2\u0091\u0098\5.\30\2\u0092"+
		"\u0098\5\60\31\2\u0093\u0094\5\62\32\2\u0094\u0095\7\24\2\2\u0095\u0098"+
		"\3\2\2\2\u0096\u0098\5\26\f\2\u0097\u008d\3\2\2\2\u0097\u008e\3\2\2\2"+
		"\u0097\u008f\3\2\2\2\u0097\u0090\3\2\2\2\u0097\u0091\3\2\2\2\u0097\u0092"+
		"\3\2\2\2\u0097\u0093\3\2\2\2\u0097\u0096\3\2\2\2\u0098\37\3\2\2\2\u0099"+
		"\u009c\5\"\22\2\u009a\u009c\5&\24\2\u009b\u0099\3\2\2\2\u009b\u009a\3"+
		"\2\2\2\u009c!\3\2\2\2\u009d\u009e\7\25\2\2\u009e\u009f\7\20\2\2\u009f"+
		"\u00a0\5\62\32\2\u00a0\u00a1\7\21\2\2\u00a1\u00a2\5$\23\2\u00a2\u00a3"+
		"\7\26\2\2\u00a3\u00a4\5\36\20\2\u00a4#\3\2\2\2\u00a5\u00b0\5\"\22\2\u00a6"+
		"\u00b0\5(\25\2\u00a7\u00b0\5*\26\2\u00a8\u00b0\5,\27\2\u00a9\u00b0\5."+
		"\30\2\u00aa\u00b0\5\60\31\2\u00ab\u00ac\5\62\32\2\u00ac\u00ad\7\24\2\2"+
		"\u00ad\u00b0\3\2\2\2\u00ae\u00b0\5\26\f\2\u00af\u00a5\3\2\2\2\u00af\u00a6"+
		"\3\2\2\2\u00af\u00a7\3\2\2\2\u00af\u00a8\3\2\2\2\u00af\u00a9\3\2\2\2\u00af"+
		"\u00aa\3\2\2\2\u00af\u00ab\3\2\2\2\u00af\u00ae\3\2\2\2\u00b0%\3\2\2\2"+
		"\u00b1\u00b2\7\25\2\2\u00b2\u00b3\7\20\2\2\u00b3\u00b4\5\62\32\2\u00b4"+
		"\u00b5\7\21\2\2\u00b5\u00b6\5\36\20\2\u00b6\'\3\2\2\2\u00b7\u00bb\7\31"+
		"\2\2\u00b8\u00ba\5\36\20\2\u00b9\u00b8\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb"+
		"\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00be\3\2\2\2\u00bd\u00bb\3\2"+
		"\2\2\u00be\u00bf\7\30\2\2\u00bf\u00c0\5\62\32\2\u00c0\u00c1\7\24\2\2\u00c1"+
		")\3\2\2\2\u00c2\u00c3\7\27\2\2\u00c3\u00c4\7\20\2\2\u00c4\u00c5\5\62\32"+
		"\2\u00c5\u00c6\7\24\2\2\u00c6\u00c7\5\62\32\2\u00c7\u00c8\7\24\2\2\u00c8"+
		"\u00c9\5\62\32\2\u00c9\u00ca\7\21\2\2\u00ca\u00cb\5\36\20\2\u00cb+\3\2"+
		"\2\2\u00cc\u00cd\7\32\2\2\u00cd\u00ce\7\24\2\2\u00ce-\3\2\2\2\u00cf\u00d0"+
		"\7\33\2\2\u00d0\u00d1\7\24\2\2\u00d1/\3\2\2\2\u00d2\u00d3\7\34\2\2\u00d3"+
		"\u00d9\7\24\2\2\u00d4\u00d5\7\34\2\2\u00d5\u00d6\5\62\32\2\u00d6\u00d7"+
		"\7\24\2\2\u00d7\u00d9\3\2\2\2\u00d8\u00d2\3\2\2\2\u00d8\u00d4\3\2\2\2"+
		"\u00d9\61\3\2\2\2\u00da\u00db\b\32\1\2\u00db\u00dc\7\20\2\2\u00dc\u00dd"+
		"\5\62\32\2\u00dd\u00de\7\21\2\2\u00de\u00f5\3\2\2\2\u00df\u00e0\7,\2\2"+
		"\u00e0\u00e1\7\3\2\2\u00e1\u00e2\5\62\32\2\u00e2\u00e3\7\4\2\2\u00e3\u00f5"+
		"\3\2\2\2\u00e4\u00e5\5\64\33\2\u00e5\u00e6\7\3\2\2\u00e6\u00e7\5\62\32"+
		"\2\u00e7\u00e8\7\4\2\2\u00e8\u00f5\3\2\2\2\u00e9\u00ea\t\3\2\2\u00ea\u00f5"+
		"\5\62\32\13\u00eb\u00ec\5\64\33\2\u00ec\u00ed\t\4\2\2\u00ed\u00ee\5\64"+
		"\33\2\u00ee\u00f5\3\2\2\2\u00ef\u00f0\5\64\33\2\u00f0\u00f1\t\5\2\2\u00f1"+
		"\u00f2\5\64\33\2\u00f2\u00f5\3\2\2\2\u00f3\u00f5\5\66\34\2\u00f4\u00da"+
		"\3\2\2\2\u00f4\u00df\3\2\2\2\u00f4\u00e4\3\2\2\2\u00f4\u00e9\3\2\2\2\u00f4"+
		"\u00eb\3\2\2\2\u00f4\u00ef\3\2\2\2\u00f4\u00f3\3\2\2\2\u00f5\u0107\3\2"+
		"\2\2\u00f6\u00f7\f\n\2\2\u00f7\u00f8\t\6\2\2\u00f8\u0106\5\62\32\13\u00f9"+
		"\u00fa\f\t\2\2\u00fa\u00fb\t\7\2\2\u00fb\u0106\5\62\32\n\u00fc\u00fd\f"+
		"\6\2\2\u00fd\u00fe\7+\2\2\u00fe\u0106\5\62\32\7\u00ff\u0100\f\5\2\2\u0100"+
		"\u0101\7\'\2\2\u0101\u0106\5\62\32\6\u0102\u0103\f\4\2\2\u0103\u0104\7"+
		")\2\2\u0104\u0106\5\62\32\4\u0105\u00f6\3\2\2\2\u0105\u00f9\3\2\2\2\u0105"+
		"\u00fc\3\2\2\2\u0105\u00ff\3\2\2\2\u0105\u0102\3\2\2\2\u0106\u0109\3\2"+
		"\2\2\u0107\u0105\3\2\2\2\u0107\u0108\3\2\2\2\u0108\63\3\2\2\2\u0109\u0107"+
		"\3\2\2\2\u010a\u010b\b\33\1\2\u010b\u010c\7\20\2\2\u010c\u010d\5\62\32"+
		"\2\u010d\u010e\7\21\2\2\u010e\u0113\3\2\2\2\u010f\u0110\t\3\2\2\u0110"+
		"\u0113\5\64\33\t\u0111\u0113\5\66\34\2\u0112\u010a\3\2\2\2\u0112\u010f"+
		"\3\2\2\2\u0112\u0111\3\2\2\2\u0113\u0125\3\2\2\2\u0114\u0115\f\b\2\2\u0115"+
		"\u0116\t\6\2\2\u0116\u0124\5\64\33\t\u0117\u0118\f\7\2\2\u0118\u0119\t"+
		"\7\2\2\u0119\u0124\5\64\33\b\u011a\u011b\f\6\2\2\u011b\u011c\7+\2\2\u011c"+
		"\u0124\5\64\33\7\u011d\u011e\f\5\2\2\u011e\u011f\7\'\2\2\u011f\u0124\5"+
		"\64\33\6\u0120\u0121\f\4\2\2\u0121\u0122\7)\2\2\u0122\u0124\5\64\33\4"+
		"\u0123\u0114\3\2\2\2\u0123\u0117\3\2\2\2\u0123\u011a\3\2\2\2\u0123\u011d"+
		"\3\2\2\2\u0123\u0120\3\2\2\2\u0124\u0127\3\2\2\2\u0125\u0123\3\2\2\2\u0125"+
		"\u0126\3\2\2\2\u0126\65\3\2\2\2\u0127\u0125\3\2\2\2\u0128\u012c\58\35"+
		"\2\u0129\u012c\7,\2\2\u012a\u012c\5:\36\2\u012b\u0128\3\2\2\2\u012b\u0129"+
		"\3\2\2\2\u012b\u012a\3\2\2\2\u012c\67\3\2\2\2\u012d\u012e\t\b\2\2\u012e"+
		"9\3\2\2\2\u012f\u0130\7,\2\2\u0130\u0131\7\20\2\2\u0131\u0132\5<\37\2"+
		"\u0132\u0133\7\21\2\2\u0133;\3\2\2\2\u0134\u0139\5\62\32\2\u0135\u0136"+
		"\7\5\2\2\u0136\u0138\5\62\32\2\u0137\u0135\3\2\2\2\u0138\u013b\3\2\2\2"+
		"\u0139\u0137\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139"+
		"\3\2\2\2\u013c\u0134\3\2\2\2\u013c\u013d\3\2\2\2\u013d=\3\2\2\2\33AER"+
		"Yehorx\u0084\u008a\u0097\u009b\u00af\u00bb\u00d8\u00f4\u0105\u0107\u0112"+
		"\u0123\u0125\u012b\u0139\u013c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}