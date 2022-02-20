/**
 * A parent class for different types of calculators.
 *
 * @author Nikolay Rozanov
 * @version 2022
 */
public interface Calculator {

    /**
     * Calculates expression.
     *
     * @param userInput a String that represents the expression given by the user
     * @return the result of expression as a String
     */
    String calculateExpression(String userInput);

    /**
     * Gets button values.
     *
     * @return an array of strings representing button values
     */
    String[] getButtonValues();
}
