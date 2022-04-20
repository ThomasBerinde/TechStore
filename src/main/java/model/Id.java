package model;

/**
 * Represents the id used as primary key for each table in the database.
 * It is directly linked to the table "id" in the "techstore" database.
 *
 * Conditions for the proper functionality of the project:
 *  - the class and the respective table must have the same fields and data types.
 *  - the class must have getters and setters for all attributes
 *  - the class' name and the table's name must be the same (case-insensitive)
 *  - the class must have an empty constructor
 */
public class Id {

    String item;
    Long value;

    public Id() { }

    public Id(String item, Long value) {
        this.item = item;
        this.value = value;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
