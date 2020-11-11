package root;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        boxLeft.add(Box.createVerticalGlue());

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

        boxRadio.add(Box.createHorizontalGlue());
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

        //ACTION BUTTONS
        Box boxActions = Box.createHorizontalBox();
        boxActions.add(Box.createHorizontalGlue());

        JButton mPlus = new JButton("M+");
        mPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double value = 0;
                try {
                    value = Double.parseDouble(fieldResult.getText());
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(CalculatorFrame.this, "Invalid arguments");
                }
                memory[currentMemorySection] += value;
                labelsMemory[currentMemorySection].setText(String.valueOf(memory[currentMemorySection]));
            }
        });
        boxActions.add(mPlus);

        JButton mClear = new JButton("MC");
        mClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memory[currentMemorySection] = 0;
                labelsMemory[currentMemorySection].setText("0.0");
            }
        });
        boxActions.add(mClear);
        boxActions.add(Box.createHorizontalGlue());

        boxLeft.add(boxActions);

        boxLeft.add(Box.createVerticalGlue());

        boxContent.add(boxLeft);
    }

    public void constructRight() {
        Box boxRight = Box.createVerticalBox();
        boxRight.add(Box.createVerticalGlue());

        //IMAGES
        Box boxImages = Box.createHorizontalBox();
        boxImages.add(Box.createHorizontalGlue());

        BufferedImage buffer = null;
        try {
            buffer = ImageIO.read(new File("src/root/f1.png"));
        } catch (IOException exception) {
            throw new RuntimeException();
        }
        Image img = buffer.getScaledInstance(500, 50, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);
        imgFormula1 = new JLabel(icon);

        try {
            buffer = ImageIO.read(new File("src/root/f2.png"));
        } catch (IOException exception) {
            throw new RuntimeException();
        }
        img = buffer.getScaledInstance(800, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        imgFormula2 = new JLabel(icon);

        imgFormula2.setVisible(false);

        boxImages.add(imgFormula1);
        boxImages.add(imgFormula2);
        boxImages.add(Box.createHorizontalGlue());

        boxRight.add(boxImages);

        //RADIO-BUTTONS
        ButtonGroup groupRadio = new ButtonGroup();
        Box boxRadio = Box.createHorizontalBox();
        boxRadio.add(Box.createHorizontalGlue());

        JRadioButton buttonFormula1 = new JRadioButton("Formula 1");
        buttonFormula1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorFrame.this.currentFormula = Formula.FORMULA1;
                imgFormula1.setVisible(true);
                imgFormula2.setVisible(false);
            }
        });
        groupRadio.add(buttonFormula1);
        boxRadio.add(buttonFormula1);

        JRadioButton buttonFormula2 = new JRadioButton("Formula 2");
        buttonFormula2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculatorFrame.this.currentFormula = Formula.FORMULA2;
                imgFormula1.setVisible(false);
                imgFormula2.setVisible(true);
            }
        });
        groupRadio.add(buttonFormula2);
        boxRadio.add(buttonFormula2);

        boxRadio.add(Box.createHorizontalGlue());
        groupRadio.setSelected(groupRadio.getElements().nextElement().getModel(), true);

        boxRight.add(boxRadio);

        //VARIABLES
        Box boxVariables = Box.createHorizontalBox();
        boxVariables.add(Box.createHorizontalGlue());

        fieldX = new JTextField("0.0", 20);
        fieldX.setMaximumSize(fieldX.getPreferredSize());
        fieldY = new JTextField("0.0", 20);
        fieldY.setMaximumSize(fieldY.getPreferredSize());
        fieldZ = new JTextField("0.0", 20);
        fieldZ.setMaximumSize(fieldZ.getPreferredSize());

        boxVariables.add(new JLabel("X: "));
        boxVariables.add(Box.createHorizontalStrut(10));
        boxVariables.add(fieldX);
        boxVariables.add(Box.createHorizontalStrut(60));
        boxVariables.add(new JLabel("Y: "));
        boxVariables.add(Box.createHorizontalStrut(10));
        boxVariables.add(fieldY);
        boxVariables.add(Box.createHorizontalStrut(60));
        boxVariables.add(new JLabel("Z: "));
        boxVariables.add(Box.createHorizontalStrut(10));
        boxVariables.add(fieldZ);
        boxVariables.add(Box.createHorizontalStrut(60));
        boxVariables.add(Box.createHorizontalGlue());

        boxRight.add(boxVariables);

        boxContent.add(boxRight);
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
        constructRight();

        boxContent.add(Box.createVerticalGlue());

        add(boxContent);
    }
}
