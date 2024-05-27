package org.example.spring_back.Repository_Interface.UserRepo;

import jakarta.transaction.Transactional;
import org.example.spring_back.DTOFILE.User.User_Data;
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
    @Query(value = "INSERT INTO user (userId, pw, name, email,user_id) VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    int insertUser(String userId, String password, String name, String email, String user_id);

    @Procedure(name = "LoginUser")
    boolean loginUser(@Param("userid") String userid, @Param("pass_W") String password);

    @Procedure(name = "FindUserID")
    void findUserID(@Param("input_Email") String input_Email);

    @Procedure(name = "FindUserPW")
    void findUserPW(@Param("inputuser_ID") String inputuser_ID);


}
