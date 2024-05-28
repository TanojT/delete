package com.app.userdetails.repository;

import com.app.common.dto.AssetDetails;
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
public class AssetDetailsRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void create(AssetDetails assetDetails){
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("VEHICLE_NUMBER", assetDetails.getVehicleNumber());
            params.addValue("MODEL", assetDetails.getModel());
            params.addValue("MANUFACTURER", assetDetails.getManufacturer());
            params.addValue("TYPE", assetDetails.getType());
            params.addValue("ASSET_OWNER", assetDetails.getAssetOwner());
            params.addValue("DATE_OF_REGISTRATION", assetDetails.getDateOfRegistration());
            params.addValue("RC_VALIDITY", assetDetails.getRcValidity());
            params.addValue("PERMIT_TYPE", assetDetails.getPermitType());
            params.addValue("PERMIT_STATUS", assetDetails.getPermitStatus());
            params.addValue("VEHICLE_STATUS", assetDetails.getVehicleStatus());
            params.addValue("CREATED_BY", assetDetails.getCreatedBy());


            int updateCount = namedParameterJdbcTemplate.update("insert into ASSET_DETAILS (VEHICLE_NUMBER," +
                    "MODEL," +
                    "MANUFACTURER," +
                    "TYPE," +
                    "ASSET_OWNER," +
                    "DATE_OF_REGISTRATION," +
                    "RC_VALIDITY," +
                    "PERMIT_TYPE," +
                    "PERMIT_STATUS," +
                    "VEHICLE_STATUS," +
                    "CREATED_BY," +
                    "CREATED_ON) values(:VEHICLE_NUMBER,:MODEL,:MANUFACTURER,:TYPE,:ASSET_OWNER,:DATE_OF_REGISTRATION,:RC_VALIDITY,:PERMIT_TYPE,:PERMIT_STATUS,:VEHICLE_STATUS, :CREATED_BY, now())",params);
            log.info("ASSET_DETAILS created  count: ()", updateCount);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Exception while creating ASSET_DETAILS: {}", e.getMessage());
        }

    }

    public void update(AssetDetails assetDetails){
        try{

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("VEHICLE_NUMBER", assetDetails.getVehicleNumber());
            params.addValue("MODEL", assetDetails.getModel());
            params.addValue("MANUFACTURER", assetDetails.getManufacturer());
            params.addValue("TYPE", assetDetails.getType());
            params.addValue("ASSET_OWNER", assetDetails.getAssetOwner());
            params.addValue("DATE_OF_REGISTRATION", assetDetails.getDateOfRegistration());
            params.addValue("RC_VALIDITY", assetDetails.getRcValidity());
            params.addValue("PERMIT_TYPE", assetDetails.getPermitType());
            params.addValue("PERMIT_STATUS", assetDetails.getPermitStatus());
            params.addValue("VEHICLE_STATUS", assetDetails.getVehicleStatus());
            params.addValue("MODIFIED_BY", assetDetails.getModifiedBy());

            int updateCount = namedParameterJdbcTemplate.update("update ASSET_DETAILS set " +
                    "VEHICLE_NUMBER=:VEHICLE_NUMBER," +
                    "MODEL=:MODEL," +
                    "TYPE=:TYPE," +
                    "ASSET_OWNER=:ASSET_OWNER," +
                    "DATE_OF_REGISTRATION=:DATE_OF_REGISTRATION," +
                    "RC_VALIDITY=:RC_VALIDITY," +
                    "PERMIT_TYPE=:PERMIT_TYPE," +
                    "PERMIT_STATUS=:PERMIT_STATUS," +
                    "VEHICLE_STATUS=:VEHICLE_STATUS," +
                    "MODIFIED_BY=:MODIFIED_BY, " +
                    "MODIFIED_ON=now() " +
                    "WHERE VEHICLE_NUMBER=:VEHICLE_NUMBER" , params);

            log.info("ASSET_DETAILS updated Count: {}",updateCount);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public AssetDetails getById(String vehicleNo){
        AssetDetails assetDetails = null;
        try{
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("VEHICLE_NUMBER",vehicleNo);
            assetDetails = namedParameterJdbcTemplate.queryForObject(
                    "select * from ASSET_DETAILS where VEHICLE_NUMBER=:VEHICLE_NUMBER" , params,
                    BeanPropertyRowMapper.newInstance(AssetDetails.class));
            log.info("inventory_details for: {} is : {}",vehicleNo, assetDetails);
        }catch(Exception e){
            e.printStackTrace();
        }
        return assetDetails;
    }

    public List<AssetDetails> getAll(){
        List<AssetDetails> assetDetailsList = null;
        try{
            assetDetailsList = namedParameterJdbcTemplate.query(
                    "select * from ASSET_DETAILS" ,
                    BeanPropertyRowMapper.newInstance(AssetDetails.class));
            log.info("ASSET_DETAILS count: {}", assetDetailsList!=null ? assetDetailsList.size() : assetDetailsList);
        }catch(Exception e){
            e.printStackTrace();
        }
        return assetDetailsList;
    }

    public void delete(String vehicleNo){
        try{
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("VEHICLE_NUMBER",vehicleNo);
            int count = namedParameterJdbcTemplate.update(
                    "delete from ASSET_DETAILS where VEHICLE_NUMBER=:VEHICLE_NUMBER" , params);
            log.info("ASSET_DETAILS for: {} is deleted. Delete Count: {}",vehicleNo, count);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
