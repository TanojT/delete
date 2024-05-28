package com.app.userdetails.controller;

import com.app.common.dto.ShipmentDetails;
import com.app.userdetails.service.ShipmentDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipment-details")
public class ShipmentDetailsController {

    @Autowired
    private ShipmentDetailsService shipmentDetailsService;

    @GetMapping("/get-all")
    public List<ShipmentDetails> getAllShipments(){
        return shipmentDetailsService.getAllShipments();
    }

    @GetMapping("/get-shipment/{shipmentNum}")
    public ShipmentDetails getShipment(@PathVariable String shipmentNum){
        return shipmentDetailsService.getShipment(shipmentNum);
    }

    @PostMapping("/create")
    public ShipmentDetails createShipment(@RequestBody ShipmentDetails shipmentDetails){
        return shipmentDetailsService.createShipment(shipmentDetails);
    }

    @PutMapping("/update")
    public ShipmentDetails updateShipment(@RequestBody ShipmentDetails shipmentDetails){
        return shipmentDetailsService.updateShipment(shipmentDetails);
    }

    @DeleteMapping("/delete/{shipmentNum}")
    public void deleteShipment(@PathVariable String shipmentNum){
        shipmentDetailsService.deleteShipment(shipmentNum);
    }

}
