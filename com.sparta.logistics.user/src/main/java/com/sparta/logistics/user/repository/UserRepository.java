package com.sparta.logistics.user.repository;

import com.sparta.logistics.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdAndIsDeletedFalse(Long id);

    List<User> findAllByIsDeletedFalse();

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);
}
