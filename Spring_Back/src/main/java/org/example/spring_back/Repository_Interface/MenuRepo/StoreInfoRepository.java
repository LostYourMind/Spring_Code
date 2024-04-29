package org.example.spring_back.Repository_Interface.MenuRepo;

import org.example.spring_back.DTOFILE.Menu.StoreInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreInfoRepository extends JpaRepository<StoreInfoEntity, Long> {
    // Custom query methods, if needed...
}