package graph;

public class MathEngine {

    public static double sin(double x) {
        x %= (2 * Math.PI);
        double sum = 0, term = x;
        for (int i = 1; i <= 13; i += 2) {
            sum += term;
            term *= -x * x / ((i + 1) * (i + 2));
        }
        return sum;
    }

    public static double cos(double x) {
        x %= (2 * Math.PI);
        double sum = 1, term = 1;
        for (int i = 0; i <= 12; i += 2) {
            term *= -x * x / ((i + 1) * (i + 2));
            sum += term;
        }
        return sum;
    }

    public static double tan(double x) {
        return sin(x) / cos(x);
    }

    public static double ln(double x) {
        if (x <= 0) return Double.NaN;
        double y = (x - 1) / (x + 1);
        double sum = 0, term = y;
        for (int i = 1; i < 20; i += 2) {
            sum += term / i;
            term *= y * y;
        }
        return 2 * sum;
    }

    public static double log(double x) {
        return ln(x) / ln(10);
    }

    public static double asin(double x) {
        if (Math.abs(x) > 1) return Double.NaN;
        double sum = x, term = x;
        for (int n = 1; n < 10; n++) {
            term *= (x * x * (2*n - 1) * (2*n - 1)) / (2*n * (2*n + 1));
            sum += term;
        }
        return sum;
    }

    public static double acos(double x) {
        return Math.PI / 2 - asin(x);
    }

    public static double atan(double x) {
        if (Math.abs(x) > 1)
            return Math.copySign(Math.PI/2, x) - atan(1/x);
        double sum = 0, term = x;
        for (int i = 1; i < 15; i += 2) {
            sum += term / i;
            term *= -x * x;
        }
        return sum;
    }
}
