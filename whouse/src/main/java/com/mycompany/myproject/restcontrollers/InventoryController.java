package com.mycompany.myproject.restcontrollers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myproject.model.Inventory;
import com.mycompany.myproject.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;


@RestController
public class InventoryController {


    @Autowired(required = true)
    private InventoryRepository inventoryRepository;

    @GetMapping("/inventories")
    public List<Inventory> getInventories() {
        return (List<Inventory>) inventoryRepository.getInventories();
    }

    @PostMapping("/inventories")
    void addInventory(@RequestBody String json) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            Iterator<JsonNode> jsonNodeIterator = rootNode.iterator();
            ArrayList<LinkedHashMap<String,String>> inventories;

            while (jsonNodeIterator.hasNext()) {
                JsonNode next = jsonNodeIterator.next();
                inventories = (ArrayList<LinkedHashMap<String,String>>) objectMapper.treeToValue(next,Object.class);
                for (LinkedHashMap<String,String> inventory: inventories) {
                    ArrayList<String> inventoryDefauls = new ArrayList<>();
                    for (String key : inventory.keySet()) {
                        inventoryDefauls.add(inventory.get(key));
                    }
                    inventoryRepository.addInventory(new Inventory(inventoryDefauls));
                }
            }

        } catch (Exception e){
            System.out.println("APPEND LOG: INCORRECT FILE UPLOADED: " + e.getMessage());
        }

    }

}