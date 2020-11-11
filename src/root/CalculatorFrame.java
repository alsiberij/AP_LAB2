package root;

import javax.swing.*;

public class CalculatorFrame extends JFrame {

    private enum Formula {FORMULA1, FORMULA2}

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private Formula currentFormula = Formula.FORMULA1;

    private Box boxContent = Box.createHorizontalBox();

    private JLabel imgFormula1;
    private JLabel imgFormula2;

    private JTextField fieldX;
    private JTextField fieldY;
    private JTextField fieldZ;
    private JTextField fieldResult;

    double[] memory = new double[3];
    private JLabel[] labelsMemory = new JLabel[3];
    short currentMemorySection = 0;

}
