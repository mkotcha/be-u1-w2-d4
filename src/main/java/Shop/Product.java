package Shop;

import java.util.Random;

public class Product {
    private final Long id;
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        Random rnd = new Random();
        this.id = rnd.nextLong();
        this.name = name;
        this.category = category;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return name + "@" + category + "@" + price + "#";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
