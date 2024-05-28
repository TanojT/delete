package com.app.userdetails.service;

import com.app.common.dto.ContactDto;
import com.app.userdetails.repository.ContactDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactDetailsService {

    private final ContactDetailsRepository contactDetailsRepository;

    public void createContactInfo(ContactDto contactDto){
        contactDetailsRepository.createContactInfo(contactDto);
    }
    public List<ContactDto> getContactsByEmployeeId(String employeeId){
        return contactDetailsRepository.getContactsByEmployeeId(employeeId);
    }
    public List<ContactDto> getContactsByRicemillId(String ricemillId){
        return contactDetailsRepository.getContactsByRicemillId(ricemillId);
    }
    public List<ContactDto> getContactsByCompanyId(String companyId){
        return contactDetailsRepository.getContactsByCompanyId(companyId);
    }
    public void deleteContactByContactId(String contactId){
        contactDetailsRepository.deleteContactByContactId(contactId);
    }
}
