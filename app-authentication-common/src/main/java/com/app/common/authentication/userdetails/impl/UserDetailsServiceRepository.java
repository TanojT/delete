package com.app.common.authentication.userdetails.impl;

import com.app.common.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Slf4j
@Profile("local")
@Repository
public class UserDetailsServiceRepository{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserDto getUserDetails(String username) {

        try {

            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("username", username);

            return this.namedParameterJdbcTemplate.queryForObject("select user_id as userId, user_name as username, first_name as firstName, last_name as lastname, RICE_MILL_NAME as riceMillName, PASSWORD as password, EXPIRE_ON as expireOn, USER_TYPE as userType, created_by as createdBy, modified_by as modifiedBy, modified_on as modifiedOn from user_info where user_name=:username",mapSqlParameterSource, new BeanPropertyRowMapper<>(UserDto.class));


        }catch (Exception e){
            log.error("Exception in getUserDetails(): ", e);
        }

        return null;
    }
}
