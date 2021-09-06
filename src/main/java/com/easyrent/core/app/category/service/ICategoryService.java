package com.easyrent.core.app.category.service;

import com.easyrent.core.app.category.dto.CategorySave;
import com.easyrent.core.app.category.model.Category;
import com.easyrent.core.util.dto.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {
    /**
     * Method for saving the Categories
     *
     * @param dto : CategorySave (DTO)
     *            - User-given data for saving the category.
     * @return : ResponseMessage
     *           - returns pojo with response message and http status
     * */
    ResponseMessage save(CategorySave dto);

    /**
     * Get the list of all Category data in db
     *
     * @param pageable: Pageable (Object)
     *                - Backend Pagination
     *                - consists of following data
     *                - page : Number, current page no
     *                - size : Number, size of the data in current page
     *                - sort : String, sorting condition
     *                - eg; page=1&size=20&sort=name,ASC
     * @return : Page<Category>
     * */
    Page<Category> findAll(Pageable pageable);
}
