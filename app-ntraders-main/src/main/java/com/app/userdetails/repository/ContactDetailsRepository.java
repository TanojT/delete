package com.app.userdetails.repository;

import com.app.common.dto.ContactDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ContactDetailsRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createContactInfo(ContactDto contactDto)
    {
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("CONTACT_ID", UUID.randomUUID().toString());
            params.addValue("EMPLOYEE_ID", contactDto.getEmployeeId());
            params.addValue("COMPANY_ID", contactDto.getCompanyId());
            params.addValue("RICEMILL_ID", contactDto.getRicemillId());
            params.addValue("TYPE", contactDto.getType());
            params.addValue("MOBILE_NO", contactDto.getMobileNo());
            params.addValue("EMAIL_ID", contactDto.getEmailId());
            params.addValue("CREATED_BY", contactDto.getCreatedBy());

            int updateCount = namedParameterJdbcTemplate.update("insert into contact_info (" +
                    "CONTACT_ID," +
                    "EMPLOYEE_ID,\n" +
                    "COMPANY_ID,\n" +
                    "RICEMILL_ID,\n" +
                    "TYPE,\n" +
                    "MOBILE_NO,\n" +
                    "EMAIL_ID,\n" +
                    "CREATED_BY,\n" +
                    "CREATED_ON) values(:CONTACT_ID, " +
                    ":EMPLOYEE_ID, \n" +
                    ":COMPANY_ID, \n" +
                    ":RICEMILL_ID, \n" +
                    ":TYPE, \n" +
                    ":MOBILE_NO, \n" +
                    ":EMAIL_ID, \n" +
                    ":CREATED_BY, \n" +
                    "now())",params);
            log.info("Contact Info created  count: ()", updateCount);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public List<ContactDto> getContactsByEmployeeId(String employeeId)
    {
        List<ContactDto> contactDtos = null;
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("EMPLOYEE_ID", employeeId);
            contactDtos = namedParameterJdbcTemplate.query("select * from contact_info where EMPLOYEE_ID=:EMPLOYEE_ID", params,
                    BeanPropertyRowMapper.newInstance(ContactDto.class));
            log.info("Contact info for: {} is : {}", employeeId, contactDtos );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return contactDtos;
    }

    public List<ContactDto> getContactsByRicemillId(String ricemillId)
    {
        List<ContactDto> contactDtos = null;
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("RICEMILL_ID", ricemillId);
            contactDtos = namedParameterJdbcTemplate.query("select * from contact_info where RICEMILL_ID=:RICEMILL_ID", params,
                    BeanPropertyRowMapper.newInstance(ContactDto.class));
            log.info("Contact info for: {} is : {}", ricemillId, contactDtos );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return contactDtos;
    }

    public List<ContactDto> getContactsByCompanyId(String companyId)
    {
        List<ContactDto> contactDtos = null;
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("COMPANY_ID", companyId);
            contactDtos = namedParameterJdbcTemplate.query("select * from contact_info where COMPANY_ID=:COMPANY_ID", params,
                    BeanPropertyRowMapper.newInstance(ContactDto.class));
            log.info("Contact info for: {} is : {}", companyId, contactDtos );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return contactDtos;
    }

    public void deleteContactByContactId(String contactId)
    {
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("CONTACT_ID", contactId);

            int count = namedParameterJdbcTemplate.update(
                    "delete from contact_info where CONTACT_ID=:CONTACT_ID", params);
            log.info("Contact info deleted for: {}. Delete count: {} ", contactId, count);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
