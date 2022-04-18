package dao;

import org.jetbrains.annotations.NotNull;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAccessObject <T> {

    protected static final Logger LOGGER = Logger.getLogger(DataAccessObject.class.getName());

    private final Class<T> TYPE;

    public DataAccessObject(Class<T> type) {
        this.TYPE = type;//(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public int insert(@NotNull T object) throws FieldsException {

        Connection connection = null;
        Statement statement = null;
        StringBuilder insertQuery = new StringBuilder();
        int nrFields = object.getClass().getDeclaredFields().length, status = -1;

        /** Create query */
        String tableName = this.TYPE.getSimpleName().toLowerCase();
        try {
            insertQuery
                    .append("INSERT into ")
                    .append(tableName.equals("order") ? "`" + tableName + "`" : tableName)
                    .append(" VALUES (");
            for (int i = 0; i < nrFields - 1; i++) {
                Field field = object.getClass().getDeclaredFields()[i];
                field.setAccessible(true);
                insertQuery
                        .append("'")
                        .append(field.get(object))
                        .append("', ");
            }
            Field field = object.getClass().getDeclaredFields()[nrFields - 1];
            field.setAccessible(true);
            insertQuery
                    .append("'")
                    .append(field.get(object))
                    .append("')");

        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARNING, TYPE.getName() + "DataAccessObject: insert " + e.getMessage());
            e.printStackTrace();
        }

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            status = statement.executeUpdate(insertQuery.toString());

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, TYPE.getName() + "DataAccessObject: insert " + e.getMessage());
            e.printStackTrace();

        } finally {
            DatabaseConnection.close(statement);
            DatabaseConnection.close(connection);
        }

        if (status != 1) {
            throw new FieldsException("Query execution failed");
        }

        return status;
    }

    public int update(String fieldName, String fieldValue, @NotNull T object) throws FieldsException {

        Connection connection = null;
        Statement statement = null;
        StringBuilder updateQuery = new StringBuilder();
        int nrFields = object.getClass().getDeclaredFields().length, nrEntries = -1;

        /** Create query */
        try {
            updateQuery
                    .append("UPDATE ")
                    .append(this.TYPE.getSimpleName().toLowerCase())
                    .append(" SET ");
            for (int i = 0; i < nrFields - 1; i++) {
                Field field = object.getClass().getDeclaredFields()[i];
                field.setAccessible(true);
                updateQuery
                        .append(field.getName())
                        .append(" ")
                        .append("= '")
                        .append(field.get(object))
                        .append("', ");
            }
            Field field = object.getClass().getDeclaredFields()[nrFields - 1];
            field.setAccessible(true);
            updateQuery
                    .append(field.getName())
                    .append(" ")
                    .append("= '")
                    .append(field.get(object))
                    .append("' WHERE (")
                    .append(fieldName)
                    .append("= '")
                    .append(fieldValue)
                    .append("')");

        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARNING, TYPE.getName() + "DataAccessObject: update " + e.getMessage());
            e.printStackTrace();
        }

        /** Establish a connection with the database and execute the query */
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            nrEntries = statement.executeUpdate(updateQuery.toString());

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, TYPE.getName() + "DataAccessObject: update " + e.getMessage());
            e.printStackTrace();

        }
        finally {
            DatabaseConnection.close(statement);
            DatabaseConnection.close(connection);
        }

        if (nrEntries < 0) {
            throw new FieldsException("Query execution failed");
        }

        /** Return the number of modified entries */
        return nrEntries;
    }

    public int delete(String fieldName, String fieldValue) throws FieldsException {

        Connection connection = null;
        Statement statement = null;
        int nrEntries = -1;

        String query =
                "DELETE FROM " +
                this.TYPE.getSimpleName().toLowerCase() +
                " WHERE (" +
                fieldName +
                " = '" +
                fieldValue +
                "')";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            nrEntries = statement.executeUpdate(query);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, TYPE.getName() + "DataAccessObject: delete " + e.getMessage());
            e.printStackTrace();

        } finally {
            DatabaseConnection.close(statement);
            DatabaseConnection.close(connection);
        }

        if (nrEntries < 0) {
            throw new FieldsException("Query execution failed");
        }

        return nrEntries;
    }

    public int deleteAll() throws FieldsException {

        Connection connection = null;
        Statement statement = null;
        int nrEntries = -1;

        String query = "DELETE FROM " + this.TYPE.getSimpleName().toLowerCase();

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            nrEntries = statement.executeUpdate(query);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, TYPE.getName() + "DataAccessObject: deleteAll " + e.getMessage());
            e.printStackTrace();

        } finally {
            DatabaseConnection.close(statement);
            DatabaseConnection.close(connection);
        }

        if (nrEntries < 0) {
            throw new FieldsException("Query execution failed");
        }

        return nrEntries;
    }

    public List<T> findAll() throws FieldsException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<T> entries = null;

        String query = "SELECT * from " + this.TYPE.getSimpleName().toLowerCase();

        try {
            connection =  DatabaseConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            entries =  createObjects(resultSet);
        } catch (SQLException | ModelClassException e) {
            LOGGER.log(Level.WARNING, TYPE.getName() + "DataAccessObject: findAll " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(statement);
            DatabaseConnection.close(connection);
        }

        if (entries == null) {
            throw new FieldsException("Query execution failed");
        }

        return  entries;
    }

    public T findById(String id) throws FieldsException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        T entry = null;

        String query =
                "SELECT * FROM " +
                this.TYPE.getSimpleName().toLowerCase() +
                " WHERE (id = " +
                id +
                ")";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            List<T> resultList = createObjects(resultSet);

           if (resultList.isEmpty() || resultList.size() == 1){
                entry = resultList.get(0);
           } else {
               LOGGER.log(Level.SEVERE, "Error: more elements in the table with id=" + id);
               throw new FieldsException("Duplicate id");
           }

        } catch (SQLException | ModelClassException e) {
            LOGGER.log(Level.WARNING, TYPE.getName() + "DataAccessObject: findById " + e.getMessage());
            e.printStackTrace();

        } finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(statement);
            DatabaseConnection.close(connection);
        }

        if (entry == null) {
            throw new FieldsException("Query execution failed");
        }

        return entry;
    }

    public List<T> find(String fieldName, String fieldValue) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<T> resultList = null;

        String query =
                "SELECT * FROM " +
                this.TYPE.getSimpleName().toLowerCase() +
                " WHERE (" +
                fieldName +
                " = '" +
                fieldValue +
                "')";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            resultList = createObjects(resultSet);

        } catch (SQLException | ModelClassException e) {
            LOGGER.log(Level.WARNING, TYPE.getName() + "DataAccessObject: findById " + e.getMessage());
            e.printStackTrace();

        } finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(statement);
            DatabaseConnection.close(connection);
        }

        return resultList;
    }

    private @NotNull List<T> createObjects(@NotNull ResultSet resultSet) throws ModelClassException {

        List<T> list = new ArrayList<T>();

        Constructor[] constructors = TYPE.getDeclaredConstructors();
        Constructor constructor = null;

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, TYPE.getName() + "DataAccessObject: createObjects " + e.getMessage());
            e.printStackTrace();
        }

        /** Get constructor with the same number of parameters as the result set */
        for (int i = 0; i < constructors.length && constructor == null; i++) {
            if (constructors[i].getGenericParameterTypes().length == 0) {
                constructor = constructors[i];
            }
        }
        if (constructor == null) {
            throw new ModelClassException(
                    "Class " +
                    TYPE.getSimpleName() +
                    " has no constructor that matches all columns of table " +
                    TYPE.getSimpleName().toLowerCase()
            );
        }

        try {
            while (resultSet.next()) {
                constructor.setAccessible(true);
                T instance = (T) constructor.newInstance();

                for (Field field : TYPE.getDeclaredFields()) {
                    String fieldName = field.getName(); //id
                    Object value = resultSet.getObject(fieldName);  //id
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, TYPE);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }

                list.add(instance);
            }
        } catch (
                InstantiationException |
                IllegalAccessException |
                SecurityException |
                IllegalArgumentException |
                InvocationTargetException |
                SQLException |
                IntrospectionException e) {
            e.printStackTrace();
        }

        return list;
    }

}
