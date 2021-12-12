import java.util.Scanner;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        processInput();
    }

    public static void processInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please, chose the type of calculator from following Algebra, Basic Math, Calculus," +
                " Linear Algebra :");
        String userInput = input.nextLine();
        while (true) {
            if (userInput.equals("q") || userInput.equals("quit")) {
                System.exit(0);
            }
            if (verifyInput(userInput, "calculator type")) {
                calculator(userInput);
            } else{
                System.out.println(userInput + " is not valid type of calculator. Please, try again:");
            }
            System.out.println("If you no longer need CalcApp, please, enter <q> or <quit>. If you want to change" +
                    "the type of calculator, please, choose from following Algebra, Basic Math, Calculus, " +
                    "Linear Algebra:");
            userInput = input.nextLine();
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

    public static void calculator(String typeOfCalculator){
        System.out.println(typeOfCalculator);
    }
}
