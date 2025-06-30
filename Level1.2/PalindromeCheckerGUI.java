import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PalindromeCheckerGUI extends JFrame {

    private JTextField inputField;
    private JButton checkButton;
    private JLabel resultLabel;

    public PalindromeCheckerGUI() {
        setTitle("Palindrome Checker");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // UI Components
        inputField = new JTextField(20);
        checkButton = new JButton("Check");
        resultLabel = new JLabel("Result will appear here");

        // Layout
        setLayout(new FlowLayout());
        add(new JLabel("Enter a word or phrase:"));
        add(inputField);
        add(checkButton);
        add(resultLabel);

        // Button action
        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkPalindrome();
            }
        });
    }

    private void checkPalindrome() {
        String input = inputField.getText();
        String cleaned = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();

        if (cleaned.equals(reversed)) {
            resultLabel.setText(" It is a palindrome!");
        } else {
            resultLabel.setText(" Not a palindrome.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PalindromeCheckerGUI().setVisible(true);
        });
    }
}
