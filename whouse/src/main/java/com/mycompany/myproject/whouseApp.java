package com.mycompany.myproject;

import com.mycompany.myproject.model.Inventory;
import com.mycompany.myproject.model.Product;
import com.mycompany.myproject.repositories.InventoryRepository;
import com.mycompany.myproject.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;


@SpringBootApplication
public class whouseApp {

    public static void main(String[] args) {
        SpringApplication.run(whouseApp.class, args);
    }

    @Bean
    CommandLineRunner init(
            InventoryRepository inventoryRepository,
            ProductRepository productRepository
    ) {
        return args -> {
            System.out.println("Starting...");

            Inventory inventory1 = new Inventory(73,"Wood",5340);
            Inventory inventory2 = new Inventory(93,"Metal stick",2345);

            inventoryRepository.addInventory(inventory1);
            inventoryRepository.addInventory(inventory2);

            Product chair = new Product("Chair","73:2,93:6");
            Product wardrobe = new Product("Wardrobe","73:14,93:15");

            productRepository.addProduct(chair);
            productRepository.addProduct(wardrobe);

            System.out.println("Running");
        };
    }

}
