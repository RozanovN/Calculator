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
        ArrayList<String> expression = convertToReversePolishNotation(userInput);
        for (int i = 0; i < expression.size(); i++) {
            if (!isOperand(expression.get(i))) {
                try {
                    expression.set(i, evaluateExpression(expression.get(i - 2), expression.get(i), expression.get(i - 1)));
                    expression.remove(i - 1);
                    expression.remove(i - 2);
                    i -= 2;
                }
                catch (IndexOutOfBoundsException exception) {
                    expression.set(i, evaluateExpression("0", expression.get(i), expression.get(i - 1)));
                    expression.remove(i - 1);
                    i -= 1;
                }
            }
        }
        return String.join("", expression);
    }

    private String evaluateExpression(String firstOperand, String operator, String secondOperand) {
        double result = switch (operator) {
            case "+" -> Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand);
            case "-" -> Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand);
            case "/" -> Double.parseDouble(firstOperand) / Double.parseDouble(secondOperand);
            case "*" -> Double.parseDouble(firstOperand) * Double.parseDouble(secondOperand);
            case "^" -> Math.pow(Double.parseDouble(firstOperand), Double.parseDouble(secondOperand));
            case "√" -> Math.pow(Double.parseDouble(firstOperand), 1 / Double.parseDouble(secondOperand));
            case "log" -> Math.log(Double.parseDouble(secondOperand)) / Math.log(Double.parseDouble(firstOperand));
            case "ln" -> Math.log(Double.parseDouble(secondOperand));
            default -> 0;
        };
        return String.valueOf(result);
    }

    private ArrayList<String> convertToReversePolishNotation(@NotNull String expression){
        ArrayList<String> result = new ArrayList<>();
        Stack<String> stackOfOperands = new Stack<>();
        for (String operandOrOperator : expression.strip().split(" "))
            // If operandOrOperator is number, adds it to the result array
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
        // Adds the rest of the stack to the array
        addElementsOfStackToArray(result, stackOfOperands);
        return result;
    }

    private void addElementsOfStackToArray(ArrayList<String> result, Stack<String> stackOfOperands) {
        while (!stackOfOperands.isEmpty() && !stackOfOperands.peek().equals("(")) {
            result.add(stackOfOperands.pop());
        }
        //Removes the opening parenthesis if there's any
        if (!stackOfOperands.isEmpty()) {
            stackOfOperands.pop();
        }

    }

    private void addElementsOfStackToArray(ArrayList<String> result, Stack<String> stackOfOperands, String operator) {
        while (!stackOfOperands.isEmpty() && getOrderValue(operator) <= getOrderValue(stackOfOperands.peek())) {
            result.add(stackOfOperands.pop());
        }
        //Adds the new operator on top of the stack
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
                entry("-", 1)

        );
        return operationOrder.get(operator.strip());
    }
}
