import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.SecureRandom;

public class RandomPasswordGenerator extends JFrame {
    private JTextField txtLength;
    private JCheckBox chkLowercase, chkUppercase, chkNumbers, chkSpecial;
    private JButton btnGenerate;
    private JTextArea txtPasswordDisplay;

    private final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String NUMBERS = "0123456789";
    private final String SPECIAL = "!@#$%^&*()-_=+[]{};:,.<>?/";

    public RandomPasswordGenerator() {
        setTitle("Random Password Generator");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Password Length:"));
        txtLength = new JTextField(5);
        add(txtLength);

        chkLowercase = new JCheckBox("Include Lowercase");
        chkUppercase = new JCheckBox("Include Uppercase");
        chkNumbers = new JCheckBox("Include Numbers");
        chkSpecial = new JCheckBox("Include Special Characters");

        add(chkLowercase);
        add(chkUppercase);
        add(chkNumbers);
        add(chkSpecial);

        btnGenerate = new JButton("Generate Password");
        txtPasswordDisplay = new JTextArea(3, 30);
        txtPasswordDisplay.setLineWrap(true);
        txtPasswordDisplay.setWrapStyleWord(true);
        txtPasswordDisplay.setEditable(false);

        add(btnGenerate);
        add(new JScrollPane(txtPasswordDisplay));

        btnGenerate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });
    }

    private void generatePassword() {
        StringBuilder charPool = new StringBuilder();

        if (chkLowercase.isSelected()) charPool.append(LOWERCASE);
        if (chkUppercase.isSelected()) charPool.append(UPPERCASE);
        if (chkNumbers.isSelected()) charPool.append(NUMBERS);
        if (chkSpecial.isSelected()) charPool.append(SPECIAL);

        if (charPool.length() == 0) {
            JOptionPane.showMessageDialog(this, "Please select at least one character set.");
            return;
        }

        int length;
        try {
            length = Integer.parseInt(txtLength.getText());
            if (length <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive number for password length.");
            return;
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charPool.length());
            password.append(charPool.charAt(index));
        }

        txtPasswordDisplay.setText("Generated Password:\n" + password.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RandomPasswordGenerator().setVisible(true);
        });
    }
}
