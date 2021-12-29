import org.jetbrains.annotations.NotNull;
import java.util.Map;
import static java.util.Map.entry;
import java.util.ArrayList;
import java.util.Stack;
import java.lang.Math;

public class BasicMath {

    public BasicMath() {
    }

    public String calculateExpression(String userInput) {
        // Convert the math expression to an array using reverse Polish notation.
        ArrayList<String> expression = convertToReversePolishNotation(userInput);
        // Traverse through the array
        for (int i = 0; i < expression.size(); i++) {
            // If the array element is not an operand (number), evaluate expression.
            if (!isOperand(expression.get(i))) {
                // If there are 2 elements before the encountered operator, evaluate the expression with two operands.
                if (i - 2 >= 0) {
                    expression.set(i, evaluateExpression(expression.get(i - 2), expression.get(i), expression.get(i - 1)));
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

    private String evaluateExpression(String firstOperand, String operator, String secondOperand) {
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
        GUI.addSteps(firstOperand, operator, secondOperand, String.valueOf(result));
        return String.valueOf(result);
    }

    private ArrayList<String> convertToReversePolishNotation(@NotNull String expression){
        ArrayList<String> result = new ArrayList<>();
        Stack<String> stackOfOperands = new Stack<>();
        for (String operandOrOperator : expression.strip().split(" ")) // test (\\d)+ later
            // If operandOrOperator is number, adds it to the result array.
            if (isOperand(operandOrOperator)) {
                result.add(operandOrOperator);
            }
            else if (operandOrOperator.strip().equals("(")) {
                stackOfOperands.push(operandOrOperator.strip());
            }
            else if (operandOrOperator.strip().equals(")")) {
                addElementsOfStackToArray(result, stackOfOperands);
            }
            else {
                addElementsOfStackToArray(result, stackOfOperands, operandOrOperator);
            }
        // Adds the rest of the stack to the array.
        addElementsOfStackToArray(result, stackOfOperands);
        return result;
    }

    private void addElementsOfStackToArray(ArrayList<String> result, Stack<String> stackOfOperands) {
        while (!stackOfOperands.isEmpty() && !stackOfOperands.peek().equals("(")) {
            result.add(stackOfOperands.pop());
        }
        // Removes the opening parenthesis if there's any.
        if (!stackOfOperands.isEmpty()) {
            stackOfOperands.pop();
        }

    }

    private void addElementsOfStackToArray(ArrayList<String> result, Stack<String> stackOfOperands, String operator) {
        while (!stackOfOperands.isEmpty() && getOrderValue(operator) <= getOrderValue(stackOfOperands.peek())) {
            result.add(stackOfOperands.pop());
        }
        // Adds the new operator on top of the stack.
        stackOfOperands.push(operator);
    }

    private boolean isOperand(String operandOrOperator) {
        try {
            Double.parseDouble(operandOrOperator);
            return true;
        }
        catch (NumberFormatException exception) {
            return false;
        }
    }

    private int getOrderValue(String operator) {
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
