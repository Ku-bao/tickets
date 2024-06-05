package com.example.tickets.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.tickets.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "CALL GetAllUsers()", nativeQuery = true)
    List<User> getAllUsers();

    @Transactional
    @Modifying
    @Query(value = "CALL InsertUser(:username, :password, :email, :phoneNumber)", nativeQuery = true)
    void insertUser(@Param("username") String username, 
                    @Param("password") String password, 
                    @Param("email") String email, 
                    @Param("phoneNumber") String phoneNumber);

    @Transactional
    @Modifying
    @Query(value = "CALL DeleteUserByEmail(:email)", nativeQuery = true)
    void deleteByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "CALL UpdateUserPassword(:email, :newPassword)", nativeQuery = true)
    void updateUserPassword(@Param("email") String email, 
                            @Param("newPassword") String newPassword);
}
