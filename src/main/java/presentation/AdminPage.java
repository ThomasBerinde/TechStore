package presentation;

import presentation.controller.ControllerAdminPage;

import javax.swing.*;
import java.awt.*;

public class AdminPage {

    private JFrame frame;
    private JTable table;
    private JPanel titlePanel;
    private JPanel menuPanel;
    private JPanel buttonsPanel;
    private JButton switchPageButton;
    private JPanel borderLayoutPanel;
    private JScrollPane scrollPane;
    private JButton deleteItemButton;
    private JButton updateItemButton;
    private JButton insertItemButton;
    private JButton logoutButton;
    private JPanel menuFooterPanel;

    private final ControllerAdminPage controller = new ControllerAdminPage(this);

    public AdminPage() {

        frame = new JFrame("TechStore (user: admin, id: 1)");
        frame.setSize(new Dimension(700, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        /** Border layout panel */
        borderLayoutPanel = new JPanel();
        borderLayoutPanel.setLayout(new BorderLayout());
        borderLayoutPanel.setPreferredSize(new Dimension(550, frame.getHeight()));
        borderLayoutPanel.setBorder(BorderFactory.createEtchedBorder());

        /** Title panel */
        titlePanel = Utilities.makePanel(Color.cyan, "", null, FlowLayout.CENTER);
        titlePanel.setPreferredSize(new Dimension(700, 25));

        /** Scroll pane */
        scrollPane = new JScrollPane(table);
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
        switchPageButton = Utilities.makeButton(Color.cyan, "");
        switchPageButton.addActionListener(controller);

        /** Delete item button */
        deleteItemButton = Utilities.makeButton(Color.cyan, "DELETE");
        deleteItemButton.addActionListener(controller);

        /** Update item button */
        updateItemButton = Utilities.makeButton(Color.cyan, "UPDATE");
        updateItemButton.addActionListener(controller);

        /** Insert item button */
        insertItemButton = Utilities.makeButton(Color.cyan, "INSERT");
        insertItemButton.addActionListener(controller);

        /** Initialize items */
        controller.initializeItems();

        /** Menu footer panel */
        menuFooterPanel = Utilities.makePanel(Color.gray, "", null, FlowLayout.CENTER);

        /** Log out button */
        logoutButton = Utilities.makeButton(Color.red, "LOG OUT");
        logoutButton.addActionListener(controller);

        /** Menu footer panel */
        menuFooterPanel.add(logoutButton);
        menuPanel.add(menuFooterPanel, BorderLayout.SOUTH);

        buttonsPanel.add(switchPageButton);
        buttonsPanel.add(deleteItemButton);
        buttonsPanel.add(updateItemButton);
        buttonsPanel.add(insertItemButton);

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

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
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

    public JButton getSwitchPageButton() {
        return switchPageButton;
    }

    public void setSwitchPageButton(JButton switchPageButton) {
        this.switchPageButton = switchPageButton;
    }

    public JPanel getBorderLayoutPanel() {
        return borderLayoutPanel;
    }

    public void setBorderLayoutPanel(JPanel borderLayoutPanel) {
        this.borderLayoutPanel = borderLayoutPanel;
    }

    public ControllerAdminPage getController() {
        return controller;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JButton getDeleteItemButton() {
        return deleteItemButton;
    }

    public void setDeleteItemButton(JButton deleteItemButton) {
        this.deleteItemButton = deleteItemButton;
    }

    public JButton getUpdateItemButton() {
        return updateItemButton;
    }

    public void setUpdateItemButton(JButton updateItemButton) {
        this.updateItemButton = updateItemButton;
    }

    public JButton getInsertItemButton() {
        return insertItemButton;
    }

    public void setInsertItemButton(JButton insertItemButton) {
        this.insertItemButton = insertItemButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public void setLogoutButton(JButton logoutButton) {
        this.logoutButton = logoutButton;
    }

    public JPanel getMenuFooterPanel() {
        return menuFooterPanel;
    }

    public void setMenuFooterPanel(JPanel menuFooterPanel) {
        this.menuFooterPanel = menuFooterPanel;
    }
}
