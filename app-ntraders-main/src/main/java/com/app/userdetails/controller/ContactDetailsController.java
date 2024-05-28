package com.app.userdetails.controller;

import com.app.common.dto.ContactDto;
import com.app.userdetails.service.ContactDetailsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/contact")
public class ContactDetailsController {

    private final ContactDetailsService contactDetailsService;

    @PostMapping("/createEmployeeContactInfo")
    public List<ContactDto> createEmployeeContactInfo(@RequestBody ContactDto contactDto){
        contactDetailsService.createContactInfo(contactDto);
        return contactDetailsService.getContactsByEmployeeId(contactDto.getEmployeeId());
    }

    @PostMapping("/createCompanyContactInfo")
    public List<ContactDto> createCompanyContactInfo(@RequestBody ContactDto contactDto){
        contactDetailsService.createContactInfo(contactDto);
        return contactDetailsService.getContactsByCompanyId(contactDto.getCompanyId());
    }

    @PostMapping("/createRicemillContactInfo")
    public List<ContactDto> createRicemillContactInfo(@RequestBody ContactDto contactDto){
        contactDetailsService.createContactInfo(contactDto);
        return contactDetailsService.getContactsByRicemillId(contactDto.getRicemillId());
    }

    @GetMapping("/getContactsByEmployeeId/{employeeId}")
    public List<ContactDto> getContactsByEmployeeId(@PathVariable String employeeId){
        return contactDetailsService.getContactsByEmployeeId(employeeId);
    }

    @GetMapping("/getContactsByRicemillId/{ricemillId}")
    public List<ContactDto> getContactsByRicemillId(@PathVariable String ricemillId){
        return contactDetailsService.getContactsByRicemillId(ricemillId);
    }

    @GetMapping("/getContactsByCompanyId/{companyId}")
    public List<ContactDto> getContactsByCompanyId(@PathVariable String companyId){
        return contactDetailsService.getContactsByCompanyId(companyId);
    }

    @GetMapping("/deleteEmployeeContact/{contactId}/{employeeId}")
    public List<ContactDto> deleteEmployeeContact(@PathVariable String contactId, @PathVariable String employeeId){
        contactDetailsService.deleteContactByContactId(contactId);
        return contactDetailsService.getContactsByEmployeeId(employeeId);
    }

    @GetMapping("/deleteRicemillContact/{contactId}/{ricemillId}")
    public List<ContactDto> deleteRicemillContact(@PathVariable String contactId, @PathVariable String ricemillId){
        contactDetailsService.deleteContactByContactId(contactId);
        return contactDetailsService.getContactsByRicemillId(ricemillId);
    }

    @GetMapping("/deleteCompanyContact/{contactId}/{companyId}")
    public List<ContactDto> deleteCompanyContact(@PathVariable String contactId, @PathVariable String companyId){
        contactDetailsService.deleteContactByContactId(contactId);
        return contactDetailsService.getContactsByCompanyId(companyId);
    }
}
