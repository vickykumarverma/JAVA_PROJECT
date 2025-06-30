import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverterGUI extends JFrame {

    private JTextField tempInput;
    private JComboBox<String> unitSelector;
    private JButton convertButton;
    private JLabel resultLabel;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tempInput = new JTextField(10);
        String[] units = {"Celsius to Fahrenheit", "Fahrenheit to Celsius"};
        unitSelector = new JComboBox<>(units);
        convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result will appear here");

        setLayout(new FlowLayout());
        add(new JLabel("Enter Temperature:"));
        add(tempInput);
        add(unitSelector);
        add(convertButton);
        add(resultLabel);

        convertButton.addActionListener(e -> convertTemperature());
    }

    private void convertTemperature() {
        try {
            double inputTemp = Double.parseDouble(tempInput.getText());
            String selectedConversion = (String) unitSelector.getSelectedItem();

            if (selectedConversion.equals("Celsius to Fahrenheit")) {
                double result = (inputTemp * 9 / 5) + 32;
                resultLabel.setText(String.format("%.2f°C = %.2f°F", inputTemp, result));
            } else {
                double result = (inputTemp - 32) * 5 / 9;
                resultLabel.setText(String.format("%.2f°F = %.2f°C", inputTemp, result));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
