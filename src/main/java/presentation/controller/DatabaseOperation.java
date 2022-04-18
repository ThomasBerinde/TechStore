package presentation.controller;

import dao.DataAccessObject;
import dao.FieldsException;
import model.Client;
import model.Credentials;
import model.Id;
import model.Product;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperation {

    public static String[][] getProducts() throws FieldsException {

        DataAccessObject<Product> dao = new DataAccessObject<>(Product.class);

        List<Product> products = dao.findAll();
        ArrayList<ArrayList<String>> tableRows = new ArrayList<>();

        for (Product product : products) {
            ArrayList<String> tableRow = new ArrayList<>();

            tableRow.add(product.getId().toString());
            tableRow.add(product.getName());
            tableRow.add(product.getPrice().toString());
            tableRow.add(product.getCategory());

            tableRows.add(tableRow);
        }

        String[][] arr = new String[tableRows.size()][4];

        for (int i = 0; i < tableRows.size(); i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = tableRows.get(i).get(j);
            }
        }

        return arr;
    }

    public static String[][] getClients() throws FieldsException {


        DataAccessObject<Client> dao = new DataAccessObject<>(Client.class);

        List<Client> clients = dao.findAll();
        ArrayList<ArrayList<String>> tableRows = new ArrayList<>();

        for (Client client : clients) {
            ArrayList<String> tableRow = new ArrayList<>();

            tableRow.add(client.getId().toString());
            tableRow.add(client.getName());
            tableRow.add(client.getAge().toString());
            tableRow.add(client.getAddress());

            tableRows.add(tableRow);
        }

        String[][] arr = new String[tableRows.size()][4];

        for (int i = 0; i < tableRows.size(); i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = tableRows.get(i).get(j);
            }
        }

        return arr;
    }

    public static void deleteProduct(@NotNull String fieldName, @NotNull String fieldValue) throws FieldsException {
        DataAccessObject<Product> dao = new DataAccessObject<>(Product.class);
        dao.delete(fieldName, fieldValue);
    }

    public static void deleteClient(@NotNull String fieldName, @NotNull String fieldValue) throws FieldsException {
        DataAccessObject<Client> dao = new DataAccessObject<>(Client.class);
        dao.delete(fieldName, fieldValue);
        deleteCredentials(fieldName, fieldValue);
    }

    public static void updateProduct(
            @NotNull String fieldName,
            @NotNull String fieldValue,
            @NotNull Product product) throws FieldsException {
        DataAccessObject<Product> dao = new DataAccessObject<>(Product.class);
        dao.update(fieldName, fieldValue, product);
    }

    public static void updateClient(
            @NotNull String fieldName,
            @NotNull String fieldValue,
            @NotNull Client client) throws FieldsException {
        DataAccessObject<Client> dao = new DataAccessObject<>(Client.class);
        dao.update(fieldName, fieldValue, client);
    }

    public static void insertProduct(@NotNull Product product) throws FieldsException, SQLException {
        DataAccessObject<Product> dao = new DataAccessObject<>(Product.class);
        dao.insert(product);
    }

    public static Long getNextId(Object object) throws FieldsException {

        DataAccessObject<Id> dao = new DataAccessObject<>(Id.class);
        String columnName =  object.getClass().getSimpleName().toLowerCase();

        List<Id> ids = dao.find("item", columnName);

        if (ids.size() == 1) {
            Long id = ids.get(0).getValue();
            dao.update("item", columnName, new Id(columnName, id + 1));
            return id;
        }
        else if (ids.size() == 0) {
            throw new FieldsException("Id search didn't return anything");
        }
        else
        {
            throw new FieldsException("Id search returned more than one ID");
        }
    }

    private static void deleteCredentials(@NotNull String fieldName, @NotNull String fieldValue) throws FieldsException {
        DataAccessObject<Credentials> dao = new DataAccessObject<>(Credentials.class);
        dao.delete(fieldName, fieldValue);
    }
}
