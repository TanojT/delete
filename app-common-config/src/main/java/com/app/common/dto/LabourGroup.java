package com.app.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LabourGroup implements Serializable {
    private String groupId;
    private String groupName;
    private String groupOwnerEmpId;
    private String groupStatus;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;

}
