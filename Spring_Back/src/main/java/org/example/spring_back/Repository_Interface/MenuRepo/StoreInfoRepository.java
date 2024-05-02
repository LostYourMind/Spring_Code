package org.example.spring_back.Repository_Interface.MenuRepo;

import jakarta.transaction.Transactional;
import org.example.spring_back.DTOFILE.Menu.StoreInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreInfoRepository extends JpaRepository<StoreInfoEntity, Long> {
    // Custom query methods, if needed...

    // 커스텀 쿼리 메소드들을 정의할 수 있습니다.
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO store (kioskId, storeName, storeAddress, storeType) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    int insertStoreInfo(String kioskId, String storeName, String storeAddress, String storeType);
}