package graph;

public class FunctionNode extends Node {

    String name;
    Node arg;

    FunctionNode(String name, Node arg) {
        this.name = name;
        this.arg = arg;
    }

    public double eval(double x) {
        double v = arg.eval(x);
        switch (name) {
            case "sin": return MathEngine.sin(v);
            case "cos": return MathEngine.cos(v);
            case "tan": return MathEngine.tan(v);
            case "asin": return MathEngine.asin(v);
            case "acos": return MathEngine.acos(v);
            case "atan": return MathEngine.atan(v);
            case "ln": return MathEngine.ln(v);
            case "log": return MathEngine.log(v);
        }
        return Double.NaN;
    }
}
