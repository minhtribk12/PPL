// Generated from src/main/mc/parser/MC.g4 by ANTLR 4.6

	package mc.parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MCLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		UNSIGNINT=1, LSB=2, RSB=3, CM=4, COMMENT=5, BOOLLIT=6, BOOLTYPE=7, INTTYPE=8, 
		INTLIT=9, FLOATTYPE=10, FLOATLIT=11, STRINGTYPE=12, STRINGLIT=13, VOIDTYPE=14, 
		LB=15, RB=16, LP=17, RP=18, SEMI=19, IF=20, ELSE=21, FOR=22, WHILE=23, 
		DO=24, BREAK=25, CONTINUE=26, RETURN=27, REPEAT=28, UNTIL=29, ADD=30, 
		SUB=31, MUL=32, DIV=33, LT=34, GT=35, LEQ=36, GEQ=37, EQ=38, NOT=39, OR=40, 
		NEQ=41, ASSIGN=42, MOL=43, AND=44, ID=45, WS=46, ILLEGAL_ESCAPE=47, UNCLOSE_STRING=48, 
		ERROR_CHAR=49;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"UNSIGNINT", "LSB", "RSB", "CM", "COMMENT", "BOOLLIT", "BOOLTYPE", "INTTYPE", 
		"INTLIT", "FLOATTYPE", "FLOATLIT", "EXP", "FLOATDIGIT", "DIGIT", "STRINGTYPE", 
		"STRINGLIT", "VOIDTYPE", "LB", "RB", "LP", "RP", "SEMI", "IF", "ELSE", 
		"FOR", "WHILE", "DO", "BREAK", "CONTINUE", "RETURN", "REPEAT", "UNTIL", 
		"ADD", "SUB", "MUL", "DIV", "LT", "GT", "LEQ", "GEQ", "EQ", "NOT", "OR", 
		"NEQ", "ASSIGN", "MOL", "AND", "ID", "WS", "ILLEGAL_ESCAPE", "NOTESCAPE", 
		"UNCLOSE_STRING", "ERROR_CHAR"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'['", "']'", "','", null, null, "'boolean'", "'int'", null, 
		"'float'", null, "'string'", null, "'void'", "'('", "')'", "'{'", "'}'", 
		"';'", "'if'", "'else'", "'for'", "'while'", "'do'", "'break'", "'continue'", 
		"'return'", "'repeat'", "'until'", "'+'", "'-'", "'*'", "'/'", "'<'", 
		"'>'", "'<='", "'>='", "'=='", "'!'", "'||'", "'!='", "'='", "'%'", "'&&'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "UNSIGNINT", "LSB", "RSB", "CM", "COMMENT", "BOOLLIT", "BOOLTYPE", 
		"INTTYPE", "INTLIT", "FLOATTYPE", "FLOATLIT", "STRINGTYPE", "STRINGLIT", 
		"VOIDTYPE", "LB", "RB", "LP", "RP", "SEMI", "IF", "ELSE", "FOR", "WHILE", 
		"DO", "BREAK", "CONTINUE", "RETURN", "REPEAT", "UNTIL", "ADD", "SUB", 
		"MUL", "DIV", "LT", "GT", "LEQ", "GEQ", "EQ", "NOT", "OR", "NEQ", "ASSIGN", 
		"MOL", "AND", "ID", "WS", "ILLEGAL_ESCAPE", "UNCLOSE_STRING", "ERROR_CHAR"
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
	public Token emit() {
	    switch (getType()) {
	    case UNCLOSE_STRING:       
	        Token result = super.emit();
	        // you'll need to define this method
	        throw new UncloseString(result.getText());
	        
	    case ILLEGAL_ESCAPE:
	    	result = super.emit();
	    	throw new IllegalEscape(result.getText());

	    case ERROR_CHAR:
	    	result = super.emit();
	    	throw new ErrorToken(result.getText());	

	    default:
	        return super.emit();
	    }
	}


	public MCLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MC.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\63\u0190\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\3\2\3\2\7\2p\n\2\f\2\16\2s\13\2\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\6\3\6\7\6\177\n\6\f\6\16\6\u0082\13\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\7\6\u008a\n\6\f\6\16\6\u008d\13\6\5\6\u008f\n\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u009c\n\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\5\n\u00ab\n\n\3\n\3\n\7\n\u00af\n\n\f"+
		"\n\16\n\u00b2\13\n\3\n\5\n\u00b5\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\5\f\u00bf\n\f\3\r\3\r\3\r\3\16\6\16\u00c5\n\16\r\16\16\16\u00c6\3"+
		"\16\3\16\7\16\u00cb\n\16\f\16\16\16\u00ce\13\16\3\16\7\16\u00d1\n\16\f"+
		"\16\16\16\u00d4\13\16\3\16\3\16\6\16\u00d8\n\16\r\16\16\16\u00d9\5\16"+
		"\u00dc\n\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\7\21\u00eb\n\21\f\21\16\21\u00ee\13\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3"+
		" \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'"+
		"\3\'\3(\3(\3(\3)\3)\3)\3*\3*\3*\3+\3+\3,\3,\3,\3-\3-\3-\3.\3.\3/\3/\3"+
		"\60\3\60\3\60\3\61\3\61\7\61\u015f\n\61\f\61\16\61\u0162\13\61\3\62\6"+
		"\62\u0165\n\62\r\62\16\62\u0166\3\62\3\62\3\63\3\63\3\63\3\63\7\63\u016f"+
		"\n\63\f\63\16\63\u0172\13\63\3\63\7\63\u0175\n\63\f\63\16\63\u0178\13"+
		"\63\6\63\u017a\n\63\r\63\16\63\u017b\3\63\3\63\3\64\7\64\u0181\n\64\f"+
		"\64\16\64\u0184\13\64\3\65\3\65\7\65\u0188\n\65\f\65\16\65\u018b\13\65"+
		"\3\65\3\65\3\66\3\66\3\u0080\2\67\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\2\33\2\35\2\37\16!\17#\20%\21\'\22)\23+\24-\25/\26\61"+
		"\27\63\30\65\31\67\329\33;\34=\35?\36A\37C E!G\"I#K$M%O&Q\'S(U)W*Y+[,"+
		"]-_.a/c\60e\61g\2i\62k\63\3\2\f\3\2\63;\4\2\f\f\17\17\4\2GGgg\3\2\62;"+
		"\n\2$$))^^ddhhppttvv\6\2\f\f\17\17$$^^\5\2C\\aac|\6\2\62;C\\aac|\5\2\13"+
		"\f\17\17\"\"\3\2$$\u01a2\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2"+
		"\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2"+
		"\2e\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\3m\3\2\2\2\5t\3\2\2\2\7v\3\2\2\2\tx"+
		"\3\2\2\2\13\u008e\3\2\2\2\r\u009b\3\2\2\2\17\u009d\3\2\2\2\21\u00a5\3"+
		"\2\2\2\23\u00b4\3\2\2\2\25\u00b6\3\2\2\2\27\u00bc\3\2\2\2\31\u00c0\3\2"+
		"\2\2\33\u00db\3\2\2\2\35\u00dd\3\2\2\2\37\u00df\3\2\2\2!\u00e6\3\2\2\2"+
		"#\u00f1\3\2\2\2%\u00f6\3\2\2\2\'\u00f8\3\2\2\2)\u00fa\3\2\2\2+\u00fc\3"+
		"\2\2\2-\u00fe\3\2\2\2/\u0100\3\2\2\2\61\u0103\3\2\2\2\63\u0108\3\2\2\2"+
		"\65\u010c\3\2\2\2\67\u0112\3\2\2\29\u0115\3\2\2\2;\u011b\3\2\2\2=\u0124"+
		"\3\2\2\2?\u012b\3\2\2\2A\u0132\3\2\2\2C\u0138\3\2\2\2E\u013a\3\2\2\2G"+
		"\u013c\3\2\2\2I\u013e\3\2\2\2K\u0140\3\2\2\2M\u0142\3\2\2\2O\u0144\3\2"+
		"\2\2Q\u0147\3\2\2\2S\u014a\3\2\2\2U\u014d\3\2\2\2W\u014f\3\2\2\2Y\u0152"+
		"\3\2\2\2[\u0155\3\2\2\2]\u0157\3\2\2\2_\u0159\3\2\2\2a\u015c\3\2\2\2c"+
		"\u0164\3\2\2\2e\u016a\3\2\2\2g\u0182\3\2\2\2i\u0185\3\2\2\2k\u018e\3\2"+
		"\2\2mq\t\2\2\2np\5\35\17\2on\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2r\4"+
		"\3\2\2\2sq\3\2\2\2tu\7]\2\2u\6\3\2\2\2vw\7_\2\2w\b\3\2\2\2xy\7.\2\2y\n"+
		"\3\2\2\2z{\7\61\2\2{|\7,\2\2|\u0080\3\2\2\2}\177\13\2\2\2~}\3\2\2\2\177"+
		"\u0082\3\2\2\2\u0080\u0081\3\2\2\2\u0080~\3\2\2\2\u0081\u0083\3\2\2\2"+
		"\u0082\u0080\3\2\2\2\u0083\u0084\7,\2\2\u0084\u008f\7\61\2\2\u0085\u0086"+
		"\7\61\2\2\u0086\u0087\7\61\2\2\u0087\u008b\3\2\2\2\u0088\u008a\n\3\2\2"+
		"\u0089\u0088\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c"+
		"\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008ez\3\2\2\2\u008e"+
		"\u0085\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\b\6\2\2\u0091\f\3\2\2\2"+
		"\u0092\u0093\7v\2\2\u0093\u0094\7t\2\2\u0094\u0095\7w\2\2\u0095\u009c"+
		"\7g\2\2\u0096\u0097\7h\2\2\u0097\u0098\7c\2\2\u0098\u0099\7n\2\2\u0099"+
		"\u009a\7u\2\2\u009a\u009c\7g\2\2\u009b\u0092\3\2\2\2\u009b\u0096\3\2\2"+
		"\2\u009c\16\3\2\2\2\u009d\u009e\7d\2\2\u009e\u009f\7q\2\2\u009f\u00a0"+
		"\7q\2\2\u00a0\u00a1\7n\2\2\u00a1\u00a2\7g\2\2\u00a2\u00a3\7c\2\2\u00a3"+
		"\u00a4\7p\2\2\u00a4\20\3\2\2\2\u00a5\u00a6\7k\2\2\u00a6\u00a7\7p\2\2\u00a7"+
		"\u00a8\7v\2\2\u00a8\22\3\2\2\2\u00a9\u00ab\7/\2\2\u00aa\u00a9\3\2\2\2"+
		"\u00aa\u00ab\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00b0\t\2\2\2\u00ad\u00af"+
		"\5\35\17\2\u00ae\u00ad\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2"+
		"\u00b0\u00b1\3\2\2\2\u00b1\u00b5\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b5"+
		"\7\62\2\2\u00b4\u00aa\3\2\2\2\u00b4\u00b3\3\2\2\2\u00b5\24\3\2\2\2\u00b6"+
		"\u00b7\7h\2\2\u00b7\u00b8\7n\2\2\u00b8\u00b9\7q\2\2\u00b9\u00ba\7c\2\2"+
		"\u00ba\u00bb\7v\2\2\u00bb\26\3\2\2\2\u00bc\u00be\5\33\16\2\u00bd\u00bf"+
		"\5\31\r\2\u00be\u00bd\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\30\3\2\2\2\u00c0"+
		"\u00c1\t\4\2\2\u00c1\u00c2\5\23\n\2\u00c2\32\3\2\2\2\u00c3\u00c5\5\35"+
		"\17\2\u00c4\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6"+
		"\u00c7\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00cc\7\60\2\2\u00c9\u00cb\5"+
		"\35\17\2\u00ca\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc"+
		"\u00cd\3\2\2\2\u00cd\u00dc\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d1\5\35"+
		"\17\2\u00d0\u00cf\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d2"+
		"\u00d3\3\2\2\2\u00d3\u00d5\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d5\u00d7\7\60"+
		"\2\2\u00d6\u00d8\5\35\17\2\u00d7\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9"+
		"\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00dc\3\2\2\2\u00db\u00c4\3\2"+
		"\2\2\u00db\u00d2\3\2\2\2\u00dc\34\3\2\2\2\u00dd\u00de\t\5\2\2\u00de\36"+
		"\3\2\2\2\u00df\u00e0\7u\2\2\u00e0\u00e1\7v\2\2\u00e1\u00e2\7t\2\2\u00e2"+
		"\u00e3\7k\2\2\u00e3\u00e4\7p\2\2\u00e4\u00e5\7i\2\2\u00e5 \3\2\2\2\u00e6"+
		"\u00ec\7$\2\2\u00e7\u00e8\7^\2\2\u00e8\u00eb\t\6\2\2\u00e9\u00eb\n\7\2"+
		"\2\u00ea\u00e7\3\2\2\2\u00ea\u00e9\3\2\2\2\u00eb\u00ee\3\2\2\2\u00ec\u00ea"+
		"\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ef\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ef"+
		"\u00f0\7$\2\2\u00f0\"\3\2\2\2\u00f1\u00f2\7x\2\2\u00f2\u00f3\7q\2\2\u00f3"+
		"\u00f4\7k\2\2\u00f4\u00f5\7f\2\2\u00f5$\3\2\2\2\u00f6\u00f7\7*\2\2\u00f7"+
		"&\3\2\2\2\u00f8\u00f9\7+\2\2\u00f9(\3\2\2\2\u00fa\u00fb\7}\2\2\u00fb*"+
		"\3\2\2\2\u00fc\u00fd\7\177\2\2\u00fd,\3\2\2\2\u00fe\u00ff\7=\2\2\u00ff"+
		".\3\2\2\2\u0100\u0101\7k\2\2\u0101\u0102\7h\2\2\u0102\60\3\2\2\2\u0103"+
		"\u0104\7g\2\2\u0104\u0105\7n\2\2\u0105\u0106\7u\2\2\u0106\u0107\7g\2\2"+
		"\u0107\62\3\2\2\2\u0108\u0109\7h\2\2\u0109\u010a\7q\2\2\u010a\u010b\7"+
		"t\2\2\u010b\64\3\2\2\2\u010c\u010d\7y\2\2\u010d\u010e\7j\2\2\u010e\u010f"+
		"\7k\2\2\u010f\u0110\7n\2\2\u0110\u0111\7g\2\2\u0111\66\3\2\2\2\u0112\u0113"+
		"\7f\2\2\u0113\u0114\7q\2\2\u01148\3\2\2\2\u0115\u0116\7d\2\2\u0116\u0117"+
		"\7t\2\2\u0117\u0118\7g\2\2\u0118\u0119\7c\2\2\u0119\u011a\7m\2\2\u011a"+
		":\3\2\2\2\u011b\u011c\7e\2\2\u011c\u011d\7q\2\2\u011d\u011e\7p\2\2\u011e"+
		"\u011f\7v\2\2\u011f\u0120\7k\2\2\u0120\u0121\7p\2\2\u0121\u0122\7w\2\2"+
		"\u0122\u0123\7g\2\2\u0123<\3\2\2\2\u0124\u0125\7t\2\2\u0125\u0126\7g\2"+
		"\2\u0126\u0127\7v\2\2\u0127\u0128\7w\2\2\u0128\u0129\7t\2\2\u0129\u012a"+
		"\7p\2\2\u012a>\3\2\2\2\u012b\u012c\7t\2\2\u012c\u012d\7g\2\2\u012d\u012e"+
		"\7r\2\2\u012e\u012f\7g\2\2\u012f\u0130\7c\2\2\u0130\u0131\7v\2\2\u0131"+
		"@\3\2\2\2\u0132\u0133\7w\2\2\u0133\u0134\7p\2\2\u0134\u0135\7v\2\2\u0135"+
		"\u0136\7k\2\2\u0136\u0137\7n\2\2\u0137B\3\2\2\2\u0138\u0139\7-\2\2\u0139"+
		"D\3\2\2\2\u013a\u013b\7/\2\2\u013bF\3\2\2\2\u013c\u013d\7,\2\2\u013dH"+
		"\3\2\2\2\u013e\u013f\7\61\2\2\u013fJ\3\2\2\2\u0140\u0141\7>\2\2\u0141"+
		"L\3\2\2\2\u0142\u0143\7@\2\2\u0143N\3\2\2\2\u0144\u0145\7>\2\2\u0145\u0146"+
		"\7?\2\2\u0146P\3\2\2\2\u0147\u0148\7@\2\2\u0148\u0149\7?\2\2\u0149R\3"+
		"\2\2\2\u014a\u014b\7?\2\2\u014b\u014c\7?\2\2\u014cT\3\2\2\2\u014d\u014e"+
		"\7#\2\2\u014eV\3\2\2\2\u014f\u0150\7~\2\2\u0150\u0151\7~\2\2\u0151X\3"+
		"\2\2\2\u0152\u0153\7#\2\2\u0153\u0154\7?\2\2\u0154Z\3\2\2\2\u0155\u0156"+
		"\7?\2\2\u0156\\\3\2\2\2\u0157\u0158\7\'\2\2\u0158^\3\2\2\2\u0159\u015a"+
		"\7(\2\2\u015a\u015b\7(\2\2\u015b`\3\2\2\2\u015c\u0160\t\b\2\2\u015d\u015f"+
		"\t\t\2\2\u015e\u015d\3\2\2\2\u015f\u0162\3\2\2\2\u0160\u015e\3\2\2\2\u0160"+
		"\u0161\3\2\2\2\u0161b\3\2\2\2\u0162\u0160\3\2\2\2\u0163\u0165\t\n\2\2"+
		"\u0164\u0163\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u0164\3\2\2\2\u0166\u0167"+
		"\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u0169\b\62\2\2\u0169d\3\2\2\2\u016a"+
		"\u0179\7$\2\2\u016b\u016c\5g\64\2\u016c\u0170\7^\2\2\u016d\u016f\n\6\2"+
		"\2\u016e\u016d\3\2\2\2\u016f\u0172\3\2\2\2\u0170\u016e\3\2\2\2\u0170\u0171"+
		"\3\2\2\2\u0171\u0176\3\2\2\2\u0172\u0170\3\2\2\2\u0173\u0175\n\3\2\2\u0174"+
		"\u0173\3\2\2\2\u0175\u0178\3\2\2\2\u0176\u0174\3\2\2\2\u0176\u0177\3\2"+
		"\2\2\u0177\u017a\3\2\2\2\u0178\u0176\3\2\2\2\u0179\u016b\3\2\2\2\u017a"+
		"\u017b\3\2\2\2\u017b\u0179\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u017d\3\2"+
		"\2\2\u017d\u017e\7$\2\2\u017ef\3\2\2\2\u017f\u0181\n\3\2\2\u0180\u017f"+
		"\3\2\2\2\u0181\u0184\3\2\2\2\u0182\u0180\3\2\2\2\u0182\u0183\3\2\2\2\u0183"+
		"h\3\2\2\2\u0184\u0182\3\2\2\2\u0185\u0189\7$\2\2\u0186\u0188\n\13\2\2"+
		"\u0187\u0186\3\2\2\2\u0188\u018b\3\2\2\2\u0189\u0187\3\2\2\2\u0189\u018a"+
		"\3\2\2\2\u018a\u018c\3\2\2\2\u018b\u0189\3\2\2\2\u018c\u018d\7\2\2\3\u018d"+
		"j\3\2\2\2\u018e\u018f\13\2\2\2\u018fl\3\2\2\2\32\2q\u0080\u008b\u008e"+
		"\u009b\u00aa\u00b0\u00b4\u00be\u00c6\u00cc\u00d2\u00d9\u00db\u00ea\u00ec"+
		"\u0160\u0166\u0170\u0176\u017b\u0182\u0189\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}