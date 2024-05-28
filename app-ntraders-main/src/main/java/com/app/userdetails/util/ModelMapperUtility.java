/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.userdetails.util;

import com.app.common.authentication.model.AuthenticationResponse;
import com.app.common.dto.ContactDto;
import com.app.common.dto.UserDto;
import com.app.userdetails.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author schigullapally
 */
@Component
public class ModelMapperUtility {

    private ModelMapper mapper = new ModelMapper();

    public UserDto convert(User userEntity) {
        return mapper.map(userEntity, UserDto.class);
    }

    public User convert(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

    public AuthenticationResponse convertToAuthentication(User userEntity) {
        return mapper.map(userEntity, AuthenticationResponse.class);
    }

}
