package org.example.spring_back.DTOFILE.Menu;

import jakarta.persistence.*;


@Entity
@Table(name = "kiosk")
public class KioskEntity {

    @Id
    @Column(name = "kiosk_id")
    private String kioskId;

    // OneToOne 관계 설정: 각 Kiosk는 하나의 Store에만 속합니다.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id") // FK 컬럼 이름 지정
    private StoreInfoEntity store;

    // 생성자, Getter 및 Setter

    public KioskEntity() {
    }

    public KioskEntity(String kioskId, StoreInfoEntity store) {
        this.kioskId = kioskId;
        this.store = store;
    }

    public String getKioskId() {
        return kioskId;
    }

    public void setKioskId(String kioskId) {
        this.kioskId = kioskId;
    }

    public StoreInfoEntity getStore() {
        return store;
    }

    public void setStore(StoreInfoEntity store) {
        this.store = store;
    }
}
