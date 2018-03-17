// Generated from /Users/minhtribk12/Google_Drive/Study/Master/Principles of Programming Languages/Assignment/source/initial/src/main/mc/parser/MC.g4 by ANTLR 4.7.1

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
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LSB=1, RSB=2, CM=3, COMMENT=4, BOOLLIT=5, BOOLTYPE=6, INTTYPE=7, INTLIT=8, 
		FLOATTYPE=9, FLOATLIT=10, STRINGTYPE=11, STRINGLIT=12, VOIDTYPE=13, LB=14, 
		RB=15, LP=16, RP=17, SEMI=18, IF=19, ELSE=20, FOR=21, WHILE=22, DO=23, 
		BREAK=24, CONTINUE=25, RETURN=26, REPEAT=27, UNTIL=28, ADD=29, SUB=30, 
		MUL=31, DIV=32, LT=33, GT=34, LEQ=35, GEQ=36, EQ=37, NOT=38, OR=39, NEQ=40, 
		ASSIGN=41, MOL=42, AND=43, ID=44, WS=45, ILLEGAL_ESCAPE=46, UNCLOSE_STRING=47, 
		ERROR_CHAR=48;
	public static final int
		RULE_program = 0, RULE_declaration = 1, RULE_vardecl = 2, RULE_vartype = 3, 
		RULE_primitivetype = 4, RULE_varlist = 5, RULE_varname = 6, RULE_funcdecl = 7, 
		RULE_functype = 8, RULE_paralist = 9, RULE_paradecl = 10, RULE_block = 11, 
		RULE_blockbody = 12, RULE_declpart = 13, RULE_stmtpart = 14, RULE_stmt = 15, 
		RULE_ifstmt = 16, RULE_ifelse = 17, RULE_ifnoelse = 18, RULE_whilestmt = 19, 
		RULE_forstmt = 20, RULE_breakstmt = 21, RULE_continuestmt = 22, RULE_returnstmt = 23, 
		RULE_expression = 24, RULE_naexpression = 25, RULE_operand = 26, RULE_variable = 27, 
		RULE_literal = 28, RULE_funcall = 29, RULE_arglist = 30;
	public static final String[] ruleNames = {
		"program", "declaration", "vardecl", "vartype", "primitivetype", "varlist", 
		"varname", "funcdecl", "functype", "paralist", "paradecl", "block", "blockbody", 
		"declpart", "stmtpart", "stmt", "ifstmt", "ifelse", "ifnoelse", "whilestmt", 
		"forstmt", "breakstmt", "continuestmt", "returnstmt", "expression", "naexpression", 
		"operand", "variable", "literal", "funcall", "arglist"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'['", "']'", "','", null, null, "'boolean'", "'int'", null, "'float'", 
		null, "'string'", null, "'void'", "'('", "')'", "'{'", "'}'", "';'", "'if'", 
		"'else'", "'for'", "'while'", "'do'", "'break'", "'continue'", "'return'", 
		"'repeat'", "'until'", "'+'", "'-'", "'*'", "'/'", "'<'", "'>'", "'<='", 
		"'>='", "'=='", "'!'", "'||'", "'!='", "'='", "'%'", "'&&'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LSB", "RSB", "CM", "COMMENT", "BOOLLIT", "BOOLTYPE", "INTTYPE", 
		"INTLIT", "FLOATTYPE", "FLOATLIT", "STRINGTYPE", "STRINGLIT", "VOIDTYPE", 
		"LB", "RB", "LP", "RP", "SEMI", "IF", "ELSE", "FOR", "WHILE", "DO", "BREAK", 
		"CONTINUE", "RETURN", "REPEAT", "UNTIL", "ADD", "SUB", "MUL", "DIV", "LT", 
		"GT", "LEQ", "GEQ", "EQ", "NOT", "OR", "NEQ", "ASSIGN", "MOL", "AND", 
		"ID", "WS", "ILLEGAL_ESCAPE", "UNCLOSE_STRING", "ERROR_CHAR"
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
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(62);
				declaration();
				}
				}
				setState(65); 
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
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		try {
			setState(69);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				vardecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
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
		public VartypeContext vartype() {
			return getRuleContext(VartypeContext.class,0);
		}
		public VarlistContext varlist() {
			return getRuleContext(VarlistContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MCParser.SEMI, 0); }
		public VardeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vardecl; }
	}

	public final VardeclContext vardecl() throws RecognitionException {
		VardeclContext _localctx = new VardeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_vardecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			vartype();
			setState(72);
			varlist();
			setState(73);
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

	public static class VartypeContext extends ParserRuleContext {
		public PrimitivetypeContext primitivetype() {
			return getRuleContext(PrimitivetypeContext.class,0);
		}
		public VartypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vartype; }
	}

	public final VartypeContext vartype() throws RecognitionException {
		VartypeContext _localctx = new VartypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_vartype);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			primitivetype();
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
	}

	public final PrimitivetypeContext primitivetype() throws RecognitionException {
		PrimitivetypeContext _localctx = new PrimitivetypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_primitivetype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
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
	}

	public final VarlistContext varlist() throws RecognitionException {
		VarlistContext _localctx = new VarlistContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			varname();
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CM) {
				{
				{
				setState(80);
				match(CM);
				setState(81);
				varname();
				}
				}
				setState(86);
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
	}

	public final VarnameContext varname() throws RecognitionException {
		VarnameContext _localctx = new VarnameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_varname);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(ID);
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LSB) {
				{
				setState(88);
				match(LSB);
				setState(89);
				match(INTLIT);
				setState(90);
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
	}

	public final FuncdeclContext funcdecl() throws RecognitionException {
		FuncdeclContext _localctx = new FuncdeclContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcdecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			functype();
			setState(94);
			match(ID);
			setState(95);
			match(LB);
			setState(96);
			paralist();
			setState(97);
			match(RB);
			setState(98);
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
	}

	public final FunctypeContext functype() throws RecognitionException {
		FunctypeContext _localctx = new FunctypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_functype);
		int _la;
		try {
			setState(106);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLTYPE:
			case INTTYPE:
			case FLOATTYPE:
			case STRINGTYPE:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(100);
				primitivetype();
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LSB) {
					{
					setState(101);
					match(LSB);
					setState(102);
					match(RSB);
					}
				}

				}
				}
				break;
			case VOIDTYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
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
	}

	public final ParalistContext paralist() throws RecognitionException {
		ParalistContext _localctx = new ParalistContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_paralist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLTYPE) | (1L << INTTYPE) | (1L << FLOATTYPE) | (1L << STRINGTYPE))) != 0)) {
				{
				setState(108);
				paradecl();
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CM) {
					{
					{
					setState(109);
					match(CM);
					setState(110);
					paradecl();
					}
					}
					setState(115);
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
	}

	public final ParadeclContext paradecl() throws RecognitionException {
		ParadeclContext _localctx = new ParadeclContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_paradecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			primitivetype();
			setState(119);
			match(ID);
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LSB) {
				{
				setState(120);
				match(LSB);
				setState(121);
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
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(LP);
			setState(125);
			blockbody();
			setState(126);
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
	}

	public final BlockbodyContext blockbody() throws RecognitionException {
		BlockbodyContext _localctx = new BlockbodyContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_blockbody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			declpart();
			setState(129);
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
	}

	public final DeclpartContext declpart() throws RecognitionException {
		DeclpartContext _localctx = new DeclpartContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_declpart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLTYPE) | (1L << INTTYPE) | (1L << FLOATTYPE) | (1L << STRINGTYPE))) != 0)) {
				{
				{
				setState(131);
				vardecl();
				}
				}
				setState(136);
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
	}

	public final StmtpartContext stmtpart() throws RecognitionException {
		StmtpartContext _localctx = new StmtpartContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_stmtpart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLLIT) | (1L << INTLIT) | (1L << FLOATLIT) | (1L << STRINGLIT) | (1L << LB) | (1L << LP) | (1L << IF) | (1L << FOR) | (1L << DO) | (1L << BREAK) | (1L << CONTINUE) | (1L << RETURN) | (1L << SUB) | (1L << NOT) | (1L << ID))) != 0)) {
				{
				{
				setState(137);
				stmt();
				}
				}
				setState(142);
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
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_stmt);
		try {
			setState(153);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(143);
				ifstmt();
				}
				break;
			case DO:
				enterOuterAlt(_localctx, 2);
				{
				setState(144);
				whilestmt();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(145);
				forstmt();
				}
				break;
			case BREAK:
				enterOuterAlt(_localctx, 4);
				{
				setState(146);
				breakstmt();
				}
				break;
			case CONTINUE:
				enterOuterAlt(_localctx, 5);
				{
				setState(147);
				continuestmt();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 6);
				{
				setState(148);
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
				setState(149);
				expression(0);
				setState(150);
				match(SEMI);
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 8);
				{
				setState(152);
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
	}

	public final IfstmtContext ifstmt() throws RecognitionException {
		IfstmtContext _localctx = new IfstmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_ifstmt);
		try {
			setState(157);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(155);
				ifelse();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(156);
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
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MCParser.ELSE, 0); }
		public IfelseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifelse; }
	}

	public final IfelseContext ifelse() throws RecognitionException {
		IfelseContext _localctx = new IfelseContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_ifelse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(IF);
			setState(160);
			match(LB);
			setState(161);
			expression(0);
			setState(162);
			match(RB);
			setState(163);
			stmt();
			setState(164);
			match(ELSE);
			setState(165);
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
	}

	public final IfnoelseContext ifnoelse() throws RecognitionException {
		IfnoelseContext _localctx = new IfnoelseContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_ifnoelse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(IF);
			setState(168);
			match(LB);
			setState(169);
			expression(0);
			setState(170);
			match(RB);
			setState(171);
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
	}

	public final WhilestmtContext whilestmt() throws RecognitionException {
		WhilestmtContext _localctx = new WhilestmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_whilestmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(DO);
			setState(175); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(174);
				stmt();
				}
				}
				setState(177); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLLIT) | (1L << INTLIT) | (1L << FLOATLIT) | (1L << STRINGLIT) | (1L << LB) | (1L << LP) | (1L << IF) | (1L << FOR) | (1L << DO) | (1L << BREAK) | (1L << CONTINUE) | (1L << RETURN) | (1L << SUB) | (1L << NOT) | (1L << ID))) != 0) );
			setState(179);
			match(WHILE);
			setState(180);
			expression(0);
			setState(181);
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
	}

	public final ForstmtContext forstmt() throws RecognitionException {
		ForstmtContext _localctx = new ForstmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_forstmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(FOR);
			setState(184);
			match(LB);
			setState(185);
			expression(0);
			setState(186);
			match(SEMI);
			setState(187);
			expression(0);
			setState(188);
			match(SEMI);
			setState(189);
			expression(0);
			setState(190);
			match(RB);
			setState(191);
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
	}

	public final BreakstmtContext breakstmt() throws RecognitionException {
		BreakstmtContext _localctx = new BreakstmtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_breakstmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(BREAK);
			setState(194);
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
	}

	public final ContinuestmtContext continuestmt() throws RecognitionException {
		ContinuestmtContext _localctx = new ContinuestmtContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_continuestmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(CONTINUE);
			setState(197);
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
	}

	public final ReturnstmtContext returnstmt() throws RecognitionException {
		ReturnstmtContext _localctx = new ReturnstmtContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_returnstmt);
		try {
			setState(205);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(199);
				match(RETURN);
				setState(200);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
				match(RETURN);
				setState(202);
				expression(0);
				setState(203);
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
		public TerminalNode SUB() { return getToken(MCParser.SUB, 0); }
		public TerminalNode NOT() { return getToken(MCParser.NOT, 0); }
		public List<NaexpressionContext> naexpression() {
			return getRuleContexts(NaexpressionContext.class);
		}
		public NaexpressionContext naexpression(int i) {
			return getRuleContext(NaexpressionContext.class,i);
		}
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
			setState(228);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(208);
				match(LB);
				setState(209);
				expression(0);
				setState(210);
				match(RB);
				}
				break;
			case 2:
				{
				setState(212);
				match(ID);
				setState(213);
				match(LSB);
				setState(214);
				expression(0);
				setState(215);
				match(RSB);
				}
				break;
			case 3:
				{
				setState(217);
				_la = _input.LA(1);
				if ( !(_la==SUB || _la==NOT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(218);
				expression(9);
				}
				break;
			case 4:
				{
				setState(219);
				naexpression(0);
				setState(220);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT) | (1L << GT) | (1L << LEQ) | (1L << GEQ))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(221);
				naexpression(0);
				}
				break;
			case 5:
				{
				setState(223);
				naexpression(0);
				setState(224);
				_la = _input.LA(1);
				if ( !(_la==EQ || _la==NEQ) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(225);
				naexpression(0);
				}
				break;
			case 6:
				{
				setState(227);
				operand();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(252);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(250);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(230);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(231);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOL))) != 0)) ) {
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
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(233);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(234);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(235);
						expression(8);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(236);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(237);
						match(AND);
						setState(238);
						expression(5);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(239);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(240);
						match(OR);
						setState(241);
						expression(4);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(242);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(243);
						match(ASSIGN);
						setState(244);
						expression(2);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(245);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(246);
						match(LSB);
						setState(247);
						expression(0);
						setState(248);
						match(RSB);
						}
						break;
					}
					} 
				}
				setState(254);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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
		public TerminalNode ID() { return getToken(MCParser.ID, 0); }
		public TerminalNode LSB() { return getToken(MCParser.LSB, 0); }
		public List<NaexpressionContext> naexpression() {
			return getRuleContexts(NaexpressionContext.class);
		}
		public NaexpressionContext naexpression(int i) {
			return getRuleContext(NaexpressionContext.class,i);
		}
		public TerminalNode RSB() { return getToken(MCParser.RSB, 0); }
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
			setState(268);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(256);
				match(LB);
				setState(257);
				expression(0);
				setState(258);
				match(RB);
				}
				break;
			case 2:
				{
				setState(260);
				match(ID);
				setState(261);
				match(LSB);
				setState(262);
				naexpression(0);
				setState(263);
				match(RSB);
				}
				break;
			case 3:
				{
				setState(265);
				_la = _input.LA(1);
				if ( !(_la==SUB || _la==NOT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(266);
				naexpression(7);
				}
				break;
			case 4:
				{
				setState(267);
				operand();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(287);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(285);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new NaexpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_naexpression);
						setState(270);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(271);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOL))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(272);
						naexpression(7);
						}
						break;
					case 2:
						{
						_localctx = new NaexpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_naexpression);
						setState(273);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(274);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(275);
						naexpression(6);
						}
						break;
					case 3:
						{
						_localctx = new NaexpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_naexpression);
						setState(276);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(277);
						match(AND);
						setState(278);
						naexpression(5);
						}
						break;
					case 4:
						{
						_localctx = new NaexpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_naexpression);
						setState(279);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(280);
						match(OR);
						setState(281);
						naexpression(4);
						}
						break;
					case 5:
						{
						_localctx = new NaexpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_naexpression);
						setState(282);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(283);
						match(ASSIGN);
						setState(284);
						naexpression(2);
						}
						break;
					}
					} 
				}
				setState(289);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public FuncallContext funcall() {
			return getRuleContext(FuncallContext.class,0);
		}
		public OperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operand; }
	}

	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_operand);
		try {
			setState(293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(290);
				literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(291);
				variable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(292);
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MCParser.ID, 0); }
		public TerminalNode LSB() { return getToken(MCParser.LSB, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RSB() { return getToken(MCParser.RSB, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_variable);
		try {
			setState(301);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(295);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(296);
				match(ID);
				setState(297);
				match(LSB);
				setState(298);
				expression(0);
				setState(299);
				match(RSB);
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
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
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
	}

	public final FuncallContext funcall() throws RecognitionException {
		FuncallContext _localctx = new FuncallContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_funcall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			match(ID);
			setState(306);
			match(LB);
			setState(307);
			arglist();
			setState(308);
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
	}

	public final ArglistContext arglist() throws RecognitionException {
		ArglistContext _localctx = new ArglistContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_arglist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLLIT) | (1L << INTLIT) | (1L << FLOATLIT) | (1L << STRINGLIT) | (1L << LB) | (1L << SUB) | (1L << NOT) | (1L << ID))) != 0)) {
				{
				setState(310);
				expression(0);
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CM) {
					{
					{
					setState(311);
					match(CM);
					setState(312);
					expression(0);
					}
					}
					setState(317);
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
		case 5:
			return precpred(_ctx, 10);
		}
		return true;
	}
	private boolean naexpression_sempred(NaexpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 6);
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 4);
		case 9:
			return precpred(_ctx, 3);
		case 10:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\62\u0143\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\6\2B\n\2\r\2\16\2C\3\3\3\3\5\3H\n\3\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3"+
		"\7\3\7\3\7\7\7U\n\7\f\7\16\7X\13\7\3\b\3\b\3\b\3\b\5\b^\n\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\5\nj\n\n\3\n\5\nm\n\n\3\13\3\13\3\13\7"+
		"\13r\n\13\f\13\16\13u\13\13\5\13w\n\13\3\f\3\f\3\f\3\f\5\f}\n\f\3\r\3"+
		"\r\3\r\3\r\3\16\3\16\3\16\3\17\7\17\u0087\n\17\f\17\16\17\u008a\13\17"+
		"\3\20\7\20\u008d\n\20\f\20\16\20\u0090\13\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\5\21\u009c\n\21\3\22\3\22\5\22\u00a0\n\22\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\25\3\25\6\25\u00b2\n\25\r\25\16\25\u00b3\3\25\3\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u00d0\n\31\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\5\32\u00e7\n\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u00fd\n\32"+
		"\f\32\16\32\u0100\13\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\5\33\u010f\n\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u0120\n\33\f\33\16\33\u0123"+
		"\13\33\3\34\3\34\3\34\5\34\u0128\n\34\3\35\3\35\3\35\3\35\3\35\3\35\5"+
		"\35\u0130\n\35\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \7 \u013c\n"+
		" \f \16 \u013f\13 \5 \u0141\n \3 \2\4\62\64!\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\62\64\668:<>\2\t\5\2\b\t\13\13\r\r\4\2  ((\3"+
		"\2#&\4\2\'\'**\4\2!\",,\3\2\37 \6\2\7\7\n\n\f\f\16\16\2\u0150\2A\3\2\2"+
		"\2\4G\3\2\2\2\6I\3\2\2\2\bM\3\2\2\2\nO\3\2\2\2\fQ\3\2\2\2\16Y\3\2\2\2"+
		"\20_\3\2\2\2\22l\3\2\2\2\24v\3\2\2\2\26x\3\2\2\2\30~\3\2\2\2\32\u0082"+
		"\3\2\2\2\34\u0088\3\2\2\2\36\u008e\3\2\2\2 \u009b\3\2\2\2\"\u009f\3\2"+
		"\2\2$\u00a1\3\2\2\2&\u00a9\3\2\2\2(\u00af\3\2\2\2*\u00b9\3\2\2\2,\u00c3"+
		"\3\2\2\2.\u00c6\3\2\2\2\60\u00cf\3\2\2\2\62\u00e6\3\2\2\2\64\u010e\3\2"+
		"\2\2\66\u0127\3\2\2\28\u012f\3\2\2\2:\u0131\3\2\2\2<\u0133\3\2\2\2>\u0140"+
		"\3\2\2\2@B\5\4\3\2A@\3\2\2\2BC\3\2\2\2CA\3\2\2\2CD\3\2\2\2D\3\3\2\2\2"+
		"EH\5\6\4\2FH\5\20\t\2GE\3\2\2\2GF\3\2\2\2H\5\3\2\2\2IJ\5\b\5\2JK\5\f\7"+
		"\2KL\7\24\2\2L\7\3\2\2\2MN\5\n\6\2N\t\3\2\2\2OP\t\2\2\2P\13\3\2\2\2QV"+
		"\5\16\b\2RS\7\5\2\2SU\5\16\b\2TR\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2"+
		"W\r\3\2\2\2XV\3\2\2\2Y]\7.\2\2Z[\7\3\2\2[\\\7\n\2\2\\^\7\4\2\2]Z\3\2\2"+
		"\2]^\3\2\2\2^\17\3\2\2\2_`\5\22\n\2`a\7.\2\2ab\7\20\2\2bc\5\24\13\2cd"+
		"\7\21\2\2de\5\30\r\2e\21\3\2\2\2fi\5\n\6\2gh\7\3\2\2hj\7\4\2\2ig\3\2\2"+
		"\2ij\3\2\2\2jm\3\2\2\2km\7\17\2\2lf\3\2\2\2lk\3\2\2\2m\23\3\2\2\2ns\5"+
		"\26\f\2op\7\5\2\2pr\5\26\f\2qo\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2t"+
		"w\3\2\2\2us\3\2\2\2vn\3\2\2\2vw\3\2\2\2w\25\3\2\2\2xy\5\n\6\2y|\7.\2\2"+
		"z{\7\3\2\2{}\7\4\2\2|z\3\2\2\2|}\3\2\2\2}\27\3\2\2\2~\177\7\22\2\2\177"+
		"\u0080\5\32\16\2\u0080\u0081\7\23\2\2\u0081\31\3\2\2\2\u0082\u0083\5\34"+
		"\17\2\u0083\u0084\5\36\20\2\u0084\33\3\2\2\2\u0085\u0087\5\6\4\2\u0086"+
		"\u0085\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2"+
		"\2\2\u0089\35\3\2\2\2\u008a\u0088\3\2\2\2\u008b\u008d\5 \21\2\u008c\u008b"+
		"\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f"+
		"\37\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u009c\5\"\22\2\u0092\u009c\5(\25"+
		"\2\u0093\u009c\5*\26\2\u0094\u009c\5,\27\2\u0095\u009c\5.\30\2\u0096\u009c"+
		"\5\60\31\2\u0097\u0098\5\62\32\2\u0098\u0099\7\24\2\2\u0099\u009c\3\2"+
		"\2\2\u009a\u009c\5\30\r\2\u009b\u0091\3\2\2\2\u009b\u0092\3\2\2\2\u009b"+
		"\u0093\3\2\2\2\u009b\u0094\3\2\2\2\u009b\u0095\3\2\2\2\u009b\u0096\3\2"+
		"\2\2\u009b\u0097\3\2\2\2\u009b\u009a\3\2\2\2\u009c!\3\2\2\2\u009d\u00a0"+
		"\5$\23\2\u009e\u00a0\5&\24\2\u009f\u009d\3\2\2\2\u009f\u009e\3\2\2\2\u00a0"+
		"#\3\2\2\2\u00a1\u00a2\7\25\2\2\u00a2\u00a3\7\20\2\2\u00a3\u00a4\5\62\32"+
		"\2\u00a4\u00a5\7\21\2\2\u00a5\u00a6\5 \21\2\u00a6\u00a7\7\26\2\2\u00a7"+
		"\u00a8\5 \21\2\u00a8%\3\2\2\2\u00a9\u00aa\7\25\2\2\u00aa\u00ab\7\20\2"+
		"\2\u00ab\u00ac\5\62\32\2\u00ac\u00ad\7\21\2\2\u00ad\u00ae\5 \21\2\u00ae"+
		"\'\3\2\2\2\u00af\u00b1\7\31\2\2\u00b0\u00b2\5 \21\2\u00b1\u00b0\3\2\2"+
		"\2\u00b2\u00b3\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5"+
		"\3\2\2\2\u00b5\u00b6\7\30\2\2\u00b6\u00b7\5\62\32\2\u00b7\u00b8\7\24\2"+
		"\2\u00b8)\3\2\2\2\u00b9\u00ba\7\27\2\2\u00ba\u00bb\7\20\2\2\u00bb\u00bc"+
		"\5\62\32\2\u00bc\u00bd\7\24\2\2\u00bd\u00be\5\62\32\2\u00be\u00bf\7\24"+
		"\2\2\u00bf\u00c0\5\62\32\2\u00c0\u00c1\7\21\2\2\u00c1\u00c2\5 \21\2\u00c2"+
		"+\3\2\2\2\u00c3\u00c4\7\32\2\2\u00c4\u00c5\7\24\2\2\u00c5-\3\2\2\2\u00c6"+
		"\u00c7\7\33\2\2\u00c7\u00c8\7\24\2\2\u00c8/\3\2\2\2\u00c9\u00ca\7\34\2"+
		"\2\u00ca\u00d0\7\24\2\2\u00cb\u00cc\7\34\2\2\u00cc\u00cd\5\62\32\2\u00cd"+
		"\u00ce\7\24\2\2\u00ce\u00d0\3\2\2\2\u00cf\u00c9\3\2\2\2\u00cf\u00cb\3"+
		"\2\2\2\u00d0\61\3\2\2\2\u00d1\u00d2\b\32\1\2\u00d2\u00d3\7\20\2\2\u00d3"+
		"\u00d4\5\62\32\2\u00d4\u00d5\7\21\2\2\u00d5\u00e7\3\2\2\2\u00d6\u00d7"+
		"\7.\2\2\u00d7\u00d8\7\3\2\2\u00d8\u00d9\5\62\32\2\u00d9\u00da\7\4\2\2"+
		"\u00da\u00e7\3\2\2\2\u00db\u00dc\t\3\2\2\u00dc\u00e7\5\62\32\13\u00dd"+
		"\u00de\5\64\33\2\u00de\u00df\t\4\2\2\u00df\u00e0\5\64\33\2\u00e0\u00e7"+
		"\3\2\2\2\u00e1\u00e2\5\64\33\2\u00e2\u00e3\t\5\2\2\u00e3\u00e4\5\64\33"+
		"\2\u00e4\u00e7\3\2\2\2\u00e5\u00e7\5\66\34\2\u00e6\u00d1\3\2\2\2\u00e6"+
		"\u00d6\3\2\2\2\u00e6\u00db\3\2\2\2\u00e6\u00dd\3\2\2\2\u00e6\u00e1\3\2"+
		"\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00fe\3\2\2\2\u00e8\u00e9\f\n\2\2\u00e9"+
		"\u00ea\t\6\2\2\u00ea\u00fd\5\62\32\13\u00eb\u00ec\f\t\2\2\u00ec\u00ed"+
		"\t\7\2\2\u00ed\u00fd\5\62\32\n\u00ee\u00ef\f\6\2\2\u00ef\u00f0\7-\2\2"+
		"\u00f0\u00fd\5\62\32\7\u00f1\u00f2\f\5\2\2\u00f2\u00f3\7)\2\2\u00f3\u00fd"+
		"\5\62\32\6\u00f4\u00f5\f\4\2\2\u00f5\u00f6\7+\2\2\u00f6\u00fd\5\62\32"+
		"\4\u00f7\u00f8\f\f\2\2\u00f8\u00f9\7\3\2\2\u00f9\u00fa\5\62\32\2\u00fa"+
		"\u00fb\7\4\2\2\u00fb\u00fd\3\2\2\2\u00fc\u00e8\3\2\2\2\u00fc\u00eb\3\2"+
		"\2\2\u00fc\u00ee\3\2\2\2\u00fc\u00f1\3\2\2\2\u00fc\u00f4\3\2\2\2\u00fc"+
		"\u00f7\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2"+
		"\2\2\u00ff\63\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0102\b\33\1\2\u0102\u0103"+
		"\7\20\2\2\u0103\u0104\5\62\32\2\u0104\u0105\7\21\2\2\u0105\u010f\3\2\2"+
		"\2\u0106\u0107\7.\2\2\u0107\u0108\7\3\2\2\u0108\u0109\5\64\33\2\u0109"+
		"\u010a\7\4\2\2\u010a\u010f\3\2\2\2\u010b\u010c\t\3\2\2\u010c\u010f\5\64"+
		"\33\t\u010d\u010f\5\66\34\2\u010e\u0101\3\2\2\2\u010e\u0106\3\2\2\2\u010e"+
		"\u010b\3\2\2\2\u010e\u010d\3\2\2\2\u010f\u0121\3\2\2\2\u0110\u0111\f\b"+
		"\2\2\u0111\u0112\t\6\2\2\u0112\u0120\5\64\33\t\u0113\u0114\f\7\2\2\u0114"+
		"\u0115\t\7\2\2\u0115\u0120\5\64\33\b\u0116\u0117\f\6\2\2\u0117\u0118\7"+
		"-\2\2\u0118\u0120\5\64\33\7\u0119\u011a\f\5\2\2\u011a\u011b\7)\2\2\u011b"+
		"\u0120\5\64\33\6\u011c\u011d\f\4\2\2\u011d\u011e\7+\2\2\u011e\u0120\5"+
		"\64\33\4\u011f\u0110\3\2\2\2\u011f\u0113\3\2\2\2\u011f\u0116\3\2\2\2\u011f"+
		"\u0119\3\2\2\2\u011f\u011c\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2"+
		"\2\2\u0121\u0122\3\2\2\2\u0122\65\3\2\2\2\u0123\u0121\3\2\2\2\u0124\u0128"+
		"\5:\36\2\u0125\u0128\58\35\2\u0126\u0128\5<\37\2\u0127\u0124\3\2\2\2\u0127"+
		"\u0125\3\2\2\2\u0127\u0126\3\2\2\2\u0128\67\3\2\2\2\u0129\u0130\7.\2\2"+
		"\u012a\u012b\7.\2\2\u012b\u012c\7\3\2\2\u012c\u012d\5\62\32\2\u012d\u012e"+
		"\7\4\2\2\u012e\u0130\3\2\2\2\u012f\u0129\3\2\2\2\u012f\u012a\3\2\2\2\u0130"+
		"9\3\2\2\2\u0131\u0132\t\b\2\2\u0132;\3\2\2\2\u0133\u0134\7.\2\2\u0134"+
		"\u0135\7\20\2\2\u0135\u0136\5> \2\u0136\u0137\7\21\2\2\u0137=\3\2\2\2"+
		"\u0138\u013d\5\62\32\2\u0139\u013a\7\5\2\2\u013a\u013c\5\62\32\2\u013b"+
		"\u0139\3\2\2\2\u013c\u013f\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2"+
		"\2\2\u013e\u0141\3\2\2\2\u013f\u013d\3\2\2\2\u0140\u0138\3\2\2\2\u0140"+
		"\u0141\3\2\2\2\u0141?\3\2\2\2\33CGV]ilsv|\u0088\u008e\u009b\u009f\u00b3"+
		"\u00cf\u00e6\u00fc\u00fe\u010e\u011f\u0121\u0127\u012f\u013d\u0140";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}