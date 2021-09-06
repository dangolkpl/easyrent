package com.easyrent.core.app.category.controller;

import com.easyrent.core.app.category.constant.ServiceMessage;
import com.easyrent.core.app.category.model.Category;
import com.easyrent.core.app.category.service.impl.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerUnitTest implements ServiceMessage {
    private MockMvc mockMvc;

    @InjectMocks
    private ObjectMapper objectMapper;
    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    List<Category> categories;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView()).build();

        categories = Stream.of(
                new Category("electronics", "This is electronics categories"),
                new Category("real state", "This is real state categories", "real-state.png", "easyrent.com/real-state.png"),
                new Category("clothes", "This is clothes categories", "clothes.png", "easyrent.com/clothes.png")
        ).collect(Collectors.toList());
    }

    @Test
    public void findAllCustomer_Test() throws Exception {
        Page<Category> pagedCategory = new PageImpl<>(categories);
        when(categoryService.findAll(isA(Pageable.class))).thenReturn(pagedCategory);

        Pageable pageRequest = PageRequest.of(0, 10);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/category/get-all?size="+ pageRequest.getPageSize() +"&page="+ pageRequest.getPageNumber())
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(categoryService, Mockito.times(1)).findAll(pageRequest);
    }
}
