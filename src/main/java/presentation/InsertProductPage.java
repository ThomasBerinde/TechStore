package presentation;

import presentation.controller.ControllerInsertProductPage;

import javax.swing.*;
import java.awt.*;

public class InsertProductPage {

    private JFrame frame;

    private JPanel titlePanel;
    private JPanel contentPanel;
    private JPanel buttonsPanel;

    private JLabel productNameLabel;
    private JLabel productPriceLabel;
    private JLabel productCategoryLabel;

    private JTextField productNameTextField;
    private JTextField productPriceTextField;
    private JTextField productCategoryTextField;

    private JButton insertButton;

    private final AdminPage ADMIN_PAGE;
    private final ControllerInsertProductPage controller = new ControllerInsertProductPage(this);

    public InsertProductPage(AdminPage adminPage) {

        ADMIN_PAGE = adminPage;

        frame = new JFrame("Add product");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(new Dimension(175, 275));
        frame.setResizable(false);
//        frame.addWindowListener(controller);

        titlePanel = Utilities.makePanel(Color.cyan, "Add product", null, FlowLayout.CENTER);
        contentPanel = Utilities.makePanel(Color.lightGray, "", null, FlowLayout.LEADING);

        insertButton = Utilities.makeButton(Color.cyan, "INSERT");
        insertButton.addActionListener(controller);
        buttonsPanel = Utilities.makePanel(Color.darkGray, "", insertButton, FlowLayout.CENTER);

        productNameLabel = new JLabel("Name:");
        productPriceLabel = new JLabel("Price:");
        productCategoryLabel = new JLabel("Category:");

        productNameTextField = new JTextField();
        productNameTextField.setPreferredSize(new Dimension(150, 25));

        productPriceTextField = new JTextField();
        productPriceTextField.setPreferredSize(new Dimension(150, 25));

        productCategoryTextField = new JTextField();
        productCategoryTextField.setPreferredSize(new Dimension(150, 25));

        contentPanel.add(productNameLabel);
        contentPanel.add(productNameTextField);
        contentPanel.add(productPriceLabel);
        contentPanel.add(productPriceTextField);
        contentPanel.add(productCategoryLabel);
        contentPanel.add(productCategoryTextField);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel(JPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

    public void setButtonsPanel(JPanel buttonsPanel) {
        this.buttonsPanel = buttonsPanel;
    }

    public JLabel getProductNameLabel() {
        return productNameLabel;
    }

    public void setProductNameLabel(JLabel productNameLabel) {
        this.productNameLabel = productNameLabel;
    }

    public JLabel getProductPriceLabel() {
        return productPriceLabel;
    }

    public void setProductPriceLabel(JLabel productPriceLabel) {
        this.productPriceLabel = productPriceLabel;
    }

    public JLabel getProductCategoryLabel() {
        return productCategoryLabel;
    }

    public void setProductCategoryLabel(JLabel productCategoryLabel) {
        this.productCategoryLabel = productCategoryLabel;
    }

    public JTextField getProductNameTextField() {
        return productNameTextField;
    }

    public void setProductNameTextField(JTextField productNameTextField) {
        this.productNameTextField = productNameTextField;
    }

    public JTextField getProductPriceTextField() {
        return productPriceTextField;
    }

    public void setProductPriceTextField(JTextField productPriceTextField) {
        this.productPriceTextField = productPriceTextField;
    }

    public JTextField getProductCategoryTextField() {
        return productCategoryTextField;
    }

    public void setProductCategoryTextField(JTextField productCategoryTextField) {
        this.productCategoryTextField = productCategoryTextField;
    }

    public JButton getInsertButton() {
        return insertButton;
    }

    public void setInsertButton(JButton insertButton) {
        this.insertButton = insertButton;
    }

    public AdminPage getADMIN_PAGE() {
        return ADMIN_PAGE;
    }
}
