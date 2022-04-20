package presentation.view;

import presentation.Utilities;
import presentation.controller.ControllerRegistrationPage;

import javax.swing.*;
import java.awt.*;

public class RegistrationPage {

    private JFrame frame;

    private JPanel usernamePanel;
    private JTextField usernameTextField;

    private JPanel passwordPanel1;
    private JTextField passwordTextField1;

    private JPanel passwordPanel2;
    private JTextField passwordTextField2;

    private JPanel namePanel;
    private JTextField nameTextField;

    private JPanel addressPanel;
    private JTextField addressTextField;

    private JPanel agePanel;
    private JTextField ageTextField;

    private JPanel registrationPanel;
    private JButton registrationButton;

    private final LoginPage LOGIN_PAGE;
    private final ControllerRegistrationPage controller = new ControllerRegistrationPage(this);

    public RegistrationPage(LoginPage loginPage) {

        this.LOGIN_PAGE = loginPage;

        frame = new JFrame("Registration Page");
        frame.addWindowListener(controller);
        frame.setSize(175, 420);
        frame.setLayout(new GridLayout(7, 1));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        usernameTextField = Utilities.makeTextField("");
        usernamePanel = Utilities.makePanel(Color.lightGray, "USERNAME: ", usernameTextField, FlowLayout.LEADING);

        passwordTextField1 = Utilities.makeTextField("");
        passwordPanel1 = Utilities.makePanel(Color.lightGray, "PASSWORD: ", passwordTextField1, FlowLayout.LEADING);

        passwordTextField2 = Utilities.makeTextField("");
        passwordPanel2 = Utilities.makePanel(Color.lightGray, "PASSWORD: ", passwordTextField2, FlowLayout.LEADING);

        nameTextField = Utilities.makeTextField("");
        namePanel = Utilities.makePanel(Color.lightGray, "FULL NAME: ", nameTextField, FlowLayout.LEADING);

        addressTextField = Utilities.makeTextField("");
        addressPanel = Utilities.makePanel(Color.lightGray, "ADDRESS: ", addressTextField, FlowLayout.LEADING);

        ageTextField = Utilities.makeTextField("");
        agePanel = Utilities.makePanel(Color.lightGray, "AGE: ", ageTextField, FlowLayout.LEADING);

        registrationButton = Utilities.makeButton(Color.green, "REGISTER");
        registrationButton.addActionListener(controller);
        registrationPanel = Utilities.makePanel(Color.lightGray, "", registrationButton, FlowLayout.CENTER);

        frame.add(usernamePanel);
        frame.add(passwordPanel1);
        frame.add(passwordPanel2);
        frame.add(namePanel);
        frame.add(addressPanel);
        frame.add(agePanel);
        frame.add(registrationPanel);

        frame.setVisible(true);
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public void setUsernameTextField(JTextField usernameTextField) {
        this.usernameTextField = usernameTextField;
    }

    public JTextField getPasswordTextField1() {
        return passwordTextField1;
    }

    public void setPasswordTextField1(JTextField passwordTextField1) {
        this.passwordTextField1 = passwordTextField1;
    }

    public JTextField getPasswordTextField2() {
        return passwordTextField2;
    }

    public void setPasswordTextField2(JTextField passwordTextField2) {
        this.passwordTextField2 = passwordTextField2;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getUsernamePanel() {
        return usernamePanel;
    }

    public void setUsernamePanel(JPanel usernamePanel) {
        this.usernamePanel = usernamePanel;
    }

    public JPanel getPasswordPanel1() {
        return passwordPanel1;
    }

    public void setPasswordPanel1(JPanel passwordPanel1) {
        this.passwordPanel1 = passwordPanel1;
    }

    public JPanel getPasswordPanel2() {
        return passwordPanel2;
    }

    public void setPasswordPanel2(JPanel passwordPanel2) {
        this.passwordPanel2 = passwordPanel2;
    }

    public JPanel getNamePanel() {
        return namePanel;
    }

    public void setNamePanel(JPanel namePanel) {
        this.namePanel = namePanel;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public JPanel getAddressPanel() {
        return addressPanel;
    }

    public void setAddressPanel(JPanel addressPanel) {
        this.addressPanel = addressPanel;
    }

    public JTextField getAddressTextField() {
        return addressTextField;
    }

    public void setAddressTextField(JTextField addressTextField) {
        this.addressTextField = addressTextField;
    }

    public JPanel getAgePanel() {
        return agePanel;
    }

    public void setAgePanel(JPanel agePanel) {
        this.agePanel = agePanel;
    }

    public JTextField getAgeTextField() {
        return ageTextField;
    }

    public void setAgeTextField(JTextField ageTextField) {
        this.ageTextField = ageTextField;
    }

    public JPanel getRegistrationPanel() {
        return registrationPanel;
    }

    public void setRegistrationPanel(JPanel registrationPanel) {
        this.registrationPanel = registrationPanel;
    }

    public JButton getRegistrationButton() {
        return registrationButton;
    }

    public void setRegistrationButton(JButton registrationButton) {
        this.registrationButton = registrationButton;
    }

    public LoginPage getLOGIN_PAGE() {
        return LOGIN_PAGE;
    }

    public ControllerRegistrationPage getController() {
        return controller;
    }
}
