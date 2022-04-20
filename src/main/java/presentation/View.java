package presentation;

import exceptions.FieldsException;
import exceptions.SQLScriptsException;
import presentation.view.LoginPage;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class View {

    public static void main(String[] args) {

        String projectPath = System.getProperty("user.dir");
        String scriptsPath = projectPath + "\\src\\main\\resources\\SQLScripts";
        System.out.println(scriptsPath);

        try {
            Utilities.createDatabase(scriptsPath);
            LoginPage loginPage = new LoginPage();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Could not connect to local MySQL server. Make sure you host your local MySQL " +
                            "sever on port 3306 and that the username and password are both \"root\"",
                    "Database connection failed",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Could not create database schema. Missing SQL scripts. They should be at:\n" +
                            "\"" + scriptsPath + "\"\nIf not, please add them there!",
                    "Missing SQL scripts",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        } catch (SQLScriptsException e) {
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Missing SQL scripts",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        } catch (FieldsException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Could not initialize database IDs",
                    "Database initialization failed",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }
}
