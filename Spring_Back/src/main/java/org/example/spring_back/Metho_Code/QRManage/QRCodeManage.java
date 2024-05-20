//package org.example.spring_back.Metho_Code.QRManage;
//
//import org.example.spring_back.DTOFILE.QR.QR_INFOMATION;
//import org.example.spring_back.Repository_Interface.QRRepo.QRRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Optional;
//
//public class QRCodeManage {
//
//    private QRRepository qrCodeRepository;
//
//    /*public QRCodeManage(QRRepository qrCodeRepository) {
//        this.qrCodeRepository = qrCodeRepository;
//    }*/
//
//    public QRCodeManage() {
//    }
//
//    public QR_INFOMATION saveQRCode(String kioskId, String userUrl) {
//        QR_INFOMATION qrCode = new QR_INFOMATION();
//        qrCode.setKioskId(kioskId);
//        qrCode.setUserUrl(userUrl);
//        return qrCodeRepository.save(qrCode);
//    }
//
//    public Optional<QR_INFOMATION> findQRCode(String kioskId, String userUrl) {
//        return qrCodeRepository.findByKioskIdAndUserUrl(kioskId, userUrl);
//    }
//
//    public void deleteQRCode(Long qrId) {
//        qrCodeRepository.deleteByQrId(qrId);
//    }
//
//
//}
