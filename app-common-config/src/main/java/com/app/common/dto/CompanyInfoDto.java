package com.app.common.dto;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyInfoDto {
    private String companyInfoId;
    private String companyName;
    private String companyDesc;
    private String suppVendorCode;
    private String webUrl;
    private String compType;
    private String createdBy;
    private String createdOn;
    private String modifiedBy;
    private String modifiedOn;
}
