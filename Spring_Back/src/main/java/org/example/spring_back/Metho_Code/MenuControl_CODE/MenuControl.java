package org.example.spring_back.Metho_Code.MenuControl_CODE;

import org.example.spring_back.DTOFILE.Menu.Menu;
import org.example.spring_back.DTOFILE.Menu.Menu.Info;
import org.example.spring_back.DTOFILE.Menu.Menu.Category;
import org.example.spring_back.DTOFILE.Menu.Menu.Product;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Service;


@Service("MainMenuControl")
public class MenuControl {

    private final Logger logger = LogManager.getLogger(MenuControl.class);

    //메뉴 저장
    public Boolean Insert_Menu(Menu menuData) {
        // Log Menu Info details

        logger.trace("Start Insert Menu");

        int test = menuData.getKioskID();
        logger.info("-----------------------Kiosk ID: {}", test);

        logger.trace("Menu data : {} ", menuData);
        Info info = menuData.getInfo();
        if (info != null) {
            logger.info("Info Name: {}", info.getName());
            logger.info("Info Address: {}", info.getAddress());
            logger.info("Info Type: {}", info.getType());
        }

        for (Menu.Category category : menuData.getCategories()) {
            // Accessing the CategoryDetails inner class to get the categoryName
            String categoryName = category.getCategoryDetails().getCategoryName();
            logger.info("Category Name: {}", categoryName);
            for (Menu.Product product : category.getProducts()) {
                logger.info("Product Name: {}", product.getName());
                logger.info("Product Price: {}", product.getPrice());
                logger.info("Product Image Base64 Value: {}", product.getImageBase64());

                // Handle Base64 encoded image string if present
                // ... Your existing image processing logic ...
            }
        }

        // Implement your database save logic or any other business logic here
        // Example: menuRepository.save(menuData);

        return true;
    }


    //메뉴 전체 출력
    public Boolean menuGetList(String useridValue){
        //메뉴 전체 출력하는 로직 생성 필요

        return true;
    }
}