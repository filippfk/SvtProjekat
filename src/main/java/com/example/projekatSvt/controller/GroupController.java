package com.example.projekatSvt.controller;

import com.example.projekatSvt.dto.GroupDto;
import com.example.projekatSvt.dto.GroupRequestDto;
import com.example.projekatSvt.entity.Group;
import com.example.projekatSvt.entity.GroupRequest;
import com.example.projekatSvt.entity.User;
import com.example.projekatSvt.services.GroupService;
import com.example.projekatSvt.services.PostService;
import com.example.projekatSvt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/groups")
@CrossOrigin(origins = "http://localhost:4200")
public class GroupController {

    @Autowired
    UserService userService;
    @Autowired
    GroupService groupService;

    PostService postService;

    @Autowired
    public GroupController(GroupService groupService, PostService postService) {
        this.groupService = groupService;
        this.postService = postService;
    }

    @PostMapping("/createGroup")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GroupDto> create(@RequestBody @Validated GroupDto newGroup){

        Group createdGroup = groupService.createGroup(newGroup);

        if(createdGroup == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        GroupDto groupDto = new GroupDto(createdGroup);
        return new ResponseEntity<>(groupDto, HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GroupDto> edit(@RequestBody @Validated GroupDto editGroup){
        Group edit = groupService.findGroupById(editGroup.getId());
        edit.setDescription(editGroup.getDescription());
        edit.setName(editGroup.getName());
        groupService.saveGroup(edit);

        GroupDto groupDto = new GroupDto(edit);
        return  new ResponseEntity<>(groupDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public HttpStatus deleteGroup(Principal user, @RequestBody @Validated Long id) {
        User groupAdmin = this.userService.findUserByUsername(user.getName());

        if( this.groupService.findGroupById(id).getGroupAdmin() == groupAdmin.getId())
        {
            this.groupService.deleteGroup(id);
            return HttpStatus.ACCEPTED;
        }
        else return HttpStatus.NOT_ACCEPTABLE;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Group saveGroup(Principal user, @RequestBody @Validated GroupRequestDto dto) {
        User groupAdmin = this.userService.findUserByUsername(user.getName());

        if( this.groupService.findGroupById(dto.getId()).getGroupAdmin() == groupAdmin.getId())
        {
            Group newGroup =  groupService.findGroupById(dto.getId());
            newGroup.setName(dto.getName());
            newGroup.setDescription(dto.getDescription());
            groupService.saveGroup(newGroup);

        }
        else return null;
        return null;
    }

    @GetMapping("/all")
    public List<Group> loadAll(){return this.groupService.findAll();}
}
