package com.app.userdetails.service;

import com.app.common.dto.AddressInfoDto;
import com.app.userdetails.repository.AddressInfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class AddressInfoService
{
    private final AddressInfoRepository addressInfoRepository;

    public void createAddressInfo(AddressInfoDto addressInfoDto)
    {
        addressInfoRepository.createAddressInfo(addressInfoDto);
    }

    public List<AddressInfoDto> getAddressInfoByEmployeeId(String employeeId){
        return addressInfoRepository.getAddressInfoByEmployeeId(employeeId);
    }

    public List<AddressInfoDto> getAddressInfoCompanyId(String companyId){
        return addressInfoRepository.getAddressInfoCompanyId(companyId);
    }

    public List<AddressInfoDto> getAddressInfoByRiceMillId(String riceMillId){
        return addressInfoRepository.getAddressInfoByRiceMillId(riceMillId);
    }
    public List<AddressInfoDto> getAllAddresses()
    {
        return addressInfoRepository.getAllAddresses();
    }
    public void deleteRiceMillAddress(String riceMillId)
    {
        addressInfoRepository.deleteRiceMillAddressInfo(riceMillId);
    }

    public void deleteCompanyAddress(String companyId)
    {
        addressInfoRepository.deleteCompanyAddressInfo(companyId);
    }

    public void deleteEmployeeAddress(String employeeId)
    {
        addressInfoRepository.deleteEmployeeAddressInfo(employeeId);
    }

    public void deleteAddressInfoByAddressId(String addressId){
        addressInfoRepository.deleteAddressInfoByAddressId(addressId);
    }

}
