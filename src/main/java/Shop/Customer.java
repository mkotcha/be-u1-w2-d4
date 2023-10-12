package Shop;

import java.util.Random;

public class Customer {
    private final Long id;
    private String name;
    private int tier;

    public Customer(String name, int tier) {
        Random rnd = new Random();
        this.id = rnd.nextLong();
        this.name = name;
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }
}
