package com.spark.lms.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spark.lms.model.Category;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String shortName;
    private String notes;
    private Date createDate;
    @JsonIgnore
    private List<BookDto> books;

    public static CategoryDto fromCategoryWithBooks(Category category) {
        if(category == null) return null;
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setShortName(category.getShortName());
        categoryDto.setNotes(category.getNotes());
        categoryDto.setCreateDate(category.getCreateDate());
        categoryDto.setBooks(category.getBooks().stream().map(BookDto::fromBookWithoutCategory).collect(Collectors.toList()));
        return categoryDto;
    }

    public static CategoryDto fromCategoryWithoutBooks(Category category) {
        if(category == null) return null;
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setShortName(category.getShortName());
        categoryDto.setNotes(category.getNotes());
        categoryDto.setCreateDate(category.getCreateDate());
        return categoryDto;
    }
}