/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.userdetails.service;

import com.app.common.authentication.model.AuthenticationResponse;
import com.app.common.dto.ContactDto;
import com.app.common.dto.UserDto;
import com.app.common.exception.ThrowingWrappers;
import com.app.userdetails.repository.UserRepository;
import com.app.userdetails.util.ModelMapperUtility;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author schigullapally
 */
@Service
public class UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapperUtility mapperUtility;

    public UserDto getUserDetails(String username) {
        UserDto userDto = mapperUtility.convert(userRepository.findByUsername(username));
        userDto.setRoles(new ArrayList<>());
        return userDto;
    }

    public AuthenticationResponse getAuthenticationDetails(String username) {
        AuthenticationResponse authenticationResponse = mapperUtility.convertToAuthentication(userRepository.findByUsername(username));
        authenticationResponse.setRoles(new ArrayList<>());
        return authenticationResponse;
    }

    public UserDto create(UserDto user) {
        if (user.getPassword() != null && !user.getPassword().equals("")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        user = ThrowingWrappers.throwingFunctionWrapper(userDto -> mapperUtility.convert(userRepository.save(mapperUtility.convert(userDto))), user);
        
        return mapperUtility.convert(userRepository.save(mapperUtility.convert(user)));
    }

}
