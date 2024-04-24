package org.example.spring_back.Repository_Interface;

import jakarta.transaction.Transactional;
import org.example.spring_back.User.User_Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User_Data, String> {
    // User 테이블 관련 CRUD 메서드

    // 사용자 정의 삽입 쿼리
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user (userId, pw, name, email) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void insertUser(String userId, String password, String name, String email);


    @Procedure(name = "LoginUser")
    boolean loginUser(@Param("userid") String userid, @Param("pass_W") String password);


}
