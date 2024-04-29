package org.example.spring_back.Repository_Interface.MenuRepo;

import org.example.spring_back.DTOFILE.Menu.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}