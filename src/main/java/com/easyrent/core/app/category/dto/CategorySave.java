package com.easyrent.core.app.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorySave {
    @NotBlank
    private String name = "";
    @NotBlank
    private String description = "";

    @NotNull
    private MultipartFile image;

    private boolean isSubcategory = false;
    private String parentCategoryId = "";

    public CategorySave(String name, String description, MultipartFile image){
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
