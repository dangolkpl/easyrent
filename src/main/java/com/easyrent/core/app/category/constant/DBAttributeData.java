package com.easyrent.core.app.category.constant;

import com.easyrent.core.util.constant.DBAttributeDataMain;

public interface DBAttributeData extends DBAttributeDataMain {
    String IMAGE_NAME = "image_name";
    String IMAGE_URL = "image_url";

    String IS_DELETED = "is_deleted";
    String DELETED_BY = "deleted_by";
    String DELETED_DATE = "deleted_date";

    String PARENT_CATEGORY_ID = "parent_category_id";
}
