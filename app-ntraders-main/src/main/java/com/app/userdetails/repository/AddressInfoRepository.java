package com.app.userdetails.repository;

import com.app.common.dto.AddressInfoDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
@AllArgsConstructor
public class AddressInfoRepository
{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createAddressInfo(AddressInfoDto addressInfoDto)
    {
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("ADDRESS_ID", UUID.randomUUID().toString());
            params.addValue("EMPLOYEE_ID", addressInfoDto.getEmployeeId());
            params.addValue("COMPANY_ID", addressInfoDto.getCompanyId());
            params.addValue("RICEMILL_ID", addressInfoDto.getRicemillId());
            params.addValue("ADDRESS_DESC", addressInfoDto.getAddressDesc());
            params.addValue("ADDRESS_TYPE", addressInfoDto.getAddressType());
            params.addValue("ADDRESS_LANE1", addressInfoDto.getAddressLane1());
            params.addValue("ADDRESS_LANE2", addressInfoDto.getAddressLane2());
            params.addValue("LOCATION", addressInfoDto.getLocation());
            params.addValue("DISTRICT", addressInfoDto.getDistrict());
            params.addValue("STATE",addressInfoDto.getState());
            params.addValue("PINCODE", addressInfoDto.getPinCode());
            params.addValue("CREATED_BY", addressInfoDto.getCreatedBy());

            int updateCount = namedParameterJdbcTemplate.update("insert into address_info (ADDRESS_ID, EMPLOYEE_ID,\n" +
                    "COMPANY_ID,\n" +
                    "RICEMILL_ID,\n" +
                    "ADDRESS_DESC,\n" +
                    "ADDRESS_TYPE,\n" +
                    "ADDRESS_LANE1,\n" +
                    "ADDRESS_LANE2,\n" +
                    "LOCATION,\n" +
                    "DISTRICT,\n" +
                    "STATE,\n" +
                    "PINCODE,\n" +
                    "CREATED_BY,\n" +
                    "CREATED_ON) values(:ADDRESS_ID, :EMPLOYEE_ID, \n" +
                    ":COMPANY_ID, \n" +
                    ":RICEMILL_ID, \n" +
                    ":ADDRESS_DESC, \n" +
                    ":ADDRESS_TYPE, \n" +
                    ":ADDRESS_LANE1, \n" +
                    ":ADDRESS_LANE2, \n" +
                    ":LOCATION, \n" +
                    ":DISTRICT, \n" +
                    ":STATE, \n" +
                    ":PINCODE, \n" +
                    ":CREATED_BY, \n" +
                    "now())",params);
            log.info("Address Info created  count: ()", updateCount);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void updateAddressInfo(AddressInfoDto addressInfoDto)
    {
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("ADDRESS_ID", UUID.randomUUID().toString());
            params.addValue("EMPLOYEE_ID", addressInfoDto.getEmployeeId());
            params.addValue("COMPANY_ID", addressInfoDto.getCompanyId());
            params.addValue("RICEMILL_ID", addressInfoDto.getRicemillId());
            params.addValue("ADDRESS_DESC", addressInfoDto.getAddressDesc());
            params.addValue("ADDRESS_TYPE", addressInfoDto.getAddressType());
            params.addValue("ADDRESS_LANE1", addressInfoDto.getAddressLane1());
            params.addValue("ADDRESS_LANE2", addressInfoDto.getAddressLane2());
            params.addValue("LOCATION", addressInfoDto.getLocation());
            params.addValue("DISTRICT", addressInfoDto.getDistrict());
            params.addValue("STATE",addressInfoDto.getState());
            params.addValue("PINCODE", addressInfoDto.getPinCode());
            params.addValue("MODIFIED_BY", addressInfoDto.getCreatedBy());

            int updateCount = namedParameterJdbcTemplate.update("update address_info set "+
                    "ADDRESS_ID=:ADDRESS_ID," +
                            "EMPLOYEE_ID=:EMPLOYEE_ID," +
                            "COMPANY_ID=:COMPANY_ID," +
                            "RICEMILL_ID=:RICEMILL_ID," +
                            "ADDRESS_DESC=:ADDRESS_DESC," +
                            "ADDRESS_TYPE=:ADDRESS_TYPE," +
                            "ADDRESS_LANE1:=ADDRESS_LANE1," +
                            "ADDRESS_LANE2:ADDRESS_LANE2," +
                            "LOCATION:=LOCATION," +
                            "DISTRICT:=DISTRICT," +
                            "STATE=:STATE," +
                            "PINCODE=:PINCODE," +
                            "MODIFIED_BY=:MODIFIED_BY," +
                            "MODIFIED_ON=now()," +
                            "WHERE ADDRESS_ID=:ADDRESS_ID" , params);
            log.info("Address Info updated Count: {}",updateCount);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public List<AddressInfoDto> getAddressInfoByEmployeeId(String employeeId)
    {
        List<AddressInfoDto> addressInfoDtos = null;
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("EMPLOYEE_ID", employeeId);
            addressInfoDtos = namedParameterJdbcTemplate.query("select * from address_info where EMPLOYEE_ID=:EMPLOYEE_ID", params,
                    BeanPropertyRowMapper.newInstance(AddressInfoDto.class));
            log.info("Address info for: {} is : {}", employeeId, addressInfoDtos );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return addressInfoDtos;
    }

    public List<AddressInfoDto> getAddressInfoCompanyId(String companyId)
    {
        List<AddressInfoDto> addressInfoDtos = null;
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("COMPANY_ID", companyId);
            addressInfoDtos = namedParameterJdbcTemplate.query("select * from address_info where COMPANY_ID=:COMPANY_ID", params,
                    BeanPropertyRowMapper.newInstance(AddressInfoDto.class));
            log.info("Address info for: {} is : {}", companyId, addressInfoDtos );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return addressInfoDtos;
    }

    public List<AddressInfoDto> getAddressInfoByRiceMillId(String riceMillId)
    {
        List<AddressInfoDto> addressInfoDtos = null;
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("RICEMILL_ID", riceMillId);
            addressInfoDtos = namedParameterJdbcTemplate.query("select * from address_info where RICEMILL_ID=:RICEMILL_ID", params,
                    BeanPropertyRowMapper.newInstance(AddressInfoDto.class));
            log.info("Address info for: {} is : {}", riceMillId, addressInfoDtos );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return addressInfoDtos;
    }
    public List<AddressInfoDto> getAllAddresses()
    {
        List<AddressInfoDto> addresses = null;
        try
        {
            addresses = namedParameterJdbcTemplate.query("select * from address_info ",
                    BeanPropertyRowMapper.newInstance(AddressInfoDto.class));
            log.info("Address info count :{}" , addresses!=null ? addresses.size(): addresses);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return addresses;
    }

    public void deleteEmployeeAddressInfo(String employeeId)
    {
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("EMPLOYEE_ID", employeeId);
            int count = namedParameterJdbcTemplate.update(
                    "delete from address_info where EMPLOYEE_ID=:EMPLOYEE_ID", params);
            log.info("Address info deleted for: {} is deleted . Delete count: {} ", employeeId, count);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void deleteRiceMillAddressInfo(String ricemillId)
    {
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("RICEMILL_ID", ricemillId);
            int count = namedParameterJdbcTemplate.update(
                    "delete from address_info where RICEMILL_ID=:RICEMILL_ID", params);
            log.info("Address info deleted for: {} is deleted . Delete count: {} ", ricemillId, count);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void deleteCompanyAddressInfo(String companyId)
    {
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("COMPANY_ID", companyId);
            int count = namedParameterJdbcTemplate.update(
                    "delete from address_info where COMPANY_ID=:COMPANY_ID", params);
            log.info("Address info deleted for: {} is deleted . Delete count: {} ", companyId, count);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void deleteAddressInfoByAddressId(String addressId)
    {
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("ADDRESS_ID", addressId);

            int count = namedParameterJdbcTemplate.update(
                    "delete from address_info where ADDRESS_ID=:ADDRESS_ID", params);
            log.info("Address info deleted for: {}. Delete count: {} ", addressId, count);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
