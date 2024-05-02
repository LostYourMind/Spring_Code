package org.example.spring_back.Repository_Interface.MenuRepo;


import jakarta.transaction.Transactional;
import org.example.spring_back.DTOFILE.Menu.KioskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface KioskRepository extends JpaRepository<KioskEntity, String> {
    // 커스텀 쿼리 메소드들을 정의할 수 있습니다.
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO kiosk (kioskId, userID) VALUES (?1, ?2)", nativeQuery = true)
    int insertKioskInfo(String kioskId, String userID);
}
