package org.example.spring_back.Metho_Code.MenuControl_CODE;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.spring_back.DTOFILE.Menu.*;
import org.example.spring_back.Repository_Interface.MenuRepo.StoreInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.example.spring_back.DTOFILE.Menu.Menu.Info;
import org.example.spring_back.Repository_Interface.MenuRepo.CategoryRepository;
import org.example.spring_back.Repository_Interface.MenuRepo.KioskRepository;
import org.example.spring_back.Repository_Interface.MenuRepo.ProductRepository;








@Service("MainMenuControl")
public class MenuControl {

    private final KioskRepository kioskRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final StoreInfoRepository storeRepository;

    private final Logger logger = LogManager.getLogger(MenuControl.class);

    @Autowired
    public MenuControl(KioskRepository kioskRepository, CategoryRepository categoryRepository, ProductRepository productRepository, StoreInfoRepository storeRepository) {
        this.kioskRepository = kioskRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    // 'insertMenu' 메소드는 데이터베이스에 메뉴 데이터를 삽입하는 역할을 합니다.
    // @Transactional 어노테이션은 이 메소드를 하나의 데이터베이스 트랜잭션으로 처리하도록 지정합니다.
    // 트랜잭션이 성공적으로 완료되면 커밋되고, 예외가 발생하면 롤백됩니다.
    @Transactional
    public Boolean insertMenu(Menu menuData) {
        logger.trace("Start Insert Menu");

        // Extract KioskID from the DTO and log it
        String kioskId = menuData.getKioskID().toString();
        logger.info("-----------------------Kiosk ID: {}", kioskId);

        // Log and save the Info from the DTO into the Store entity
        Info info = menuData.getInfo();
        StoreInfoEntity storeEntity = null;
        if (info != null) {
            logger.info("Info Name: {}", info.getName());
            logger.info("Info Address: {}", info.getAddress());
            logger.info("Info Type: {}", info.getType());

            storeEntity = new StoreInfoEntity();
            storeEntity.setName(info.getName());
            storeEntity.setAddress(info.getAddress());
            storeEntity.setType(info.getType());
            storeRepository.save(storeEntity);
        }

        // Create and save Kiosk entity
        KioskEntity kioskEntity = new KioskEntity();
        kioskEntity.setKioskId(kioskId);
        if (storeEntity != null) {
            kioskEntity.setStore(storeEntity);
        }
        kioskRepository.save(kioskEntity);

        // Process each Category in the DTO
        for (Menu.Category categoryDto : menuData.getCategories()) {
            String categoryName = categoryDto.getCategoryDetails().getCategoryName();
            logger.info("Category Name: {}", categoryName);

            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setKioskId(kioskId);
            categoryEntity.setCategoryName(categoryName);
            categoryRepository.save(categoryEntity);

            // Process each Product in the Category
            for (Menu.Product productDto : categoryDto.getProducts()) {
                logger.info("Product Name: {}", productDto.getName());
                logger.info("Product Price: {}", productDto.getPrice());
                logger.info("Product Image Base64 Value: {}", productDto.getImageBase64());

                ProductEntity productEntity = new ProductEntity();
                productEntity.setCategoryName(categoryName);
                productEntity.setName(productDto.getName());
                productEntity.setPrice(productDto.getPrice().toString());
                productEntity.setImage(productDto.getImageBase64());
                productRepository.save(productEntity);
            }
        }

        return true;
    }


    //메뉴 전체 출력
    public Boolean menuGetList(String useridValue){
        //메뉴 전체 출력하는 로직 생성 필요

        return true;
    }
}