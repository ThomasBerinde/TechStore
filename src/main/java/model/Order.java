package model;

/**
 * Represents an order that a client can make at the store.
 * It is directly linked to the table "order" in the "techstore" database.
 *
 * Conditions for the proper functionality of the project:
 *  - the class and the respective table must have the same fields and data types.
 *  - the class must have getters and setters for all attributes
 *  - the class' name and the table's name must be the same (case-insensitive)
 *  - the class must have an empty constructor
 */
public class Order {

    private Long id;
    private Long client_id;
    private Long product_id;

    public Order() { }

    public Order(Long id, Long clientId, Long productId) {
        this.id = id;
        this.client_id = clientId;
        this.product_id = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }
}
