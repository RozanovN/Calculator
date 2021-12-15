import java.util.Map;
import static java.util.Map.entry;
import java.util.ArrayList;
import java.util.Collections;

public class BasicMath {

    public BasicMath() {
    }

    public int calculation(String[] listOfOperations) {

    }

    public static ArrayList<String> determineOperations(String userInput){
        String[] arrayOfInput = userInput.split(" ");
        if (arrayOfInput.length % 2 == 0) {

        }
    }

    public static ArrayList<String> determineOrderOfOperation(ArrayList<String> listOfOperations) {
        Map<Character, Integer> operationOrder = Map.ofEntries(
                entry('(', 3),
                entry('/', 2),
                entry('*', 2),
                entry('+', 1),
                entry('-', 1)
        );
        ArrayList<String> result = new ArrayList<>(listOfOperations);
        for (int i = 0; i < result.size() - 1; i++) {
            for (int j = 0; j < result.size(); j++) {
                if ((operationOrder.get(result.get(i).charAt(0)).compareTo(operationOrder.get(result.get(j).charAt(0))))
                    < 0){
                    String temp = result.get(i);
                    result.set(i, result.get(j));
                    result.set(j, temp);
                }
            }
        }
        // result.sort(Collections.reverseOrder());
        // returns a sorted list of integers
        return result;
    }
}
