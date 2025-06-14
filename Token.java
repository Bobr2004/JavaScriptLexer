public class Token {
    public final TokenType type;
    public final String value;
    public final int line;

    public Token(TokenType type, String value, int line) {
        this.type = type;
        this.value = value;
        this.line = line;
    }

    @Override
    public String toString() {
        return String.format("Token(%s, '%s', line %d)", type, value, line);
    }
}
