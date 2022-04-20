package model;

/**
 * Represents a client of the store.
 * It is directly linked to the table "client" in the "techstore" database.
 *
 * Conditions for the proper functionality of the project:
 *  - the class and the respective table must have the same fields and data types.
 *  - the class must have getters and setters for all attributes
 *  - the class' name and the table's name must be the same (case-insensitive)
 *  - the class must have an empty constructor
 */
public class Client {

    private Long id;
    private String name;
    private Long age;
    private String address;

    public Client() { }

    public Client(Long id, String name, Long age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {return age;}

    public void setAge(Long age) {this.age = age;}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
