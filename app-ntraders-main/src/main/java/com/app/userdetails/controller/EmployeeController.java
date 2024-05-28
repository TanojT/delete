package com.app.userdetails.controller;

import com.app.common.dto.EmployeeDto;
import com.app.userdetails.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/getEmployee/{employeeId}")
    public EmployeeDto getEmployee(@PathVariable String employeeId){
        return employeeService.getEmployee(employeeId);
    }

    @GetMapping("/getAllEmployees")
    public List<EmployeeDto> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public void createEmployee(@RequestBody EmployeeDto employee){
        employeeService.createEmployee(employee);
    }

    @PutMapping
    public void updateEmployee(@RequestBody EmployeeDto employee){
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping
    public void deleteEmployee(@PathVariable String employeeId){
        employeeService.deleteEmployee(employeeId);
    }
}
