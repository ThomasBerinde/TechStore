package model;

/**
 * Represents a set of credentials of a client.
 * It is directly linked to the table "credentials" in the "techstore" database.
 *
 * Conditions for the proper functionality of the project:
 *  - the class and the respective table must have the same fields and data types.
 *  - the class must have getters and setters for all attributes
 *  - the class' name and the table's name must be the same (case-insensitive)
 *  - the class must have an empty constructor
 */
public class Credentials {

    private Long id;
    private String username;
    private String password;

    public Credentials() {}

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Credentials(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
