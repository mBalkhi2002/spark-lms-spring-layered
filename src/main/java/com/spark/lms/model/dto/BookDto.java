package com.spark.lms.model.dto;

import com.spark.lms.model.Book;
import lombok.*;

import java.util.Date;

@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String title;
    private String tag;
    private String authors;
    private String publisher;
    private String isbn;
    private Integer status;
    private Date createDate;
    private CategoryDto category;
    public static BookDto fromBookWithCategory(Book book) {
        if(book == null) return null;
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setTag(book.getTag());
        bookDto.setAuthors(book.getAuthors());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setStatus(book.getStatus());
        bookDto.setCreateDate(book.getCreateDate());
        bookDto.setCategory(CategoryDto.fromCategoryWithoutBooks(book.getCategory()));
        return bookDto;
    }
    public static BookDto fromBookWithoutCategory(Book book) {
        if(book == null) return null;
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setTag(book.getTag());
        bookDto.setAuthors(book.getAuthors());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setStatus(book.getStatus());
        bookDto.setCreateDate(book.getCreateDate());
        return bookDto;
    }
}