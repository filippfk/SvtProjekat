package com.example.projekatSvt.services;

import com.example.projekatSvt.dto.GroupDto;
import com.example.projekatSvt.entity.Group;

import java.util.List;

public interface GroupService {

    Group findGroupById(Long id);
    Group findGroupByName(String name);

    Group createGroup(GroupDto groupDto);

    List<Group> findAll();

    void saveGroup(Group group);

    void deleteGroup(Long id);

}
