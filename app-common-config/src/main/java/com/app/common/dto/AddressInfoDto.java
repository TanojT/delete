package com.app.common.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AddressInfoDto {

    private String addressId;
    private String employeeId;
    private String companyId;
    private String ricemillId;
    private String addressDesc;
    private String addressType;
    private String addressLane1;
    private String addressLane2;
    private String location;
    private String district;
    private String state;
    private String pinCode;
    private String createdBy;
    private String createdOn;
    private String modifiedBy;
    private String modifiedOn;

}
