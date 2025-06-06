import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexer {
    private static final List<String> keywords = Arrays.asList(
        "if", "else", "while", "for", "int", "float", "boolean", "char", "double", "return", "void"
    );

    private static final List<String> operators = Arrays.asList(
        "+", "-", "*", "/", "=", "==", "!=", ">", "<", ">=", "<=", "&&", "||"
    );

    private static final List<String> separators = Arrays.asList(
        "(", ")", "{", "}", ";", ","
    );

    public List<Token> analyze(String input) {
        List<Token> tokens = new ArrayList<>();
        int i = 0;

        while (i < input.length()) {
            char c = input.charAt(i);

            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            //  yorumsatiri
            if (c == '/' && i + 1 < input.length() && input.charAt(i + 1) == '/') {
                int start = i;
                i += 2;
                while (i < input.length() && input.charAt(i) != '\n') i++;
                tokens.add(new Token(TokenType.COMMENT, input.substring(start, i)));
                continue;
            }

            // sayislar
            if (Character.isDigit(c)) {
                int start = i;
                while (i < input.length() && Character.isDigit(input.charAt(i))) i++;
                tokens.add(new Token(TokenType.NUMBER, input.substring(start, i)));
                continue;
            }

            // harfle baslayan anahtar 
            if (Character.isLetter(c)) {
                int start = i;
                while (i < input.length() && (Character.isLetterOrDigit(input.charAt(i)) || input.charAt(i) == '_')) i++;
                String word = input.substring(start, i);
                if (keywords.contains(word)) {
                    tokens.add(new Token(TokenType.KEYWORD, word));
                } else {
                    tokens.add(new Token(TokenType.IDENTIFIER, word));
                }
                continue;
            }

            // operator
            boolean matched = false;
            for (String op : operators) {
                if (input.startsWith(op, i)) {
                    tokens.add(new Token(TokenType.OPERATOR, op));
                    i += op.length();
                    matched = true;
                    break;
                }
            }
            if (matched) continue;

            // ayrac
            for (String sep : separators) {
                if (input.startsWith(sep, i)) {
                    tokens.add(new Token(TokenType.SEPARATOR, sep));
                    i += sep.length();
                    matched = true;
                    break;
                }
            }
            if (matched) continue;

            // eger taninmazsa
            tokens.add(new Token(TokenType.UNKNOWN, String.valueOf(c)));
            i++;
        }

        return tokens;
    }
}
