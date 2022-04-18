package presentation.controller;

import dao.DataAccessObject;
import dao.FieldsException;
import model.Order;
import presentation.ClientPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                    Long id = DatabaseOperation.getNextId(new Order());
                    ORDER_DAO.insert(
                            new Order(
                                    id,
                                    CLIENT_PAGE.getCredentials().getId(),
                                    Long.parseLong(productId)
                            )
                    );
                } catch (FieldsException ex) {
                    JOptionPane.showMessageDialog(
                            CLIENT_PAGE.getFrame(),
                            "Order failed. Please try again later",
                            "Order failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                    ex.printStackTrace();
                    return;
                }

                JOptionPane.showMessageDialog(
                        CLIENT_PAGE.getFrame(),
                        "You have ordered one " + productName,
                        "Order successful",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }
}
