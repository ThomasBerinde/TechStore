package presentation.controller;

import bll.credentials.CredentialsCheck;
import dao.DataAccessObject;
import dao.FieldsException;
import model.Client;
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
//    private final DataAccessObject<Credentials> CREDENTIALS_DAO;
//    private final DataAccessObject<Client> CLIENT_DAO;

    public ControllerLoginPage(LoginPage loginPage) {
        this.LOGIN_PAGE = loginPage;
//        this.CREDENTIALS_DAO = new DataAccessObject<>(Credentials.class);
//        this.CLIENT_DAO = new DataAccessObject<>(Client.class);
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
            RegistrationPage registrationPage = new RegistrationPage();
            LOGIN_PAGE.getFrame().dispose();
        }
    }
}
