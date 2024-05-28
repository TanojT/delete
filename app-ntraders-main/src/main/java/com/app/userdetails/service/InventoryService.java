package com.app.userdetails.service;


import com.app.common.dto.Inventory;
import com.app.userdetails.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;


    public Inventory getInventory(String vehicleNo){
        return inventoryRepository.getInventory(vehicleNo);
    }

    public List<Inventory> getAllInventories(){
        return inventoryRepository.getAllInventories();
    }

    public void createInventory(Inventory inventory){
        inventoryRepository.createInventory(inventory);
    }

    public void updateInventory(Inventory inventory){
        inventoryRepository.updateInventory(inventory);
    }

    public void deleteInventory(String vehicleNO){
        inventoryRepository.deleteInventory(vehicleNO);
    }
}
