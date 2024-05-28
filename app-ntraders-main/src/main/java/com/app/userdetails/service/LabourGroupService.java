package com.app.userdetails.service;

import com.app.common.dto.LabourGroup;
import com.app.userdetails.repository.LabourGroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LabourGroupService {

    private final LabourGroupRepository labourGroupRepository;

    public void createLabourGroup(LabourGroup labourGroup){
        labourGroup.setGroupId(UUID.randomUUID().toString());
        labourGroup.setGroupStatus("INACTIVE");
        this.labourGroupRepository.createLabourGroup(labourGroup);
    }

    public List<LabourGroup> getAllGroupsByStatus(List<String> statuses){
        return this.labourGroupRepository.getAllLabourGroupsByStatus(statuses);
    }

    public List<LabourGroup> getAllGroups(){
        return labourGroupRepository.getAllGroups();
    }
}
