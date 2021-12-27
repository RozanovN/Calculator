import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI implements ActionListener {
    private final JButton[] arrayOfButtons;
    private JTextField resultField;

    public GUI() {
        // Text field properties
        JTextField resultField = new JTextField();
        resultField.setBounds(50, 25, 450, 50);
        Font textFont = new Font("Dialog", Font.BOLD, 20);
        resultField.setFont(textFont);
        resultField.setEditable(false);
        this.resultField = resultField;

        // Buttons properties
        JButton buttonForOne = new JButton("1");
        JButton buttonForTwo = new JButton("2");
        JButton buttonForThree = new JButton("3");
        JButton buttonForFour = new JButton("4");
        JButton buttonForFive = new JButton("5");
        JButton buttonForSix = new JButton("6");
        JButton buttonForSeven = new JButton("7");
        JButton buttonForEight = new JButton("8");
        JButton buttonForNine = new JButton("9");
        JButton buttonForZero = new JButton("0");
        JButton buttonForPlus = new JButton(" + ");
        JButton buttonForMinus = new JButton(" — ");
        JButton buttonForDivide = new JButton(" / ");
        JButton buttonForMultiply = new JButton(" * ");
        JButton buttonForEquals = new JButton(" = ");
        JButton buttonForDelete = new JButton("DEL");
        JButton buttonForClear = new JButton("CLR");
        JButton buttonForDot = new JButton(".");
        JButton buttonForPower = new JButton(" ^ ");
        JButton buttonForLog = new JButton(" ln ");
        JButton buttonForLn = new JButton(" log ");
        JButton buttonForRoot = new JButton(" √ ");
        JButton buttonForPi = new JButton("π");
        JButton buttonForLeftParenthesis = new JButton(" ( ");
        JButton buttonForRightParenthesis = new JButton(" ) ");
        JButton[] arrayOfButtons = {
                buttonForPower, buttonForLeftParenthesis, buttonForRightParenthesis, buttonForDelete, buttonForClear,
                buttonForLog, buttonForOne, buttonForTwo, buttonForThree, buttonForPlus,
                buttonForLn, buttonForFour, buttonForFive, buttonForSix, buttonForMinus,
                buttonForRoot, buttonForSeven, buttonForEight, buttonForNine, buttonForDivide,
                buttonForPi, buttonForZero, buttonForDot, buttonForEquals, buttonForMultiply
        };
        this.arrayOfButtons = arrayOfButtons;
        applyActionListener(arrayOfButtons);
        applyFont(arrayOfButtons, textFont);

        // Panel properties
        JPanel panel = new JPanel();
        panel.setBounds(50, 100, 450, 500);
        //panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(5, 5, 10, 10));
        //panel.setBackground(Color.BLACK);

        // Frame properties
        JFrame frame = new JFrame("Basic Calculator");
        frame.add(panel, BorderLayout.CENTER);
        frame.add(resultField);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 720);
        frame.setTitle("Calculator");
        frame.setLayout(null);
//        frame.pack();
        frame.setVisible(true);
        addButtonsToPanel(arrayOfButtons, panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
         for (JButton button : arrayOfButtons) {
             if (button.getText().equals("=") && event.getSource() == button) {
                 BasicMath calculator = new BasicMath();
                 String result = calculator.calculateExpression(resultField.getText());
                 resultField.setText(result);
             }
             else if (button.getText().equals("DEL") && event.getSource() == button) {
                 resultField.setText(resultField.getText().substring(0, resultField.getText().length() - 1));
             }
             else if (button.getText().equals("CLR") && event.getSource() == button) {
                 resultField.setText("");
             }
             else if (event.getSource() == button) {
                 // Adds the value of the pressed button to the text field
                 resultField.setText(resultField.getText().concat(String.valueOf(button.getText())));
             }
         }
    }


    private void applyFont(JButton[] arrayOfButtons, Font font) {
        for (JButton button : arrayOfButtons){
            button.setFont(font);
            button.setFocusable(false);
        }
    }

    private void applyActionListener(JButton[] arrayOfButtons) {
        for (JButton button : arrayOfButtons){
            button.addActionListener(this);
        }
    }

    private void addButtonsToPanel(JButton[] arrayOfButtons, JPanel panel) {
        for (JButton button : arrayOfButtons){
            panel.add(button);
        }
    }
}
