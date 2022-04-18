package presentation.controller;

import dao.FieldsException;
import model.Product;
import presentation.InsertProductPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

public class ControllerInsertProductPage implements ActionListener, WindowListener{

    public final InsertProductPage INSERT_PRODUCT_PAGE;

    public ControllerInsertProductPage(InsertProductPage insertProductPage) {
        this.INSERT_PRODUCT_PAGE = insertProductPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == INSERT_PRODUCT_PAGE.getInsertButton()) {

            try {

                Long id = DatabaseOperation.getNextId(new Product());
                String productName = INSERT_PRODUCT_PAGE.getProductNameTextField().getText();
                Long productPrice = Long.parseLong(INSERT_PRODUCT_PAGE.getProductPriceTextField().getText());
                String productCategory = INSERT_PRODUCT_PAGE.getProductCategoryTextField().getText();

                Product product = new Product(
                        id,
                        productName,
                        productPrice,
                        productCategory
                );

                DatabaseOperation.insertProduct(product);
                JOptionPane.showMessageDialog(
                        INSERT_PRODUCT_PAGE.getFrame(),
                        productName + " was added to the store",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );



                INSERT_PRODUCT_PAGE.getProductNameTextField().setText("");
                INSERT_PRODUCT_PAGE.getProductPriceTextField().setText("");
                INSERT_PRODUCT_PAGE.getProductCategoryTextField().setText("");

                INSERT_PRODUCT_PAGE.getADMIN_PAGE().getController().reloadTable();

            } catch (FieldsException | SQLException ex) {
                JOptionPane.showMessageDialog(
                        INSERT_PRODUCT_PAGE.getFrame(),
                        "Could not add product. Please try again later",
                        "Unexpected error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        this.INSERT_PRODUCT_PAGE.getADMIN_PAGE().getFrame().setEnabled(false);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        this.INSERT_PRODUCT_PAGE.getADMIN_PAGE().getFrame().setEnabled(true);
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
