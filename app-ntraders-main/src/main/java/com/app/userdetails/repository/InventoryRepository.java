package com.app.userdetails.repository;

import com.app.common.dto.Inventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InventoryRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createInventory(Inventory inventory){
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("VEHICLE_NUMBER", inventory.getVehicleNumber());
            params.addValue("MODEL", inventory.getModel());
            params.addValue("MANUFACTURER", inventory.getManufacturer());
            params.addValue("TYPE", inventory.getType());
            params.addValue("DATE_OF_REGISTRATION", inventory.getDateOfRegistration());
            params.addValue("RC_VALIDITY", inventory.getRcValidity());
            params.addValue("PERMIT_TYPE", inventory.getPermitType());
            params.addValue("PERMIT_STATUS", inventory.getPermitStatus());
            params.addValue("CREATED_BY", inventory.getCreatedBy());


            int updateCount = namedParameterJdbcTemplate.update("insert into inventory_details (VEHICLE_NUMBER," +
                    "MODEL," +
                    "MANUFACTURER," +
                    "TYPE," +
                    "DATE_OF_REGISTRATION," +
                    "RC_VALIDITY," +
                    "PERMIT_TYPE," +
                    "PERMIT_STATUS," +
                    "CREATED_BY," +
                    "CREATED_ON) values(:VEHICLE_NUMBER,:MODEL,:MANUFACTURER,:TYPE,:DATE_OF_REGISTRATION,:RC_VALIDITY,:PERMIT_TYPE,:PERMIT_STATUS, :CREATED_BY, now())",params);
            log.info("Inventory created  count: ()", updateCount);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Exception while creating inventory: {}", e.getMessage());
        }

    }

    public void updateInventory(Inventory inventory){
        try{

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("VEHICLE_NUMBER", inventory.getVehicleNumber());
            params.addValue("MODEL", inventory.getModel());
            params.addValue("MANUFACTURER", inventory.getManufacturer());
            params.addValue("TYPE", inventory.getType());
            params.addValue("DATE_OF_REGISTRATION", inventory.getDateOfRegistration());
            params.addValue("RC_VALIDITY", inventory.getRcValidity());
            params.addValue("PERMIT_TYPE", inventory.getPermitType());
            params.addValue("PERMIT_STATUS", inventory.getPermitStatus());
            params.addValue("MODIFIED_BY", inventory.getCreatedBy());

            int updateCount = namedParameterJdbcTemplate.update("update inventory_details set " +
                    "VEHICLE_NUMBER=:VEHICLE_NUMBER," +
                    "MODEL=:MODEL," +
                    "TYPE=:TYPE," +
                    "DATE_OF_REGISTRATION=:DATE_OF_REGISTRATION," +
                    "RC_VALIDITY=:RC_VALIDITY," +
                    "PERMIT_TYPE=:PERMIT_TYPE," +
                    "PERMIT_STATUS=:PERMIT_STATUS," +
                    "MODIFIED_BY=:MODIFIED_BY, " +
                    "MODIFIED_ON=now() " +
                    "WHERE VEHICLE_NUMBER=:VEHICLE_NUMBER" , params);

            log.info("inventory_details updated Count: {}",updateCount);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Inventory getInventory(String vehicleNo){
        Inventory inventory = null;
        try{
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("VEHICLE_NUMBER",vehicleNo);
            inventory = namedParameterJdbcTemplate.queryForObject(
                    "select * from inventory_details where VEHICLE_NUMBER=:VEHICLE_NUMBER" , params,
                    BeanPropertyRowMapper.newInstance(Inventory.class));
            log.info("inventory_details for: {} is : {}",vehicleNo, inventory);
        }catch(Exception e){
            e.printStackTrace();
        }
        return inventory;
    }

    public List<Inventory> getAllInventories(){
        List<Inventory> inventories = null;
        try{
            inventories = namedParameterJdbcTemplate.query(
                    "select * from inventory_details" ,
                    BeanPropertyRowMapper.newInstance(Inventory.class));
            log.info("inventory_details count: {}", inventories!=null ? inventories.size() : inventories);
        }catch(Exception e){
            e.printStackTrace();
        }
        return inventories;
    }

    public void deleteInventory(String vehicleNo){
        try{
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("VEHICLE_NUMBER",vehicleNo);
            int count = namedParameterJdbcTemplate.update(
                    "delete from inventory_details where VEHICLE_NUMBER=:VEHICLE_NUMBER" , params);
            log.info("inventory_details for: {} is deleted. Delete Count: {}",vehicleNo, count);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
