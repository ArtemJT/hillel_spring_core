package org.spring.example.cart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.spring.example.product.Product;
import org.spring.example.product.ProductComparator;
import org.spring.example.product.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

@RequiredArgsConstructor
@ToString
public class Cart {
    @Getter
    private final List<Product> productList = new ArrayList<>();
    @ToString.Exclude
    private final ProductRepository productRepository;

    /**
     * Добавляет товар в корзину по коду ID
     *
     * @param id код ID товара
     */
    public void addToCart(Integer id) {
        try {
            Product product = productRepository.getProduct(id);
            productList.add(product);
            System.out.println("ID " + '{' + id + '}' + " HAS ADDED");
        } catch (NoSuchElementException e) {
            System.out.println("ID " + '{' + id + '}' + " NOT FOUND. PLEASE TRY AGAIN");
        }
    }

    /**
     * Удаляет товар из корзины по коду ID
     *
     * @param id код ID товара
     */
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

    /**
     * Проверяет пуста ли корзина
     *
     * @return если корзина пуста - возвращает true
     */
    public boolean isEmpty() {
        return productList.isEmpty();
    }

    /**
     * Формирует строку из доступных ID товаров в корзине
     *
     * @return возвращает список доступных ID в корзине
     */
    public String getAvailableIdInCart() {
        productList.sort(new ProductComparator());
        StringJoiner sj = new StringJoiner(", ", "RANGE OF IDs IN CURRENT CART {", "}");
        productList.forEach(product -> sj.add(String.valueOf(product.getId())));
        return sj.toString();
    }
}
