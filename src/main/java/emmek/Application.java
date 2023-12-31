package emmek;

import Shop.Customer;
import Shop.Order;
import Shop.Product;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.*;

public class Application {

    public static void main(String[] args) {
        Product book1 = new Product("libro1", "Books", 130);
        Product book2 = new Product("libro2", "Books", 10);
        Product book3 = new Product("libro3", "eBooks", 10);
        Product book4 = new Product("libro4", "Books", 120);
        Product baby1 = new Product("baby1", "Baby", 120);
        Product baby2 = new Product("baby2", "Baby", 10);
        Product baby3 = new Product("baby3", "Babys", 10);
        Product baby4 = new Product("baby4", "Baby", 140);
        List<Product> products = Arrays.asList(book1, book2, book3, book4, baby1, baby2, baby3, baby4);
        Customer customer1 = new Customer("aldo", 2);
        Customer customer2 = new Customer("giovanni", 1);
        Customer customer3 = new Customer("giacomo", 2);
        List<Product> cart1 = Arrays.asList(book1, baby2, book3, baby4);
        List<Product> cart2 = Arrays.asList(baby1, book2, baby3, book4);
        List<Product> cart3 = Arrays.asList(baby1, book2, book4);
        List<Product> cart4 = Arrays.asList(baby2, baby4, book4);
        Order order1 = new Order("new", LocalDate.of(2020, 1, 1), cart1, customer1);
        Order order2 = new Order("new", LocalDate.of(2020, 1, 1), cart2, customer2);
        Order order3 = new Order("new", LocalDate.of(2022, 1, 1), cart3, customer3);
        Order order4 = new Order("new", LocalDate.now(), cart4, customer1);
        List<Order> orders = Arrays.asList(order1, order2, order3, order4);


        Map<Customer, List<Order>> orderCustomerMap = orders.stream().collect(groupingBy(Order::getCustomer));
        orderCustomerMap.forEach((customer, ordersList) -> System.out.println(customer + " - " + ordersList));

//        Map<Customer, DoubleSummaryStatistics> customerTotalSales = orders.stream().collect(groupingBy(Order::getCustomer, Collectors.summarizingDouble(Order::getTotal)));
        Map<Customer, Double> customerTotalSales = orders.stream().collect(groupingBy(Order::getCustomer, summingDouble(order -> order.getProducts().stream().mapToDouble(Product::getPrice).sum())));
        customerTotalSales.forEach((customer, total) -> System.out.println(customer + " - " + total));

        List<Product> moreExpensive = products.stream().sorted(comparingDouble(Product::getPrice).reversed()).limit(5).toList();
        moreExpensive.forEach(product -> System.out.println(product.getName() + " - " + product.getPrice()));

        DoubleSummaryStatistics mediaOrdini = orders.stream().collect(summarizingDouble(Order::getTotal));
        System.out.println(mediaOrdini);
        System.out.println(mediaOrdini.getAverage());

        Map<String, Double> categoryTotal = products.stream().collect(groupingBy(Product::getCategory, summingDouble(Product::getPrice)));
        categoryTotal.forEach((category, total) -> System.out.println(category + " - " + total));


//        saveProducts(products);
        System.out.println(loadProducts());
    }

    public static void saveProducts(List<Product> products) {
        File file = new File("src/products.txt");
        String fileTxt = products.stream().map(Product::toString).collect(Collectors.joining());
        try {
            FileUtils.writeStringToFile(file, fileTxt + System.lineSeparator(), StandardCharsets.UTF_8, false);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static List<Product> loadProducts() {
        File file = new File("src/products.txt");
        String content = "";
        List<Product> products = new ArrayList<>();
        try {
            content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        Arrays.stream(content.split("#")).forEach(product -> {
            String[] arrStr = product.split("@");
            if (arrStr.length > 1) products.add(new Product(arrStr[0], arrStr[1], Double.parseDouble(arrStr[2])));
        });
        return products;
    }


}
