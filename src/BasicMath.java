import java.util.Map;
import static java.util.Map.entry;
import java.util.ArrayList;
import java.util.Collections;

public class BasicMath {

    public BasicMath() {
    }

    public int calculation(String[] listOfOperations) {

    }

    public static ArrayList<Integer> determineOrderOfOperation(String[] listOfOperations) {
        Map<Character, Integer> operationOrder = Map.ofEntries(
                entry('(', 3),
                entry('/', 2),
                entry('*', 2),
                entry('+', 1),
                entry('-', 1)
        );
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (String operation : listOfOperations) {
            // Add
            result.add(operationOrder.get(operation.charAt(0)));
        }
        return result;
    }

    pablic static void sortInDescendingOrder(ArrayList<?> listToSort) {

    }
}
