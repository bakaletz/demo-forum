package com.example.forum.repository;

import com.example.forum.entity.Category;
import com.example.forum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

        Optional<User> findByUsername(String username);

        boolean existsByUsername(String username);
}
