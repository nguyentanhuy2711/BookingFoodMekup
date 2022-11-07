package com.ecommerce.library.repository;

import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Long> {
    @Query("select s from Store s")
    Page<Store> pageStore(Pageable pageable);

    @Query("select s from Store s where s.description like %?1% or s.name like %?1%")
    Page<Store> searchStores(String keyword, Pageable pageable);

    @Query("select s from Store s where s.description like %?1% or s.name like %?1%")
    List<Store> searchStoreList(String keyword);



//    @Query("select s from Store s")
//    List<Store> getAllProducts();


}
