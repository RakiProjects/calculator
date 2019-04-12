package calculatorv1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author radak
 */
public class CalculatorGUI {

    CalculatorButton zero, one, two, three, four, five, six, seven, eight, nine,
            dot, addition, subtract, multiply, divide, equals, clear,
            sqrt, close, open, exp, ans, neg;

    private JTextField displayBox;

    private String button = "";
    private String input = "";   // input - user input that is readable to the Calculator class
    private boolean newEntry = false; // resets the screen on any button click after evaluating the expression
    private double result;
    DecimalFormat formater = new DecimalFormat("0.##########");

    public static void main(String[] args) {
        CalculatorGUI gui = new CalculatorGUI();
        gui.start();
    }

    public void start() {
        JFrame frame = new JFrame("Calculator");
        JPanel centerPane = new JPanel();
        JPanel bottomPane = new JPanel();
        displayBox = new JTextField();
        Dimension displayBoxDimension = new Dimension(250, 30);
        displayBox.setPreferredSize(displayBoxDimension);

        centerPane.setLayout(new GridLayout(5, 4));
        bottomPane.setLayout(new GridLayout(1, 2, 2, 2));

        sqrt = new CalculatorButton("sqrt", new OperationButton(), centerPane);
        exp = new CalculatorButton(" ^ ", new OperationButton(), centerPane);
        open = new CalculatorButton("( ", new OperationButton(), centerPane);
        close = new CalculatorButton(" )", new OperationButton(), centerPane);
        seven = new CalculatorButton("7", new NumberButton(), centerPane);
        eight = new CalculatorButton("8", new NumberButton(), centerPane);
        nine = new CalculatorButton("9", new NumberButton(), centerPane);
        addition = new CalculatorButton(" + ", new OperationButton(), centerPane);
        four = new CalculatorButton("4", new NumberButton(), centerPane);
        five = new CalculatorButton("5", new NumberButton(), centerPane);
        six = new CalculatorButton("6", new NumberButton(), centerPane);
        subtract = new CalculatorButton(" - ", new OperationButton(), centerPane);
        one = new CalculatorButton("1", new NumberButton(), centerPane);
        two = new CalculatorButton("2", new NumberButton(), centerPane);
        three = new CalculatorButton("3", new NumberButton(), centerPane);
        multiply = new CalculatorButton(" * ", new OperationButton(), centerPane);
        dot = new CalculatorButton(".", new NumberButton(), centerPane);
        zero = new CalculatorButton("0", new NumberButton(), centerPane);
        equals = new CalculatorButton(" = ", new EqualsButton(), centerPane);
        divide = new CalculatorButton(" / ", new OperationButton(), centerPane);
        ans = new CalculatorButton("ans", new OperationButton(), bottomPane);
        neg = new CalculatorButton("(-)", new OperationButton(), bottomPane);
        clear = new CalculatorButton("clear", new OperationButton(), bottomPane);

        frame.getContentPane().add(BorderLayout.NORTH, displayBox);
        frame.getContentPane().add(BorderLayout.CENTER, centerPane);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPane);

        frame.setSize(250, 300);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class NumberButton implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (newEntry) {
                displayBox.setText("");
                newEntry = false;
                input = "";
            }

            button = event.getActionCommand();
            displayBox.setText(displayBox.getText() + button);
            input = input + button;
        }
    }

    class OperationButton implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            double ans = 0.0;
            button = event.getActionCommand();

            if (newEntry) {
                displayBox.setText("");
                newEntry = false;
                input = "";
            }

            if (button == "ans") {
                displayBox.setText(displayBox.getText() + formater.format(result));
                input = input + Double.toString(result);
            } else if (button == "(-)") {
                displayBox.setText(displayBox.getText() + "-");
                input = input + "-";
            } else if (button == "clear") {
                displayBox.setText("");
                input = "";
            } else if (button == "sqrt") {
                displayBox.setText(displayBox.getText() + "sqrt( ");
                input = input + "sqrt ( "; //extra spaces makes string readable to "Calculator" class
            } else {
                displayBox.setText(displayBox.getText() + button);
                input = input + button;
            }
        }
    }

    class EqualsButton implements ActionListener {

        Calculator evaluate = new Calculator();

        public void actionPerformed(ActionEvent event) {
            newEntry = true;

            if (evaluate.checkInput(input)) {
                button = event.getActionCommand();
                result = evaluate.readInput(input);
                displayBox.setText(displayBox.getText() + button + formater.format(result));
            } else {
                displayBox.setText(evaluate.getException());
            }
        }
    }

}
