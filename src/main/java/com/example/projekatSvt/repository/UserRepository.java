package com.example.projekatSvt.repository;

import com.example.projekatSvt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByUsername(String username);

    User findUserById(Long id);
}

