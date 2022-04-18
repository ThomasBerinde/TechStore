package presentation;

import javax.swing.*;
import java.awt.*;

public class Utilities {

    public static JPanel makePanel(Color panelColor, String labelText, Component component, int layoutOption) {

        JPanel panel = new JPanel();

        panel.setLayout(new FlowLayout(layoutOption));
        panel.setBackground(panelColor);
        panel.setOpaque(true);

        JLabel label = new JLabel();
        label.setText(labelText);
        label.setFont(new Font("Calibri", Font.PLAIN, 15));

        panel.add(label);
        if (component != null) {panel.add(component);}

        return panel;
    }

    public static JButton makeButton(Color buttonColor, String buttonText) {

        JButton button = new JButton();

        button.setFont(new Font("Calibri", Font.BOLD, 10));
        button.setText(buttonText);
        button.setPreferredSize(new Dimension(90, 25));
        button.setBackground(buttonColor);
        button.setOpaque(true);

        return button;
    }

    public static JTextField makeTextField(String textFieldText) {

        JTextField textField = new JTextField();

        textField.setText(textFieldText);
        textField.setPreferredSize(new Dimension(150, 25));

        return textField;
    }

}
