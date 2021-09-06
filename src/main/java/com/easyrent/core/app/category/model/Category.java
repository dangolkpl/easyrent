package com.easyrent.core.app.category.model;

import com.easyrent.core.app.category.constant.DBAttributeData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements DBAttributeData {
    @Id
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    @GeneratedValue(generator = "system_uuid")
    @Column(name = ID)
    private String id;

    @NotEmpty
    @Column(name = NAME)
    private String name;
    @Column(name = DESCRIPTION, columnDefinition = "text")
    private String description;

    @Column(name = IMAGE_NAME, length = MEDIUM_LENGTH)
    private String imageName;
    @Column(name = IMAGE_URL)
    private String imageUrl;

    @Column(name = IS_DELETED)
    private boolean isDeleted = false;
    /*TODO: Link User to track who deleted the categories. For this need to complete the user model part.*/
    @Column(name = DELETED_DATE)
    private String deletedDate;

    @Column(name = CREATED)
    private Date created;
    @Column(name = UPDATED)
    private Date updated;

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = PARENT_CATEGORY_ID)
    private Category parentCategory = null;

    public Category(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Category(String name, String description, String imageName, String imageUrl){
        this.name = name;
        this.description = description;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }

    public Category(String name, String description, String imageName, String imageUrl, Category parentCategory){
        this.name = name;
        this.description = description;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.parentCategory = parentCategory;
    }
}
