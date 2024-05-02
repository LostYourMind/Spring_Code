package org.example.spring_back.Repository_Interface.MenuRepo;

import jakarta.transaction.Transactional;
import org.example.spring_back.DTOFILE.Menu.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO product (category_name, image, name, price) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    int insertCategoryInfo(String categoryName, String imageBase64, String name, int price);
}