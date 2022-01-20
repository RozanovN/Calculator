import java.util.Map;
import static java.util.Map.entry;
import java.util.ArrayList;
import java.util.Stack;

/**
 * BasicMath represents logic for calculating basic math expressions.
 *
 * @author Nikolay Rozanov
 * @version 2022
 */
public class BasicMath extends Calculator {

    public BasicMath() {
        String[] buttonValues = {
                " ln ", "( ", " )", "DEL", "CLR", " log10 ", "1", "2", "3", " + ", " ln ", "4", "5", "6", " - ", " √ ",
                "7", "8", "9", " / ", "π", "0", ".", "=", " * "
        };
        super.setButtonValues(buttonValues);
    }

    public static String calculateExpression(String userInput) {
        // Convert the math expression to an array using the reverse Polish notation.
        ArrayList<String> expression = convertToReversePolishNotation(userInput);
        // Traverse through the array
        for (int i = 0; i < expression.size(); i++) {
            // If the array element is not an operand (number), evaluate expression.
            if (!isOperand(expression.get(i))) {
                // If there are 2 elements before the encountered operator, evaluate the expression with two operands.
                if (i - 2 >= 0) {
                    expression.set(i, evaluateExpression(expression.get(i - 2), expression.get(i),
                            expression.get(i - 1)));
                    expression.remove(i - 1);
                    expression.remove(i - 2);
                    i -= 2;
                }
                /*
                    If there is only one operand before the encountered operator, evaluate the expression with one
                    operand. Zero is a default value and used as an operand only in some cases like making number
                    negative (-5 + 5).
                */
                else {
                    expression.set(i, evaluateExpression("0", expression.get(i), expression.get(i - 1)));
                    expression.remove(i - 1);
                    i -= 1;
                }
            }
        }
        // Return the converted result as a String.
        return String.join("", expression);
    }

    private static String evaluateExpression(String firstOperand, String operator, String secondOperand) {
        double result = switch (operator) {
            // Evaluate expression based on the operator.
            case "+" -> Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand);
            case "-" -> Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand);
            case "/" -> Double.parseDouble(firstOperand) / Double.parseDouble(secondOperand);
            case "*" -> Double.parseDouble(firstOperand) * Double.parseDouble(secondOperand);
            case "^" -> Math.pow(Double.parseDouble(firstOperand), Double.parseDouble(secondOperand));
            case "√" -> Math.sqrt(Double.parseDouble(secondOperand));
            case "log10" -> Math.log(10) / Math.log(Double.parseDouble(secondOperand));
            case "ln" -> Math.log(Double.parseDouble(secondOperand));
            default -> 0;
        };
        // Display the calculation on screen.
        GUI.addSteps(firstOperand, operator, secondOperand, String.valueOf(result));
        // Return the result as a String.
        return String.valueOf(result);
    }

    private static ArrayList<String> convertToReversePolishNotation(String expression){
        ArrayList<String> result = new ArrayList<>();
        Stack<String> stackOfOperators = new Stack<>();
        for (String operandOrOperator : expression.strip().split(" "))
            // If operandOrOperator is number, add it to the result array.
            if (isOperand(operandOrOperator)) {
                result.add(operandOrOperator);
            }
            // If operandOrOperator is an opening parenthesis, add it to the stack of operators.
            else if (operandOrOperator.equals("(")) {
                stackOfOperators.push(operandOrOperator);
            }
            // If operandOrOperator is a closing parenthesis, add the operators inside parentheses to the result array.
            else if (operandOrOperator.equals(")")) {
                addElementsOfStackToArray(result, stackOfOperators);
            }
            /* If none of the above is true, move operators from the stack to the array and add the
               new operator to the stack.
            */
            else {
                addElementsOfStackToArray(result, stackOfOperators, operandOrOperator);
            }
        // Add the rest of the stack to the array.
        addElementsOfStackToArray(result, stackOfOperators);
        // Return the expression in postfix notation as an ArrayList of strings.
        return result;
    }

    private static void addElementsOfStackToArray(ArrayList<String> result, Stack<String> stackOfOperands) {
        // Move operators from the stack to the array until it finds the opening parenthesis or the stack gets empty.
        while (!stackOfOperands.isEmpty() && !stackOfOperands.peek().equals("(")) {
            result.add(stackOfOperands.pop());
        }
        // If there's an opening parenthesis, remove it from the stack.
        if (!stackOfOperands.isEmpty()) {
            stackOfOperands.pop();
        }

    }

    private static void addElementsOfStackToArray(ArrayList<String> result, Stack<String> stackOfOperands,
                                                  String operator) {
        /* Move operators from the stack to the array while the given operator has a lower priority of operation and
           the stack is not fully empty.
         */
        while (!stackOfOperands.isEmpty() && getOrderValue(operator) <= getOrderValue(stackOfOperands.peek())) {
            result.add(stackOfOperands.pop());
        }
        // Add the new operator on top of the stack.
        stackOfOperands.push(operator);
    }

    private static boolean isOperand(String operandOrOperator) {
        // If string is a number, return true
        try {
            Double.parseDouble(operandOrOperator);
            return true;
        }// else return false
        catch (NumberFormatException exception) {
            return false;
        }
    }

    private static int getOrderValue(String operator) {
        // return the priority of operator
        Map<String, Integer> operationOrder = Map.ofEntries(
                entry("^", 3),
                entry("log", 3),
                entry("ln", 3),
                entry("√", 3),
                entry("/", 2),
                entry("*", 2),
                entry("+", 1),
                entry("-", 1),
                entry("(", -1)
        );
        return operationOrder.get(operator.strip());
    }
}
