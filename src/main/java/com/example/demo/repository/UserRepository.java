package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

//public interface UserRepository extends JpaRepository<User, Long>{
//	Optional<User> findByEmail(String email);
//}
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String resetToken);
}
