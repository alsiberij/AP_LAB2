package root;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public void constructLeft() {
        Box boxLeft = Box.createVerticalBox();

        //RADIO-BUTTONS
        ButtonGroup groupRadio = new ButtonGroup();
        Box boxRadio = Box.createHorizontalBox();

        boxRadio.add(Box.createHorizontalGlue());

        JRadioButton memory1 = new JRadioButton("M1");
        memory1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorFrame.this.currentMemorySection = 0;
            }
        });
        groupRadio.add(memory1);
        boxRadio.add(memory1);

        JRadioButton memory2 = new JRadioButton("M2");
        memory2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorFrame.this.currentMemorySection = 1;
            }
        });
        groupRadio.add(memory2);
        boxRadio.add(memory2);

        JRadioButton memory3 = new JRadioButton("M3");
        memory3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorFrame.this.currentMemorySection = 2;
            }
        });
        groupRadio.add(memory3);
        boxRadio.add(memory3);

        groupRadio.setSelected(groupRadio.getElements().nextElement().getModel(), true);

        boxLeft.add(boxRadio);

        //LABELS
        for (int i = 0; i < 3; i++) {
            labelsMemory[i] = new JLabel(String.valueOf(memory[i]));
        }

        Box boxMemoryLabels = Box.createHorizontalBox();
        boxMemoryLabels.add(Box.createHorizontalGlue());
        boxMemoryLabels.add(labelsMemory[0]);
        boxMemoryLabels.add(Box.createHorizontalStrut(30));
        boxMemoryLabels.add(labelsMemory[1]);
        boxMemoryLabels.add(Box.createHorizontalStrut(30));
        boxMemoryLabels.add(labelsMemory[2]);
        boxMemoryLabels.add(Box.createHorizontalGlue());

        boxLeft.add(boxMemoryLabels);
        
    }

    CalculatorFrame() {
        super("Calculator");
        setSize(WIDTH, HEIGHT);
        Toolkit tk = Toolkit.getDefaultToolkit();
        setLocation((tk.getScreenSize().width - WIDTH) / 2, (tk.getScreenSize().height - HEIGHT) / 2);

        for (int i = 0; i < 3; i++) {
            memory[i] = 0;
        }

        boxContent.add(Box.createHorizontalGlue());

        //LEFT
        constructLeft();

        //RIGHT


        add(boxContent);
    }
}
