package com.easyrent.core.app.category.service.impl;

import com.easyrent.core.app.category.constant.ServiceMessage;
import com.easyrent.core.app.category.dto.CategorySave;
import com.easyrent.core.app.category.model.Category;
import com.easyrent.core.app.category.repository.CategoryRepository;
import com.easyrent.core.app.category.service.ICategoryService;
import com.easyrent.core.util.dto.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CategoryService implements ICategoryService, ServiceMessage {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseMessage save(CategorySave dto) {

        Category category = new Category(dto.getName(), dto.getDescription());
        category.setCreated(new Date());

        /*#TODO need to implement real logic. Its just for demo.*/
        String data;
        if(dto.getImage() == null){
            category = categoryRepository.save(category);
            data = "{\"categoryId\" : \"" + category.getId() + "\"}";
        }
        else{
            data = "{\"categoryId\" : \"" + "some id" + "\"}";
        }

        return new ResponseMessage(CATEGORY_SAVE_MESSAGE, HttpStatus.CREATED, data);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
}
