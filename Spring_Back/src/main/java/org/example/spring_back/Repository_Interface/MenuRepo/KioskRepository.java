package org.example.spring_back.Repository_Interface.MenuRepo;


import jakarta.transaction.Transactional;
import org.example.spring_back.DTOFILE.Menu.KioskEntity;
import org.example.spring_back.DTOFILE.Menu.KioskInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface KioskRepository extends JpaRepository<KioskEntity, String> {
    // 커스텀 쿼리 메소드들을 정의할 수 있습니다.
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO kiosk (kioskId, userID) VALUES (?1, ?2)", nativeQuery = true)
    int insertKioskInfo(String kioskId, String userID);


    // 스토어드 프로시저 호출
    @Procedure(name = "SelectAllKiosk")
    List<Object[]> SelectAllKiosk(@Param("user_id") String userId);
}
