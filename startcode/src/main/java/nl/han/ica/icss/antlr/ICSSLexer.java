// Generated from C:/Users/stijn/OneDrive/Documents/APP/icss2022-sep/startcode/src/main/antlr4/nl/han/ica/icss/parser/ICSS.g4 by ANTLR 4.13.2
package nl.han.ica.icss.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ICSSLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, IF=5, ELSE=6, BOX_BRACKET_OPEN=7, BOX_BRACKET_CLOSE=8, 
		TRUE=9, FALSE=10, PIXELSIZE=11, PERCENTAGE=12, SCALAR=13, COLOR=14, ID_IDENT=15, 
		CLASS_IDENT=16, LOWER_IDENT=17, CAPITAL_IDENT=18, WS=19, OPEN_BRACE=20, 
		CLOSE_BRACE=21, SEMICOLON=22, COLON=23, PLUS=24, MIN=25, MUL=26, ASSIGNMENT_OPERATOR=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "IF", "ELSE", "BOX_BRACKET_OPEN", "BOX_BRACKET_CLOSE", 
			"TRUE", "FALSE", "PIXELSIZE", "PERCENTAGE", "SCALAR", "COLOR", "ID_IDENT", 
			"CLASS_IDENT", "LOWER_IDENT", "CAPITAL_IDENT", "WS", "OPEN_BRACE", "CLOSE_BRACE", 
			"SEMICOLON", "COLON", "PLUS", "MIN", "MUL", "ASSIGNMENT_OPERATOR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'color'", "'background-color'", "'width'", "'height'", "'if'", 
			"'else'", "'['", "']'", "'TRUE'", "'FALSE'", null, null, null, null, 
			null, null, null, null, null, "'{'", "'}'", "';'", "':'", "'+'", "'-'", 
			"'*'", "':='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "IF", "ELSE", "BOX_BRACKET_OPEN", "BOX_BRACKET_CLOSE", 
			"TRUE", "FALSE", "PIXELSIZE", "PERCENTAGE", "SCALAR", "COLOR", "ID_IDENT", 
			"CLASS_IDENT", "LOWER_IDENT", "CAPITAL_IDENT", "WS", "OPEN_BRACE", "CLOSE_BRACE", 
			"SEMICOLON", "COLON", "PLUS", "MIN", "MUL", "ASSIGNMENT_OPERATOR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public ICSSLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ICSS.g4"; }

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
		"\u0004\u0000\u001b\u00c0\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\n\u0004\nt\b\n\u000b\n\f\nu\u0001\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0004\u000b|\b\u000b\u000b\u000b\f\u000b}\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0004\f\u0083\b\f\u000b\f\f\f\u0084\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0004"+
		"\u000e\u0091\b\u000e\u000b\u000e\f\u000e\u0092\u0001\u000f\u0001\u000f"+
		"\u0004\u000f\u0097\b\u000f\u000b\u000f\f\u000f\u0098\u0001\u0010\u0001"+
		"\u0010\u0005\u0010\u009d\b\u0010\n\u0010\f\u0010\u00a0\t\u0010\u0001\u0011"+
		"\u0001\u0011\u0005\u0011\u00a4\b\u0011\n\u0011\f\u0011\u00a7\t\u0011\u0001"+
		"\u0012\u0004\u0012\u00aa\b\u0012\u000b\u0012\f\u0012\u00ab\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018"+
		"\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0000\u0000\u001b\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005"+
		"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019"+
		"\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015"+
		"+\u0016-\u0017/\u00181\u00193\u001a5\u001b\u0001\u0000\u0007\u0001\u0000"+
		"09\u0002\u000009af\u0003\u0000--09az\u0001\u0000az\u0001\u0000AZ\u0004"+
		"\u000009AZ__az\u0003\u0000\t\n\r\r  \u00c7\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000"+
		"\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001"+
		"\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000"+
		"\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000"+
		"\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001"+
		"\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000"+
		"\u0000\u0000\u00017\u0001\u0000\u0000\u0000\u0003=\u0001\u0000\u0000\u0000"+
		"\u0005N\u0001\u0000\u0000\u0000\u0007T\u0001\u0000\u0000\u0000\t[\u0001"+
		"\u0000\u0000\u0000\u000b^\u0001\u0000\u0000\u0000\rc\u0001\u0000\u0000"+
		"\u0000\u000fe\u0001\u0000\u0000\u0000\u0011g\u0001\u0000\u0000\u0000\u0013"+
		"l\u0001\u0000\u0000\u0000\u0015s\u0001\u0000\u0000\u0000\u0017{\u0001"+
		"\u0000\u0000\u0000\u0019\u0082\u0001\u0000\u0000\u0000\u001b\u0086\u0001"+
		"\u0000\u0000\u0000\u001d\u008e\u0001\u0000\u0000\u0000\u001f\u0094\u0001"+
		"\u0000\u0000\u0000!\u009a\u0001\u0000\u0000\u0000#\u00a1\u0001\u0000\u0000"+
		"\u0000%\u00a9\u0001\u0000\u0000\u0000\'\u00af\u0001\u0000\u0000\u0000"+
		")\u00b1\u0001\u0000\u0000\u0000+\u00b3\u0001\u0000\u0000\u0000-\u00b5"+
		"\u0001\u0000\u0000\u0000/\u00b7\u0001\u0000\u0000\u00001\u00b9\u0001\u0000"+
		"\u0000\u00003\u00bb\u0001\u0000\u0000\u00005\u00bd\u0001\u0000\u0000\u0000"+
		"78\u0005c\u0000\u000089\u0005o\u0000\u00009:\u0005l\u0000\u0000:;\u0005"+
		"o\u0000\u0000;<\u0005r\u0000\u0000<\u0002\u0001\u0000\u0000\u0000=>\u0005"+
		"b\u0000\u0000>?\u0005a\u0000\u0000?@\u0005c\u0000\u0000@A\u0005k\u0000"+
		"\u0000AB\u0005g\u0000\u0000BC\u0005r\u0000\u0000CD\u0005o\u0000\u0000"+
		"DE\u0005u\u0000\u0000EF\u0005n\u0000\u0000FG\u0005d\u0000\u0000GH\u0005"+
		"-\u0000\u0000HI\u0005c\u0000\u0000IJ\u0005o\u0000\u0000JK\u0005l\u0000"+
		"\u0000KL\u0005o\u0000\u0000LM\u0005r\u0000\u0000M\u0004\u0001\u0000\u0000"+
		"\u0000NO\u0005w\u0000\u0000OP\u0005i\u0000\u0000PQ\u0005d\u0000\u0000"+
		"QR\u0005t\u0000\u0000RS\u0005h\u0000\u0000S\u0006\u0001\u0000\u0000\u0000"+
		"TU\u0005h\u0000\u0000UV\u0005e\u0000\u0000VW\u0005i\u0000\u0000WX\u0005"+
		"g\u0000\u0000XY\u0005h\u0000\u0000YZ\u0005t\u0000\u0000Z\b\u0001\u0000"+
		"\u0000\u0000[\\\u0005i\u0000\u0000\\]\u0005f\u0000\u0000]\n\u0001\u0000"+
		"\u0000\u0000^_\u0005e\u0000\u0000_`\u0005l\u0000\u0000`a\u0005s\u0000"+
		"\u0000ab\u0005e\u0000\u0000b\f\u0001\u0000\u0000\u0000cd\u0005[\u0000"+
		"\u0000d\u000e\u0001\u0000\u0000\u0000ef\u0005]\u0000\u0000f\u0010\u0001"+
		"\u0000\u0000\u0000gh\u0005T\u0000\u0000hi\u0005R\u0000\u0000ij\u0005U"+
		"\u0000\u0000jk\u0005E\u0000\u0000k\u0012\u0001\u0000\u0000\u0000lm\u0005"+
		"F\u0000\u0000mn\u0005A\u0000\u0000no\u0005L\u0000\u0000op\u0005S\u0000"+
		"\u0000pq\u0005E\u0000\u0000q\u0014\u0001\u0000\u0000\u0000rt\u0007\u0000"+
		"\u0000\u0000sr\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000us\u0001"+
		"\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000"+
		"wx\u0005p\u0000\u0000xy\u0005x\u0000\u0000y\u0016\u0001\u0000\u0000\u0000"+
		"z|\u0007\u0000\u0000\u0000{z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000"+
		"\u0000}{\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~\u007f\u0001"+
		"\u0000\u0000\u0000\u007f\u0080\u0005%\u0000\u0000\u0080\u0018\u0001\u0000"+
		"\u0000\u0000\u0081\u0083\u0007\u0000\u0000\u0000\u0082\u0081\u0001\u0000"+
		"\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0082\u0001\u0000"+
		"\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u001a\u0001\u0000"+
		"\u0000\u0000\u0086\u0087\u0005#\u0000\u0000\u0087\u0088\u0007\u0001\u0000"+
		"\u0000\u0088\u0089\u0007\u0001\u0000\u0000\u0089\u008a\u0007\u0001\u0000"+
		"\u0000\u008a\u008b\u0007\u0001\u0000\u0000\u008b\u008c\u0007\u0001\u0000"+
		"\u0000\u008c\u008d\u0007\u0001\u0000\u0000\u008d\u001c\u0001\u0000\u0000"+
		"\u0000\u008e\u0090\u0005#\u0000\u0000\u008f\u0091\u0007\u0002\u0000\u0000"+
		"\u0090\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000"+
		"\u0092\u0090\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000"+
		"\u0093\u001e\u0001\u0000\u0000\u0000\u0094\u0096\u0005.\u0000\u0000\u0095"+
		"\u0097\u0007\u0002\u0000\u0000\u0096\u0095\u0001\u0000\u0000\u0000\u0097"+
		"\u0098\u0001\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000\u0098"+
		"\u0099\u0001\u0000\u0000\u0000\u0099 \u0001\u0000\u0000\u0000\u009a\u009e"+
		"\u0007\u0003\u0000\u0000\u009b\u009d\u0007\u0002\u0000\u0000\u009c\u009b"+
		"\u0001\u0000\u0000\u0000\u009d\u00a0\u0001\u0000\u0000\u0000\u009e\u009c"+
		"\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000\u0000\u009f\"\u0001"+
		"\u0000\u0000\u0000\u00a0\u009e\u0001\u0000\u0000\u0000\u00a1\u00a5\u0007"+
		"\u0004\u0000\u0000\u00a2\u00a4\u0007\u0005\u0000\u0000\u00a3\u00a2\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a7\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001"+
		"\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6$\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a8\u00aa\u0007\u0006"+
		"\u0000\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000"+
		"\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000"+
		"\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad\u00ae\u0006\u0012"+
		"\u0000\u0000\u00ae&\u0001\u0000\u0000\u0000\u00af\u00b0\u0005{\u0000\u0000"+
		"\u00b0(\u0001\u0000\u0000\u0000\u00b1\u00b2\u0005}\u0000\u0000\u00b2*"+
		"\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005;\u0000\u0000\u00b4,\u0001\u0000"+
		"\u0000\u0000\u00b5\u00b6\u0005:\u0000\u0000\u00b6.\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b8\u0005+\u0000\u0000\u00b80\u0001\u0000\u0000\u0000\u00b9\u00ba"+
		"\u0005-\u0000\u0000\u00ba2\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005*"+
		"\u0000\u0000\u00bc4\u0001\u0000\u0000\u0000\u00bd\u00be\u0005:\u0000\u0000"+
		"\u00be\u00bf\u0005=\u0000\u0000\u00bf6\u0001\u0000\u0000\u0000\t\u0000"+
		"u}\u0084\u0092\u0098\u009e\u00a5\u00ab\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}