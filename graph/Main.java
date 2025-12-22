package graph;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Graphing Software by Surya Sreenivasan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.add(new GraphPanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
}
