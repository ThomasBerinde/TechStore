package presentation.controller;

import bll.credentials.CredentialsCheck;
import dao.FieldsException;
import model.Credentials;
import presentation.AdminPage;
import presentation.ClientPage;
import presentation.LoginPage;
import presentation.RegistrationPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerLoginPage implements ActionListener {

    private final LoginPage LOGIN_PAGE;

    public ControllerLoginPage(LoginPage loginPage) {
        this.LOGIN_PAGE = loginPage;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == LOGIN_PAGE.getLoginButton()) {

            Credentials credentials = new Credentials(
                    LOGIN_PAGE.getUsernameTextField().getText(),
                    LOGIN_PAGE.getPasswordTextField().getText()
            );

            if (credentials.getUsername().equals("admin")) {
                if (credentials.getPassword().equals("admin")) {
                    AdminPage adminPage = new AdminPage();
                    LOGIN_PAGE.getFrame().dispose();
                }
                else {
                    JOptionPane.showMessageDialog(
                            LOGIN_PAGE.getFrame(),
                            "Wrong password",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
                return;
            }

            Long userId = -1L;

            try {
                userId = CredentialsCheck.check(credentials);
            } catch (FieldsException ex) {
                JOptionPane.showMessageDialog(
                        LOGIN_PAGE.getFrame(),
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
                return;
            }

            if (userId > 0) {
                credentials.setId(userId);
                ClientPage clientPage = new ClientPage(credentials);
                LOGIN_PAGE.getFrame().dispose();
            }

        }
        else if (e.getSource() == LOGIN_PAGE.getRegisterButton()) {
            RegistrationPage registrationPage = new RegistrationPage(this.LOGIN_PAGE);
        }
    }
}
