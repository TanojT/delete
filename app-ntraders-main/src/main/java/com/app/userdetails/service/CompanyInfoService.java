package com.app.userdetails.service;

import com.app.common.dto.CompanyInfoDto;
import com.app.userdetails.repository.CompanyInfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class CompanyInfoService {

    private final CompanyInfoRepository companyInfoRepository;

    public void createCompanyInfo(CompanyInfoDto companyInfoDto){
        companyInfoDto.setCompanyInfoId(UUID.randomUUID().toString());
        companyInfoRepository.createCompanyInfo(companyInfoDto);
    }
    public void updateCompanyInfo(CompanyInfoDto companyInfoDto){
        companyInfoRepository.updateCompanyInfo(companyInfoDto);
    }
    public CompanyInfoDto getCompanyInfo(String companyInfoId){
        return companyInfoRepository.getCompanyInfo(companyInfoId);
    }

    public List<CompanyInfoDto> getAllCompanyInfos(){
        return companyInfoRepository.getAllCompanyInfos();
    }
    public void deleteCompanyInfo(String companyInfoId){
        companyInfoRepository.deleteCompanyInfo(companyInfoId);
    }
}
