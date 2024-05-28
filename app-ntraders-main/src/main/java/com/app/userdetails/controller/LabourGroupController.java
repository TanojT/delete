package com.app.userdetails.controller;

import com.app.common.dto.LabourGroup;
import com.app.userdetails.service.LabourGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/labour-group")
public class LabourGroupController {

    private final LabourGroupService labourGroupService;

    @PostMapping("/createLabourGroup")
    public List<LabourGroup> createLabourGroup(@RequestBody LabourGroup labourGroup){
        this.labourGroupService.createLabourGroup(labourGroup);
        return this.labourGroupService.getAllGroupsByStatus(Arrays.asList("ACTIVE","INACTIVE"));
    }

    @GetMapping("/getAllGroupsByStatus/{groupStatus}")
    public List<LabourGroup> getAllGroupsByStatus(@PathVariable String groupStatus){
        return this.labourGroupService.getAllGroupsByStatus(Arrays.asList(groupStatus));
    }

    @GetMapping("/getAllGroups")
    public List<LabourGroup> getAllGroups(){
        return this.labourGroupService.getAllGroups();
    }


}
