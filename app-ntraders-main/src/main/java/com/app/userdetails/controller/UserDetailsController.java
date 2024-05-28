/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.userdetails.controller;

import com.app.common.dto.ContactDto;
import com.app.common.dto.UserDto;
import com.app.userdetails.service.UserDetailsService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author schigullapally
 */
@Slf4j
@RestController
@RequestMapping("/member")
public class UserDetailsController {

    @Autowired
    private UserDetailsService memberService;

    @GetMapping("/authentication/{username}")
    public UserDto getUserDetails(@PathVariable(name = "username", required = true) String username) {
        log.info("username details: {}", username);
        return memberService.getUserDetails(username);
    }
    
    @PostMapping("/create/member")
    public UserDto create(@RequestBody UserDto user) {
        log.info("create: {}", user);
        return memberService.create(user);
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Santhosh!!!!";
    }

}
