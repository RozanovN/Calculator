import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * GUI represents the GUI interface of a calculator.
 *
 * @author Nikolay Rozanov
 * @version 2022
 */
public class GUI implements ActionListener {
    private static JTextArea stepsField;
    private final ArrayList<JButton> arrayOfButtons;
    private final JTextField resultField;
    private final Font TEXT_FONT;
    private final Calculator calculator;

    /**
     * Constructs a GUI interface.
     *
     * @param calculatorType unused for now
     */
    public GUI(final String calculatorType) {
        this.calculator = switch (calculatorType) {
            default -> new BasicMath();
        };

        // Font properties.
        this.TEXT_FONT = new Font("Dialog", Font.BOLD, 15);

        // Result text field properties.
        JTextField resultField = new JTextField();
        resultField.setBounds(50, 25, 440, 50);
        resultField.setBackground(Color.white);
        resultField.setFont(TEXT_FONT);
        resultField.setEditable(false);
        this.resultField = resultField;

        // Result text field properties.
        JTextArea stepsField = new JTextArea();
        stepsField.setBounds(530, 25, 150, 380);
        stepsField.setBackground(Color.white);
        stepsField.setFont(TEXT_FONT);
        stepsField.setEditable(false);
        this.stepsField = stepsField;

        // Buttons of the GUI.
        this.arrayOfButtons = createArrayOfButtons(calculator.getButtonValues());

        // Panel properties.
        JPanel panel = new JPanel();
        panel.setBounds(50, 100, 440, 300);
        panel.setLayout(new GridLayout(5, 5, 10, 10));

        // Frame properties.
        JFrame frame = new JFrame("Basic Calculator");
        frame.add(panel, BorderLayout.CENTER);
        frame.add(resultField);
        frame.add(stepsField);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(740, 470);
        frame.setTitle("Calculator");
        frame.setLayout(null);
        frame.setVisible(true);
        addButtonsToPanel(arrayOfButtons, panel);
    }


    /**
     *  * Responds to the clicked button and invokes different calculator function based on the given input.
     *
     * @param event an ActionEvent
     */
    @Override
    public void actionPerformed(final ActionEvent event) {
         for (JButton button : arrayOfButtons) {
             // Calculate the expression.
             if (button.getText().equals("=") && event.getSource() == button) {
                 String result = calculator.calculateExpression(resultField.getText());
                 resultField.setText(result);
             }
             // Display the pi.
             else if (button.getText().equals("π") && event.getSource() == button) {
                 resultField.setText(resultField.getText().concat(String.valueOf(Math.PI)));
             }
             // Delete the last character.
             else if (button.getText().equals("DEL") && event.getSource() == button) {
                 resultField.setText(resultField.getText().substring(0, resultField.getText().length() - 1));
             }
             // Clear the result and steps text fields.
             else if (button.getText().equals("CLR") && event.getSource() == button) {
                 resultField.setText("");
                 stepsField.setText("");
             }
             else if (event.getSource() == button) {
                 // Add the value of the pressed button to the result text field.
                 resultField.setText(resultField.getText().concat(String.valueOf(button.getText())));
             }
         }
    }

    /**
     * Adds the calculation steps to the stepsField for the user to see.
     *
     * @param firstOperand a String that represents a numeric value
     * @param operator a String
     * @param secondOperand a String that represents a numeric value
     * @param result a String that represents a numeric value
     */
    public static void addSteps(final String firstOperand, final String operator, final String secondOperand,
                                final String result) {
        // Display the calculation step.
        if (operator.equals("log10") || operator.equals("ln")) {
            stepsField.setText(
                    stepsField.getText() + operator + "(" + secondOperand + ") = " + result + "\n"
            );
        } else {
            stepsField.setText(
                    stepsField.getText() + firstOperand + " " + operator + " " + secondOperand + " = " + result + "\n"
            );
        }
    }

    private ArrayList<JButton> createArrayOfButtons(final String[] buttonsValues) {
        ArrayList<JButton> arrayOfButtons = new ArrayList<>();
        for (String value : buttonsValues) {
            JButton newButton = new JButton(value);
            applyFont(newButton);
            applyActionListener(newButton);
            arrayOfButtons.add(newButton);
        }
        return arrayOfButtons;
    }

    private void applyFont(final JButton button) {
        // Apply font to a button.
        button.setFont(TEXT_FONT);
        button.setFocusable(false);
    }

    private void applyActionListener(final JButton button) {
        // Add action listener to a button.
        button.addActionListener(this);
    }

    private void addButtonsToPanel(final ArrayList<JButton> arrayOfButtons, final JPanel panel) {
        // Add every button in the array to the given panel.
        for (JButton button : arrayOfButtons) {
            panel.add(button);
        }
    }
}
