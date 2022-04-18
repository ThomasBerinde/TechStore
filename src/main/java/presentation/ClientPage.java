package presentation;

import dao.FieldsException;
import model.Credentials;
import presentation.controller.ControllerClientPage;
import presentation.controller.DatabaseOperation;

import javax.swing.*;
import java.awt.*;

public class ClientPage {

    private JFrame frame;
    private JTable productsTable;
    private JPanel titlePanel;
    private JPanel menuPanel;
    private JPanel buttonsPanel;
    private JButton orderButton;
    private JPanel borderLayoutPanel;
    private JScrollPane scrollPane;

    private final Credentials credentials;
    private final String[] columns = {"product_id", "product_name", "product_price", "product_category"};
    private final ControllerClientPage controller = new ControllerClientPage(this);

    public ClientPage(Credentials credentials) {

        this.credentials = credentials;
        frame = new JFrame("TechStore (user: " + credentials.getUsername() + ", id: " + credentials.getId() + ")");
        frame.setSize(new Dimension(700, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        /** Products table */
        try {
            productsTable = new JTable(DatabaseOperation.getProducts(), columns);
        } catch (FieldsException e) {
            JOptionPane.showMessageDialog(
                    frame,
                    "There are currently no products in the store",
                    "Empty store",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

        /** Border layout panel */
        borderLayoutPanel = new JPanel();
        borderLayoutPanel.setLayout(new BorderLayout());
        borderLayoutPanel.setPreferredSize(new Dimension(550, frame.getHeight()));

        /** Title panel */
        titlePanel = Utilities.makePanel(Color.cyan, "Products", null, FlowLayout.CENTER);
        titlePanel.setPreferredSize(new Dimension(700, 25));

        /** Scroll pane */
        scrollPane = new JScrollPane(productsTable); // displays the product table as a scrollable object
        scrollPane.setBackground(Color.white);

        /** Menu panel*/
        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.add(new JLabel("Menu"), BorderLayout.NORTH);
        menuPanel.setPreferredSize(new Dimension(150, frame.getHeight()));
        menuPanel.setBorder(BorderFactory.createEtchedBorder());

        /** Buttons panel */
        buttonsPanel = Utilities.makePanel(Color.gray, "", null, FlowLayout.LEADING);
        buttonsPanel.setPreferredSize(new Dimension(700, 35));

        /** Order button */
        orderButton = Utilities.makeButton(Color.cyan, "ORDER");
        orderButton.addActionListener(controller);

        buttonsPanel.add(orderButton);
        frame.add(menuPanel, BorderLayout.CENTER);
        frame.add(borderLayoutPanel, BorderLayout.EAST);
        borderLayoutPanel.add(buttonsPanel, BorderLayout.SOUTH);
        borderLayoutPanel.add(titlePanel, BorderLayout.NORTH);
        borderLayoutPanel.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTable getProductsTable() {
        return productsTable;
    }

    public void setProductsTable(JTable productsTable) {
        this.productsTable = productsTable;
    }

    public ControllerClientPage getController() {
        return controller;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    public void setMenuPanel(JPanel menuPanel) {
        this.menuPanel = menuPanel;
    }

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

    public void setButtonsPanel(JPanel buttonsPanel) {
        this.buttonsPanel = buttonsPanel;
    }

    public JButton getOrderButton() {
        return orderButton;
    }

    public void setOrderButton(JButton orderButton) {
        this.orderButton = orderButton;
    }

    public String[] getColumns() {
        return columns;
    }

    public JPanel getBorderLayoutPanel() {
        return borderLayoutPanel;
    }

    public void setBorderLayoutPanel(JPanel borderLayoutPanel) {
        this.borderLayoutPanel = borderLayoutPanel;
    }

    public Credentials getCredentials() {
        return credentials;
    }
}
