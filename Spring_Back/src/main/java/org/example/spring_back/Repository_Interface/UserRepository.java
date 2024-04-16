package org.example.spring_back.Repository_Interface;

import org.example.spring_back.User.User_Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User_Data, Long> {
    // User 테이블 관련 CRUD 메서드
}
