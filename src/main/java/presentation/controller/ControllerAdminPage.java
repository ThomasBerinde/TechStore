package presentation.controller;

import exceptions.FieldsException;
import model.Product;
import presentation.*;
import presentation.view.AdminPage;
import presentation.view.InsertProductPage;
import presentation.view.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerAdminPage implements ActionListener {

    private enum Items {
        CLIENTS, PRODUCTS
    }

    private final AdminPage ADMIN_PAGE;
    private JTable productTable;
    private JTable clientTable;
    private final JTable emptyProductTable;
    private final JTable emptyClientTable;
    private Items items;

    private final String[] productColumns = {"product_id", "product_name", "product_price", "product_category"};
    private final String[] clientColumns = {"client_id", "client_name", "client_age", "client_address"};

    public ControllerAdminPage(AdminPage adminPage) {
        this.ADMIN_PAGE = adminPage;

        /** Create a generic product table with no items */
        this.emptyProductTable = new JTable(new String[1][4], productColumns);

        /** Create a generic client table with no items */
        this.emptyClientTable = new JTable(new String[1][4], clientColumns);

        /** Get all the products from the database */
        try {
            this.productTable = new JTable(DatabaseOperation.getProducts(), productColumns);
        } catch (FieldsException e) {
            this.productTable = null;
        }

        /** Get all the clients from the database */
        try {
            this.clientTable = new JTable(DatabaseOperation.getClients(), productColumns);
        } catch (FieldsException e) {
            this.clientTable = null;
        }

    }

    public void initializeItems() {
        this.items = Items.CLIENTS;
        switchItems();
    }

    public void reloadTable() {

        if (items.equals(Items.PRODUCTS)) {
            try {
                this.productTable = new JTable(DatabaseOperation.getProducts(), productColumns);
            } catch (FieldsException e) {
                this.productTable = null;
            }
            ADMIN_PAGE.setTable(this.productTable);
        }
        else if (items.equals(Items.CLIENTS)) {
            try {
                this.clientTable = new JTable(DatabaseOperation.getClients(), clientColumns);
            } catch (FieldsException e) {
                this.clientTable = null;
            }
            ADMIN_PAGE.setTable(this.clientTable);
        }

        ADMIN_PAGE.getScrollPane().setViewportView(ADMIN_PAGE.getTable());

        ADMIN_PAGE.getTable().revalidate();
        ADMIN_PAGE.getTable().repaint();
        ADMIN_PAGE.getScrollPane().revalidate();
        ADMIN_PAGE.getButtonsPanel().repaint();
    }

    private void switchItems() {

        if (items.equals(Items.CLIENTS)) {
            /** Switch page button */
            ADMIN_PAGE.getSwitchPageButton().setText("CLIENTS");

            /** Table */
            if (productTable != null) {
                ADMIN_PAGE.setTable(productTable);
            }
            else {
                ADMIN_PAGE.setTable(emptyProductTable);
                JOptionPane.showMessageDialog(
                        ADMIN_PAGE.getFrame(),
                        "There are currently no products in the store",
                        "Empty store",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            /** Title panel*/
            ADMIN_PAGE.setTitlePanel(Utilities.makePanel(Color.cyan, "PRODUCTS", null, FlowLayout.CENTER));
            ADMIN_PAGE.getBorderLayoutPanel().add(ADMIN_PAGE.getTitlePanel(), BorderLayout.NORTH);

            /** Update item button */
            ADMIN_PAGE.getUpdateItemButton().setVisible(true);

            /** Insert item button */
            ADMIN_PAGE.getInsertItemButton().setVisible(true);

            /** Change the page identifier */
            items = Items.PRODUCTS;
        }
        else if (items.equals(Items.PRODUCTS)) {
            /** Switch page button */
            ADMIN_PAGE.getSwitchPageButton().setText("PRODUCTS");

            /** Table */
            if (clientTable != null) {
                ADMIN_PAGE.setTable(clientTable);
            }
            else {
                ADMIN_PAGE.setTable(emptyClientTable);
                JOptionPane.showMessageDialog(
                        ADMIN_PAGE.getFrame(),
                        "There are currently no clients registered",
                        "No clients",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            /** Title panel */
            ADMIN_PAGE.setTitlePanel(Utilities.makePanel(Color.cyan, "CLIENTS", null, FlowLayout.CENTER));
            ADMIN_PAGE.getBorderLayoutPanel().add(ADMIN_PAGE.getTitlePanel(), BorderLayout.NORTH);

            /** Update item button */
            ADMIN_PAGE.getUpdateItemButton().setVisible(false);

            /** Insert item button */
            ADMIN_PAGE.getInsertItemButton().setVisible(false);

            /** Change the page identifier */
            items = Items.CLIENTS;
        }

        // This method sets whatever should be displayed as scrollable
        ADMIN_PAGE.getScrollPane().setViewportView(ADMIN_PAGE.getTable());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == ADMIN_PAGE.getSwitchPageButton()) {
            switchItems();
        }
        else if (e.getSource() == ADMIN_PAGE.getDeleteItemButton() ||
                e.getSource() == ADMIN_PAGE.getUpdateItemButton()) {

            int[] selectedRows = ADMIN_PAGE.getTable().getSelectedRows();

            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(
                        ADMIN_PAGE.getFrame(),
                        "You must select an item to perform this operation",
                        "Invalid operation",
                        JOptionPane.WARNING_MESSAGE
                );
            }
            else if (selectedRows.length > 1) {
                JOptionPane.showMessageDialog(
                        ADMIN_PAGE.getFrame(),
                        "Cannot perform this operation on multiple items",
                        "Invalid operation",
                        JOptionPane.WARNING_MESSAGE
                );
            }
            else {

                // Get selected item's ID
                String idValue =
                        (String) ADMIN_PAGE
                                .getTable()
                                .getModel()
                                .getValueAt(selectedRows[0], 0);

                if (e.getSource() == ADMIN_PAGE.getDeleteItemButton()) {

                    if (items.equals(Items.CLIENTS)) {
                        try {
                            DatabaseOperation.deleteClient("id", idValue);
                        } catch (FieldsException ex) {
                            JOptionPane.showMessageDialog(
                                    ADMIN_PAGE.getFrame(),
                                    "Could not delete client. Please try again later",
                                    "Unexpected error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                        reloadTable();
                    }
                    else if (items.equals(Items.PRODUCTS)) {
                        try {
                            DatabaseOperation.deleteProduct("id", idValue);

                        } catch (FieldsException ex) {
                            JOptionPane.showMessageDialog(
                                    ADMIN_PAGE.getFrame(),
                                    "Could not delete product. Please try again later",
                                    "Unexpected error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                        reloadTable();
                    }
                }
                else if (e.getSource() == ADMIN_PAGE.getUpdateItemButton()) {

                    // Create new product
                    String productName = (String) ADMIN_PAGE.getTable().getModel().getValueAt(selectedRows[0], 1);
                    Long productPrice = Long.parseLong((String)ADMIN_PAGE.getTable().getModel().getValueAt(selectedRows[0], 2));
                    String productCategory = (String) ADMIN_PAGE.getTable().getModel().getValueAt(selectedRows[0], 3);

                    Product product = new Product(
                            Long.parseLong(idValue),
                            productName,
                            productPrice,
                            productCategory
                    );

                    if (items.equals(Items.PRODUCTS)) {
                        try {
                            DatabaseOperation.updateProduct("id", idValue, product);
                        } catch (FieldsException ex) {
                            JOptionPane.showMessageDialog(
                                    ADMIN_PAGE.getFrame(),
                                    "Could not update product. Please try again later",
                                    "Unexpected error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                    reloadTable();
                } // end of source event when there is one single selected row
            } // end of selected rows condition

        }
        else if (e.getSource() == ADMIN_PAGE.getInsertItemButton()) {
            InsertProductPage insertProductPage = new InsertProductPage(ADMIN_PAGE);
        }
        else if (e.getSource() == ADMIN_PAGE.getLogoutButton()) {

            ADMIN_PAGE.getFrame().dispose();
            LoginPage loginPage = new LoginPage();
        }// end of source event

    } // end of action performed method

}// end of class
