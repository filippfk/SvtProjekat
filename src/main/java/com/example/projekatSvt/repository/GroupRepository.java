package com.example.projekatSvt.repository;

import com.example.projekatSvt.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findFirstByName(String name);

    Group findGroupById(Long Id);
}