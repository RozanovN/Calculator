import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    public GUI() {
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
        JButton buttonForPlus = new JButton("+");
        JButton buttonForMinus = new JButton("-");
        JButton buttonForDivide = new JButton("/");
        JButton buttonForMultiply = new JButton("*");
        buttonForOne.addActionListener(this);
        buttonForTwo.addActionListener(this);
        buttonForThree.addActionListener(this);
        buttonForFour.addActionListener(this);
        buttonForFive.addActionListener(this);
        buttonForSix.addActionListener(this);
        buttonForSeven.addActionListener(this);
        buttonForEight.addActionListener(this);
        buttonForNine.addActionListener(this);
        buttonForZero.addActionListener(this);
        buttonForPlus.addActionListener(this);
        buttonForMinus.addActionListener(this);
        buttonForDivide.addActionListener(this);
        buttonForMultiply.addActionListener(this);
        JLabel resultOfCalculation = new JLabel("Result goes here");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(4, 4));
        panel.add(buttonForOne, buttonForTwo, buttonForThree, buttonForFour, buttonForFive, buttonForSix,
                buttonForSeven, buttonForEight, buttonForNine, buttonForZero, buttonForPlus, buttonForMinus,
                buttonForDivide, buttonForMultiply, resultOfCalculation);

        JFrame frame = new JFrame();
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculator");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
