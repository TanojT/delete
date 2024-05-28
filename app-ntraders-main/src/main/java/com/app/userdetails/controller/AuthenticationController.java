/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.userdetails.controller;

import com.app.common.authentication.model.AuthenticationRequest;
import com.app.common.authentication.model.AuthenticationResponse;
import com.app.common.authentication.model.AuthenticationResponseWrapper;
import com.app.common.authentication.service.MyUserDetailsService;
import com.app.common.exception.AppCommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 * @author schigullapally
 */
@Profile("local")
@Slf4j
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Resource
    public AuthenticationResponseWrapper authenticationResponseWrapper;

    @GetMapping("/hello")
    public String firstPage() {
        return "Hello World";
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        AuthenticationResponse authenticationResponse = null;

        log.debug("authenticate() received.");
        log.debug("authenticationRequest: {}",authenticationRequest);
        log.debug("authenticationResponseWrapper: {}", authenticationResponseWrapper.getAuthenticationResponse());
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            authenticationResponse = authenticationResponseWrapper.getAuthenticationResponse();
            log.debug("AuthenticationResponse: {}", authenticationResponse);
        }catch (BadCredentialsException ex) {
            throw new AppCommonException(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        }catch (UsernameNotFoundException ex) {
            throw ex;
        } catch (AppCommonException ex) {
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppCommonException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unknown Exception while authenticating the username: " + authenticationRequest.getUsername() + "Ex: " + e.getMessage());
        }

        return authenticationResponse;
    }

    @GetMapping("/refreshToken")
    public AuthenticationResponse refreshToken(@RequestHeader(name = "username", required = true) String username) throws Exception {

        log.debug("refreshToken() received for: {}", username);
        AuthenticationResponse authenticationResponse = null;
        try {

            authenticationResponse = userDetailsService.getUserDetails(username);

        } catch (AppCommonException | UsernameNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppCommonException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unknown Exception while getting the userdetails for the username: " + username + "Ex: " + e.getMessage());
        }

        log.debug("refreshToken() response: {}", authenticationResponse);

        return authenticationResponse;
    }

}
