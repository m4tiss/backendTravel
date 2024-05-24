package com.example.backendTravel.api.repository;

import com.example.backendTravel.api.model.Role;
import com.example.backendTravel.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role")
    Long countUsersByRole(@Param("role") Role role);

}