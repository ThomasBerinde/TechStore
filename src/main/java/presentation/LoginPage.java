package presentation;

import presentation.controller.ControllerLoginPage;

import javax.swing.*;
import java.awt.*;

public class LoginPage  {

    private JFrame frame;

    private JPanel usernamePanel;
    private JTextField usernameTextField;

    private JPanel passwordPanel;
    private JTextField passwordTextField;

    private JPanel loginPanel;
    private JButton loginButton;

    private JPanel registerPanel;
    private JButton registerButton;

    private final ControllerLoginPage controller = new ControllerLoginPage(this);

    public LoginPage() {

        frame = new JFrame("LoginPage page");
        frame.setLayout(new GridLayout(4, 1));
        frame.setSize(new Dimension(250, 200));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.cyan);

        usernameTextField = Utilities.makeTextField("");
        passwordTextField = Utilities.makeTextField("");

        loginButton = Utilities.makeButton(Color.green, "LOGIN");
        loginButton.addActionListener(controller);
        registerButton = Utilities.makeButton(Color.lightGray, "REGISTER");
        registerButton.addActionListener(controller);

        usernamePanel = Utilities.makePanel(Color.lightGray, "USER  ", usernameTextField, FlowLayout.LEADING);
        passwordPanel = Utilities.makePanel(Color.lightGray, "PASS  ", passwordTextField, FlowLayout.LEADING);
        loginPanel = Utilities.makePanel(Color.lightGray, "", loginButton, FlowLayout.CENTER);
        registerPanel = Utilities.makePanel(Color.lightGray, "", registerButton, FlowLayout.CENTER);


        frame.add(usernamePanel);
        frame.add(passwordPanel);
        frame.add(loginPanel);
        frame.add(registerPanel);

        frame.setVisible(true);
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

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public void setUsernameTextField(JTextField usernameTextField) {
        this.usernameTextField = usernameTextField;
    }

    public JPanel getPasswordPanel() {
        return passwordPanel;
    }

    public void setPasswordPanel(JPanel passwordPanel) {
        this.passwordPanel = passwordPanel;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    public void setPasswordTextField(JTextField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public void setLoginPanel(JPanel loginPanel) {
        this.loginPanel = loginPanel;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public JPanel getRegisterPanel() {
        return registerPanel;
    }

    public void setRegisterPanel(JPanel registerPanel) {
        this.registerPanel = registerPanel;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(JButton registerButton) {
        this.registerButton = registerButton;
    }

    public ControllerLoginPage getController() {
        return controller;
    }
}
