package org.spring.example.cart.cart_managers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.spring.example.cart.Cart;
import org.spring.example.product.ProductRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import static org.spring.example.constants.Constants.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsoleCartManager {

    private Cart cart;
    private ProductRepository productRepository;

    /**
     * Запускает консольный менеджер корзины.
     * Основные команды:
     * add - добавление товара в корзину;
     * del - удаление товара из корзины;
     * cart - список товаров в корзине;
     * exit - закрыть менеджер.
     */
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(START_MSG);
            String line;
            while (!(line = reader.readLine()).equals(EXIT)) {
                switch (line.toLowerCase(Locale.ROOT)) {
                    case ADD: {
                        addToCart(reader);
                        break;
                    }
                    case DEL: {
                        removeFromCart(reader);
                        break;
                    }
                    case CART: {
                        printAvailableIdInCart();
                        break;
                    }
                    default:
                        break;
                }
                System.out.println(START_MSG);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printAvailableIdInCart() {
        if (cart.isEmpty()) {
            System.out.println(CART_IS_EMPTY);
        } else {
            System.out.println(cart);
        }
    }

    private void addToCart(BufferedReader reader) {
        String mess = ENTER_ID + productRepository.getRangeOfId();
        System.out.println(mess);

        Integer id = parseStringToInt(reader);
        cart.addToCart(id);
    }

    private void removeFromCart(BufferedReader reader) {
        if (cart.isEmpty()) {
            System.out.println(CART_IS_EMPTY);
        } else {
            String mess = ENTER_ID + cart.getAvailableIdInCart();
            System.out.println(mess);

            Integer id = parseStringToInt(reader);
            cart.removeFromCart(id);
        }
    }

    private Integer parseStringToInt(BufferedReader reader) {
        Integer id = null;
        try {
            String line2;
            while ((line2 = reader.readLine()) != null) {
                if (line2.matches("\\d")) {
                    id = Integer.parseInt(line2);
                    break;
                } else {
                    System.out.println("WRONG NUMBER, PLEASE TRY AGAIN");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }
}
