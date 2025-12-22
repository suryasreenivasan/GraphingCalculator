package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GraphPanel extends JPanel {

    private Node expression;
    private double scale = 50;

    private JTextField inputField;
    private JLabel errorLabel;

    public GraphPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        createInputPanel();
        parseExpression("sin(x)");
    }

    // ───────────────────────────────
    // INPUT UI
    // ───────────────────────────────
    private void createInputPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());

        inputField = new JTextField("sin(x)");
        JButton graphButton = new JButton("Graph");
        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);

        ActionListener graphAction = e -> graph();

        inputField.addActionListener(graphAction);
        graphButton.addActionListener(graphAction);

        topPanel.add(inputField, BorderLayout.CENTER);
        topPanel.add(graphButton, BorderLayout.EAST);
        topPanel.add(errorLabel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
    }

    private void graph() {
        try {
            parseExpression(inputField.getText());
            errorLabel.setText(" ");
            repaint();
        } catch (Exception e) {
            errorLabel.setText("Invalid expression");
        }
    }

    private void parseExpression(String input) {
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);
        expression = parser.parse();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (expression == null) return;
        drawGrid(g);
        drawAxes(g);
        drawTicks(g);
        drawLabels(g);
        drawGraph(g);
    }
    private void drawGrid(Graphics g) {
        g.setColor(new Color(230, 230, 230));

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        for (int x = centerX; x < getWidth(); x += scale)
            g.drawLine(x, 0, x, getHeight());

        for (int x = centerX; x > 0; x -= scale)
            g.drawLine(x, 0, x, getHeight());

        for (int y = centerY; y < getHeight(); y += scale)
            g.drawLine(0, y, getWidth(), y);

        for (int y = centerY; y > 0; y -= scale)
            g.drawLine(0, y, getWidth(), y);
    }

    private void drawLabels(Graphics g) {
        g.setColor(Color.BLACK);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int unitsX = (int) (getWidth() / (2 * scale));
        int unitsY = (int) (getHeight() / (2 * scale));

        // X-axis numbers
        for (int i = -unitsX; i <= unitsX; i++) {
            int x = (int) (centerX + i * scale);
            if (x >= 0 && x <= getWidth()) {
                g.drawString(Integer.toString(i), x - 5, centerY + 20);
            }
        }

        // Y-axis numbers
        for (int i = -unitsY; i <= unitsY; i++) {
            if (i == 0) continue; // avoid overlap at origin
            int y = (int) (centerY - i * scale);
            if (y >= 0 && y <= getHeight()) {
                g.drawString(Integer.toString(i), centerX + 8, y + 5);
            }
        }
    }

    private void drawAxes(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
    }
    
    private void drawTicks(Graphics g) {
        g.setColor(Color.GRAY);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int tickSize = 5;

        // X-axis ticks
        for (int x = centerX; x < getWidth(); x += scale) {
            g.drawLine(x, centerY - tickSize, x, centerY + tickSize);
        }
        for (int x = centerX; x > 0; x -= scale) {
            g.drawLine(x, centerY - tickSize, x, centerY + tickSize);
        }

        // Y-axis ticks
        for (int y = centerY; y < getHeight(); y += scale) {
            g.drawLine(centerX - tickSize, y, centerX + tickSize, y);
        }
        for (int y = centerY; y > 0; y -= scale) {
            g.drawLine(centerX - tickSize, y, centerX + tickSize, y);
        }
    }


    private void drawGraph(Graphics g) {
        g.setColor(Color.RED);

        double prevX = -getWidth() / (2 * scale);
        double prevY = expression.eval(prevX);

        for (double x = prevX; x <= getWidth() / (2 * scale); x += 0.01) {
            double y = expression.eval(x);

            if (Double.isNaN(y) || Double.isInfinite(y)) {
                prevX = x;
                prevY = y;
                continue;
            }

            int x1 = (int) (getWidth() / 2 + prevX * scale);
            int y1 = (int) (getHeight() / 2 - prevY * scale);
            int x2 = (int) (getWidth() / 2 + x * scale);
            int y2 = (int) (getHeight() / 2 - y * scale);

            g.drawLine(x1, y1, x2, y2);

            prevX = x;
            prevY = y;
        }
    }
}
