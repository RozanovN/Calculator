import org.jetbrains.annotations.NotNull;
import java.util.Map;
import static java.util.Map.entry;
import java.util.ArrayList;
import java.util.Stack;

public class BasicMath {

    public BasicMath() {
    }

    public int calculation(String[] listOfOperations) {
        return -1;
    }




    private void addElementsOfStackToArray(ArrayList<String> result, Stack<String> stackOfOperands) {
        while (!stackOfOperands.isEmpty() && !stackOfOperands.peek().equals("(")) {
            result.add(stackOfOperands.pop());
        }
        //Removes the opening parenthesis
        stackOfOperands.pop();
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
