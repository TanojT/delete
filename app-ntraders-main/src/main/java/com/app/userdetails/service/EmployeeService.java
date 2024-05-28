package com.app.userdetails.service;

import com.app.common.dto.EmployeeDto;
import com.app.userdetails.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDto getEmployee(String employeeId){
        return employeeRepository.getEmployee(employeeId);
    }

    public List<EmployeeDto> getAllEmployees(){
        return employeeRepository.getAllEmployees();
    }

    public void createEmployee(EmployeeDto employee){
        employeeRepository.createEmployee(employee);
    }

    public void updateEmployee(EmployeeDto employee){
        employeeRepository.updateEmployee(employee);
    }

    public void deleteEmployee(String employeeId){
        employeeRepository.deleteEmployee(employeeId);
    }
}
