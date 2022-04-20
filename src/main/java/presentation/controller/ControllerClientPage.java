package presentation.controller;

import com.itextpdf.text.DocumentException;
import dao.DataAccessObject;
import exceptions.FieldsException;
import model.Client;
import model.Order;
import model.Product;
import presentation.view.ClientPage;
import presentation.view.LoginPage;
import presentation.Utilities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class ControllerClientPage implements ActionListener {

    private final ClientPage CLIENT_PAGE;
    private final DataAccessObject<Order> ORDER_DAO;

    public ControllerClientPage(ClientPage clientPage) {
        this.CLIENT_PAGE = clientPage;
        this.ORDER_DAO = new DataAccessObject<>(Order.class);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == CLIENT_PAGE.getOrderButton()) {
            int[] selectedRows = CLIENT_PAGE.getProductsTable().getSelectedRows();

            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(
                        CLIENT_PAGE.getFrame(),
                        "You must select a product to order it",
                        "Invalid order",
                        JOptionPane.WARNING_MESSAGE
                );
            }
            else if (selectedRows.length > 1) {
                JOptionPane.showMessageDialog(
                        CLIENT_PAGE.getFrame(),
                        "Cannot order more than one product at a time",
                        "Invalid order",
                        JOptionPane.WARNING_MESSAGE
                );
            }
            else {

                Client client;
                Product product;
                Order order;

                String productName =
                        (String) CLIENT_PAGE
                                .getProductsTable()
                                .getModel()
                                .getValueAt(selectedRows[0], 1);
                String productId =
                        (String) CLIENT_PAGE
                                .getProductsTable()
                                .getModel()
                                .getValueAt(selectedRows[0], 0);

                try {
                    client = (new DataAccessObject<>(Client.class))
                            .findById(CLIENT_PAGE.getCredentials().getId().toString());

                    product = (new DataAccessObject<>(Product.class))
                            .findById(productId);

                    Long id = DatabaseOperation.getNextId(new Order());
                    order = new Order(id, CLIENT_PAGE.getCredentials().getId(), Long.parseLong(productId));
                    ORDER_DAO.insert(order);

                    Utilities.generateInvoice(client, product, order);

                    JOptionPane.showMessageDialog(
                            CLIENT_PAGE.getFrame(),
                            "You have ordered one " + productName,
                            "Order successful",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } catch (FieldsException | SQLException ex) {
                    JOptionPane.showMessageDialog(
                            CLIENT_PAGE.getFrame(),
                            "Order failed. Please try again later",
                            "Order failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                    ex.printStackTrace();

                } catch (FileNotFoundException | DocumentException ex) {
                    JOptionPane.showMessageDialog(
                            CLIENT_PAGE.getFrame(),
                            "Order succeeded. Could not create create invoice.",
                            "Unexpected error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    ex.printStackTrace();
                }
            }
        }
        else if (e.getSource() == CLIENT_PAGE.getLogoutButton()) {

            CLIENT_PAGE.getFrame().dispose();
            LoginPage loginPage = new LoginPage();
        }
    }
}
