package com.spark.lms.confguration;

import com.spark.lms.model.dto.CategoryDto;
import com.spark.lms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategoryDtoConverter implements Converter<String, CategoryDto> {

    @Autowired
    private CategoryService categoryService;

    @Override
    public CategoryDto convert(String id) {
        try {
            Long categoryId = Long.parseLong(id);
            return categoryService.get(categoryId);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
