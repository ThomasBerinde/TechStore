package model;

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
