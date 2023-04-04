package com.example.demo.infrastructure;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u WHERE u.username = :username")
//    public User getUserByUsername(@Param("username") String username);
}