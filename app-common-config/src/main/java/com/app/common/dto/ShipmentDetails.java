package com.app.common.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ShipmentDetails {
    private String shipmentNum;
    private String vehicleNo;
    private String driverName;
    private Date shipmentDate;
    private String shipmentDateDb;
    private String ricemillName;
    private String ricemillArea;
    private Double purchasePricePerTon;
    private Double loadedGrossWeight;
    private Double loadedEmptyWeight;
    private Double loadedNetWeight;
    private Double purchaseTotalCost;
    private Date unloadedDate;
    private String unloadedDateDb;
    private String deliverToCompany;
    private String deliverToLocation;
    private String deliverToAddress;
    private String deliveryChallanNum;
    private Double unloadPricePerTon;
    private Double unloadGrossWeight;
    private Double unloadEmptyWeight;
    private Double unloadNetWeight;
    private Double moistureDeductionPercentage;
    private Double unloadTotalCost;
    private String tractorNum;
    private String labourGroupName;
    private Double labourRatePerTon;
}
