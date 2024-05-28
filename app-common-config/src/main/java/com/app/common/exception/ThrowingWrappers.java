/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.common.exception;

import java.util.function.Consumer;
import java.util.function.Function;
import org.springframework.http.HttpStatus;

/**
 *
 * @author schigullapally
 */
public class ThrowingWrappers {

    public static <T> Consumer<T> throwingConsumerWrapper(
            ThrowingConsumer<T, Exception> throwingConsumer) {

        return i -> throwingConsumer.accept(i);
//{
//            try {
//                throwingConsumer.accept(i);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                throw new AppCommonException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
//            }
//        };
    }

    public static <T, R> R throwingFunctionWrapper(ThrowingFunction<T, R> throwingFunction, T t) {

        return throwingFunction.apply(t);

    }

}
