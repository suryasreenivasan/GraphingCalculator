package graph;

public class Lexer {

    private String input;
    private int pos = 0;

    public Lexer(String input) {
        this.input = input.replaceAll("\\s+", "");
    }

    char peek() {
        return pos < input.length() ? input.charAt(pos) : '\0';
    }

    char next() {
        return input.charAt(pos++);
    }

    public Token nextToken() {
        while (Character.isDigit(peek()) || peek() == '.') {
            int start = pos;
            while (Character.isDigit(peek()) || peek() == '.') next();
            return new Token(TokenType.NUMBER, input.substring(start, pos));
        }

        if (Character.isLetter(peek())) {
            int start = pos;
            while (Character.isLetter(peek())) next();
            String name = input.substring(start, pos);

            switch (name) {
                case "sin": return new Token(TokenType.SIN, name);
                case "cos": return new Token(TokenType.COS, name);
                case "tan": return new Token(TokenType.TAN, name);
                case "asin": return new Token(TokenType.ASIN, name);
                case "acos": return new Token(TokenType.ACOS, name);
                case "atan": return new Token(TokenType.ATAN, name);
                case "ln": return new Token(TokenType.LN, name);
                case "log": return new Token(TokenType.LOG, name);
                case "x": return new Token(TokenType.VARIABLE, name);
            }
        }

        if (pos >= input.length()) return new Token(TokenType.EOF, "");

        char c = next();
        switch (c) {
            case '+': return new Token(TokenType.PLUS, "+");
            case '-': return new Token(TokenType.MINUS, "-");
            case '*': return new Token(TokenType.MULTIPLY, "*");
            case '/': return new Token(TokenType.DIVIDE, "/");
            case '^': return new Token(TokenType.POWER, "^");
            case '(': return new Token(TokenType.LPAREN, "(");
            case ')': return new Token(TokenType.RPAREN, ")");
        }

        return new Token(TokenType.EOF, "");
    }
}
