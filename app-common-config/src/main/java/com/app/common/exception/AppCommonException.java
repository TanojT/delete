/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author schigullapally
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppCommonException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Integer errCode;
	private String errMsg;

}