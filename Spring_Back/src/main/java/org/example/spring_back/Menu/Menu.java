package org.example.spring_back.Menu;

import org.springframework.web.multipart.MultipartFile;

public class Menu {

    //region 프로퍼티

    private String name;
    private int price;
    private MultipartFile  menuImage;

    //endregion

    //region 생성자

    // 기본 생성자
    public Menu() {
    }

    // 모든 필드를 포함하는 생성자
    public Menu(String name, int price, MultipartFile  menuImage) {
        this.name = name;
        this.price = price;
        this.menuImage = menuImage;
    }

    //endregion

    //region  Get & Set Method

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public MultipartFile getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(MultipartFile menuImage) {
        this.menuImage = menuImage;
    }

    //endregion
}
