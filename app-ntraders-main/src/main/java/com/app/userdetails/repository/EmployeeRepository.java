package com.app.userdetails.repository;

import com.app.common.dto.EmployeeDto;
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
public class EmployeeRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public EmployeeDto getEmployee(String employeeId){
        EmployeeDto employeeDto = null;
        try{
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("EMPLOYEE_ID",employeeId);
            employeeDto = namedParameterJdbcTemplate.queryForObject(
                    "select * from employee_details where EMPLOYEE_ID=:EMPLOYEE_ID" , params,
                    BeanPropertyRowMapper.newInstance(EmployeeDto.class));
            log.info("employee_details for: {} is : {}",employeeId, employeeDto);
        }catch(Exception e){
            e.printStackTrace();
        }
        return employeeDto;
    }

    public List<EmployeeDto> getAllEmployees(){
        List<EmployeeDto> employeeDtos = null;
        try{
            employeeDtos = namedParameterJdbcTemplate.query(
                    "select * from employee_details" ,
                    BeanPropertyRowMapper.newInstance(EmployeeDto.class));
            log.info("employee_details count: {}", employeeDtos!=null ? employeeDtos.size() : employeeDtos);
        }catch(Exception e){
            e.printStackTrace();
        }
        return employeeDtos;
    }

    public void createEmployee(EmployeeDto employee){
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("EMPLOYEE_ID", employee.getEmployeeId());
            params.addValue("EMPLOYEE_TYPE", employee.getEmployeeType());
            params.addValue("EMPLOYEE_NAME", employee.getEmployeeName());
            params.addValue("DOB", employee.getDob());
            params.addValue("STATUS", employee.getStatus());
            params.addValue("LICENCE_NUMBER", employee.getLicenceNumber());
            params.addValue("LICENCE_VALIDITY", employee.getLicenceValidity());
            params.addValue("LICENCE_STATE", employee.getLicenceState());
            params.addValue("AADHAR_NO", employee.getAadharNo());
            params.addValue("CREATED_BY", employee.getCreatedBy());


            int updateCount = namedParameterJdbcTemplate.update("insert into employee_details (EMPLOYEE_ID, \n" +
                    "EMPLOYEE_TYPE, \n" +
                    "EMPLOYEE_NAME,\n" +
                    "DOB, \n" +
                    "STATUS, \n" +
                    "LICENCE_NUMBER, \n" +
                    "LICENCE_VALIDITY, \n" +
                    "LICENCE_STATE, \n" +
                    "AADHAR_NO, \n" +
                    "CREATED_BY, \n" +
                    "CREATED_ON) values(:EMPLOYEE_ID,\n" +
                    "\t\t\t\t\t:EMPLOYEE_TYPE,\n" +
                    "\t\t\t\t\t:EMPLOYEE_NAME,\n" +
                    "\t\t\t\t\t:DOB,\n" +
                    "\t\t\t\t\t:STATUS,\n" +
                    "\t\t\t\t\t:LICENCE_NUMBER,\n" +
                    "\t\t\t\t\t:LICENCE_VALIDITY,\n" +
                    "\t\t\t\t\t:LICENCE_STATE,\n" +
                    "\t\t\t\t\t:AADHAR_NO,\n" +
                    "\t\t\t\t\t:CREATED_BY,\n" +
                    "\t\t\t\t\tnow())",params);
            log.info("employee_details created  count: ()", updateCount);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Exception while creating employee_details: {}", e.getMessage());
        }
    }

    public void updateEmployee(EmployeeDto employee){
        try{

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("EMPLOYEE_ID", employee.getEmployeeId());
            params.addValue("EMPLOYEE_TYPE", employee.getEmployeeType());
            params.addValue("EMPLOYEE_NAME", employee.getEmployeeName());
            params.addValue("DOB", employee.getDob());
            params.addValue("STATUS", employee.getStatus());
            params.addValue("LICENCE_NUMBER", employee.getLicenceNumber());
            params.addValue("LICENCE_VALIDITY", employee.getLicenceValidity());
            params.addValue("LICENCE_STATE", employee.getLicenceState());
            params.addValue("AADHAR_NO", employee.getAadharNo());
            params.addValue("MODIFIED_BY", employee.getModifiedBy());

            int updateCount = namedParameterJdbcTemplate.update("update employee_details set " +
                            "EMPLOYEE_ID=:EMPLOYEE_ID, \n" +
                            "EMPLOYEE_TYPE=:EMPLOYEE_TYPE, \n" +
                            "EMPLOYEE_NAME=:EMPLOYEE_NAME, \n" +
                            "DOB=:DOB, \n" +
                            "STATUS=:STATUS, \n" +
                            "LICENCE_NUMBER=:LICENCE_NUMBER, \n" +
                            "LICENCE_VALIDITY=:LICENCE_VALIDITY, \n" +
                            "LICENCE_STATE=:LICENCE_STATE, \n" +
                            "AADHAR_NO=:AADHAR_NO, \n" +
                            "MODIFIED_BY=:MODIFIED_BY, \n" +
                            "MODIFIED_ON=now() " +
                    "WHERE EMPLOYEE_ID=:EMPLOYEE_ID" , params);

            log.info("employee_details updated Count: {}",updateCount);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteEmployee(String employeeId){
        try{
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("EMPLOYEE_ID",employeeId);
            int count = namedParameterJdbcTemplate.update(
                    "delete from employee_details where EMPLOYEE_ID=:EMPLOYEE_ID" , params);
            log.info("employee_details for: {} is deleted. Delete Count: {}",employeeId, count);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
