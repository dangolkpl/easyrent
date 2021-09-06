package com.easyrent.core.annotation;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "int", paramType = "query", value = "Results page you want to retrieve (0..N)", example = "1"),
        @ApiImplicitParam(name = "size", dataType = "int", paramType = "query", value = "Number of records per page.", example = "10"),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
                + "\nDefault sort order is ascending. " + "\nMultiple sort criteria are supported.") })
public @interface ApiPageable {
    String message() default "Pagination Format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
