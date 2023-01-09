/*
    NAME: Kristi LaVigne
    COURSE: CSCI 530 - Section 01
    SEMESTER: SPRING 2022
    ASSIGNMENT: Lexical Analyzer Implementation
        Input: a source code file, (.txt file). Ask user to input the file name.
        Output: printing all recognized tokens
        The source code accepted tokens include
            Keywords:  int   double  String  (case sensitive)
            operators:  =   (  )  +  -  *  /  ,  ;
            identifier:  letter(letter|digit)*
            int constant
            double constant
            string constant: string constant is enclosed in a pair of "", such as "abc"
            anything not recognized is detected as errors
    references used:
    https://www.tutorialspoint.com/compiler_design/compiler_design_lexical_analysis.htm
    https://rosettacode.org/wiki/Compiler/lexical_analyzer#Java
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Lexer {
    private int line;
    private int pos;
    private int position;
    private char chr;
    private String s;

    Map<String, TokenType> keywords = new HashMap<>();

    static class Token {
        public TokenType tokentype;
        public String value;
        public int line;
        public int pos;
        Token(TokenType token, String value, int line, int pos) {
            this.tokentype = token;
            this.value = value;
            this.line = line;
            this.pos = pos;
        }
        @Override
        public String toString() {
            String result = String.format("%5d  %5d %-15s", this.line, this.pos, this.tokentype);
            // switch-case for converting token types to strings
            switch (this.tokentype) {
                case integer:
                    result += String.format("  %4s", value);
                    break;
                case identifier:
                    result += String.format(" %s", value);
                    break;
                case keyword:
                    result += String.format(" %s", value);
                    break;
                case String:
                    result += String.format(" \"%s\"", value);
                    break;
            }
            return result;
        }
    } // end of Token class

    static enum TokenType {
        End_of_input, Op_multiply,  Op_divide, Op_mod, Op_add, Op_subtract,
        Op_negate, Op_not, Op_less, Op_lessequal, Op_greater, Op_greaterequal,
        Op_equal, Op_notequal, Op_assign, Op_and, Op_or, Keyword_if,
        Keyword_else, Keyword_while, Keyword_print, Keyword_putc, LeftParen, RightParen,
        LeftBrace, RightBrace, Semicolon, Comma, identifier, integer, String, operator, keyword, error
    } // end enum TokenType

    static void error(int line, int pos, String msg) {
        if (line > 0 && pos > 0) {
            System.out.printf("%s line:%d pos:%d\n", msg, line, pos);
        } else {
            System.out.println(msg);
        }
    } // end of error

    static void error(char c,int line, int pos, String msg) {
        if (line > 0 && pos > 0) {
            System.out.printf("%s %c line:%d pos:%d\n", msg, c, line, pos);
        } else {
            System.out.println(msg);
        }
    } // end of error for char

    static void error(String s,int line, int pos, String msg) {
        if (line > 0 && pos > 0) {
            System.out.printf("%s %s line:%d pos:%d\n", msg, s, line, pos);
        } else {
            System.out.println(msg);
        }
    } // end of error for string

    // defines string constant
    Lexer(String source) {
        this.line = 1;
        this.pos = 0;
        this.position = 0;
        this.s = source;
        this.chr = this.s.charAt(0);
        this.keywords.put("if", TokenType.Keyword_if);
        this.keywords.put("else", TokenType.Keyword_else);
        this.keywords.put("print", TokenType.Keyword_print);
        this.keywords.put("putc", TokenType.Keyword_putc);
        this.keywords.put("while", TokenType.Keyword_while);

    }

    Token follow(char expect, TokenType ifyes, TokenType ifno, int line, int pos) {
        if (getNextChar() == expect) {
            getNextChar();
            return new Token(ifyes, "", line, pos);
        }
        if (ifno == TokenType.End_of_input) {
            error((this.chr),line, pos, String.format("unrecognized character:", this.chr));
        }
        return new Token(ifno, "", line, pos);
    }

    //checking char literals
    Token char_lit(int line, int pos) {
        char c = getNextChar(); // skip opening quote
        int n = (int)c;
        if (c == '\'') {
            error(c, line, pos, "empty character constant");
        } else if (c == '\\') {
            c = getNextChar();
            if (c == 'n') {
                n = 10;
            } else if (c == '\\') {
                n = '\\';
            } else {
                error(c, line, pos, String.format("unknown escape sequence \\%c", c));
            }
        }
        if (getNextChar() != '\'') {
            error(c, line, pos, "multi-character constant");
        }
        getNextChar();
        return new Token(TokenType.integer, "" + n, line, pos);
    }

    // checking string literals
    Token string_lit(char start, int line, int pos) {
        String result = "";
        while (getNextChar() != start) {
            if (this.chr == '\u0000') {
                error(start, line, pos, "EOF while scanning string literal");
            }
            if (this.chr == '\n') {
                error(start, line, pos, "EOL while scanning string literal");
            }
            result += this.chr;
        }
        getNextChar();
        return new Token(TokenType.String, result, line, pos);
    }

    // function to determine if the slash is one (for division), double or slash asterisks (for comments)
    Token div_or_comment(int line, int pos) {
        if (getNextChar() != '*') {
            return new Token(TokenType.Op_divide, "", line, pos);
        }
        getNextChar();
        while (true) {
            if (this.chr == '\u0000') {
                error((this.chr),line, pos, "EOF in comment");
            } else if (this.chr == '*') {
                if (getNextChar() == '/') {
                    getNextChar();
                    return getToken();
                }
            } else {
                getNextChar();
            }
        }
    }

    // function to determine if is an integer or an identifier
    Token identifier_or_integer(int line, int pos) {
        boolean is_number = true;
        String text = "";

        while (Character.isAlphabetic(this.chr) || Character.isDigit(this.chr) || this.chr == '_') {
            text += this.chr;

            if (!Character.isDigit(this.chr)) {
                is_number = false;
            }
            getNextChar();
        }

        if (text.equals("")) {
            error(text,line, pos, String.format("error: %c", this.chr));
        }
        if (Character.isDigit(text.charAt(0))) {
            if (!is_number) {
                error(text,line, pos, String.format("invalid number: %s", text));
            }
            return new Token(TokenType.integer, text, line, pos);
        }
        if (this.keywords.containsKey(text)) {
            return new Token(this.keywords.get(text), "", line, pos);
        }
        return new Token(TokenType.identifier, text, line, pos);
    }

    // function for fetching tokens and identifying most symbols
    Token getToken() {
        int line, pos;
        while (Character.isWhitespace(this.chr)) {
            getNextChar();
        }
        line = this.line;
        pos = this.pos;

        // switch-case for processing symbols
        switch (this.chr) {
            case '\u0000': return new Token(TokenType.End_of_input, "", this.line, this.pos);
            case '/': return div_or_comment(line, pos);
            case '\'': return char_lit(line, pos);
            case '<': return follow('=', TokenType.Op_lessequal, TokenType.Op_less, line, pos);
            case '>': return follow('=', TokenType.Op_greaterequal, TokenType.Op_greater, line, pos);
            case '=': return follow('=', TokenType.Op_equal, TokenType.Op_assign, line, pos);
            case '!': return follow('=', TokenType.Op_notequal, TokenType.Op_not, line, pos);
            case '&': error('&', line, pos, "not recognized"); getNextChar(); return new Token(TokenType.error, "", line, pos);
            case '|': return follow('|', TokenType.Op_or, TokenType.End_of_input, line, pos);
            case '"': return string_lit(this.chr, line, pos);
            case '{': getNextChar(); return new Token(TokenType.LeftBrace, "", line, pos);
            case '}': getNextChar(); return new Token(TokenType.RightBrace, "", line, pos);
            case '(': getNextChar(); return new Token(TokenType.LeftParen, "(", line, pos);
            case ')': getNextChar(); return new Token(TokenType.operator, ")", line, pos);
            case '+': getNextChar(); return new Token(TokenType.operator, "+", line, pos);
            case '-': getNextChar(); return new Token(TokenType.operator, "-", line, pos);
            case '*': getNextChar(); return new Token(TokenType.operator, "*", line, pos);
            case '%': getNextChar(); return new Token(TokenType.Op_mod, "", line, pos);
            case ';': getNextChar(); return new Token(TokenType.operator, "", line, pos);
            case ',': getNextChar(); return new Token(TokenType.operator, ",", line, pos);

            default: return identifier_or_integer(line, pos);
        }
    }

    // for fetching the next char
    char getNextChar() {
        this.pos++;
        this.position++;
        if (this.position >= this.s.length()) {
            this.chr = '\u0000';
            return this.chr;
        }
        this.chr = this.s.charAt(this.position);
        if (this.chr == '\n') {
            this.line++;
            this.pos = 0;
        }
        return this.chr;
    }

    // for printing tokens
    void printTokens() {
        Token t;
        while ((t = getToken()).tokentype != TokenType.End_of_input) {
            System.out.println(t);
        }
        System.out.println(t);
    }
    public static void main(String[] args) {
        if (args.length > 0) {
            try {

                File f = new File(args[0]);
                Scanner s = new Scanner(f);
                String source = " ";
                while (s.hasNext()) {
                    source += s.nextLine() + "\n";
                }
                Lexer l = new Lexer(source);
                l.printTokens();
            } catch(FileNotFoundException e) {
                error(-1, -1, "Exception: " + e.getMessage());
            }
        } else {
            error(-1, -1, "No args");
        }
    }
}