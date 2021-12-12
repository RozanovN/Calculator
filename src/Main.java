import java.util.Scanner;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        processInput();
    }

    public static void processInput() {
        Scanner input = new Scanner(System.in);
        boolean isFinished = false;
        System.out.println("Please, chose the type of calculator from following Algebra, Basic Math, Calculus," +
                " Linear Algebra :");
        String userInput = input.nextLine();
        while (!isFinished) {
            if (userInput.equals("q") || userInput.equals("quit")) {
                isFinished = true;
            }
            if (verifyInput(userInput, "calculator type")) {
                calulator(userInput);
            } else{
                System.out.println(userInput + "is not valid type of calculator. Please, try again:");
            }
        }
    }

    public static boolean verifyInput(String userInput, String typeOfInput) {
        // type of input will be implemented later
        String[] typesOfCalculators = {"Algebra", "Basic Math", "Calculus", "Linear Algebra"};
        for (String type : typesOfCalculators) {
            if (type.equals(userInput)){
                return true;
            }
        }
        return false;
    }
}
