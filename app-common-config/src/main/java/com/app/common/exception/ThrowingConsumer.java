/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.common.exception;

import org.springframework.http.HttpStatus;

/**
 *
 * @author schigullapally
 * @param <T>
 * @param <E>
 */
@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {

    default void accept(T t) {
        try {
            acceptThrows(t);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AppCommonException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    void acceptThrows(T t) throws Exception;
}