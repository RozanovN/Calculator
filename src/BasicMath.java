import org.jetbrains.annotations.NotNull;
import java.util.regex.*;
import java.util.Map;
import static java.util.Map.entry;
import java.util.ArrayList;
import java.util.Collections;

public class BasicMath {

    public BasicMath() {
    }

    public int calculation(String[] listOfOperations) {
        return -1;
    }

    private ArrayList<Object> parseExpression(String expression) {
        String[] tokensOfExpression = expression.split(" ");

    }

    public ArrayList<String> determineOperations(@NotNull String userInput){
        ArrayList<String> result = new ArrayList<>();
        Pattern expression = Pattern.compile("\\(?(\\d+)(\\+|-|/|\\*)(\\d+)\\)?");

        return result;
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
