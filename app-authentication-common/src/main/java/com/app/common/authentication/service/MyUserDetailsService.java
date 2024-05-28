package com.app.common.authentication.service;

import com.app.common.authentication.userdetails.impl.UserDetailsServiceRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.common.authentication.model.AuthenticationResponse;
import com.app.common.authentication.model.AuthenticationResponseWrapper;
import com.app.common.authentication.util.JwtUtil;
import com.app.common.dto.UserDto;
import com.app.common.exception.AppCommonException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceRepository userDetailsServiceRepository;
    
    @Resource
    public AuthenticationResponseWrapper authenticationResponseWrapper;
            
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, AppCommonException {
        UserDetails userDetails = null;
        try {
            UserDto userResponse = userDetailsServiceRepository.getUserDetails(username);

            log.debug("userResponse: {}", userResponse);

            if (userResponse == null) {
                throw new UsernameNotFoundException(username + " Not found.");
            }
            
                List<GrantedAuthority> authorities = userResponse.getRoles()!=null ? userResponse.getRoles().stream().map(authorityStr -> {
                        GrantedAuthority authority = new SimpleGrantedAuthority(authorityStr);
                        return authority;
                    }).collect(Collectors.toList()) : new ArrayList<>();

            userDetails = User.builder()
                    .username(userResponse.getUsername())
                    .password(userResponse.getPassword())  
                    .authorities(authorities)
                    .accountExpired(userResponse.getExpireOn().compareTo(new Date()) < 0)
                    .accountLocked(userResponse.getExpireOn().compareTo(new Date()) < 0)
                    .credentialsExpired(userResponse.getExpireOn().compareTo(new Date()) < 0)
                    .disabled(userResponse.getExpireOn().compareTo(new Date()) < 0)
                    .build();
                
          
             AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setUsername(userResponse.getUsername());
            authenticationResponse.setFirstName(userResponse.getFirstName());
            authenticationResponse.setLastName(userResponse.getLastName());
            authenticationResponse.setRiceMillName(userResponse.getRiceMillName());
            authenticationResponse.setUserType(userResponse.getUserType());
            authenticationResponse.setRoles(userResponse.getRoles());
            authenticationResponse.setJwt(this.jwtTokenUtil.generateToken(authorities,userResponse.getUsername()));
            authenticationResponseWrapper.setAuthenticationResponse(authenticationResponse);

        }catch (UsernameNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppCommonException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exception while fetching loadUserByUsername for the username: " + username + "Ex: " + e.getMessage());
        }

        return userDetails;
    }
    
    public AuthenticationResponse getUserDetails(String username) throws UsernameNotFoundException, AppCommonException {
        AuthenticationResponse authResponse = null;
        try {
            UserDto userResponse = userDetailsServiceRepository.getUserDetails(username);

            log.debug("getUserDetails() userResponse: {}", userResponse);

            if (userResponse == null) {
                throw new UsernameNotFoundException(username + " Not found.");
            }

            List<GrantedAuthority> authorities = userResponse.getRoles()!=null ? userResponse.getRoles().stream().map(authorityStr -> {
                GrantedAuthority authority = new SimpleGrantedAuthority(authorityStr);
                return authority;
            }).collect(Collectors.toList()) : new ArrayList<>();

            authResponse = new AuthenticationResponse();
            authResponse.setUsername(userResponse.getUsername());
            authResponse.setFirstName(userResponse.getFirstName());
            authResponse.setLastName(userResponse.getLastName());
            authResponse.setRiceMillName(userResponse.getRiceMillName());
            authResponse.setUserType(userResponse.getUserType());
            authResponse.setRoles(userResponse.getRoles());
            authResponse.setJwt(this.jwtTokenUtil.generateToken(authorities,userResponse.getUsername()));
            

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppCommonException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exception while fetching getUserDetails for the username: " + username + "Ex: " + e.getMessage());
        }

        return authResponse;
    }

}
