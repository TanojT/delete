/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.userdetails.repository;

import com.app.userdetails.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.data.rest.core.annotation.RestResource;

/**
 *
 * @author schigullapally
 */
//@RepositoryRestResource(collectionResourceRel = "users", path = "users")
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String>{
    
    public User findByUsernameAndPassword(@Param("username") String userName, @Param("password") String password);
    
    public User findByUsername(@Param("username") String username);
    
}
