package com.ecommerce.library.service;

import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.dto.StoreDto;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface StoreService {

    List<StoreDto> findAll();

    Store save(StoreDto storeDto);

    Store update(StoreDto storeDto);

    void deleteById(Long id);


    StoreDto getById(Long id);


    Page<StoreDto> pageProducts(int pageNo);

    Page<StoreDto> searchProducts(int pageNo, String keyword);
}
