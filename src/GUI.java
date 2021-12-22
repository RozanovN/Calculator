import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener {

    public GUI() {
        //
        double
        // Number Buttons
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
        JButton[] buttonsForNumber = {buttonForOne, buttonForTwo, buttonForThree, buttonForFour, buttonForFive,
                                      buttonForSix, buttonForSeven, buttonForEight, buttonForNine, buttonForZero};
        applyActionListener(buttonsForNumber);
        // Operand buttons
        JButton buttonForPlus = new JButton("+");
        JButton buttonForMinus = new JButton("-");
        JButton buttonForDivide = new JButton("/");
        JButton buttonForMultiply = new JButton("*");
        JButton buttonForEquals = new JButton("=");
        JButton buttonForDelete = new JButton("DEL");
        JButton buttonForClear = new JButton("CLR");
        JButton[] buttonsForOperands = {buttonForPlus, buttonForMinus, buttonForDivide, buttonForMultiply,
                                        buttonForEquals};

//        JLabel resultOfCalculation = new JLabel("Result goes here");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(4, 4));
//        panel.add(buttonForOne, buttonForTwo, buttonForThree, buttonForFour, buttonForFive, buttonForSix,
//                buttonForSeven, buttonForEight, buttonForNine, buttonForZero, buttonForPlus, buttonForMinus,
//                buttonForDivide, buttonForMultiply, resultOfCalculation);

        JTextField resultField = new JTextField();
        resultField.setBounds(50, 25, 300, 50);
        Font textFont = new Font("Dialog", Font.BOLD, 45);
        resultField.setFont(textFont);
        resultField.setEditable(false);

        JFrame frame = new JFrame("Basic Calculator");
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 720);
        frame.setTitle("Calculator");
        frame.setLayout(null);
//        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void applyFont(JButton[] arrayOfButtons, Font font) {
        for (JButton button : arrayOfButtons){
            button.setFont(font);
            // button.setFocusable(false);
        }
    }


}
