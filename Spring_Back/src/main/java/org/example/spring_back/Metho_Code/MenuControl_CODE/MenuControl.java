package org.example.spring_back.Metho_Code.MenuControl_CODE;

import org.springframework.transaction.annotation.Transactional;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;


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
    public List<Object[]> menuGetList(String userId){
        //메뉴 전체 출력하는 로직 생성 필요

        return db_Model.GetKioskList(userId);
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


    //region Insert Method

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


    public Boolean insertCateAndProduct(String kioskId, Menu menuData) {
        boolean allSuccess = true;

        try {
            for (Menu.Category categoryDto : menuData.getCategories()) {
                String categoryName = categoryDto.getCategoryDetails().getCategoryName();
                logger.info("Category Name: {}", categoryName);

                CategoryEntity categoryEntity = new CategoryEntity();
                categoryEntity.setKioskId(kioskId);
                categoryEntity.setCategoryName(categoryName);
                int resultCategory = categoryRepository.insertCategoryInfo(kioskId, categoryName);

                if (resultCategory <= 0) {
                    logger.error("Failed to insert category: {}", categoryName);
                    allSuccess = false;
                    continue;
                }

                // Process each Product in the Category
                for (Menu.Product productDto : categoryDto.getProducts()) {
                    logger.info("Product Name: {}", productDto.getName());
                    logger.info("Product Price: {}", productDto.getPrice());
                    logger.info("Product Image Base64 Value: {}", productDto.getImageBase64());

                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setCategoryName(categoryName);
                    productEntity.setName(productDto.getName());
                    productEntity.setPrice(productDto.getPrice());

                    // 디코딩하여 이미지 파일로 저장
                    String imagePath = saveImage(productDto.getImageBase64(), productDto.getName());
                    if (imagePath == null) {
                        logger.error("Failed to save image for product: {}", productDto.getName());
                        allSuccess = false;
                        continue;
                    }
                    productEntity.setImage(imagePath);  // 이미지 파일 경로를 설정

                    int resultProduct = productRepository.insertCategoryInfo(
                            categoryName,
                            productEntity.getImage(),  // 파일 경로를 DB에 저장
                            productEntity.getName(),
                            productEntity.getPrice()
                    );

                    if (resultProduct <= 0) {
                        logger.error("Failed to insert product: {}", productDto.getName());
                        allSuccess = false;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Exception occurred while inserting categories and products", e);
            return false;
        }
        return allSuccess;
    }

    //endregion


    //region Delete Method

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

    //endregion


    //region 전체 출력

    @Transactional(readOnly = false)  // 읽기 전용 트랜잭션 설정
    public List<Object[]> GetKioskList(String userId){
        logger.info("Start GetKioskList / userId: {}", userId);
        try{
            List<Object[]> kioskList = kioskRepository.SelectAllKiosk(userId);
            logger.info("Kiosk List: {}", kioskList);
            return kioskList;
        }
        catch(Exception e){

            logger.error(e);
            return null;
        }
    }


    //endregion


    //region Base 64 Decode and Save Image Code

    // Base64 디코딩 및 이미지 파일 저장 메서드 수정
    private String saveImage(String base64Image, String productName) {
        try {
            String imageDataBytes = base64Image.substring(base64Image.indexOf(",") + 1);
            byte[] imageBytes = Base64.getDecoder().decode(imageDataBytes);

            // 지정된 경로로 디렉토리 설정
            String directoryPath = "C:\\Users\\WSU\\Documents\\GitHub\\CapStone_Spring_Code\\Spring_Code\\Spring_Back\\src\\main\\java\\org\\example\\spring_back\\TEST_Image_File";
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();  // 디렉토리가 존재하지 않는 경우, 디렉토리 생성
            }

            File outputFile = new File(directory, productName + ".png");  // 파일 이름을 productName으로 설정
            try (OutputStream out = new FileOutputStream(outputFile)) {
                out.write(imageBytes);
            }
            return outputFile.getAbsolutePath();  // 저장된 파일의 절대 경로를 반환
        } catch (Exception e) {
            logger.error("Error saving image for product {}: {}", productName, e.getMessage());
            return null;
        }
    }

    //endregion


}