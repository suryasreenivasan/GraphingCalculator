package graph;

public class NumberNode extends Node {
    double value;

    NumberNode(double value) {
        this.value = value;
    }

    public double eval(double x) {
        return value;
    }
}
