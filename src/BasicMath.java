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

    private String[] parseExpression(String expression) {
        return expression.split(" ");
    }

    public ArrayList<Object> convertToReversePolishNotation(@NotNull String expression){
        ArrayList<Object> result = new ArrayList<>();
        Stack<String> stackOfOperands = new Stack<>();


        return result;
    }

    private boolean isDigit(String operandOrOperator) {
        try {
            Double.parseDouble(operandOrOperator);
            return true;
        }
        catch (NumberFormatException exception) {
            return false;
        }
    }

    private int determineOrderOfOperation(String operatorOrOperand) {
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
        return operationOrder.get(operatorOrOperand.strip());
    }
}
