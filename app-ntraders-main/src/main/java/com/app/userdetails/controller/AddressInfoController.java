package com.app.userdetails.controller;

import com.app.common.dto.AddressInfoDto;
import com.app.userdetails.service.AddressInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/addressInfo")
public class AddressInfoController
{
    private final AddressInfoService addressInfoService;

    @GetMapping("/getAllAddresses")
    public List<AddressInfoDto> getAllAddresses()
    {
        return addressInfoService.getAllAddresses();
    }

    @GetMapping("/getAddressInfoByEmployeeId/{employeeId}")
    public List<AddressInfoDto> getAddressInfoByEmployeeId(@PathVariable String employeeId){
        return addressInfoService.getAddressInfoByEmployeeId(employeeId);
    }

    @GetMapping("/getAddressInfoCompanyId/{companyId}")
    public List<AddressInfoDto> getAddressInfoCompanyId(@PathVariable String companyId){
        return addressInfoService.getAddressInfoCompanyId(companyId);
    }

    @GetMapping("/getAddressInfoByRiceMillId/{riceMillId}")
    public List<AddressInfoDto> getAddressInfoByRiceMillId(@PathVariable String riceMillId){
        return addressInfoService.getAddressInfoByRiceMillId(riceMillId);
    }


    @PostMapping("/createEmployeeAddress")
    public List<AddressInfoDto> createEmployeeAddress(@RequestBody AddressInfoDto addressInfoDto )
    {
        addressInfoService.createAddressInfo(addressInfoDto);
        return addressInfoService.getAddressInfoByEmployeeId(addressInfoDto.getEmployeeId());
    }

    @PostMapping("/createCompanyAddress")
    public List<AddressInfoDto> createCompanyAddress(@RequestBody AddressInfoDto addressInfoDto )
    {
        addressInfoService.createAddressInfo(addressInfoDto);
        return addressInfoService.getAddressInfoCompanyId(addressInfoDto.getCompanyId());
    }

    @PostMapping("/createRiceMillAddress")
    public List<AddressInfoDto> createRiceMillAddress(@RequestBody AddressInfoDto addressInfoDto )
    {
        addressInfoService.createAddressInfo(addressInfoDto);
        return addressInfoService.getAddressInfoByRiceMillId(addressInfoDto.getCompanyId());
    }

    @DeleteMapping("/deleteRiceMillAddress/{riceMillId}/{addressId}")
    public List<AddressInfoDto> deleteRiceMillAddress(@PathVariable String riceMillId, @PathVariable String addressId)
    {
        addressInfoService.deleteAddressInfoByAddressId(addressId);
        return addressInfoService.getAddressInfoByRiceMillId(riceMillId);

    }

    @DeleteMapping("/deleteCompanyAddress/{companyId}/{addressId}")
    public List<AddressInfoDto> deleteCompanyAddress(@PathVariable String companyId, @PathVariable String addressId)
    {
        addressInfoService.deleteAddressInfoByAddressId(addressId);
        return addressInfoService.getAddressInfoCompanyId(companyId);
    }

    @DeleteMapping("/deleteEmployeeAddress/{employeeId}/{addressId}")
    public List<AddressInfoDto> deleteEmployeeAddress(@PathVariable String employeeId, @PathVariable String addressId)
    {
        addressInfoService.deleteAddressInfoByAddressId(addressId);
        return addressInfoService.getAddressInfoByEmployeeId(employeeId);
    }


}
