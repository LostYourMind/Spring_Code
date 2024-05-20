//package org.example.spring_back.Repository_Interface.QRRepo;
//
//import jakarta.transaction.Transactional;
//import org.example.spring_back.DTOFILE.QR.QR_INFOMATION;
////import org.example.spring_back.Metho_Code.QRManage.QRCodeManage;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//
//@Repository
//public interface QRRepository extends JpaRepository<QR_INFOMATION, Long> {
//
//    @Query("SELECT q FROM qr q WHERE q.kioskId = :kioskId AND q.userUrl = :userUrl")
//    Optional<QR_INFOMATION> findByKioskIdAndUserUrl(@Param("kioskId") String kioskId, @Param("userUrl") String userUrl);
//
//    @Modifying
//    @Transactional
//    @Query("DELETE FROM qr q WHERE q.qrId = :qrId")
//    void deleteByQrId(@Param("qrId") Long qrId);
//}
