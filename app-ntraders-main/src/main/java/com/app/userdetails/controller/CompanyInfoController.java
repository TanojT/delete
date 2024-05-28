package com.app.userdetails.controller;

import com.app.common.dto.CompanyInfoDto;
import com.app.userdetails.service.CompanyInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/companyInfo")
public class CompanyInfoController {

    private final CompanyInfoService companyInfoService;

    @GetMapping("/getAllCompanyInfos")
    public List<CompanyInfoDto> getAllCompanyInfos() {
        return companyInfoService.getAllCompanyInfos();
    }

    @GetMapping("/getCompanyInfoByCompanyId/{companyInfoId}")
    public CompanyInfoDto getCompanyInfoByCompanyId(@PathVariable String companyInfoId) {
        return companyInfoService.getCompanyInfo(companyInfoId);
    }

    @PostMapping("/createCompanyInfo")
    public List<CompanyInfoDto> createCompanyInfo(@RequestBody CompanyInfoDto companyInfoDto) {
        companyInfoService.createCompanyInfo(companyInfoDto);
        return companyInfoService.getAllCompanyInfos();
    }

    @PutMapping("/updateCompanyInfo")
    public CompanyInfoDto updateCompanyInfo(@RequestBody CompanyInfoDto companyInfoDto) {
        companyInfoService.updateCompanyInfo(companyInfoDto);
        return companyInfoService.getCompanyInfo(companyInfoDto.getCompanyInfoId());
    }

    @PutMapping("/deleteCompanyInfo/{companyInfoId}")
    public List<CompanyInfoDto> deleteCompanyInfo(@PathVariable String companyInfoId) {
        companyInfoService.deleteCompanyInfo(companyInfoId);
        return companyInfoService.getAllCompanyInfos();
    }
}
