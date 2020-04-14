/* The following code was generated by JFlex 1.7.0 tweaked for IntelliJ platform */

package nl.hannahsten.texifyidea.grammar;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import java.util.*;

import static nl.hannahsten.texifyidea.psi.LatexTypes.*;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>LatexLexer.flex</tt>
 */
public class LatexLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int INLINE_MATH = 2;
  public static final int INLINE_MATH_LATEX = 4;
  public static final int DISPLAY_MATH = 6;
  public static final int TEXT_INSIDE_INLINE_MATH = 8;
  public static final int NESTED_INLINE_MATH = 10;
  public static final int PREAMBLE_OPTION = 12;
  public static final int INLINE_VERBATIM_START = 14;
  public static final int INLINE_VERBATIM_PIPE = 16;
  public static final int INLINE_VERBATIM_EXCL_MARK = 18;
  public static final int INLINE_VERBATIM_QUOTES = 20;
  public static final int INLINE_VERBATIM_EQUALS = 22;
  public static final int POSSIBLE_VERBATIM_START = 24;
  public static final int VERBATIM_START = 26;
  public static final int POSSIBLE_VERBATIM_END = 28;
  public static final int VERBATIM = 30;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int[] ZZ_LEXSTATE = {
     0,  0,  1,  1,  2,  2,  3,  3,  4,  4,  5,  5,  6,  6,  7,  7, 
     8,  8,  9,  9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15
  };

  /** 
   * Translates characters to character classes
   * Chosen bits are [7, 7, 7]
   * Total runtime size is 1928 bytes
   */
  public static int ZZ_CMAP(int ch) {
    return ZZ_CMAP_A[(ZZ_CMAP_Y[ZZ_CMAP_Z[ch>>14]|((ch>>7)&0x7f)]<<7)|(ch&0x7f)];
  }

  /* The ZZ_CMAP_Z table has 68 entries */
  static final char[] ZZ_CMAP_Z = zzUnpackCMap(
    "\1\0\103\200");

  /* The ZZ_CMAP_Y table has 256 entries */
  static final char[] ZZ_CMAP_Y = zzUnpackCMap(
    "\1\0\1\1\53\2\1\3\22\2\1\4\37\2\1\3\237\2");

  /* The ZZ_CMAP_A table has 640 entries */
  static final char[] ZZ_CMAP_A = zzUnpackCMap(
    "\11\0\1\3\1\2\2\13\1\1\22\0\1\3\1\44\1\45\1\0\1\46\1\34\2\0\1\7\1\10\1\40"+
    "\21\0\1\47\1\36\1\47\1\0\1\24\32\22\1\4\1\6\1\5\3\0\1\32\1\14\1\30\1\21\1"+
    "\15\1\25\1\16\1\31\1\17\2\22\1\41\1\22\1\20\3\22\1\33\1\42\1\27\1\22\1\37"+
    "\1\22\1\26\2\22\1\11\1\43\1\12\7\0\1\23\32\0\1\35\337\0\1\35\177\0\13\35\35"+
    "\0\2\23\5\0\1\35\57\0\1\35\40\0");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\20\0\1\1\2\2\1\3\1\4\1\5\1\6\1\7"+
    "\1\10\1\11\1\5\1\12\1\5\1\13\1\14\1\15"+
    "\1\16\1\17\1\5\1\20\3\5\1\21\1\22\1\1"+
    "\1\23\1\24\1\25\1\26\1\27\1\21\1\30\1\31"+
    "\1\32\1\27\2\1\1\0\1\33\1\2\1\34\1\35"+
    "\7\33\2\12\1\33\1\20\1\36\2\30\2\32\1\0"+
    "\1\37\5\33\1\37\1\33\1\0\1\33\1\40\4\33"+
    "\1\41\2\33\1\42\1\33\1\43\1\44\1\33\1\42"+
    "\10\33\1\42\2\33\1\45";

  private static int [] zzUnpackAction() {
    int [] result = new int[112];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\50\0\120\0\170\0\240\0\310\0\360\0\u0118"+
    "\0\u0140\0\u0168\0\u0190\0\u01b8\0\u01e0\0\u0208\0\u0230\0\u0258"+
    "\0\u0280\0\u02a8\0\u02d0\0\u02f8\0\u02f8\0\u0320\0\u02f8\0\u02f8"+
    "\0\u02f8\0\u02f8\0\u0348\0\u0370\0\u02f8\0\u02f8\0\u0280\0\u02f8"+
    "\0\u02f8\0\u02f8\0\u0398\0\u02f8\0\u03c0\0\u03e8\0\u0410\0\u02f8"+
    "\0\u02f8\0\u02f8\0\u02f8\0\u02f8\0\u02f8\0\u02f8\0\u0348\0\u0348"+
    "\0\u0438\0\u02f8\0\u0460\0\u0488\0\u04b0\0\u04d8\0\u0500\0\u0348"+
    "\0\u0348\0\u0348\0\u0348\0\u0528\0\u0550\0\u0578\0\u05a0\0\u05c8"+
    "\0\u05f0\0\u0500\0\u0618\0\u0640\0\u0668\0\u0348\0\u0348\0\u0690"+
    "\0\u06b8\0\u06e0\0\u0708\0\u0730\0\u0348\0\u0758\0\u0780\0\u07a8"+
    "\0\u07d0\0\u07f8\0\u0618\0\u0820\0\u0848\0\u0870\0\u0578\0\u0898"+
    "\0\u08c0\0\u08e8\0\u0910\0\u0348\0\u0938\0\u0960\0\u0988\0\u09b0"+
    "\0\u0578\0\u0578\0\u09d8\0\u0348\0\u0a00\0\u0a28\0\u0a50\0\u0a78"+
    "\0\u0aa0\0\u0ac8\0\u0af0\0\u0b18\0\u0578\0\u0b40\0\u0b68\0\u0348";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[112];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\21\2\22\1\23\1\24\1\25\1\26\1\27\1\30"+
    "\1\31\1\32\1\22\7\21\1\33\10\21\1\34\1\35"+
    "\1\36\1\21\1\37\2\21\3\36\1\40\2\21\2\22"+
    "\1\23\1\41\1\42\1\43\1\27\1\30\1\31\1\32"+
    "\1\22\7\21\1\33\10\21\1\34\1\35\1\36\1\21"+
    "\1\37\2\21\3\36\1\44\2\21\2\22\1\23\1\41"+
    "\1\42\1\45\1\27\1\30\1\31\1\32\1\22\7\21"+
    "\1\33\10\21\1\34\1\35\1\36\1\21\1\37\2\21"+
    "\3\36\1\35\2\21\2\22\1\23\1\41\1\42\1\46"+
    "\1\27\1\30\1\31\1\32\1\22\7\21\1\33\10\21"+
    "\1\34\1\35\1\36\1\21\1\37\2\21\3\36\1\40"+
    "\2\21\2\22\1\23\1\24\1\25\1\47\1\27\1\30"+
    "\1\31\1\50\1\22\7\21\1\33\10\21\1\34\1\35"+
    "\1\36\1\21\1\37\2\21\3\36\1\51\2\21\2\22"+
    "\1\23\1\24\1\25\1\47\1\27\1\30\1\31\1\32"+
    "\1\22\7\21\1\33\10\21\1\34\1\35\1\36\1\21"+
    "\1\37\2\21\3\36\1\44\2\21\2\22\1\23\1\24"+
    "\1\25\1\47\1\27\1\30\1\31\1\50\1\22\7\21"+
    "\1\33\10\21\1\34\1\35\1\36\1\21\1\37\2\21"+
    "\3\36\1\52\2\21\2\22\1\23\1\24\1\25\1\47"+
    "\1\27\1\30\1\31\1\32\1\22\7\21\1\33\10\21"+
    "\1\34\1\35\1\53\1\21\1\37\2\21\1\54\1\55"+
    "\1\56\1\35\1\21\1\57\1\33\11\57\1\33\7\57"+
    "\1\33\17\57\1\60\5\57\1\33\11\57\1\33\7\57"+
    "\1\33\20\57\1\60\4\57\1\33\11\57\1\33\7\57"+
    "\1\33\21\57\1\60\3\57\1\33\11\57\1\33\7\57"+
    "\1\33\12\57\1\60\11\57\1\61\2\22\1\23\1\24"+
    "\1\25\1\47\1\27\1\30\1\31\1\50\1\22\7\61"+
    "\1\33\10\61\1\34\1\35\1\36\4\61\3\36\1\35"+
    "\1\61\1\21\2\22\1\23\1\24\1\25\1\47\1\27"+
    "\1\30\1\31\1\62\1\22\7\21\1\33\10\21\1\34"+
    "\1\35\1\36\1\21\1\37\2\21\3\36\1\35\1\21"+
    "\1\63\2\22\1\23\1\24\1\25\1\47\1\27\1\30"+
    "\1\31\1\32\1\22\7\63\1\33\10\63\1\34\1\35"+
    "\1\36\4\63\3\36\1\35\1\63\1\57\1\33\4\57"+
    "\1\64\4\57\1\33\7\57\1\33\24\57\1\65\13\0"+
    "\7\65\1\0\10\65\3\0\4\65\4\0\1\66\1\0"+
    "\3\22\7\0\1\22\35\0\3\22\7\0\1\22\33\0"+
    "\1\67\47\0\1\67\2\70\1\71\1\70\1\72\2\70"+
    "\1\73\3\70\1\0\1\74\1\75\5\76\1\0\1\77"+
    "\7\76\3\70\1\100\1\70\1\101\1\76\4\70\1\102"+
    "\50\0\1\103\2\0\44\103\1\104\2\70\1\71\1\70"+
    "\1\72\6\70\1\0\1\74\1\75\5\76\1\0\1\77"+
    "\2\76\1\105\4\76\3\70\1\100\1\70\1\101\1\76"+
    "\4\70\1\102\2\70\1\71\1\70\1\72\3\70\1\106"+
    "\2\70\1\0\1\74\1\75\5\76\1\0\1\77\7\76"+
    "\3\70\1\100\1\70\1\101\1\76\4\70\1\102\2\70"+
    "\1\71\1\70\1\72\1\107\1\70\1\73\3\70\1\0"+
    "\1\74\1\75\5\76\1\0\1\77\7\76\3\70\1\100"+
    "\1\70\1\101\1\76\4\70\1\102\2\70\1\71\1\70"+
    "\1\72\6\70\1\0\1\74\1\75\5\76\1\0\1\77"+
    "\7\76\3\70\1\100\1\70\1\101\1\76\4\70\1\102"+
    "\1\110\13\0\7\110\1\0\10\110\3\0\4\110\4\0"+
    "\1\111\1\112\13\0\7\112\1\0\10\112\3\0\4\112"+
    "\4\0\1\113\15\0\1\114\32\0\1\65\13\0\7\65"+
    "\1\0\10\65\3\0\4\65\4\0\2\65\10\0\1\115"+
    "\2\0\7\65\1\0\10\65\3\0\4\65\4\0\1\65"+
    "\11\0\1\115\52\0\1\76\1\116\5\76\1\0\10\76"+
    "\3\0\1\76\1\0\2\76\21\0\4\76\1\117\2\76"+
    "\1\0\10\76\3\0\1\76\1\0\2\76\21\0\7\76"+
    "\1\0\10\76\3\0\1\76\1\0\2\76\21\0\3\76"+
    "\1\120\3\76\1\0\10\76\3\0\1\76\1\0\2\76"+
    "\21\0\1\76\1\121\5\76\1\0\10\76\3\0\1\76"+
    "\1\0\2\76\21\0\7\76\1\0\10\76\3\0\1\76"+
    "\1\0\1\76\1\122\5\0\1\103\2\0\46\103\2\0"+
    "\6\103\1\123\36\103\14\0\1\76\1\124\5\76\1\0"+
    "\10\76\3\0\1\76\1\0\2\76\5\0\1\110\13\0"+
    "\7\110\1\0\10\110\3\0\4\110\4\0\2\110\10\0"+
    "\1\115\2\0\7\110\1\0\10\110\3\0\4\110\4\0"+
    "\1\110\1\112\13\0\7\112\1\0\10\112\3\0\4\112"+
    "\4\0\2\112\10\0\1\115\2\0\7\112\1\0\10\112"+
    "\3\0\4\112\4\0\1\112\20\0\1\125\43\0\2\76"+
    "\1\126\4\76\1\0\10\76\3\0\1\76\1\0\2\76"+
    "\21\0\5\76\1\127\1\76\1\0\10\76\3\0\1\76"+
    "\1\0\2\76\21\0\7\76\1\0\1\76\1\130\6\76"+
    "\3\0\1\76\1\0\2\76\21\0\7\76\1\0\7\76"+
    "\1\131\3\0\1\76\1\0\2\76\21\0\7\76\1\0"+
    "\3\76\1\132\4\76\3\0\1\76\1\0\2\76\21\0"+
    "\7\76\1\0\2\76\1\133\5\76\3\0\1\76\1\0"+
    "\2\76\26\0\1\134\42\0\3\76\1\135\3\76\1\0"+
    "\10\76\3\0\1\76\1\0\2\76\21\0\4\76\1\136"+
    "\2\76\1\0\10\76\3\0\1\76\1\0\2\76\21\0"+
    "\1\137\6\76\1\0\10\76\3\0\1\76\1\0\2\76"+
    "\21\0\3\76\1\140\3\76\1\0\10\76\3\0\1\76"+
    "\1\0\2\76\21\0\7\76\1\0\3\76\1\141\4\76"+
    "\3\0\1\76\1\0\2\76\21\0\4\76\1\142\2\76"+
    "\1\0\10\76\3\0\1\76\1\0\2\76\21\0\1\76"+
    "\1\143\5\76\1\0\10\76\3\0\1\76\1\0\2\76"+
    "\21\0\7\76\1\0\10\76\3\0\1\76\1\144\2\76"+
    "\21\0\4\76\1\145\2\76\1\0\10\76\3\0\1\76"+
    "\1\0\2\76\21\0\7\76\1\0\2\76\1\146\5\76"+
    "\3\0\1\76\1\0\2\76\21\0\7\76\1\0\10\76"+
    "\3\0\1\76\1\0\1\147\1\76\21\0\7\76\1\0"+
    "\3\76\1\150\4\76\3\0\1\76\1\0\2\76\21\0"+
    "\3\76\1\151\3\76\1\0\10\76\3\0\1\76\1\0"+
    "\2\76\21\0\7\76\1\0\4\76\1\152\3\76\3\0"+
    "\1\76\1\0\2\76\21\0\4\76\1\153\2\76\1\0"+
    "\10\76\3\0\1\76\1\0\2\76\21\0\7\76\1\0"+
    "\5\76\1\154\2\76\3\0\1\76\1\0\2\76\21\0"+
    "\1\76\1\155\5\76\1\0\10\76\3\0\1\76\1\0"+
    "\2\76\21\0\7\76\1\0\6\76\1\156\1\76\3\0"+
    "\1\76\1\0\2\76\21\0\7\76\1\0\7\76\1\157"+
    "\3\0\1\76\1\0\2\76\5\0\1\160\2\0\10\160"+
    "\1\0\7\76\1\0\10\76\3\160\1\76\1\160\2\76"+
    "\5\160";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2960];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\20\0\12\1\1\11\23\1\2\11\6\1\1\0\4\11"+
    "\12\1\2\11\4\1\1\0\1\11\7\1\1\0\6\1"+
    "\1\11\7\1\1\11\13\1\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[112];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  private Deque<Integer> stack = new ArrayDeque<>();

  private final Set<String> verbatimEnvironments = new HashSet<>(Arrays.asList("verbatim", "lstlisting"));

  public void yypushState(int newState) {
    stack.push(yystate());
    yybegin(newState);
  }

  public void yypopState() {
    yybegin(stack.pop());
  }


  public LatexLexer() {
    this(null);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public LatexLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    int size = 0;
    for (int i = 0, length = packed.length(); i < length; i += 2) {
      size += packed.charAt(i);
    }
    char[] map = new char[size];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < packed.length()) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      {@code false}, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position {@code pos} from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + ZZ_CMAP(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return NORMAL_TEXT_WORD;
            } 
            // fall through
          case 38: break;
          case 2: 
            { return com.intellij.psi.TokenType.WHITE_SPACE;
            } 
            // fall through
          case 39: break;
          case 3: 
            { return OPEN_BRACKET;
            } 
            // fall through
          case 40: break;
          case 4: 
            { return CLOSE_BRACKET;
            } 
            // fall through
          case 41: break;
          case 5: 
            { return com.intellij.psi.TokenType.BAD_CHARACTER;
            } 
            // fall through
          case 42: break;
          case 6: 
            { return OPEN_PAREN;
            } 
            // fall through
          case 43: break;
          case 7: 
            { return CLOSE_PAREN;
            } 
            // fall through
          case 44: break;
          case 8: 
            { return OPEN_BRACE;
            } 
            // fall through
          case 45: break;
          case 9: 
            { return CLOSE_BRACE;
            } 
            // fall through
          case 46: break;
          case 10: 
            { return COMMENT_TOKEN;
            } 
            // fall through
          case 47: break;
          case 11: 
            { return NORMAL_TEXT_CHAR;
            } 
            // fall through
          case 48: break;
          case 12: 
            { return STAR;
            } 
            // fall through
          case 49: break;
          case 13: 
            { yypushState(INLINE_MATH); return INLINE_MATH_START;
            } 
            // fall through
          case 50: break;
          case 14: 
            { return M_OPEN_BRACKET;
            } 
            // fall through
          case 51: break;
          case 15: 
            { return M_CLOSE_BRACKET;
            } 
            // fall through
          case 52: break;
          case 16: 
            { yypopState(); return INLINE_MATH_END;
            } 
            // fall through
          case 53: break;
          case 17: 
            { yypopState(); return CLOSE_BRACE;
            } 
            // fall through
          case 54: break;
          case 18: 
            { yypushState(NESTED_INLINE_MATH); return INLINE_MATH_START;
            } 
            // fall through
          case 55: break;
          case 19: 
            { yypopState(); yypushState(INLINE_VERBATIM_EQUALS); return OPEN_BRACE;
            } 
            // fall through
          case 56: break;
          case 20: 
            { yypopState(); yypushState(INLINE_VERBATIM_PIPE); return OPEN_BRACE;
            } 
            // fall through
          case 57: break;
          case 21: 
            { yypopState(); yypushState(INLINE_VERBATIM_EXCL_MARK); return OPEN_BRACE;
            } 
            // fall through
          case 58: break;
          case 22: 
            { yypopState(); yypushState(INLINE_VERBATIM_QUOTES); return OPEN_BRACE;
            } 
            // fall through
          case 59: break;
          case 23: 
            { return RAW_TEXT_TOKEN;
            } 
            // fall through
          case 60: break;
          case 24: 
            { yypopState();
          // toString to fix comparisons of charsequence subsequences with string
          if (verbatimEnvironments.contains(yytext().toString())) { // todo add more envs
                yypushState(VERBATIM_START);
          }
          return NORMAL_TEXT_WORD;
            } 
            // fall through
          case 61: break;
          case 25: 
            { yypopState(); yypushState(VERBATIM); return CLOSE_BRACE;
            } 
            // fall through
          case 62: break;
          case 26: 
            { if (verbatimEnvironments.contains(yytext().toString())) { // todo add more envs
                  // Pop current state and verbatim state
                  yypopState(); yypopState();
              }
              return NORMAL_TEXT_WORD;
            } 
            // fall through
          case 63: break;
          case 27: 
            { return COMMAND_TOKEN;
            } 
            // fall through
          case 64: break;
          case 28: 
            { yypushState(DISPLAY_MATH); return DISPLAY_MATH_START;
            } 
            // fall through
          case 65: break;
          case 29: 
            { yypushState(INLINE_MATH_LATEX); return INLINE_MATH_START;
            } 
            // fall through
          case 66: break;
          case 30: 
            { yypopState(); return DISPLAY_MATH_END;
            } 
            // fall through
          case 67: break;
          case 31: 
            { yypushState(PREAMBLE_OPTION); return OPEN_BRACE;
            } 
            // fall through
          case 68: break;
          case 32: 
            { return END_TOKEN;
            } 
            // fall through
          case 69: break;
          case 33: 
            { yypushState(POSSIBLE_VERBATIM_END); return END_TOKEN;
            } 
            // fall through
          case 70: break;
          case 34: 
            { yypushState(INLINE_VERBATIM_START); return COMMAND_TOKEN;
            } 
            // fall through
          case 71: break;
          case 35: 
            { yypushState(TEXT_INSIDE_INLINE_MATH); return COMMAND_TOKEN;
            } 
            // fall through
          case 72: break;
          case 36: 
            { yypushState(POSSIBLE_VERBATIM_START); return BEGIN_TOKEN;
            } 
            // fall through
          case 73: break;
          case 37: 
            { return COMMAND_IFNEXTCHAR;
            } 
            // fall through
          case 74: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
