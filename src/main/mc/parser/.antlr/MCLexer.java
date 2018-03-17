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
		BREAK=24, CONTINUE=25, RETURN=26, REPEAT=27, UNTIL=28, ADD=29, SUB=30, 
		MUL=31, DIV=32, LT=33, GT=34, LEQ=35, GEQ=36, EQ=37, NOT=38, OR=39, NEQ=40, 
		ASSIGN=41, MOL=42, AND=43, ID=44, WS=45, ILLEGAL_ESCAPE=46, UNCLOSE_STRING=47, 
		ERROR_CHAR=48;
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
		"DO", "BREAK", "CONTINUE", "RETURN", "REPEAT", "UNTIL", "ADD", "SUB", 
		"MUL", "DIV", "LT", "GT", "LEQ", "GEQ", "EQ", "NOT", "OR", "NEQ", "ASSIGN", 
		"MOL", "AND", "ID", "WS", "ILLEGAL_ESCAPE", "NOTESCAPE", "UNCLOSE_STRING", 
		"ERROR_CHAR"
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\62\u018a\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\7\5v\n\5\f\5\16"+
		"\5y\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u0081\n\5\f\5\16\5\u0084\13\5\5\5"+
		"\u0086\n\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0093\n\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\5\t\u00a2\n\t\3\t\6"+
		"\t\u00a5\n\t\r\t\16\t\u00a6\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\5\13\u00b1"+
		"\n\13\3\13\5\13\u00b4\n\13\3\13\6\13\u00b7\n\13\r\13\16\13\u00b8\3\13"+
		"\3\13\5\13\u00bd\n\13\3\f\3\f\3\f\3\r\6\r\u00c3\n\r\r\r\16\r\u00c4\3\r"+
		"\3\r\7\r\u00c9\n\r\f\r\16\r\u00cc\13\r\3\r\7\r\u00cf\n\r\f\r\16\r\u00d2"+
		"\13\r\3\r\3\r\6\r\u00d6\n\r\r\r\16\r\u00d7\5\r\u00da\n\r\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\7\20\u00e9\n\20\f\20"+
		"\16\20\u00ec\13\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\23\3"+
		"\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3"+
		"\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3"+
		"\37\3 \3 \3 \3 \3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3"+
		"\'\3(\3(\3(\3)\3)\3)\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3.\3.\3/\3/\3/\3\60"+
		"\3\60\7\60\u015d\n\60\f\60\16\60\u0160\13\60\3\61\6\61\u0163\n\61\r\61"+
		"\16\61\u0164\3\61\3\61\3\62\3\62\5\62\u016b\n\62\3\62\3\62\3\62\5\62\u0170"+
		"\n\62\6\62\u0172\n\62\r\62\16\62\u0173\3\62\3\62\3\63\7\63\u0179\n\63"+
		"\f\63\16\63\u017c\13\63\3\64\3\64\3\64\3\64\7\64\u0182\n\64\f\64\16\64"+
		"\u0185\13\64\3\64\3\64\3\65\3\65\3w\2\66\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\2\31\2\33\2\35\r\37\16!\17#\20%\21\'\22)\23+\24-\25"+
		"/\26\61\27\63\30\65\31\67\329\33;\34=\35?\36A\37C E!G\"I#K$M%O&Q\'S(U"+
		")W*Y+[,]-_.a/c\60e\2g\61i\62\3\2\13\4\2\f\f\17\17\4\2GGgg\3\2\62;\n\2"+
		"$$))^^ddhhppttvv\6\2\f\f\17\17$$^^\5\2C\\aac|\6\2\62;C\\aac|\5\2\13\f"+
		"\17\17\"\"\3\2$$\2\u019e\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2"+
		"\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2"+
		"\2g\3\2\2\2\2i\3\2\2\2\3k\3\2\2\2\5m\3\2\2\2\7o\3\2\2\2\t\u0085\3\2\2"+
		"\2\13\u0092\3\2\2\2\r\u0094\3\2\2\2\17\u009c\3\2\2\2\21\u00a1\3\2\2\2"+
		"\23\u00a8\3\2\2\2\25\u00bc\3\2\2\2\27\u00be\3\2\2\2\31\u00d9\3\2\2\2\33"+
		"\u00db\3\2\2\2\35\u00dd\3\2\2\2\37\u00e4\3\2\2\2!\u00ef\3\2\2\2#\u00f4"+
		"\3\2\2\2%\u00f6\3\2\2\2\'\u00f8\3\2\2\2)\u00fa\3\2\2\2+\u00fc\3\2\2\2"+
		"-\u00fe\3\2\2\2/\u0101\3\2\2\2\61\u0106\3\2\2\2\63\u010a\3\2\2\2\65\u0110"+
		"\3\2\2\2\67\u0113\3\2\2\29\u0119\3\2\2\2;\u0122\3\2\2\2=\u0129\3\2\2\2"+
		"?\u0130\3\2\2\2A\u0136\3\2\2\2C\u0138\3\2\2\2E\u013a\3\2\2\2G\u013c\3"+
		"\2\2\2I\u013e\3\2\2\2K\u0140\3\2\2\2M\u0142\3\2\2\2O\u0145\3\2\2\2Q\u0148"+
		"\3\2\2\2S\u014b\3\2\2\2U\u014d\3\2\2\2W\u0150\3\2\2\2Y\u0153\3\2\2\2["+
		"\u0155\3\2\2\2]\u0157\3\2\2\2_\u015a\3\2\2\2a\u0162\3\2\2\2c\u0168\3\2"+
		"\2\2e\u017a\3\2\2\2g\u017d\3\2\2\2i\u0188\3\2\2\2kl\7]\2\2l\4\3\2\2\2"+
		"mn\7_\2\2n\6\3\2\2\2op\7.\2\2p\b\3\2\2\2qr\7\61\2\2rs\7,\2\2sw\3\2\2\2"+
		"tv\13\2\2\2ut\3\2\2\2vy\3\2\2\2wx\3\2\2\2wu\3\2\2\2xz\3\2\2\2yw\3\2\2"+
		"\2z{\7,\2\2{\u0086\7\61\2\2|}\7\61\2\2}~\7\61\2\2~\u0082\3\2\2\2\177\u0081"+
		"\n\2\2\2\u0080\177\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082"+
		"\u0083\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0085q\3\2\2\2"+
		"\u0085|\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\b\5\2\2\u0088\n\3\2\2"+
		"\2\u0089\u008a\7v\2\2\u008a\u008b\7t\2\2\u008b\u008c\7w\2\2\u008c\u0093"+
		"\7g\2\2\u008d\u008e\7h\2\2\u008e\u008f\7c\2\2\u008f\u0090\7n\2\2\u0090"+
		"\u0091\7u\2\2\u0091\u0093\7g\2\2\u0092\u0089\3\2\2\2\u0092\u008d\3\2\2"+
		"\2\u0093\f\3\2\2\2\u0094\u0095\7d\2\2\u0095\u0096\7q\2\2\u0096\u0097\7"+
		"q\2\2\u0097\u0098\7n\2\2\u0098\u0099\7g\2\2\u0099\u009a\7c\2\2\u009a\u009b"+
		"\7p\2\2\u009b\16\3\2\2\2\u009c\u009d\7k\2\2\u009d\u009e\7p\2\2\u009e\u009f"+
		"\7v\2\2\u009f\20\3\2\2\2\u00a0\u00a2\7/\2\2\u00a1\u00a0\3\2\2\2\u00a1"+
		"\u00a2\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3\u00a5\5\33\16\2\u00a4\u00a3\3"+
		"\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\22\3\2\2\2\u00a8\u00a9\7h\2\2\u00a9\u00aa\7n\2\2\u00aa\u00ab\7q\2\2\u00ab"+
		"\u00ac\7c\2\2\u00ac\u00ad\7v\2\2\u00ad\24\3\2\2\2\u00ae\u00b0\5\31\r\2"+
		"\u00af\u00b1\5\27\f\2\u00b0\u00af\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00bd"+
		"\3\2\2\2\u00b2\u00b4\7/\2\2\u00b3\u00b2\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b6\3\2\2\2\u00b5\u00b7\5\33\16\2\u00b6\u00b5\3\2\2\2\u00b7\u00b8\3"+
		"\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba"+
		"\u00bb\5\27\f\2\u00bb\u00bd\3\2\2\2\u00bc\u00ae\3\2\2\2\u00bc\u00b3\3"+
		"\2\2\2\u00bd\26\3\2\2\2\u00be\u00bf\t\3\2\2\u00bf\u00c0\5\21\t\2\u00c0"+
		"\30\3\2\2\2\u00c1\u00c3\5\33\16\2\u00c2\u00c1\3\2\2\2\u00c3\u00c4\3\2"+
		"\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6"+
		"\u00ca\7\60\2\2\u00c7\u00c9\5\33\16\2\u00c8\u00c7\3\2\2\2\u00c9\u00cc"+
		"\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00da\3\2\2\2\u00cc"+
		"\u00ca\3\2\2\2\u00cd\u00cf\5\33\16\2\u00ce\u00cd\3\2\2\2\u00cf\u00d2\3"+
		"\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d3\3\2\2\2\u00d2"+
		"\u00d0\3\2\2\2\u00d3\u00d5\7\60\2\2\u00d4\u00d6\5\33\16\2\u00d5\u00d4"+
		"\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8"+
		"\u00da\3\2\2\2\u00d9\u00c2\3\2\2\2\u00d9\u00d0\3\2\2\2\u00da\32\3\2\2"+
		"\2\u00db\u00dc\t\4\2\2\u00dc\34\3\2\2\2\u00dd\u00de\7u\2\2\u00de\u00df"+
		"\7v\2\2\u00df\u00e0\7t\2\2\u00e0\u00e1\7k\2\2\u00e1\u00e2\7p\2\2\u00e2"+
		"\u00e3\7i\2\2\u00e3\36\3\2\2\2\u00e4\u00ea\7$\2\2\u00e5\u00e6\7^\2\2\u00e6"+
		"\u00e9\t\5\2\2\u00e7\u00e9\n\6\2\2\u00e8\u00e5\3\2\2\2\u00e8\u00e7\3\2"+
		"\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb"+
		"\u00ed\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00ee\7$\2\2\u00ee \3\2\2\2\u00ef"+
		"\u00f0\7x\2\2\u00f0\u00f1\7q\2\2\u00f1\u00f2\7k\2\2\u00f2\u00f3\7f\2\2"+
		"\u00f3\"\3\2\2\2\u00f4\u00f5\7*\2\2\u00f5$\3\2\2\2\u00f6\u00f7\7+\2\2"+
		"\u00f7&\3\2\2\2\u00f8\u00f9\7}\2\2\u00f9(\3\2\2\2\u00fa\u00fb\7\177\2"+
		"\2\u00fb*\3\2\2\2\u00fc\u00fd\7=\2\2\u00fd,\3\2\2\2\u00fe\u00ff\7k\2\2"+
		"\u00ff\u0100\7h\2\2\u0100.\3\2\2\2\u0101\u0102\7g\2\2\u0102\u0103\7n\2"+
		"\2\u0103\u0104\7u\2\2\u0104\u0105\7g\2\2\u0105\60\3\2\2\2\u0106\u0107"+
		"\7h\2\2\u0107\u0108\7q\2\2\u0108\u0109\7t\2\2\u0109\62\3\2\2\2\u010a\u010b"+
		"\7y\2\2\u010b\u010c\7j\2\2\u010c\u010d\7k\2\2\u010d\u010e\7n\2\2\u010e"+
		"\u010f\7g\2\2\u010f\64\3\2\2\2\u0110\u0111\7f\2\2\u0111\u0112\7q\2\2\u0112"+
		"\66\3\2\2\2\u0113\u0114\7d\2\2\u0114\u0115\7t\2\2\u0115\u0116\7g\2\2\u0116"+
		"\u0117\7c\2\2\u0117\u0118\7m\2\2\u01188\3\2\2\2\u0119\u011a\7e\2\2\u011a"+
		"\u011b\7q\2\2\u011b\u011c\7p\2\2\u011c\u011d\7v\2\2\u011d\u011e\7k\2\2"+
		"\u011e\u011f\7p\2\2\u011f\u0120\7w\2\2\u0120\u0121\7g\2\2\u0121:\3\2\2"+
		"\2\u0122\u0123\7t\2\2\u0123\u0124\7g\2\2\u0124\u0125\7v\2\2\u0125\u0126"+
		"\7w\2\2\u0126\u0127\7t\2\2\u0127\u0128\7p\2\2\u0128<\3\2\2\2\u0129\u012a"+
		"\7t\2\2\u012a\u012b\7g\2\2\u012b\u012c\7r\2\2\u012c\u012d\7g\2\2\u012d"+
		"\u012e\7c\2\2\u012e\u012f\7v\2\2\u012f>\3\2\2\2\u0130\u0131\7w\2\2\u0131"+
		"\u0132\7p\2\2\u0132\u0133\7v\2\2\u0133\u0134\7k\2\2\u0134\u0135\7n\2\2"+
		"\u0135@\3\2\2\2\u0136\u0137\7-\2\2\u0137B\3\2\2\2\u0138\u0139\7/\2\2\u0139"+
		"D\3\2\2\2\u013a\u013b\7,\2\2\u013bF\3\2\2\2\u013c\u013d\7\61\2\2\u013d"+
		"H\3\2\2\2\u013e\u013f\7>\2\2\u013fJ\3\2\2\2\u0140\u0141\7@\2\2\u0141L"+
		"\3\2\2\2\u0142\u0143\7>\2\2\u0143\u0144\7?\2\2\u0144N\3\2\2\2\u0145\u0146"+
		"\7@\2\2\u0146\u0147\7?\2\2\u0147P\3\2\2\2\u0148\u0149\7?\2\2\u0149\u014a"+
		"\7?\2\2\u014aR\3\2\2\2\u014b\u014c\7#\2\2\u014cT\3\2\2\2\u014d\u014e\7"+
		"~\2\2\u014e\u014f\7~\2\2\u014fV\3\2\2\2\u0150\u0151\7#\2\2\u0151\u0152"+
		"\7?\2\2\u0152X\3\2\2\2\u0153\u0154\7?\2\2\u0154Z\3\2\2\2\u0155\u0156\7"+
		"\'\2\2\u0156\\\3\2\2\2\u0157\u0158\7(\2\2\u0158\u0159\7(\2\2\u0159^\3"+
		"\2\2\2\u015a\u015e\t\7\2\2\u015b\u015d\t\b\2\2\u015c\u015b\3\2\2\2\u015d"+
		"\u0160\3\2\2\2\u015e\u015c\3\2\2\2\u015e\u015f\3\2\2\2\u015f`\3\2\2\2"+
		"\u0160\u015e\3\2\2\2\u0161\u0163\t\t\2\2\u0162\u0161\3\2\2\2\u0163\u0164"+
		"\3\2\2\2\u0164\u0162\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0166\3\2\2\2\u0166"+
		"\u0167\b\61\2\2\u0167b\3\2\2\2\u0168\u0171\7$\2\2\u0169\u016b\5e\63\2"+
		"\u016a\u0169\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016d"+
		"\7^\2\2\u016d\u016f\n\5\2\2\u016e\u0170\5e\63\2\u016f\u016e\3\2\2\2\u016f"+
		"\u0170\3\2\2\2\u0170\u0172\3\2\2\2\u0171\u016a\3\2\2\2\u0172\u0173\3\2"+
		"\2\2\u0173\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0175\3\2\2\2\u0175"+
		"\u0176\7$\2\2\u0176d\3\2\2\2\u0177\u0179\n\2\2\2\u0178\u0177\3\2\2\2\u0179"+
		"\u017c\3\2\2\2\u017a\u0178\3\2\2\2\u017a\u017b\3\2\2\2\u017bf\3\2\2\2"+
		"\u017c\u017a\3\2\2\2\u017d\u0183\7$\2\2\u017e\u017f\7^\2\2\u017f\u0182"+
		"\7$\2\2\u0180\u0182\n\n\2\2\u0181\u017e\3\2\2\2\u0181\u0180\3\2\2\2\u0182"+
		"\u0185\3\2\2\2\u0183\u0181\3\2\2\2\u0183\u0184\3\2\2\2\u0184\u0186\3\2"+
		"\2\2\u0185\u0183\3\2\2\2\u0186\u0187\7\2\2\3\u0187h\3\2\2\2\u0188\u0189"+
		"\13\2\2\2\u0189j\3\2\2\2\34\2w\u0082\u0085\u0092\u00a1\u00a6\u00b0\u00b3"+
		"\u00b8\u00bc\u00c4\u00ca\u00d0\u00d7\u00d9\u00e8\u00ea\u015e\u0164\u016a"+
		"\u016f\u0173\u017a\u0181\u0183\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}