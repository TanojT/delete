package com.app.userdetails.repository;

import com.app.common.dto.ShipmentDetails;
import com.app.common.util.Util;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Component
public class ShipmentDetailsRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public List<ShipmentDetails> getAllShipments() {
        List<ShipmentDetails> shipmentDetails = new ArrayList<>();
        try{
            String GET_ALL_SHIPMENTS = "select \n" +
                    "shipmentNum,\n" +
                    "vehicleNo,\n" +
                    "driverName,\n" +
                    "shipmentDate as shipmentDateDb,\n" +
                    "ricemillName,\n" +
                    "ricemillArea,\n" +
                    "purchasePricePerTon,\n" +
                    "loadedGrossWeight,\n" +
                    "loadedEmptyWeight,\n" +
                    "loadedNetWeight,\n" +
                    "purchaseTotalCost,\n" +
                    "unloadedDate as unloadedDateDb,\n" +
                    "deliverToCompany,\n" +
                    "deliverToLocation,\n" +
                    "deliverToAddress,\n" +
                    "deliveryChallanNum,\n" +
                    "unloadPricePerTon,\n" +
                    "unloadGrossWeight,\n" +
                    "unloadEmptyWeight,\n" +
                    "unloadNetWeight,\n" +
                    "moistureDeductionPercentage,\n" +
                    "unloadTotalCost,\n" +
                    "tractorNum,\n" +
                    "labourGroupName,\n" +
                    "labourRatePerTon\n" +
                    "from SHIPMENT_DETAILS";

            shipmentDetails =  namedParameterJdbcTemplate.query(GET_ALL_SHIPMENTS, BeanPropertyRowMapper.newInstance(ShipmentDetails.class));

        }catch (Exception e){
            log.error("Exception in ShipmentDetailsRepository.getAllShipments()", e);
        }
        return shipmentDetails;
    }

    public ShipmentDetails getShipment(String shipmentNum) {
        ShipmentDetails shipmentDetails = null;
        try{
            String GET_ALL_SHIPMENT_BY_ID = "select \n" +
                    "shipmentNum,\n" +
                    "vehicleNo,\n" +
                    "driverName,\n" +
                    "shipmentDate as shipmentDateDb,\n" +
                    "ricemillName,\n" +
                    "ricemillArea,\n" +
                    "purchasePricePerTon,\n" +
                    "loadedGrossWeight,\n" +
                    "loadedEmptyWeight,\n" +
                    "loadedNetWeight,\n" +
                    "purchaseTotalCost,\n" +
                    "unloadedDate as unloadedDateDb,\n" +
                    "deliverToCompany,\n" +
                    "deliverToLocation,\n" +
                    "deliverToAddress,\n" +
                    "deliveryChallanNum,\n" +
                    "unloadPricePerTon,\n" +
                    "unloadGrossWeight,\n" +
                    "unloadEmptyWeight,\n" +
                    "unloadNetWeight,\n" +
                    "moistureDeductionPercentage,\n" +
                    "unloadTotalCost,\n" +
                    "tractorNum,\n" +
                    "labourGroupName,\n" +
                    "labourRatePerTon\n" +
                    "from SHIPMENT_DETAILS where shipmentNum=:shipmentNum";
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("shipmentNum", shipmentNum);


            shipmentDetails =  namedParameterJdbcTemplate.queryForObject(GET_ALL_SHIPMENT_BY_ID, mapSqlParameterSource, BeanPropertyRowMapper.newInstance(ShipmentDetails.class));

        }catch (Exception e){
            log.error("Exception in ShipmentDetailsRepository.getAllShipment()", e);
        }
        return shipmentDetails;
    }


    public void createShipment(ShipmentDetails shipmentDetails) {
        try{
            String CREATE_SHIPMENT= "INSERT INTO  SHIPMENT_DETAILS (\n" +
                    "                    shipmentNum,\n" +
                    "                    vehicleNo,\n" +
                    "                    driverName,\n" +
                    "                    shipmentDate,\n" +
                    "                    ricemillName,\n" +
                    "                    ricemillArea,\n" +
                    "                    purchasePricePerTon,\n" +
                    "                    loadedGrossWeight,\n" +
                    "                    loadedEmptyWeight,\n" +
                    "                    loadedNetWeight,\n" +
                    "                    purchaseTotalCost,\n" +
                    "                    unloadedDate,\n" +
                    "                    deliverToCompany,\n" +
                    "                    deliverToLocation,\n" +
                    "                    deliverToAddress,\n" +
                    "                    deliveryChallanNum,\n" +
                    "                    unloadPricePerTon,\n" +
                    "                    unloadGrossWeight,\n" +
                    "                    unloadEmptyWeight,\n" +
                    "                    unloadNetWeight,\n" +
                    "                    moistureDeductionPercentage,\n" +
                    "                    unloadTotalCost,\n" +
                    "                    tractorNum,\n" +
                    "                    labourGroupName,\n" +
                    "                    labourRatePerTon) VALUES(:shipmentNum,\n" +
                    "\t\t\t\t\t:vehicleNo,\n" +
                    "\t\t\t\t\t:driverName,\n" +
                    "\t\t\t\t\t:shipmentDate,\n" +
                    "\t\t\t\t\t:ricemillName,\n" +
                    "\t\t\t\t\t:ricemillArea,\n" +
                    "\t\t\t\t\t:purchasePricePerTon,\n" +
                    "\t\t\t\t\t:loadedGrossWeight,\n" +
                    "\t\t\t\t\t:loadedEmptyWeight,\n" +
                    "\t\t\t\t\t:loadedNetWeight,\n" +
                    "\t\t\t\t\t:purchaseTotalCost,\n" +
                    "\t\t\t\t\t:unloadedDate,\n" +
                    "\t\t\t\t\t:deliverToCompany,\n" +
                    "\t\t\t\t\t:deliverToLocation,\n" +
                    "\t\t\t\t\t:deliverToAddress,\n" +
                    "\t\t\t\t\t:deliveryChallanNum,\n" +
                    "\t\t\t\t\t:unloadPricePerTon,\n" +
                    "\t\t\t\t\t:unloadGrossWeight,\n" +
                    "\t\t\t\t\t:unloadEmptyWeight,\n" +
                    "\t\t\t\t\t:unloadNetWeight,\n" +
                    "\t\t\t\t\t:moistureDeductionPercentage,\n" +
                    "\t\t\t\t\t:unloadTotalCost,\n" +
                    "\t\t\t\t\t:tractorNum,\n" +
                    "\t\t\t\t\t:labourGroupName,\n" +
                    "\t\t\t\t\t:labourRatePerTon)";

            shipmentDetails.setShipmentNum(UUID.randomUUID().toString());
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("shipmentNum", shipmentDetails.getShipmentNum());
            mapSqlParameterSource.addValue("vehicleNo", shipmentDetails.getVehicleNo());
            mapSqlParameterSource.addValue("driverName", shipmentDetails.getDriverName());
            mapSqlParameterSource.addValue("shipmentDate", Util.sqlDateFormat.format(shipmentDetails.getShipmentDate()));
            mapSqlParameterSource.addValue("ricemillName", shipmentDetails.getRicemillName());
            mapSqlParameterSource.addValue("ricemillArea", shipmentDetails.getRicemillArea());
            mapSqlParameterSource.addValue("purchasePricePerTon", shipmentDetails.getPurchasePricePerTon());
            mapSqlParameterSource.addValue("loadedGrossWeight", shipmentDetails.getLoadedGrossWeight());
            mapSqlParameterSource.addValue("loadedEmptyWeight", shipmentDetails.getLoadedEmptyWeight());
            mapSqlParameterSource.addValue("loadedNetWeight", shipmentDetails.getLoadedNetWeight());
            mapSqlParameterSource.addValue("purchaseTotalCost", shipmentDetails.getPurchaseTotalCost());
            mapSqlParameterSource.addValue("unloadedDate", Util.sqlDateFormat.format(shipmentDetails.getUnloadedDate()));
            mapSqlParameterSource.addValue("deliverToCompany", shipmentDetails.getDeliverToCompany());
            mapSqlParameterSource.addValue("deliverToLocation", shipmentDetails.getDeliverToLocation());
            mapSqlParameterSource.addValue("deliverToAddress", shipmentDetails.getDeliverToAddress());
            mapSqlParameterSource.addValue("deliveryChallanNum", shipmentDetails.getDeliveryChallanNum());
            mapSqlParameterSource.addValue("unloadPricePerTon", shipmentDetails.getUnloadPricePerTon());
            mapSqlParameterSource.addValue("unloadGrossWeight", shipmentDetails.getUnloadGrossWeight());
            mapSqlParameterSource.addValue("unloadEmptyWeight", shipmentDetails.getUnloadEmptyWeight());
            mapSqlParameterSource.addValue("unloadNetWeight", shipmentDetails.getUnloadNetWeight());
            mapSqlParameterSource.addValue("moistureDeductionPercentage", shipmentDetails.getMoistureDeductionPercentage());
            mapSqlParameterSource.addValue("unloadTotalCost", shipmentDetails.getUnloadTotalCost());
            mapSqlParameterSource.addValue("tractorNum", shipmentDetails.getTractorNum());
            mapSqlParameterSource.addValue("labourGroupName", shipmentDetails.getLabourGroupName());
            mapSqlParameterSource.addValue("labourRatePerTon", shipmentDetails.getLabourRatePerTon());

            int updatedCount =  namedParameterJdbcTemplate.update(CREATE_SHIPMENT, mapSqlParameterSource);

            log.info("SHIPMENT INSERTED COUNT {}, SHIPMENT_ID: {}", updatedCount, shipmentDetails.getShipmentNum());

        }catch (Exception e){
            log.error("Exception in ShipmentDetailsRepository.createShipment()", e);
        }
    }


    public void updateShipment(ShipmentDetails shipmentDetails) {
        try{
            String UPDATE_SHIPMENT= "UPDATE  SHIPMENT_DETAILS SET\n" +
                    "            vehicleNo=:vehicleNo\n" +
                    "            driverName=:driverName\n" +
                    "            shipmentDate=:shipmentDate\n" +
                    "            ricemillName=:ricemillName\n" +
                    "            ricemillArea=:ricemillArea\n" +
                    "            purchasePricePerTon=:purchasePricePerTon\n" +
                    "            loadedGrossWeight=:loadedGrossWeight\n" +
                    "            loadedEmptyWeight=:loadedEmptyWeight\n" +
                    "            loadedNetWeight=:loadedNetWeight\n" +
                    "            purchaseTotalCost=:purchaseTotalCost\n" +
                    "            unloadedDate=:unloadedDate\n" +
                    "            deliverToCompany=:deliverToCompany\n" +
                    "            deliverToLocation=:deliverToLocation\n" +
                    "            deliverToAddress=:deliverToAddress\n" +
                    "            deliveryChallanNum=:deliveryChallanNum\n" +
                    "            unloadPricePerTon=:unloadPricePerTon\n" +
                    "            unloadGrossWeight=:unloadGrossWeight\n" +
                    "            unloadEmptyWeight=:unloadEmptyWeight\n" +
                    "            unloadNetWeight=:unloadNetWeight\n" +
                    "            moistureDeductionPercentage=:moistureDeductionPercentage\n" +
                    "            unloadTotalCost=:unloadTotalCost\n" +
                    "            tractorNum=:tractorNum\n" +
                    "            labourGroupName=:labourGroupName\n" +
                    "            labourRatePerTon=:labourRatePerTon\n" +
                    "            WHERE shipmentNum=:shipmentNum";

            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("shipmentNum", shipmentDetails.getShipmentNum());
            mapSqlParameterSource.addValue("vehicleNo", shipmentDetails.getVehicleNo());
            mapSqlParameterSource.addValue("driverName", shipmentDetails.getDriverName());
            mapSqlParameterSource.addValue("shipmentDate", Util.sqlDateFormat.format(shipmentDetails.getShipmentDate()));
            mapSqlParameterSource.addValue("ricemillName", shipmentDetails.getRicemillName());
            mapSqlParameterSource.addValue("ricemillArea", shipmentDetails.getRicemillArea());
            mapSqlParameterSource.addValue("purchasePricePerTon", shipmentDetails.getPurchasePricePerTon());
            mapSqlParameterSource.addValue("loadedGrossWeight", shipmentDetails.getLoadedGrossWeight());
            mapSqlParameterSource.addValue("loadedEmptyWeight", shipmentDetails.getLoadedEmptyWeight());
            mapSqlParameterSource.addValue("loadedNetWeight", shipmentDetails.getLoadedNetWeight());
            mapSqlParameterSource.addValue("purchaseTotalCost", shipmentDetails.getPurchaseTotalCost());
            mapSqlParameterSource.addValue("unloadedDate", Util.sqlDateFormat.format(shipmentDetails.getUnloadedDate()));
            mapSqlParameterSource.addValue("deliverToCompany", shipmentDetails.getDeliverToCompany());
            mapSqlParameterSource.addValue("deliverToLocation", shipmentDetails.getDeliverToLocation());
            mapSqlParameterSource.addValue("deliverToAddress", shipmentDetails.getDeliverToAddress());
            mapSqlParameterSource.addValue("deliveryChallanNum", shipmentDetails.getDeliveryChallanNum());
            mapSqlParameterSource.addValue("unloadPricePerTon", shipmentDetails.getUnloadPricePerTon());
            mapSqlParameterSource.addValue("unloadGrossWeight", shipmentDetails.getUnloadGrossWeight());
            mapSqlParameterSource.addValue("unloadEmptyWeight", shipmentDetails.getUnloadEmptyWeight());
            mapSqlParameterSource.addValue("unloadNetWeight", shipmentDetails.getUnloadNetWeight());
            mapSqlParameterSource.addValue("moistureDeductionPercentage", shipmentDetails.getMoistureDeductionPercentage());
            mapSqlParameterSource.addValue("unloadTotalCost", shipmentDetails.getUnloadTotalCost());
            mapSqlParameterSource.addValue("tractorNum", shipmentDetails.getTractorNum());
            mapSqlParameterSource.addValue("labourGroupName", shipmentDetails.getLabourGroupName());
            mapSqlParameterSource.addValue("labourRatePerTon", shipmentDetails.getLabourRatePerTon());

            int updatedCount =  namedParameterJdbcTemplate.update(UPDATE_SHIPMENT, mapSqlParameterSource);

            log.info("SHIPMENT UPDATED COUNT {}, SHIPMENT_ID: {}", updatedCount, shipmentDetails.getShipmentNum());

        }catch (Exception e){
            log.error("Exception in ShipmentDetailsRepository.updateShipment()", e);
        }
    }

    public void deleteShipment(String shipmentNum) {
        try{
            String DELETE_SHIPMENT = "DELETE from SHIPMENT_DETAILS where shipmentNum=:shipmentNum";
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("shipmentNum", shipmentNum);

            int deleteCount = namedParameterJdbcTemplate.update(DELETE_SHIPMENT, mapSqlParameterSource);

            log.info("SHIPMENT DELETED COUNT: {}, SHIPMENT NUM: {}", deleteCount, shipmentNum);

        }catch (Exception e){
            log.error("Exception in ShipmentDetailsRepository.deleteShipment()", e);
        }
    }

}
