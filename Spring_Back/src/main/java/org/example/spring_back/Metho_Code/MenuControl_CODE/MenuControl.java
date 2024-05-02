package org.example.spring_back.Metho_Code.MenuControl_CODE;

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

    //region 맴버필드 & 생성자

    private final Logger logger = LogManager.getLogger(MenuControl.class);
    DB_Model db_Model;

    public MenuControl(DB_Model insert_db){
        this.db_Model = insert_db;
    }

    //endregion


    //메뉴 저장
    public Boolean insertMenu(Menu menuData){
        try{
            String kioskid = db_Model.insertKioskIDandInfo(menuData);

            if(kioskid != null){
                Boolean result = db_Model.insertCateAndProduct(kioskid, menuData);
                if(result){
                    return true;
                }
                else throw new Exception("DB 저장 오류(Category + Product");
            }
        }
        catch(Exception e){
            logger.error(e);
        }
        return false;
    }

    //메뉴 삭제
    public Boolean deleteMenu(String cName, String pName){
        return db_Model.deleteProduct(cName, pName);
    }

    //메뉴 전체 출력
    public Boolean menuGetList(String useridValue){
        //메뉴 전체 출력하는 로직 생성 필요

        return true;
    }

}

@Service
class DB_Model {

    //region 맴버필드 & 생성자

    private final KioskRepository kioskRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final StoreInfoRepository storeRepository;

    private final Logger logger = LogManager.getLogger(MenuControl.class);

    @Autowired
    public DB_Model(KioskRepository kioskRepository, CategoryRepository categoryRepository, ProductRepository productRepository, StoreInfoRepository storeRepository) {
        this.kioskRepository = kioskRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;

    }

    //endregion

    public String insertKioskIDandInfo(Menu menuData){
        logger.trace("Start Insert Menu");

        // Extract KioskID from the DTO and log it
        String kioskId = menuData.getKioskID();
        logger.info("Kiosk ID: {}", kioskId);

        Info info = menuData.getInfo();
        StoreInfoEntity storeEntity;
        storeEntity = new StoreInfoEntity();
        if (info != null) {
            logger.info("Info Name: {}", info.getName());
            logger.info("Info Address: {}", info.getAddress());
            logger.info("Info Type: {}", info.getType());

            storeEntity.setName(info.getName());
            storeEntity.setAddress(info.getAddress());
            storeEntity.setType(info.getType());
        }

        try{
            int resultKiosk = kioskRepository.insertKioskInfo(kioskId,"2222");
            int resultStore = storeRepository.insertStoreInfo(kioskId, storeEntity.getName(), storeEntity.getAddress(), storeEntity.getType());
            if(resultKiosk > 0 || resultStore > 0){
                return kioskId;
            }
            throw new Exception();
        }
        catch(Exception e){
            logger.error(e);
            return null;
        }

    }

    public Boolean insertCateAndProduct(String kioskId, Menu menuData){

        int resultProduct = 0, resultCategory;

        try{
            for (Menu.Category categoryDto : menuData.getCategories()) {
                String categoryName = categoryDto.getCategoryDetails().getCategoryName();
                logger.info("Category Name: {}", categoryName);

                CategoryEntity categoryEntity = new CategoryEntity();
                categoryEntity.setKioskId(kioskId);
                categoryEntity.setCategoryName(categoryName);
                resultCategory = categoryRepository.insertCategoryInfo(kioskId,categoryName);

                // Process each Product in the Category
                for (Menu.Product productDto : categoryDto.getProducts()) {
                    logger.info("Product Name: {}", productDto.getName());
                    logger.info("Product Price: {}", productDto.getPrice());
                    logger.info("Product Image Base64 Value: {}", productDto.getImageBase64());

                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setCategoryName(categoryName);
                    productEntity.setName(productDto.getName());
                    productEntity.setPrice(productDto.getPrice());
                    productEntity.setImage(productDto.getImageBase64());

                    resultProduct = productRepository.insertCategoryInfo(categoryName,  productEntity.getImage(), productEntity.getName(), productEntity.getPrice());
                }

                if(resultCategory > 0 && resultProduct > 0){
                    return true;
                }
                else throw new Exception("DB 저장 오류");

            }
        }catch (Exception e){
            logger.error(e);
            return false;
        }
        return false;
    }

    public boolean deleteProduct(String cName, String pName){
        try{
            int result = productRepository.deleteProduct(cName, pName);
            if(result > 0){
                logger.info("Successfully delete product {}",pName);
                return true;
            }
            else throw new Exception();
        }
        catch(Exception e){
            logger.error(e);
            return false;
        }
    }
}