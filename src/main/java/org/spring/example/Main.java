package org.spring.example;

import org.spring.example.cart.cart_managers.ConsoleCartManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        String configPath = "file:src/main/resources/ApplicationContext.xml";
        ApplicationContext context = new FileSystemXmlApplicationContext(configPath);

        ConsoleCartManager consoleCartManager = context.getBean("consoleCartManager", ConsoleCartManager.class);
        consoleCartManager.run();
    }
}
