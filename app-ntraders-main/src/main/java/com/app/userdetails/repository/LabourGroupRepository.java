package com.app.userdetails.repository;

import com.app.common.dto.Inventory;
import com.app.common.dto.LabourGroup;
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
public class LabourGroupRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public void createLabourGroup(LabourGroup labourGroup){
        try
        {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("GROUP_ID", labourGroup.getGroupId());
            params.addValue("GROUP_NAME", labourGroup.getGroupName());
            params.addValue("GROUP_OWNER_EMP_ID", labourGroup.getGroupOwnerEmpId());
            params.addValue("GROUP_STATUS", labourGroup.getGroupStatus());
            params.addValue("CREATED_BY", labourGroup.getCreatedBy());


            int updateCount = namedParameterJdbcTemplate.update("insert into LABOUR_GROUP (GROUP_ID," +
                    "GROUP_NAME," +
                    "GROUP_OWNER_EMP_ID," +
                    "GROUP_STATUS," +
                    "CREATED_BY," +
                    "CREATED_ON) values(:GROUP_ID,:GROUP_NAME,:GROUP_OWNER_EMP_ID,:GROUP_STATUS, :CREATED_BY, now())",params);
            log.info("LabourGroup created  count: {}", updateCount);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Exception while creating LabourGroup: {}", e.getMessage());
        }
    }

    public List<LabourGroup> getAllLabourGroupsByStatus(List<String> statuses){
        List<LabourGroup> labourGroups = null;
        try{
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("GROUP_STATUS", statuses);
            labourGroups = namedParameterJdbcTemplate.query(
                    "select * from LABOUR_GROUP where GROUP_STATUS in (:GROUP_STATUS)" , params,
                    BeanPropertyRowMapper.newInstance(LabourGroup.class));
            log.info("LabourGroup count: {}", labourGroups!=null ? labourGroups.size() : labourGroups);
        }catch(Exception e){
            e.printStackTrace();
        }
        return labourGroups;
    }

    public List<LabourGroup> getAllGroups(){
        List<LabourGroup> labourGroups = null;
        try{
            labourGroups = namedParameterJdbcTemplate.query(
                    "select * from LABOUR_GROUP",
                    BeanPropertyRowMapper.newInstance(LabourGroup.class));
            log.info("LabourGroup count: {}", labourGroups!=null ? labourGroups.size() : labourGroups);
        }catch(Exception e){
            e.printStackTrace();
        }
        return labourGroups;
    }


}
