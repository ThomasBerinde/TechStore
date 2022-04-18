package presentation.controller;

import bll.ValidationException;
import bll.client.AddressValidator;
import bll.client.AgeValidator;
import bll.client.NameValidator;
import bll.credentials.PasswordValidator;
import bll.credentials.UsernameValidator;
import dao.DataAccessObject;
import dao.FieldsException;
import model.Client;
import model.Credentials;
import presentation.RegistrationPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


public class ControllerRegistrationPage implements ActionListener, WindowListener {

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
            PasswordValidator.validate(REGISTRATION_PAGE.getPasswordTextField1().getText());
            UsernameValidator.validate(REGISTRATION_PAGE.getUsernameTextField().getText());


            if (!REGISTRATION_PAGE.getPasswordTextField1().getText()
                    .equals(REGISTRATION_PAGE.getPasswordTextField2().getText())) {
                throw new ValidationException("Passwords don't match");
            }

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

            JOptionPane.showMessageDialog(
                    REGISTRATION_PAGE.getFrame(),
                    "Registration successful",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Simulates the event of the user pressing the "X" icon or "Alt + F4" on the window
            REGISTRATION_PAGE.getFrame().dispatchEvent(
                    new WindowEvent(
                            REGISTRATION_PAGE.getFrame(),
                            WindowEvent.WINDOW_CLOSING)
            );

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(
                    REGISTRATION_PAGE.getFrame(),
                    "A client with this username already exists",
                    "Registration failed",
                    JOptionPane.WARNING_MESSAGE
            );
            ex.printStackTrace();

        } catch (FieldsException | SQLException ex) {
            JOptionPane.showMessageDialog(
                    REGISTRATION_PAGE.getFrame(),
                    "Could not execute registration. Please try again later",
                    "Unexpected error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(
                    REGISTRATION_PAGE.getFrame(),
                    ex.getMessage(),
                    "Invalid field",
                    JOptionPane.WARNING_MESSAGE
            );
            ex.printStackTrace();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        REGISTRATION_PAGE.getLOGIN_PAGE().getFrame().setEnabled(false);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        REGISTRATION_PAGE.getLOGIN_PAGE().getFrame().setEnabled(true);
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
