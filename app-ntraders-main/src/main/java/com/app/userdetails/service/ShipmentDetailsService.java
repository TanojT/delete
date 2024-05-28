package com.app.userdetails.service;

import com.app.common.dto.ShipmentDetails;
import com.app.common.util.Util;
import com.app.userdetails.repository.ShipmentDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ShipmentDetailsService {



    private final ShipmentDetailsRepository shipmentDetailsRepository;

    public List<ShipmentDetails> getAllShipments() {
        return shipmentDetailsRepository.getAllShipments()
                .stream().map(shipment ->  this.convertUtilDate(shipment)).collect(Collectors.toList());
    }

    public ShipmentDetails getShipment(String shipmentNum){
        return this.convertUtilDate(shipmentDetailsRepository.getShipment(shipmentNum));
    }

    public ShipmentDetails createShipment(ShipmentDetails shipmentDetails){
        shipmentDetails.setLoadedNetWeight(shipmentDetails.getLoadedGrossWeight() - shipmentDetails.getLoadedEmptyWeight());
        shipmentDetails.setUnloadNetWeight(shipmentDetails.getUnloadGrossWeight() - shipmentDetails.getUnloadEmptyWeight());
        shipmentDetailsRepository.createShipment(shipmentDetails);
        return this.getShipment(shipmentDetails.getShipmentNum());
    }

    public ShipmentDetails updateShipment(ShipmentDetails shipmentDetails){
        shipmentDetails.setLoadedNetWeight(shipmentDetails.getLoadedGrossWeight() - shipmentDetails.getLoadedEmptyWeight());
        shipmentDetails.setUnloadNetWeight(shipmentDetails.getUnloadGrossWeight() - shipmentDetails.getUnloadEmptyWeight());
        shipmentDetailsRepository.updateShipment(shipmentDetails);
        return this.getShipment(shipmentDetails.getShipmentNum());
    }

    public void deleteShipment(String shipmentNum){
        shipmentDetailsRepository.deleteShipment(shipmentNum);
    }

    private ShipmentDetails convertUtilDate(ShipmentDetails shipmentDetails){
        shipmentDetails.setShipmentDate(Util.getUtilDate(shipmentDetails.getShipmentDateDb()));
        shipmentDetails.setUnloadedDate(Util.getUtilDate(shipmentDetails.getUnloadedDateDb()));
        return shipmentDetails;
    }

}
