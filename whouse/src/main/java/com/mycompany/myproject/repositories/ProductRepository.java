package com.mycompany.myproject.repositories;

import com.mycompany.myproject.model.Inventory;
import com.mycompany.myproject.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addProduct(Product product) {
        this.entityManager.persist(product);
    }

    @Transactional
    public StringBuilder getAvailableProducts(){

        List<Product> allProducts = this.entityManager.createQuery("Select e from Product e").getResultList();
        HashMap<Product,Integer> availableProducts = new HashMap<>();
        List<Inventory> allInventories = this.entityManager.createQuery("Select e from Inventory e").getResultList();
        HashMap<Long,Integer> inventoriesLeft = new HashMap<>();

        for(Inventory inventory: allInventories){
            inventoriesLeft.put(inventory.getArt_id(),inventory.getStock());
        }
        Boolean productAdded = true;

        while (productAdded) {
            for (Product product : allProducts) {
                HashMap<Long, Integer> requiredItems4CertainProduct = new HashMap<>();
                String[] parts = product.getContainArticles().split(",");
                for (String part : parts) {
                    requiredItems4CertainProduct.put(Long.parseLong(part.substring(0, part.indexOf(":"))), Integer.parseInt(part.substring(part.indexOf(":") + 1)));
                }
                Boolean available = true;
                for (Long item : requiredItems4CertainProduct.keySet()) {
                    if (requiredItems4CertainProduct.get(item) > inventoriesLeft.getOrDefault(item, 0)) {
                        available = false;
                    }
                }
                if (available) {
                    availableProducts.put(product, availableProducts.getOrDefault(product, 0) + 1);
                    for (Long item : requiredItems4CertainProduct.keySet()) {
                        inventoriesLeft.put(item, inventoriesLeft.get(item) - requiredItems4CertainProduct.get(item));
                    }
                    productAdded = true;
                } else {
                    productAdded = false;
                }
            }
        }

        StringBuilder availableProductsString = new StringBuilder();
        availableProductsString.append("Available products: ");
        for(Product product: availableProducts.keySet()){
            availableProductsString.append(availableProducts.get(product) + " of " + product.getName() + ", ");
        }
        availableProductsString.append("Left stocks: ");
        for(Long inventory: inventoriesLeft.keySet()){
            availableProductsString.append(inventory + ": " + inventoriesLeft.get(inventory) + " items, ");
        }

        return availableProductsString;

    }

}
