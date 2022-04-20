package presentation;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import dao.DataAccessObject;
import exceptions.FieldsException;
import model.Client;
import model.Id;
import model.Order;
import model.Product;
import org.apache.ibatis.jdbc.ScriptRunner;
import exceptions.SQLScriptsException;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Utilities {

    public static JPanel makePanel(Color panelColor, String labelText, Component component, int layoutOption) {

        JPanel panel = new JPanel();

        panel.setLayout(new FlowLayout(layoutOption));
        panel.setBackground(panelColor);
        panel.setOpaque(true);

        JLabel label = new JLabel();
        label.setText(labelText);
        label.setFont(new Font("Calibri", Font.PLAIN, 15));

        panel.add(label);
        if (component != null) {panel.add(component);}

        return panel;
    }

    public static JButton makeButton(Color buttonColor, String buttonText) {

        JButton button = new JButton();

        button.setFont(new Font("Calibri", Font.BOLD, 10));
        button.setText(buttonText);
        button.setPreferredSize(new Dimension(90, 25));
        button.setBackground(buttonColor);
        button.setOpaque(true);

        return button;
    }

    public static JTextField makeTextField(String textFieldText) {

        JTextField textField = new JTextField();

        textField.setText(textFieldText);
        textField.setPreferredSize(new Dimension(150, 25));

        return textField;
    }

    private static void makeFolder(String directoryPath, String folderName) {
        File ordersDirectory = new File(directoryPath + "\\" + folderName); // create new file object with the path of the folder that you want to create
        boolean success = ordersDirectory.mkdir(); // create the directory
        if (!success) {
            // throw new FileCreationException()
            System.out.println("Orders directory creation failed!");
        }
    }
    private static void createFolder(String directoryPath, String folderName) {

        File resourcesDirectory = new File(directoryPath); // create a new file object with the path of the folder where you want to do stuff

        String[] contents = resourcesDirectory.list(); // get contents of the directory

        if (contents == null) {
            makeFolder(directoryPath, folderName);
        }
        else {
            // Search for the folder in the list of folders of the directory
            boolean found = false;
            for (String content : contents) {
                if (content.equals(folderName)) {
                    found = true;
                    break;
                }
            }
            // If you don't find it, create it
            if (!found) {
                makeFolder(directoryPath, folderName);
            }
        }

    }
    private static void createPDF(String directoryPath, Client client, Product product, Order order)
            throws FileNotFoundException, DocumentException {
        Document doc = new Document();

        // Create PDF
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(directoryPath + "\\order_" + order.getId().toString() +".pdf"));
        // PDF created

        doc.open();
        doc.add(new Paragraph(
                "INVOICE\n" +
                        "TechStore, Strada Obersvatorului 32-34, Cluj-Napoca, Cluj, 400488\n\n" +
                        client.getName() + " bought " + product.getName() + "\n\n" +
                        "Date and time: " + LocalDate.now().toString() + " " + LocalTime.now().toString().split("\\.")[0] // get current date and time
        ));

        doc.close();
        writer.close();
    }

    public static void generateInvoice(Client client, Product product, Order order) throws DocumentException, FileNotFoundException {

        // Get path of project directory and navigate to the resources" folder created by Maven
        String directoryPath = System.getProperty("user.dir") +
                "\\" +
                "src" +
                "\\" +
                "main" +
                "\\" +
                "resources";
        String folderName = "orders";

        createFolder(directoryPath, folderName);

        directoryPath = directoryPath + "\\" + folderName;
        folderName = client.getName();

        createFolder(directoryPath, folderName);

        directoryPath = directoryPath + "\\" + folderName;

        createPDF(
                directoryPath,
                client,
                product,
                order
        );
    }

    public static void createDatabase(String scriptsPath) throws SQLException, FileNotFoundException, SQLScriptsException, FieldsException {

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        String mysqlUrl = "jdbc:mysql://localhost:3306";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "root");
        System.out.println("Connection established...");

        // Check if database exists
        String dbName = "techstore";
        ResultSet rs = con.getMetaData().getCatalogs(); // get list of databases
        while (rs.next()) {
            String catalogs = rs.getString(1);
            if (dbName.equals(catalogs)) {
                return; // if database already exists, then exit the function
            }
        }

        ScriptRunner sr = new ScriptRunner(con);
        File resourcesDirectory = new File(scriptsPath);

        String[] contents = resourcesDirectory.list();

        if (contents == null) {
            throw new SQLScriptsException("Not all 6 SQL scripts are in\n\"" + scriptsPath +"\"");
        }
        else {
            int sqlScriptsFound = 0;
            for (String file : contents) {
                if (file.endsWith(".sql")) {
                    sqlScriptsFound += 1;
                }
            }

            if (sqlScriptsFound < 6) {
                throw new SQLScriptsException("Not all 6 SQL scripts are in\n\"" + scriptsPath +"\"");
            } else if (sqlScriptsFound > 6) {
                throw  new SQLScriptsException("There are more than 6 SQL scripts in\n\"" + scriptsPath + "\"");
            }

            for (String file : contents) {
                if (file.endsWith(".sql")) {
                    Reader reader = new BufferedReader(new FileReader(scriptsPath + "\\" + file));
                    sr.runScript(reader);
                }
            }
        }

        // Create ids for "client", "order" and "product"
        DataAccessObject<Id> dao = new DataAccessObject<Id>(Id.class);
        dao.insert(new Id("client", 1L));
        dao.insert(new Id("order", 1L));
        dao.insert(new Id("product", 1L));
    }
}
