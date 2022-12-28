package org.spring.example;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String ADD = "add";
    public static final String DEL = "del";
    public static final String CART = "cart";
    public static final String EXIT = "exit";
    public static final String START_MSG =
            String.format("PLEASE ENTER COMMANDS \"%s\" OR \"%s\" OR \"%s\" OR \"%s\" TO EXIT",
            ADD, DEL, CART, EXIT);
    public static final String ENTER_ID ="PLEASE ENTER ID. ";
    public static final String CART_IS_EMPTY = "CART IS EMPTY. ADD SOME PRODUCT FIRST";

}
