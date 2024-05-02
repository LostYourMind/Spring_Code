package org.example.spring_back.DTOFILE.Menu;

import jakarta.persistence.*;

// Product 엔티티
@Entity
@Table(name = "product")
public class ProductEntity {

    //region 멤버 필드

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition="TEXT")
    private String image;

    @Column(nullable = false)
    private Integer price;

    //endregion

    //region 생성자

    // 기본 생성자
    public ProductEntity() {
    }

    // 모든 필드를 매개변수로 하는 생성자
    public ProductEntity(String categoryName, String name, String image, int price) {
        this.categoryName = categoryName;
        this.name = name;
        this.image = image;
        this.price = price;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    //endregion

}
