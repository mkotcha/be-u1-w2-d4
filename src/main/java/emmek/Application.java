package emmek;

import Shop.Customer;
import Shop.Order;
import Shop.Product;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Application {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Product book1 = new Product("libro1", "Books", 120);
        Product book2 = new Product("libro2", "Books", 10);
        Product book3 = new Product("libro3", "eBooks", 10);
        Product book4 = new Product("libro4", "Books", 120);
        Product baby1 = new Product("baby1", "Baby", 120);
        Product baby2 = new Product("baby2", "Baby", 10);
        Product baby3 = new Product("baby3", "Babys", 10);
        Product baby4 = new Product("baby4", "Baby", 120);


        List<Product> products = Arrays.asList(book1, book2, book3, book4);
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


        Map<Customer, DoubleSummaryStatistics> customerTotalSales = orders.stream().collect(groupingBy(Order::getCustomer, Collectors.summarizingDouble(Order::getTotal)));
        customerTotalSales.forEach((customer, total) -> System.out.println(customer + " - " + total.getSum()));


    }
}
