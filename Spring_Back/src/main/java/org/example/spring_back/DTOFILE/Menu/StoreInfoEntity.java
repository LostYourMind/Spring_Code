package org.example.spring_back.DTOFILE.Menu;

import jakarta.persistence.*;

@Entity
@Table(name = "store")
public class StoreInfoEntity {

    //region 맴버 필드

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String type;

    //endregion


    //region 생성자

    // 기본 생성자
    public StoreInfoEntity() {
    }

    // 모든 필드를 매개변수로 하는 생성자
    public StoreInfoEntity(String name, String address, String type) {
        this.name = name;
        this.address = address;
        this.type = type;
    }

    //endregion


    //region Getter & Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //endregion


}
