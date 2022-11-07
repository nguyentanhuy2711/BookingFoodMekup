package com.ecommerce.library.service.impl;

import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.dto.StoreDto;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.Store;
import com.ecommerce.library.repository.ProductRepository;
import com.ecommerce.library.repository.StoreRepository;
import com.ecommerce.library.service.StoreService;
import com.ecommerce.library.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;


    /*Admin*/
    @Override
    public List<StoreDto> findAll() {
        List<Store> stores = storeRepository.findAll();
        List<StoreDto> storeDtoList = transfer(stores);
        return storeDtoList;
    }

    @Override
    public Store save(StoreDto storeDto) {
        try {
            Store store = new Store();
            store.setName(storeDto.getName());
            store.setDescription(storeDto.getDescription());
            store.setAddress(storeDto.getAddress());
            store.setPhone(store.getPhone());
            return storeRepository.save(store);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Store update(StoreDto storeDto) {
        try {
            Store store = new Store();
            store.setName(storeDto.getName());
            store.setDescription(storeDto.getDescription());
            store.setAddress(storeDto.getAddress());
            store.setPhone(store.getPhone());
            return storeRepository.save(store);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void deleteById(Long id) {
        Store product = storeRepository.getById(id);
        storeRepository.delete(product);
    }



    @Override
    public StoreDto getById(Long id) {
        Store store = storeRepository.getById(id);
        StoreDto storeDto = new StoreDto();
        store.setName(storeDto.getName());
        store.setDescription(storeDto.getDescription());
        store.setAddress(storeDto.getAddress());
        store.setPhone(store.getPhone());
        return storeDto ;
    }

    @Override
    public Page<StoreDto> pageProducts(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        List<StoreDto> storeDtos = transfer(storeRepository.findAll());
        Page<StoreDto> storePages = toPage(storeDtos, pageable);
        return storePages;
    }

    @Override
    public Page<StoreDto> searchProducts(int pageNo, String keyword) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        List<StoreDto> storeDtoList = transfer(storeRepository.searchStoreList(keyword));
        Page<StoreDto> stores = toPage(storeDtoList, pageable);
        return stores;
    }



    private Page toPage(List<StoreDto> list , Pageable pageable){
        if(pageable.getOffset() >= list.size()){
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = ((pageable.getOffset() + pageable.getPageSize()) > list.size())
                ? list.size()
                : (int) (pageable.getOffset() + pageable.getPageSize());
        List subList = list.subList(startIndex, endIndex);
        return new PageImpl(subList, pageable, list.size());
    }


    private List<StoreDto> transfer(List<Store> stores){
        List<StoreDto> storeDtoList = new ArrayList<>();
        for(Store store : stores){
            StoreDto storeDto = new StoreDto();
            storeDto.setId(store.getId());
            storeDto.setName(store.getName());
            storeDto.setDescription(store.getDescription());
            storeDto.setAddress(store.getAddress());
            storeDto.setPhone((store.getPhone()));
            storeDtoList.add(storeDto);
        }
        return storeDtoList;
    }
}
