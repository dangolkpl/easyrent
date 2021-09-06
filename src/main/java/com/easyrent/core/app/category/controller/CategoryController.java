package com.easyrent.core.app.category.controller;

import com.easyrent.core.annotation.ApiPageable;
import com.easyrent.core.app.category.constant.ServiceMessage;
import com.easyrent.core.app.category.dto.CategorySave;
import com.easyrent.core.app.category.model.Category;
import com.easyrent.core.app.category.service.ICategoryService;
import com.easyrent.core.util.dto.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/category")
@Api(value = "Category", description = "REST API for Category", tags = { "Category" })
public class CategoryController implements ServiceMessage {
    public final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/get-all")
    @ResponseBody
    @ApiOperation(value = "Fetch the list of saved Category in Paginated Object",
            notes = "Fetch the list of saved Category in Paginated Object")
    @ApiResponse(code = 200, message = SUCCESSFUL_MESSAGE)
    @ApiPageable
    public ResponseEntity<Page<Category>> findAllCategories(@ApiIgnore Pageable pageable){
        return ResponseEntity.ok(categoryService.findAll(pageable));
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value="Save the Category",
            notes="Save the Category")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = CATEGORY_SAVE_MESSAGE),
            @ApiResponse(code = 400, message = VALIDATION_ERROR_MESSAGE)
    })
    public ResponseEntity<ResponseMessage> saveCategory(@RequestParam(value = "name") String name,
                                                        @RequestParam(value = "description") String description,
                                                        @RequestParam(value = "image", required = false) MultipartFile image,
                                                        @RequestParam(value = "isSubCategory", required = false) boolean isSubCategory,
                                                        @RequestParam(value = "parentCategoryId", required = false) String parentCategoryId){
        ResponseMessage response = categoryService.save(new CategorySave(name, description, image, isSubCategory, parentCategoryId));
        return new ResponseEntity<>(response, response.getStatus());
    }
}
