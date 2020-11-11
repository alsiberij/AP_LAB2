package root;

import javax.swing.*;

public class MainApplication {
    public static void main(String[] args) {
        CalculatorFrame calculatorFrame = new CalculatorFrame();
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculatorFrame.setVisible(true);
    }
}
