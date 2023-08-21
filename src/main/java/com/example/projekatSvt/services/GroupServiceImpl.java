package com.example.projekatSvt.services;

import com.example.projekatSvt.dto.GroupDto;
import com.example.projekatSvt.entity.Group;
import com.example.projekatSvt.repository.GroupRepository;
import com.example.projekatSvt.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Override
    public Group findGroupById(Long Id) {
        return this.groupRepository.findGroupById(Id);

    }

    @Override
    public Group findGroupByName(String name) {
        Optional<Group> group = groupRepository.findFirstByName(name);
        return group.orElse(null);
    }

    @Override
    public Group createGroup(GroupDto GroupDto) {
        Optional<Group> group = groupRepository.findFirstByName(GroupDto.getName());

        if(group.isPresent()){
            return null;
        }
        Group newGroup = new Group();
        newGroup.setName(GroupDto.getName());
        newGroup.setDescription(GroupDto.getDescription());
        newGroup.setCreationDate(LocalDateTime.now());
        newGroup.setSuspended(false);
        newGroup = groupRepository.save(newGroup);

        return  newGroup;
    }

    @Override
    public List<Group> findAll() {
        return this.groupRepository.findAll();
    }

    @Override
    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Long Id) {
        Group grupa =  this.groupRepository.findGroupById(Id);
        grupa.setDeleted(true);
        this.groupRepository.save(grupa);
    }

}