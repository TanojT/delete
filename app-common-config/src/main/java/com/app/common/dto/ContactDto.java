/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author schigullapally
 */
@Data
public class ContactDto implements Serializable {

    private String contactId;
    private String employeeId;
    private String companyId;
    private String ricemillId;
    private String type;
    private String mobileNo;
    private String emailId;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;

}
