package org.example.spring_back.Repository_Interface.MenuRepo;

import jakarta.transaction.Transactional;
import org.example.spring_back.DTOFILE.Menu.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO category (kioskId, categoryName) VALUES (?1, ?2)", nativeQuery = true)
    int insertCategoryInfo(String kioskId, String categoryName);



}