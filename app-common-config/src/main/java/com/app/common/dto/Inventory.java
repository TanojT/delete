package com.app.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Inventory implements Serializable {
    private String vehicleNumber;
    private String model;
    private String manufacturer;
    private String type;
    private String dateOfRegistration;
    private String rcValidity;
    private String permitType;
    private String permitStatus;
    private String createdBy;
    private String createdOn;
    private String modifiedBy;
    private String modifiedOn;
}
