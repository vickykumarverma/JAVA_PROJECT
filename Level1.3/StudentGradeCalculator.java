import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentGradeCalculator extends JFrame {
    private JLabel lblNumGrades;
    private JTextField txtNumGrades;
    private JButton btnEnterGrades;
    private JTextArea txtGradesInput;
    private JButton btnCalculateAverage;
    private JLabel lblResult;

    public StudentGradeCalculator() {
        setTitle("Student Grade Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        lblNumGrades = new JLabel("Enter number of grades:");
        txtNumGrades = new JTextField(10);
        btnEnterGrades = new JButton("Enter Grades");
        txtGradesInput = new JTextArea(10, 30);
        txtGradesInput.setLineWrap(true);
        txtGradesInput.setWrapStyleWord(true);
        txtGradesInput.setEditable(false);

        btnCalculateAverage = new JButton("Calculate Average");
        btnCalculateAverage.setEnabled(false);

        lblResult = new JLabel("Average: ");

        add(lblNumGrades);
        add(txtNumGrades);
        add(btnEnterGrades);
        add(new JScrollPane(txtGradesInput));
        add(btnCalculateAverage);
        add(lblResult);

        btnEnterGrades.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numStr = txtNumGrades.getText();
                try {
                    int numGrades = Integer.parseInt(numStr);
                    if (numGrades <= 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a positive number.");
                        return;
                    }
                    txtGradesInput.setEditable(true);
                    txtGradesInput.setText("");
                    txtGradesInput.append("Enter " + numGrades + " grades (separated by spaces):\n");
                    btnCalculateAverage.setEnabled(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                }
            }
        });

        btnCalculateAverage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = txtGradesInput.getText().replaceAll("[^0-9\\s]", "");
                String[] tokens = input.trim().split("\\s+");
                try {
                    int[] grades = new int[tokens.length];
                    int sum = 0;

                    for (int i = 0; i < tokens.length; i++) {
                        grades[i] = Integer.parseInt(tokens[i]);
                        if (grades[i] < 0 || grades[i] > 100) {
                            throw new NumberFormatException("Grade out of range");
                        }
                        sum += grades[i];
                    }

                    double average = (double) sum / grades.length;
                    lblResult.setText(String.format("Average: %.2f", average));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid grades entered. Please enter only numbers between 0 and 100.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentGradeCalculator().setVisible(true);
        });
    }
}
