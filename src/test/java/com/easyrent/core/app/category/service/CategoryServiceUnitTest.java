package com.easyrent.core.app.category.service;

import com.easyrent.core.app.category.constant.ServiceMessage;
import com.easyrent.core.app.category.dto.CategorySave;
import com.easyrent.core.app.category.model.Category;
import com.easyrent.core.app.category.repository.CategoryRepository;
import com.easyrent.core.app.category.service.impl.CategoryService;
import com.easyrent.core.util.dto.ResponseMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceUnitTest implements ServiceMessage {
    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    List<Category> categories;

    @Before
    public void init(){
        categories = Stream.of(
                new Category("electronics", "This is electronics categories"),
                new Category("real state", "This is real state categories", "real-state.png", "easyrent.com/real-state.png"),
                new Category("clothes", "This is clothes categories", "clothes.png", "easyrent.com/clothes.png")
        ).collect(Collectors.toList());
    }

    @Test
    public void save_Test(){
        Category category = categories.get(0);
        Mockito.when(categoryRepository.save(isA(Category.class))).thenReturn(category);

        ResponseMessage response = categoryService.save(
                new CategorySave(category.getName(), category.getDescription(), null)
        );
        Assert.assertEquals(HttpStatus.CREATED, response.getStatus());
        Assert.assertEquals(CATEGORY_SAVE_MESSAGE, response.getMessage());
    }

    @Test
    public void paginatedGetAll_DataPresent_Test(){
        Page<Category> pagedCategory = new PageImpl<>(categories);
        Mockito.when(categoryRepository.findAll(isA(Pageable.class))).thenReturn(pagedCategory);

        Pageable pageRequest = PageRequest.of(0, 10);
        Page<Category> pagedResponse = categoryService.findAll(pageRequest);
        Assert.assertEquals(categories.size(), pagedResponse.getTotalElements());
    }

    @Test
    public void paginatedGetAll_DataEmpty_Test(){
        Page<Category> pagedCategory = new PageImpl<>(Collections.emptyList());
        Mockito.when(categoryRepository.findAll(isA(Pageable.class))).thenReturn(pagedCategory);

        Pageable pageRequest = PageRequest.of(0, 10);
        Page<Category> pagedResponse = categoryService.findAll(pageRequest);
        Assert.assertEquals(0, pagedResponse.getTotalElements());
    }
}
