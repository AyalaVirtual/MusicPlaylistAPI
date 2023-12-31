package com.example.miniproject.repository;

import com.example.miniproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // This is used to register the user
    boolean existsByEmailAddress(String userEmailAddress);

    // This is used to login
    User findUserByEmailAddress(String emailAddress);
}
