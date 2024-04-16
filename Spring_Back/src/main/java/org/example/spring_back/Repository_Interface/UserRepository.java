package org.example.spring_back.Repository_Interface;

import org.example.spring_back.User.User_Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User_Data, Long> {
    // User 테이블 관련 CRUD 메서드

    @Procedure(name = "AddUser")
    void addUser(@Param("userid") String userid,
                 @Param("username") String username,
                 @Param("email") String email,
                 @Param("plain_password") String plainPassword);
}
