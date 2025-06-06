public class Token {
    public TokenType type;
    public String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return type + ": " + value;
    }
}

enum TokenType {
    KEYWORD, IDENTIFIER, NUMBER, OPERATOR, COMMENT, SEPARATOR, UNKNOWN
}
