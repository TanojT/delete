package com.app.userdetails.repository;

import com.app.common.dto.CompanyInfoDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Slf4j
@Repository
@AllArgsConstructor
public class CompanyInfoRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createCompanyInfo(CompanyInfoDto companyInfoDto){
        try{

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("COMPANY_INFO_ID",companyInfoDto.getCompanyInfoId());
            params.addValue("COMPANY_NAME",companyInfoDto.getCompanyName());
            params.addValue("COMPANY_DESC",companyInfoDto.getCompanyDesc());
            params.addValue("SUPP_VENDOR_CODE",companyInfoDto.getSuppVendorCode());
            params.addValue("WEB_URL",companyInfoDto.getWebUrl());
            params.addValue("COMP_TYPE",companyInfoDto.getCompType());
            params.addValue("CREATED_BY",companyInfoDto.getCreatedBy());
            params.addValue("CREATED_ON",companyInfoDto.getCreatedOn());
            params.addValue("MODIFIED_BY",companyInfoDto.getModifiedBy());
            params.addValue("MODIFIED_ON",companyInfoDto.getModifiedOn());


            int updateCount = namedParameterJdbcTemplate.update("insert into company_info values(:COMPANY_INFO_ID," +
                            ":COMPANY_NAME," +
                            ":COMPANY_DESC," +
                            ":SUPP_VENDOR_CODE," +
                            ":WEB_URL," +
                            ":COMP_TYPE," +
                            ":CREATED_BY," +
                            ":CREATED_ON," +
                            ":MODIFIED_BY," +
                            ":MODIFIED_ON)", params);

            log.info("company_info created Count: {}",updateCount);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateCompanyInfo(CompanyInfoDto companyInfoDto){
        try{

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("COMPANY_INFO_ID",companyInfoDto.getCompanyInfoId());
            params.addValue("COMPANY_NAME",companyInfoDto.getCompanyName());
            params.addValue("COMPANY_DESC",companyInfoDto.getCompanyDesc());
            params.addValue("SUPP_VENDOR_CODE",companyInfoDto.getSuppVendorCode());
            params.addValue("WEB_URL",companyInfoDto.getWebUrl());
            params.addValue("COMP_TYPE",companyInfoDto.getCompType());
            params.addValue("MODIFIED_BY",companyInfoDto.getModifiedBy());
            params.addValue("MODIFIED_ON",companyInfoDto.getModifiedOn());

            int updateCount = namedParameterJdbcTemplate.update("update company_info set " +
                    "COMPANY_NAME=:COMPANY_NAME," +
                    "COMPANY_DESC=:COMPANY_DESC," +
                    "SUPP_VENDOR_CODE=:SUPP_VENDOR_CODE," +
                    "WEB_URL=:WEB_URL," +
                    "COMP_TYPE=:COMP_TYPE," +
                    "MODIFIED_BY=:MODIFIED_BY," +
                    "MODIFIED_ON=:MODIFIED_ON " +
                    "WHERE COMPANY_INFO_ID=:COMPANY_INFO_ID" , params);

            log.info("company_info updated Count: {}",updateCount);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public CompanyInfoDto getCompanyInfo(String companyInfoId){
        CompanyInfoDto companyInfoDto = null;
        try{
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("COMPANY_INFO_ID",companyInfoId);
            companyInfoDto = namedParameterJdbcTemplate.queryForObject(
                    "select * from company_info where COMPANY_INFO_ID=:COMPANY_INFO_ID" , params,
                    BeanPropertyRowMapper.newInstance(CompanyInfoDto.class));
            log.info("company_info for: {} is : {}",companyInfoId, companyInfoDto);
        }catch(Exception e){
            e.printStackTrace();
        }
        return companyInfoDto;
    }

    public List<CompanyInfoDto> getAllCompanyInfos(){
        List<CompanyInfoDto> companyInfoDtos = null;
        try{
            companyInfoDtos = namedParameterJdbcTemplate.query("select * from company_info" ,
                    BeanPropertyRowMapper.newInstance(CompanyInfoDto.class));
            log.info("company_info count: {}", companyInfoDtos!=null ? companyInfoDtos.size() : companyInfoDtos);
        }catch(Exception e){
            e.printStackTrace();
        }
        return companyInfoDtos;
    }

    public void deleteCompanyInfo(String companyInfoId){
        try{
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("COMPANY_INFO_ID",companyInfoId);
            int count = namedParameterJdbcTemplate.update(
                    "delete from company_info where COMPANY_INFO_ID=:COMPANY_INFO_ID" , params);
            log.info("company_info for: {} is deleted. Delete Count: {}",companyInfoId, count);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
