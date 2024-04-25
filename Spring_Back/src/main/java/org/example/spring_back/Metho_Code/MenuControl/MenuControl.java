package org.example.spring_back.Metho_Code.MenuControl;

import org.example.spring_back.Menu.Menu;
import org.example.spring_back.Menu.Menu.Info;
import org.example.spring_back.Menu.Menu.Category;
import org.example.spring_back.Menu.Menu.Product;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Service;


@Service("MainMenuControl")
public class MenuControl {

    private final Logger logger = LogManager.getLogger(MenuControl.class);

    public Boolean Insert_Menu(Menu menuData) {
        // Log Menu Info details

        logger.info("Menu data : {} ", menuData);
        Info info = menuData.getInfo();
        if (info != null) {
            logger.info("Info Name: {}", info.getName());
            logger.info("Info Address: {}", info.getAddress());
            logger.info("Info Type: {}", info.getType());
        }

        // Iterate through the categories and products
        for (Category category : menuData.getCategories()) {
            logger.info("Category Name: {}", category.getCategoryName());
            for (Product product : category.getProducts()) {
                logger.info("Product Name: {}", product.getName());
                logger.info("Product Price: {}", product.getPrice());

                // Handle Base64 encoded image string if present
               /* String base64Image = product.getImageBase64();
                if (base64Image != null && !base64Image.trim().isEmpty()) {
                    // Convert Base64 encoded string to image file or save to database
                    // The actual implementation will depend on your specific requirements
                }*/
            }
        }

        // Implement your database save logic or any other business logic here
        // Example: menuRepository.save(menuData);

        return true;
    }
}