package com.mycompany.myproject.repositories;

import com.mycompany.myproject.model.Inventory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class InventoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addInventory(Inventory inventory) {
        this.entityManager.persist(inventory);
    }

    @Transactional
    public List<Inventory> getInventories(){
        return this.entityManager.createQuery("Select e from Inventory e").getResultList();
    }

}
