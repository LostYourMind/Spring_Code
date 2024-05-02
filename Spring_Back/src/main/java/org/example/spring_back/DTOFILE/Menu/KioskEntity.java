package org.example.spring_back.DTOFILE.Menu;

import jakarta.persistence.*;

@Entity
@Table(name = "kiosk")
public class KioskEntity {

    @Id
    @Column(name = "kiosk_id")
    private String kioskId;

    // 생성자, Getter 및 Setter

    public KioskEntity() {
    }

    public KioskEntity(String kioskId) {
        this.kioskId = kioskId;
    }

    public String getKioskId() {
        return kioskId;
    }

    public void setKioskId(String kioskId) {
        this.kioskId = kioskId;
    }


}
