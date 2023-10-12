package Shop;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Order {
    private final Long id;
    private final LocalDate orderDate;
    private final Customer customer;
    private String status;
    private LocalDate deliveryDate;
    private List<Product> products;

    public Order(String status, LocalDate orderDate, List<Product> products, Customer customer) {
        Random rnd = new Random();
        this.id = rnd.nextLong();
        this.status = status;
        this.orderDate = orderDate;
        this.products = products;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Order{" +

                ", orderDate=" + orderDate +
                ", customer=" + customer +
                

                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }
}


