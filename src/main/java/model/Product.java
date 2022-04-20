package model;

/**
 * Represents a product that can be bought at the store
 * It is directly linked to the table "product" in the "techstore" database.
 *
 * Conditions for the proper functionality of the project:
 *  - the class and the respective table must have the same fields and data types.
 *  - the class must have getters and setters for all attributes
 *  - the class' name and the table's name must be the same (case-insensitive)
 *  - the class must have an empty constructor
 */
public class Product {

    private Long id;
    private String name;
    private Long price;
    private String category;

    public Product() { }

    public Product(Long id, String name, Long price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {return price;}

    public void setPrice(Long price) {this.price = price;}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
