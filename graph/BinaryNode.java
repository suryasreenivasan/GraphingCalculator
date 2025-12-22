package graph;

public class BinaryNode extends Node {
    Node left, right;
    TokenType op;

    BinaryNode(Node left, TokenType op, Node right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public double eval(double x) {
        switch (op) {
            case PLUS: return left.eval(x) + right.eval(x);
            case MINUS: return left.eval(x) - right.eval(x);
            case MULTIPLY: return left.eval(x) * right.eval(x);
            case DIVIDE: return left.eval(x) / right.eval(x);
            case POWER: return Math.pow(left.eval(x), right.eval(x));
            default: return 0;
        }
    }
}
