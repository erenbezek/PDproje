import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public boolean parse() {
    int braceCount = 0;

    // İlk olarak tüm tokenlarda UNKNOWN varsa hata ver
    for (Token token : tokens) {
        if (token.type == TokenType.UNKNOWN) {
            return false;
        }

        // Süslü parantez kontrolü
        if (token.value.equals("{")) {
            braceCount++;
        } else if (token.value.equals("}")) {
            braceCount--;
            if (braceCount < 0) return false;
        }
    }

    // Eğer süslü parantez dengesi bozuksa, direkt hata
    if (braceCount != 0) return false;

    // Şimdi tüm yapıları tek tek parse et
    current = 0;  // index sıfırla
    while (!isAtEnd()) {
        if (!parseStatement()) {
            return false; // herhangi bir yapı hatalıysa tüm kod hatalı say
        }
    }

    return true;
}


    private boolean parseStatement() {
        Token token = peek();

        if (match(TokenType.KEYWORD, "if")) {
            return parseIfStatement();
        } else if (match(TokenType.KEYWORD, "while")) {
            return parseWhileStatement();
        } else if (match(TokenType.KEYWORD, "int") || match(TokenType.KEYWORD, "float") || match(TokenType.KEYWORD, "boolean")) {
            return parseVariableDeclaration();
        } else if (match(TokenType.SEPARATOR, "{")) {
            while (!check(TokenType.SEPARATOR, "}")) {
                if (!parseStatement()) return false;
            }
            return match(TokenType.SEPARATOR, "}");
        } else {
            return parseExpressionStatement();
        }
    }

    private boolean parseIfStatement() {
        if (!match(TokenType.SEPARATOR, "(")) return false;
        if (!parseExpression()) return false;
        if (!match(TokenType.SEPARATOR, ")")) return false;
        return parseStatement();
    }

    private boolean parseWhileStatement() {
        if (!match(TokenType.SEPARATOR, "(")) return false;
        if (!parseExpression()) return false;
        if (!match(TokenType.SEPARATOR, ")")) return false;
        return parseStatement();
    }

    private boolean parseVariableDeclaration() {
        if (!match(TokenType.IDENTIFIER)) return false;
        if (match(TokenType.OPERATOR, "=")) {
            if (!parseExpression()) return false;
        }
        return match(TokenType.SEPARATOR, ";");
    }

    private boolean parseExpressionStatement() {
        if (!parseExpression()) return false;
        return match(TokenType.SEPARATOR, ";");
    }

    private boolean parseExpression() {
        // Örnek: x > 0, 5 + 3, x = y
        return match(TokenType.IDENTIFIER, TokenType.NUMBER) &&
               (match(TokenType.OPERATOR) ? match(TokenType.IDENTIFIER, TokenType.NUMBER) : true);
    }

    // Yardımcılar
    private boolean match(TokenType type) {
        if (check(type)) {
            advance();
            return true;
        }
        return false;
    }

    private boolean match(TokenType type, String value) {
        if (check(type, value)) {
            advance();
            return true;
        }
        return false;
    }

    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return peek().type == type;
    }

    private boolean check(TokenType type, String value) {
        if (isAtEnd()) return false;
        Token token = peek();
        return token.type == type && token.value.equals(value);
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    private boolean isAtEnd() {
        return current >= tokens.size();
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }
}
