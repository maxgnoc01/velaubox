package com.shoppingCart.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.shoppingCart.app.model"})
public class ShoppingCartApplication {

    public static void main(String[] args) {
//    	 System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ShoppingCartApplication.class, args);
    }
}
