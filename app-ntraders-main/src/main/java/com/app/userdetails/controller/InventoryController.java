package com.app.userdetails.controller;

import com.app.common.dto.Inventory;
import com.app.userdetails.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("getByVehicleNo/{vehicleNo}")
    public Inventory getInventory(@PathVariable String vehicleNo){
        return inventoryService.getInventory(vehicleNo);
    }

    @GetMapping("getAllInventories")
    public List<Inventory> getAllInventories(){
        return inventoryService.getAllInventories();
    }

    @PostMapping
    public void createInventory(@RequestBody Inventory inventory){
        inventoryService.createInventory(inventory);
    }

    @PutMapping
    public void updateInventory(@RequestBody Inventory inventory){
        inventoryService.updateInventory(inventory);
    }

    @DeleteMapping
    public void deleteInventory(@PathVariable String vehicleNO){
        inventoryService.deleteInventory(vehicleNO);
    }
}
