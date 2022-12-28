package org.spring.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ProductRepository {

    private final List<Product> productList;

    public Product getProduct(int id) {
        return productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductException(String.format("NO PRODUCTS FOUND FOR BY ID: {%d}", id)));
    }
}
