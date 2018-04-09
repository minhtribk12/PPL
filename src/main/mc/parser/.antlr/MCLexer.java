// Generated from /Users/minhtribk12/Google_Drive/Study/Master/Principles of Programming Languages/Assignment/source/initial/src/main/mc/parser/MC.g4 by ANTLR 4.7.1

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
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"LSB", "RSB", "CM", "COMMENT", "BOOLLIT", "BOOLTYPE", "INTTYPE", "INTLIT", 
		"FLOATTYPE", "FLOATLIT", "EXP", "FLOATDIGIT", "DIGIT", "STRINGTYPE", "STRINGLIT", 
		"VOIDTYPE", "LB", "RB", "LP", "RP", "SEMI", "IF", "ELSE", "FOR", "WHILE", 
		"DO", "BREAK", "CONTINUE", "RETURN", "ADD", "SUB", "MUL", "DIV", "LT", 
		"GT", "LEQ", "GEQ", "EQ", "NOT", "OR", "NEQ", "ASSIGN", "MOL", "AND", 
		"ID", "WS", "ILLEGAL_ESCAPE", "NOTESCAPE", "UNCLOSE_STRING", "ERROR_CHAR"
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
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 14:
			STRINGLIT_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRINGLIT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			String s = getText(); setText(s.substring(1, s.length() - 1));
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\60\u017d\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\7\5r\n\5\f\5\16\5u\13\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\7\5}\n\5\f\5\16\5\u0080\13\5\5\5\u0082\n\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u008f\n\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\t\5\t\u009e\n\t\3\t\6\t\u00a1\n\t\r\t\16\t"+
		"\u00a2\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\5\13\u00ad\n\13\3\13\5\13\u00b0"+
		"\n\13\3\13\6\13\u00b3\n\13\r\13\16\13\u00b4\3\13\3\13\5\13\u00b9\n\13"+
		"\3\f\3\f\3\f\3\r\5\r\u00bf\n\r\3\r\6\r\u00c2\n\r\r\r\16\r\u00c3\3\r\3"+
		"\r\7\r\u00c8\n\r\f\r\16\r\u00cb\13\r\3\r\7\r\u00ce\n\r\f\r\16\r\u00d1"+
		"\13\r\3\r\3\r\6\r\u00d5\n\r\r\r\16\r\u00d6\5\r\u00d9\n\r\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\7\20\u00e8\n\20\f\20"+
		"\16\20\u00eb\13\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3"+
		"\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3"+
		"\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3"+
		"\33\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\""+
		"\3\"\3#\3#\3$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3)\3)\3)\3*\3*\3"+
		"*\3+\3+\3,\3,\3-\3-\3-\3.\3.\7.\u0150\n.\f.\16.\u0153\13.\3/\6/\u0156"+
		"\n/\r/\16/\u0157\3/\3/\3\60\3\60\5\60\u015e\n\60\3\60\3\60\3\60\5\60\u0163"+
		"\n\60\6\60\u0165\n\60\r\60\16\60\u0166\3\60\3\60\3\61\7\61\u016c\n\61"+
		"\f\61\16\61\u016f\13\61\3\62\3\62\3\62\3\62\7\62\u0175\n\62\f\62\16\62"+
		"\u0178\13\62\3\62\3\62\3\63\3\63\3s\2\64\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\2\31\2\33\2\35\r\37\16!\17#\20%\21\'\22)\23+\24-\25"+
		"/\26\61\27\63\30\65\31\67\329\33;\34=\35?\36A\37C E!G\"I#K$M%O&Q\'S(U"+
		")W*Y+[,]-_.a\2c/e\60\3\2\13\4\2\f\f\17\17\4\2GGgg\3\2\62;\n\2$$))^^dd"+
		"hhppttvv\6\2\f\f\17\17$$^^\5\2C\\aac|\6\2\62;C\\aac|\5\2\13\f\17\17\""+
		"\"\3\2$$\2\u0192\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2"+
		"\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2"+
		"\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2"+
		"\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y"+
		"\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\3g\3\2"+
		"\2\2\5i\3\2\2\2\7k\3\2\2\2\t\u0081\3\2\2\2\13\u008e\3\2\2\2\r\u0090\3"+
		"\2\2\2\17\u0098\3\2\2\2\21\u009d\3\2\2\2\23\u00a4\3\2\2\2\25\u00b8\3\2"+
		"\2\2\27\u00ba\3\2\2\2\31\u00be\3\2\2\2\33\u00da\3\2\2\2\35\u00dc\3\2\2"+
		"\2\37\u00e3\3\2\2\2!\u00ef\3\2\2\2#\u00f4\3\2\2\2%\u00f6\3\2\2\2\'\u00f8"+
		"\3\2\2\2)\u00fa\3\2\2\2+\u00fc\3\2\2\2-\u00fe\3\2\2\2/\u0101\3\2\2\2\61"+
		"\u0106\3\2\2\2\63\u010a\3\2\2\2\65\u0110\3\2\2\2\67\u0113\3\2\2\29\u0119"+
		"\3\2\2\2;\u0122\3\2\2\2=\u0129\3\2\2\2?\u012b\3\2\2\2A\u012d\3\2\2\2C"+
		"\u012f\3\2\2\2E\u0131\3\2\2\2G\u0133\3\2\2\2I\u0135\3\2\2\2K\u0138\3\2"+
		"\2\2M\u013b\3\2\2\2O\u013e\3\2\2\2Q\u0140\3\2\2\2S\u0143\3\2\2\2U\u0146"+
		"\3\2\2\2W\u0148\3\2\2\2Y\u014a\3\2\2\2[\u014d\3\2\2\2]\u0155\3\2\2\2_"+
		"\u015b\3\2\2\2a\u016d\3\2\2\2c\u0170\3\2\2\2e\u017b\3\2\2\2gh\7]\2\2h"+
		"\4\3\2\2\2ij\7_\2\2j\6\3\2\2\2kl\7.\2\2l\b\3\2\2\2mn\7\61\2\2no\7,\2\2"+
		"os\3\2\2\2pr\13\2\2\2qp\3\2\2\2ru\3\2\2\2st\3\2\2\2sq\3\2\2\2tv\3\2\2"+
		"\2us\3\2\2\2vw\7,\2\2w\u0082\7\61\2\2xy\7\61\2\2yz\7\61\2\2z~\3\2\2\2"+
		"{}\n\2\2\2|{\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0082\3"+
		"\2\2\2\u0080~\3\2\2\2\u0081m\3\2\2\2\u0081x\3\2\2\2\u0082\u0083\3\2\2"+
		"\2\u0083\u0084\b\5\2\2\u0084\n\3\2\2\2\u0085\u0086\7v\2\2\u0086\u0087"+
		"\7t\2\2\u0087\u0088\7w\2\2\u0088\u008f\7g\2\2\u0089\u008a\7h\2\2\u008a"+
		"\u008b\7c\2\2\u008b\u008c\7n\2\2\u008c\u008d\7u\2\2\u008d\u008f\7g\2\2"+
		"\u008e\u0085\3\2\2\2\u008e\u0089\3\2\2\2\u008f\f\3\2\2\2\u0090\u0091\7"+
		"d\2\2\u0091\u0092\7q\2\2\u0092\u0093\7q\2\2\u0093\u0094\7n\2\2\u0094\u0095"+
		"\7g\2\2\u0095\u0096\7c\2\2\u0096\u0097\7p\2\2\u0097\16\3\2\2\2\u0098\u0099"+
		"\7k\2\2\u0099\u009a\7p\2\2\u009a\u009b\7v\2\2\u009b\20\3\2\2\2\u009c\u009e"+
		"\7/\2\2\u009d\u009c\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u00a0\3\2\2\2\u009f"+
		"\u00a1\5\33\16\2\u00a0\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a0\3"+
		"\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\22\3\2\2\2\u00a4\u00a5\7h\2\2\u00a5\u00a6"+
		"\7n\2\2\u00a6\u00a7\7q\2\2\u00a7\u00a8\7c\2\2\u00a8\u00a9\7v\2\2\u00a9"+
		"\24\3\2\2\2\u00aa\u00ac\5\31\r\2\u00ab\u00ad\5\27\f\2\u00ac\u00ab\3\2"+
		"\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00b9\3\2\2\2\u00ae\u00b0\7/\2\2\u00af"+
		"\u00ae\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b2\3\2\2\2\u00b1\u00b3\5\33"+
		"\16\2\u00b2\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4"+
		"\u00b5\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\5\27\f\2\u00b7\u00b9\3"+
		"\2\2\2\u00b8\u00aa\3\2\2\2\u00b8\u00af\3\2\2\2\u00b9\26\3\2\2\2\u00ba"+
		"\u00bb\t\3\2\2\u00bb\u00bc\5\21\t\2\u00bc\30\3\2\2\2\u00bd\u00bf\7/\2"+
		"\2\u00be\u00bd\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00d8\3\2\2\2\u00c0\u00c2"+
		"\5\33\16\2\u00c1\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c1\3\2\2\2"+
		"\u00c3\u00c4\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c9\7\60\2\2\u00c6\u00c8"+
		"\5\33\16\2\u00c7\u00c6\3\2\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00c7\3\2\2\2"+
		"\u00c9\u00ca\3\2\2\2\u00ca\u00d9\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc\u00ce"+
		"\5\33\16\2\u00cd\u00cc\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2"+
		"\u00cf\u00d0\3\2\2\2\u00d0\u00d2\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00d4"+
		"\7\60\2\2\u00d3\u00d5\5\33\16\2\u00d4\u00d3\3\2\2\2\u00d5\u00d6\3\2\2"+
		"\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d9\3\2\2\2\u00d8\u00c1"+
		"\3\2\2\2\u00d8\u00cf\3\2\2\2\u00d9\32\3\2\2\2\u00da\u00db\t\4\2\2\u00db"+
		"\34\3\2\2\2\u00dc\u00dd\7u\2\2\u00dd\u00de\7v\2\2\u00de\u00df\7t\2\2\u00df"+
		"\u00e0\7k\2\2\u00e0\u00e1\7p\2\2\u00e1\u00e2\7i\2\2\u00e2\36\3\2\2\2\u00e3"+
		"\u00e9\7$\2\2\u00e4\u00e5\7^\2\2\u00e5\u00e8\t\5\2\2\u00e6\u00e8\n\6\2"+
		"\2\u00e7\u00e4\3\2\2\2\u00e7\u00e6\3\2\2\2\u00e8\u00eb\3\2\2\2\u00e9\u00e7"+
		"\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ec\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec"+
		"\u00ed\7$\2\2\u00ed\u00ee\b\20\3\2\u00ee \3\2\2\2\u00ef\u00f0\7x\2\2\u00f0"+
		"\u00f1\7q\2\2\u00f1\u00f2\7k\2\2\u00f2\u00f3\7f\2\2\u00f3\"\3\2\2\2\u00f4"+
		"\u00f5\7*\2\2\u00f5$\3\2\2\2\u00f6\u00f7\7+\2\2\u00f7&\3\2\2\2\u00f8\u00f9"+
		"\7}\2\2\u00f9(\3\2\2\2\u00fa\u00fb\7\177\2\2\u00fb*\3\2\2\2\u00fc\u00fd"+
		"\7=\2\2\u00fd,\3\2\2\2\u00fe\u00ff\7k\2\2\u00ff\u0100\7h\2\2\u0100.\3"+
		"\2\2\2\u0101\u0102\7g\2\2\u0102\u0103\7n\2\2\u0103\u0104\7u\2\2\u0104"+
		"\u0105\7g\2\2\u0105\60\3\2\2\2\u0106\u0107\7h\2\2\u0107\u0108\7q\2\2\u0108"+
		"\u0109\7t\2\2\u0109\62\3\2\2\2\u010a\u010b\7y\2\2\u010b\u010c\7j\2\2\u010c"+
		"\u010d\7k\2\2\u010d\u010e\7n\2\2\u010e\u010f\7g\2\2\u010f\64\3\2\2\2\u0110"+
		"\u0111\7f\2\2\u0111\u0112\7q\2\2\u0112\66\3\2\2\2\u0113\u0114\7d\2\2\u0114"+
		"\u0115\7t\2\2\u0115\u0116\7g\2\2\u0116\u0117\7c\2\2\u0117\u0118\7m\2\2"+
		"\u01188\3\2\2\2\u0119\u011a\7e\2\2\u011a\u011b\7q\2\2\u011b\u011c\7p\2"+
		"\2\u011c\u011d\7v\2\2\u011d\u011e\7k\2\2\u011e\u011f\7p\2\2\u011f\u0120"+
		"\7w\2\2\u0120\u0121\7g\2\2\u0121:\3\2\2\2\u0122\u0123\7t\2\2\u0123\u0124"+
		"\7g\2\2\u0124\u0125\7v\2\2\u0125\u0126\7w\2\2\u0126\u0127\7t\2\2\u0127"+
		"\u0128\7p\2\2\u0128<\3\2\2\2\u0129\u012a\7-\2\2\u012a>\3\2\2\2\u012b\u012c"+
		"\7/\2\2\u012c@\3\2\2\2\u012d\u012e\7,\2\2\u012eB\3\2\2\2\u012f\u0130\7"+
		"\61\2\2\u0130D\3\2\2\2\u0131\u0132\7>\2\2\u0132F\3\2\2\2\u0133\u0134\7"+
		"@\2\2\u0134H\3\2\2\2\u0135\u0136\7>\2\2\u0136\u0137\7?\2\2\u0137J\3\2"+
		"\2\2\u0138\u0139\7@\2\2\u0139\u013a\7?\2\2\u013aL\3\2\2\2\u013b\u013c"+
		"\7?\2\2\u013c\u013d\7?\2\2\u013dN\3\2\2\2\u013e\u013f\7#\2\2\u013fP\3"+
		"\2\2\2\u0140\u0141\7~\2\2\u0141\u0142\7~\2\2\u0142R\3\2\2\2\u0143\u0144"+
		"\7#\2\2\u0144\u0145\7?\2\2\u0145T\3\2\2\2\u0146\u0147\7?\2\2\u0147V\3"+
		"\2\2\2\u0148\u0149\7\'\2\2\u0149X\3\2\2\2\u014a\u014b\7(\2\2\u014b\u014c"+
		"\7(\2\2\u014cZ\3\2\2\2\u014d\u0151\t\7\2\2\u014e\u0150\t\b\2\2\u014f\u014e"+
		"\3\2\2\2\u0150\u0153\3\2\2\2\u0151\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152"+
		"\\\3\2\2\2\u0153\u0151\3\2\2\2\u0154\u0156\t\t\2\2\u0155\u0154\3\2\2\2"+
		"\u0156\u0157\3\2\2\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u0159"+
		"\3\2\2\2\u0159\u015a\b/\2\2\u015a^\3\2\2\2\u015b\u0164\7$\2\2\u015c\u015e"+
		"\5a\61\2\u015d\u015c\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u015f\3\2\2\2\u015f"+
		"\u0160\7^\2\2\u0160\u0162\n\5\2\2\u0161\u0163\5a\61\2\u0162\u0161\3\2"+
		"\2\2\u0162\u0163\3\2\2\2\u0163\u0165\3\2\2\2\u0164\u015d\3\2\2\2\u0165"+
		"\u0166\3\2\2\2\u0166\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0168\3\2"+
		"\2\2\u0168\u0169\7$\2\2\u0169`\3\2\2\2\u016a\u016c\n\2\2\2\u016b\u016a"+
		"\3\2\2\2\u016c\u016f\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016e"+
		"b\3\2\2\2\u016f\u016d\3\2\2\2\u0170\u0176\7$\2\2\u0171\u0172\7^\2\2\u0172"+
		"\u0175\7$\2\2\u0173\u0175\n\n\2\2\u0174\u0171\3\2\2\2\u0174\u0173\3\2"+
		"\2\2\u0175\u0178\3\2\2\2\u0176\u0174\3\2\2\2\u0176\u0177\3\2\2\2\u0177"+
		"\u0179\3\2\2\2\u0178\u0176\3\2\2\2\u0179\u017a\7\2\2\3\u017ad\3\2\2\2"+
		"\u017b\u017c\13\2\2\2\u017cf\3\2\2\2\35\2s~\u0081\u008e\u009d\u00a2\u00ac"+
		"\u00af\u00b4\u00b8\u00be\u00c3\u00c9\u00cf\u00d6\u00d8\u00e7\u00e9\u0151"+
		"\u0157\u015d\u0162\u0166\u016d\u0174\u0176\4\b\2\2\3\20\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}