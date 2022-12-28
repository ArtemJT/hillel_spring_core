package org.spring.example.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@ToString
public class ProductRepository {

    private final List<Product> productList;

    /**
     * Метод ищет товар в репозитории по переданному id и возвращает его.
     * В противном случае бросает NoSuchElementException
     *
     * @param id принимает номер ID
     * @return возвращает товар или бросает NoSuchElementException
     */
    public Product getProduct(Integer id) {
        return productList.stream()
                .filter(product -> Objects.equals(product.getId(), id))
                .findFirst()
                .orElseThrow();
    }

    /**
     * Метод сортирует репозиторий и формирует строку из диапазона ID доступных товаров
     *
     * @return строка с первым и последним ID
     */
    public String getRangeOfId() {
        productList.sort(new ProductComparator());
        Integer firstId = productList.get(0).getId();
        Integer lastId = productList.get(productList.size() - 1).getId();
        return String.format("Products ID from: %d to %d", firstId, lastId);
    }
}
