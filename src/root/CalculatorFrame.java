package root;

import javax.swing.*;

import static java.lang.Math.*;

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


    public double calculate(double x, double y, double z) {
        switch (currentFormula) {
            case FORMULA1: {
                if (y <= 0) {
                    throw new NumberFormatException();
                }
                return sin(log(y) + sin(PI * y * y))* sqrt(sqrt(x * x + sin(z) + exp(cos(z))));
            }
            case FORMULA2: {
                if (1 + y <= 0 || x == 0) {
                    throw new NumberFormatException();
                }
                return pow(pow(cos(exp(x)) + log((1 + y) * (1 + y)), 2) + sqrt(exp(cos(x)) + sin(PI * z) * sin(PI * x)) + sqrt(1 / x) + cos(y*y), sin(z));
            }
            default: {
                return 0;
            }
        }
    }

    
}
