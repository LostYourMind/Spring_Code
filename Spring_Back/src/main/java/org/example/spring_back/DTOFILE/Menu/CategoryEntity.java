package org.example.spring_back.DTOFILE.Menu;

import jakarta.persistence.*;

// Category 엔티티
@Entity
@Table(name = "category")
public class CategoryEntity {

    //region 프로퍼티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String kioskId;

    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private Integer categoryCount;

    @Column(nullable = false)
    private Integer productCount;

    //endregion

    //region 맴버필드

    // 기본 생성자
    public CategoryEntity() {
    }

    // 모든 필드를 매개변수로 하는 생성자
    public CategoryEntity(String kioskId, String categoryName, Integer categoryCount, Integer productCount) {
        this.kioskId = kioskId;
        this.categoryName = categoryName;
        this.categoryCount = categoryCount;
        this.productCount = productCount;
    }

    //endregion

    //region Getter & Setter
    // Getter와 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKioskId() {
        return kioskId;
    }

    public void setKioskId(String kioskId) {
        this.kioskId = kioskId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(Integer categoryCount) {
        this.categoryCount = categoryCount;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
    //endregion
}
