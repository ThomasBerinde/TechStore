package presentation.controller;

import bll.client.AddressValidator;
import bll.client.AgeValidator;
import bll.client.NameValidator;
import dao.DataAccessObject;
import dao.FieldsException;
import model.Client;
import model.Credentials;
import presentation.RegistrationPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControllerRegistrationPage implements ActionListener {

    private final RegistrationPage REGISTRATION_PAGE;
    private final DataAccessObject<Credentials> CREDENTIALS_DAO;
    private final DataAccessObject<Client> CLIENT_DAO;

    public ControllerRegistrationPage(RegistrationPage registrationPage) {
        this.REGISTRATION_PAGE = registrationPage;
        this.CREDENTIALS_DAO = new DataAccessObject<>(Credentials.class);
        this.CLIENT_DAO = new DataAccessObject<>(Client.class);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            AddressValidator.validate(REGISTRATION_PAGE.getAddressTextField().getText());
            AgeValidator.validate(REGISTRATION_PAGE.getAgeTextField().getText());
            NameValidator.validate(REGISTRATION_PAGE.getNameTextField().getText());
            //PasswordValidator.validate(registrationPage.getPasswordTextField1().getText());
            //UsernameValidator.validate(registrationPage.getUsernameTextField().getText());

        } catch (FieldsException ex) {
            JOptionPane.showMessageDialog(
                    REGISTRATION_PAGE.getFrame(),
                    ex.getMessage(),
                    "Invalid field",
                    JOptionPane.WARNING_MESSAGE
            );
            ex.printStackTrace();
        }

        if (!REGISTRATION_PAGE.getPasswordTextField1().getText()
                .equals(REGISTRATION_PAGE.getPasswordTextField2().getText())) {
            JOptionPane.showMessageDialog(
                    REGISTRATION_PAGE.getFrame(),
                    "Passwords don't match",
                    "Invalid field",
                    JOptionPane.WARNING_MESSAGE
            );
        }

        try {

            Long id = DatabaseOperation.getNextId(new Client());

            Credentials credentials = new Credentials(
                    id,
                    REGISTRATION_PAGE.getUsernameTextField().getText(),
                    REGISTRATION_PAGE.getPasswordTextField1().getText()
            );

            Client client = new Client(
                    id,
                    REGISTRATION_PAGE.getNameTextField().getText(),
                    Long.parseLong(REGISTRATION_PAGE.getAgeTextField().getText()),
                    REGISTRATION_PAGE.getAddressTextField().getText()
            );

            CREDENTIALS_DAO.insert(credentials);
            CLIENT_DAO.insert(client);

        } catch (FieldsException ex) {
            JOptionPane.showMessageDialog(
                    REGISTRATION_PAGE.getFrame(),
                    "Could not execute registration. Please try again later",
                    "Unexpected error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(
                REGISTRATION_PAGE.getFrame(),
                "Registration successful",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
