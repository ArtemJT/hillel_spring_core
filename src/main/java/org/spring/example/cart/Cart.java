package org.spring.example;

import lombok.Getter;
import org.spring.example.product.Product;
import org.spring.example.product.ProductComparator;
import org.spring.example.product.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class Cart {

    @Getter
    private final List<Product> productList = new ArrayList<>();
    private final ProductRepository productRepository;

    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addToCart(Integer id) {
        try {
            Product product = productRepository.getProduct(id);
            productList.add(product);
            System.out.println("ID " + '{' + id + '}' + " HAS ADDED");
        } catch (NoSuchElementException e) {
            System.out.println("ID " + '{' + id + '}' + " NOT FOUND. PLEASE TRY AGAIN");
        }
    }

    public void removeFromCart(Integer id) {
        try {
            Product product = productList.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst().orElseThrow();
            productList.remove(product);
            System.out.println("ID " + '{' + id + '}' + " HAS DELETED");
        } catch (NoSuchElementException e) {
            System.out.println("ID " + '{' + id + '}' + " NOT FOUND. PLEASE TRY AGAIN");
        }
    }

    public boolean isEmpty() {
        return productList.isEmpty();
    }

    public String getAvailableIdInCart() {
        if (productList.isEmpty()) {
            return "CART IS EMPTY. ADD SOME PRODUCT FIRST";
        }
        productList.sort(new ProductComparator());
        StringJoiner sj = new StringJoiner(", ", "RANGE OF IDs IN CURRENT CART {", "}");
        productList.forEach(product -> sj.add(String.valueOf(product.getId())));
        return sj.toString();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productList=" + productList +
                '}';
    }
}
