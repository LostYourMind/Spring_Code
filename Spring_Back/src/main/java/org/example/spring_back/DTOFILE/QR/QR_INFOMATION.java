//package org.example.spring_back.DTOFILE.QR;
//
//import jakarta.persistence.*;
//
//@Entity(name = "qr")
//@Table(name = "qr")
//public class QR_INFOMATION {
//
//    private String kioskID;
//    private String userURL;
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "qrId")
//    private Long qrId;
//
//    @Column(name = "kioskId", nullable = false)
//    private String kioskId;
//
//    @Column(name = "userUrl", nullable = false)
//    private String userUrl;
//
//    // Getters and Setters
//
//    public Long getQrId() {
//        return qrId;
//    }
//
//    public void setQrId(Long qrId) {
//        this.qrId = qrId;
//    }
//
//    public String getKioskId() {
//        return kioskId;
//    }
//
//    public void setKioskId(String kioskId) {
//        this.kioskId = kioskId;
//    }
//
//    public String getUserUrl() {
//        return userUrl;
//    }
//
//    public void setUserUrl(String userUrl) {
//        this.userUrl = userUrl;
//    }
//}
