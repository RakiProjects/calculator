/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorv1;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author radak
 */
public class Calculator {

    private String token = "";
    private String exception = "";

    public String getException() {
        return exception;
    }

    public boolean checkInput(String input) {
        int openBrackets = 0;
        int closedBrackets = 0;
        int numberCount = 0;
        int operatorCount = 0;
        int sqrtCount = 0;
        boolean inputResult = true;

        Scanner reader = new Scanner(input);

        while (reader.hasNext()) {
            token = reader.next();
            if (token.equals(" (")) {
                openBrackets++;
            }
            if (token.equals(") ")) {
                closedBrackets++;
            }
            if ((isNumber(token))) {
                numberCount++;
            }
            if (isOperator(token)) {
                operatorCount++;
            }
            if (token.equals("sqrt")) {
                sqrtCount++;
            }
        }

        if (openBrackets != closedBrackets) {
            inputResult = false;
            exception = "Error: unbalanced parenthesis";
        }

        if (numberCount <= operatorCount) {
            inputResult = false;
            exception = "Error: too many operators";
        }

        if (operatorCount == 0 && sqrtCount == 0) {
            inputResult = false;
            exception = "Error: no operators";
        }
        return inputResult;
    }

    public double readInput(String value) {
        Scanner input = new Scanner(value);
        Stack<Double> numbers = new Stack<Double>();
        Stack<String> operators = new Stack<String>();

        while (input.hasNext()) {
            token = input.next();
            if (isNumber(token)) {
                numbers.push(Double.parseDouble(token));
            } else if (token.equals(" (")) {
                operators.push(token);
            } else if (token.equals(") ")) {
                while (!operators.peek().equals(" (")) {
                    if (operators.peek().equals("sqrt")) {
                        numbers.push(Math.sqrt(numbers.pop()));
                        operators.pop();
                    } else {
                        numbers.push(evaluate(operators.pop(), numbers.pop(), numbers.pop()));
                    }
                }
                operators.pop();
            } else {
                while (!operators.empty() && hasPrecedence(token, operators.peek())) {
                    if (operators.peek().equals("sqrt")) {
                        numbers.push(Math.sqrt(numbers.pop()));
                        operators.pop();
                    } else {
                        numbers.push(evaluate(operators.pop(), numbers.pop(), numbers.pop()));
                    }
                }
                operators.push(token);
            }
        }

        while (!operators.empty()) {
            if (operators.peek().equals("sqrt")) {
                numbers.push(Math.sqrt(numbers.pop()));
                operators.pop();
            } else {
                numbers.push(evaluate(operators.pop(), numbers.pop(), numbers.pop()));
            }
        }
        return numbers.pop();

    }

    private static boolean isNumber(String value) {
        if (value.startsWith("-") && value.length() > 1) {
            return true;
        } else if (value.startsWith("0")) {
            return true;
        } else if (value.startsWith("1")) {
            return true;
        } else if (value.startsWith("2")) {
            return true;
        } else if (value.startsWith("3")) {
            return true;
        } else if (value.startsWith("4")) {
            return true;
        } else if (value.startsWith("5")) {
            return true;
        } else if (value.startsWith("6")) {
            return true;
        } else if (value.startsWith("7")) {
            return true;
        } else if (value.startsWith("8")) {
            return true;
        } else if (value.startsWith("9")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOperator(String value) {
        if (value.equals("+")) {
            return true;
        } else if (value.equals("-")) {
            return true;
        } else if (value.equals("*")) {
            return true;
        } else if (value.equals("/")) {
            return true;
        } else if (value.equals("^")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean hasPrecedence(String op1, String op2) {
        if (op2.equals("(") || op2.equals(")")) {
            return false;
        }
        if ((op1.equals("*") || op1.equals("/")) && (op2.equals("+") || op2.equals("-"))) {
            return false;
        }
        if (op1.equals("^") && (op2.equals("/") || op2.equals("*"))) {
            return false;
        }
        if (op1.equals("^") && (op2.equals("-") || op2.equals("+"))) {
            return false;
        }
        if (op1.equals("sqrt") && (op2.equals("/") || op2.equals("*"))) {
            return false;
        }
        if (op1.equals("sqrt") && (op2.equals("-") || op2.equals("+"))) {
            return false;
        } else {
            return true;
        }
    }

    private static double evaluate(String operation, double number2, double number1) {
        if (operation.equals("+")) {
            number1 = number1 + number2;
        } else if (operation.equals("-")) {
            number1 = number1 - number2;
        } else if (operation.equals("*")) {
            number1 = number1 * number2;
        } else if (operation.equals("/")) {
            number1 = number1 / number2;
        } else if (operation.equals("^")) {
            number1 = Math.pow(number1, number2);
        }
        return number1;
    }

}
