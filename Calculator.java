package JavaProject;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.text.*;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private final static Integer MAX_CHARACTERS = 14;
    private static final Format FORMAT = DecimalFormat.getNumberInstance();

    private Double value;
    private Double operationValue;
    private Operation operation;
    private boolean operationPressed = false;
    private boolean hasDot = false;
    private boolean lastButtonEquals = false;

    JButton button7 = new JButton("7");
    JButton button8 = new JButton("8");
    JButton clean = new JButton("CE");
    JButton closeButton = new JButton("close!");
    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton button3 = new JButton("3");
    JButton button4 = new JButton("4");
    JButton button5 = new JButton("5");
    JButton button6 = new JButton("6");
    JButton button00 = new JButton("00");
    JButton buttonComma = new JButton(",");
    JButton buttonSubstraction = new JButton("-");
    JButton buttonPrecent = new JButton("%");
    JButton button9 = new JButton("9");
    JButton button0 = new JButton("0");
    JButton buttonPlus = new JButton("+");
    JButton buttonEqualsSign = new JButton("=");
    JButton buttonMultiplication = new JButton("*");
    JButton buttonDivision = new JButton("/");

    JFormattedTextField textField = new JFormattedTextField();

    public Calculator() {
        createAndShowGUI();
    }

    public void createAndShowGUI() {

        JFrame frame = new JFrame("Swing Starter Kit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        List<JComponent> buttonList = new ArrayList<>();

        GridLayout layout = new GridLayout(4, 5, 5, 5);
        JPanel panel = new JPanel();
        panel.setLayout(layout);

        panel.setPreferredSize(new Dimension(600, 400));

        //btn.setPreferredSize(new Dimension(40, 40));
        value =0.0;

        // create a label

        buttonList.add(button7);
        buttonList.add(button8);
        buttonList.add(button9);
        buttonList.add(clean);
        buttonList.add(closeButton);
        buttonList.add(button4);
        buttonList.add(button5);
        buttonList.add(button6);
        buttonList.add(buttonPlus);
        buttonList.add(buttonPrecent);
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(buttonSubstraction);
        buttonList.add(buttonDivision);
        buttonList.add(button0);
        buttonList.add(button00);
        buttonList.add(buttonComma);
        buttonList.add(buttonEqualsSign);
        buttonList.add(buttonMultiplication);


        closeButton.addActionListener(actionEvent -> {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });


        //JTextArea textArea = new JTextArea("you can write here!");


        textField.setSize(new Dimension(400, 80));
        textField.setBorder(new LineBorder(Color.GRAY));
        Font font1 = new Font("SansSerif", Font.BOLD, 30);
        textField.setFont(font1);
        Font buttonFont = new Font("Arial", Font.PLAIN, 30);
        //button7.setFont(buttonFont);

        for (JComponent component : buttonList) {

            component.setFont(buttonFont);
            panel.add(component);

        }

        button1.addActionListener(actionEvent -> {
            numberButtonOperation(1);

        });

        button2.addActionListener(actionEvent -> {
            numberButtonOperation(2);

        });

        button3.addActionListener(actionEvent -> {
            numberButtonOperation(3);
        });

        button4.addActionListener(actionEvent -> {
            numberButtonOperation(4);
        });

        button5.addActionListener(actionEvent -> {
            numberButtonOperation(5);
        });

        button6.addActionListener(actionEvent -> {
            numberButtonOperation(6);
        });

        button7.addActionListener(actionEvent -> {
            numberButtonOperation(7);
        });

        button8.addActionListener(actionEvent -> {
            numberButtonOperation(8);
        });

        button9.addActionListener(actionEvent -> {
            numberButtonOperation(9);
        });

        button0.addActionListener(actionEvent -> {
            numberButtonOperation(0);
        });
        button00.addActionListener(actionEvent -> {
            numberButtonOperation(00);
        });
        buttonPlus.addActionListener(actionEvent -> {
            actionButtonOperation(Operation.SUM);
            lastButtonEquals = false;
        });
        buttonSubstraction.addActionListener(actionEvent -> {
            actionButtonOperation(Operation.SUBSTRACT);
            lastButtonEquals = false;
        });
        buttonEqualsSign.addActionListener(actionEvent -> {
            actionButtonEquals();
        });



//        buttonPlus.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                actionButtonOperation(Operation.SUM);
//            }
//        });
        // add the label to the panel
//        panel.add(button7);
//        panel.add(button8);
//        panel.add(button9);
//        panel.add(clean);
//        panel.add(closeButton);
//        panel.add(button4);
//        panel.add(button5);
//        panel.add(button6);
//        panel.add(buttonPlus);
//        panel.add(buttonPrecent);
//        panel.add(button1);
//        panel.add(button2);
//        panel.add(button3);
//        panel.add(buttonSubstraction);
//        panel.add(buttoneDivision);
//        panel.add(button0);
//        panel.add(button00);
//        panel.add(buttonComma);
//        panel.add(buttonEqualsSign);
//        panel.add(buttonMultiplication);

        frame.getContentPane().add(textField, BorderLayout.NORTH);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);

        // show the window.
        frame.pack();
        frame.setVisible(true);

    } // createAndShowGUI

    private void actionButtonOperation(Operation operation) {
        if(lastButtonEquals) {
            operationValue = null;
        }
        if (!operationPressed && operationValue!=null) {
            value = processOperation(operation, operationValue, value);
            operationValue=null;
            operationPressed=false;
        }
        else if (!operationPressed) {
            operationValue = value;
            operationPressed = true;
        }
        this.operation = operation;

       refresh();
    }
    private void actionEqualsOperation() {
        if (!operationPressed && operationValue!=null) {
            value = processOperation(operation, operationValue, value);
            operationValue=null;
            operationPressed=false;
        }
        else if (!operationPressed) {
            operationValue = value;
            operationPressed = true;
        }
        this.operation = operation;

        refresh();
    }

    private double actionButtonEquals() {
        lastButtonEquals = true;
        Double temporaryValue = operationValue;
        actionEqualsOperation();
        operationValue = temporaryValue;
        operationPressed=false;
        refresh();
        return 0.0;
    }
    private Double processOperation(Operation operation, Double number1, Double number2) {

        switch (operation) {

            case SUM:
                return number1 + number2;
            case SUBSTRACT:
                return number1-number2;
            case MULTIPLY:
                return number1 * number2;
            case DIVISION:
                return number1 / number2;
            case PERCENT:
                break;
            default:
                break;
        }

        return number2;
    }

    private void numberButtonOperation(int number) {

        String text = FORMAT.format(value);

        if (text.length()>MAX_CHARACTERS) {
            return;
        }
//        if ()


        if (!hasDot && !operationPressed) {
            value = value*10+number;
        } else if (operationPressed) {
            hasDot=false;
            value=0.0+number;
            operationPressed=false;

        } else {
            text = text + number;
            try {
                value = ((Number) FORMAT.parseObject(text)).doubleValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        refresh();
        lastButtonEquals = false;
    }

    private void refresh() {
        textField.setValue(value);
    }

}
