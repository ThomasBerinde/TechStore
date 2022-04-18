package start;

import dao.DataAccessObject;
import dao.FieldsException;
import dao.ModelClassException;
import model.Client;

import java.sql.SQLException;
import java.util.List;

/**
 * Conditions for the proper functionality of the application:
 *  - the elements in the techstore database should have a unique, not null id
 *  - the model classes of the application represent the tables of the database;
 *  they should have the same name as the tables (case-insensitive) and the table's
 *  fields should match the class attributes; each class attribute must have a
 *  setter and a getter
 * */

public class Main {

    public static void main(String[] args) throws FieldsException {
//        Client oanaJimborean = new Client(Long.valueOf(1), "Oana Jimborean", Long.valueOf(20), "Manastur");
//        Client cristianDora = new Client(Long.valueOf(2), "Cristian A. Dora", Long.valueOf(20), "Ceahlau 77");
//        Client maierCatalin = new Client(Long.valueOf(3), "Maier Catalin", Long.valueOf(20), "Ceahlau 77");
//        Client thomasBerinde = new Client(Long.valueOf(4), "Thomas Berinde", Long.valueOf(22), "Ceahlau 77");
//        Client ioanDragomir = new Client(Long.valueOf(5), "Ioan Dragomir", Long.valueOf(20), "Ceahlau 77");
//
//        DataAccessObject<Client> dao = new DataAccessObject<Client>(Client.class);
//        List<Client> all = null;
//
//        dao.insert(oanaJimborean);
//        dao.insert(cristianDora);
//        all = dao.findAll();
//        all.forEach(System.out::println);
//        System.out.println("");
//
//        Client newOanaJimborean = new Client(Long.valueOf(1), "Oana Jimborean", Long.valueOf(20), "Ceahlau 77");
//        dao.update("id", "1", newOanaJimborean);
//        all = dao.findAll();
//        all.forEach(System.out::println);
//        System.out.println("");
//
//        dao.insert(maierCatalin);
//        dao.insert(thomasBerinde);
//        all = dao.findAll();
//        all.forEach(System.out::println);
//        System.out.println("");
//
//        System.out.println(dao.findById("4"));
//        System.out.println("");
//
//        dao.delete("address", "Ceahlau 77");
//        all = dao.findAll();
//        all.forEach(System.out::println);
//        System.out.println("");
//
//        dao.insert(ioanDragomir);
//        System.out.println(dao.findById("5"));
//
//        System.out.println(dao.deleteAll());
    }
}
