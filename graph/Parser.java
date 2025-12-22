package graph;

public class Parser {

    private Lexer lexer;
    private Token current;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.current = lexer.nextToken();
    }

    private void eat(TokenType type) {
        if (current.type == type) {
            current = lexer.nextToken();
        } else {
            throw new RuntimeException(
                "Expected " + type + " but got " + current.type
            );
        }
    }

    public Node parse() {
        return expression();
    }

    private Node expression() {
        Node node = term();

        while (current.type == TokenType.PLUS || current.type == TokenType.MINUS) {
            TokenType op = current.type;
            eat(op);
            node = new BinaryNode(node, op, term());
        }
        return node;
    }

 
    private Node term() {
        Node node = power();
        while (current.type == TokenType.MULTIPLY || current.type == TokenType.DIVIDE)  {
            TokenType op = current.type;
            eat(op);
            node = new BinaryNode(node, op, power());
        }
        return node;
    }
    
    private Node power() {
        Node node = factor();

        if (current.type == TokenType.POWER) {
            eat(TokenType.POWER);
            node = new BinaryNode(node, TokenType.POWER, power());
        }
        return node;
    }

    private Node factor() {

        switch (current.type) {

            case NUMBER:
                double value = Double.parseDouble(current.text);
                eat(TokenType.NUMBER);
                return new NumberNode(value);

            case VARIABLE:
                eat(TokenType.VARIABLE);
                return new VariableNode();

            case SIN: case COS: case TAN:
            case ASIN: case ACOS: case ATAN:
            case LN: case LOG:
                String name = current.text;
                eat(current.type);
                eat(TokenType.LPAREN);
                Node arg = expression();
                eat(TokenType.RPAREN);
                return new FunctionNode(name, arg);

            case LPAREN:
                eat(TokenType.LPAREN);
                Node node = expression();
                eat(TokenType.RPAREN);
                return node;

            case MINUS:
                eat(TokenType.MINUS);
                return new BinaryNode(new NumberNode(0), TokenType.MINUS, factor());

            default:
                throw new RuntimeException("Unexpected token: " + current.type);
        }
    }
}
