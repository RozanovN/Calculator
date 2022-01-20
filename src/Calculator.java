/**
 * A parent class for different types of calculators.
 *
 * @author Nikolay Rozanov
 * @version 2022
 */
public class Calculator {
    private String[] BUTTON_VALUES;

    /**
     * Returns the value of BUTTON_VALUES.
     *
     */
    public String[] getButtonValues() {
        return BUTTON_VALUES;
    }

    /**
     * Sets the value of BUTTON_VALUES.
     *
     * @param buttonValues a String array of values
     */
    public void setButtonValues(final String[] buttonValues) {
        this.BUTTON_VALUES = buttonValues;
    }
}
