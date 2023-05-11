package com.spark.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spark.lms.model.dto.BookDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotNull(message = "*Please enter book title")
	@NotBlank(message = "*Please enter book title")
	@Column(name = "title")
	private String title;
	
	@NotNull(message = "*Please enter book tag")
	@NotBlank(message = "*Please enter book tag")
	@Column(name = "tag")
	private String tag;
	
	@NotNull(message = "*Please enter book authors")
	@NotBlank(message = "*Please enter book authors")
	@Column(name = "authors")
	private String authors;
	
	@Column(name = "publisher")
	private String publisher;
	
	@Column(name = "isbn")
	private String isbn;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "category_id")
	@NotNull(message = "*Please select category")
	private Category category;

	public static Book fromBookDto(BookDto bookDto) {
		if(bookDto == null) return null;
		Book book = new Book();
		book.setId(bookDto.getId());
		book.setTitle(bookDto.getTitle());
		book.setTag(bookDto.getTag());
		book.setAuthors(bookDto.getAuthors());
		book.setPublisher(bookDto.getPublisher());
		book.setIsbn(bookDto.getIsbn());
		book.setStatus(bookDto.getStatus());
		book.setCreateDate(bookDto.getCreateDate());
		book.setCategory(Category.fromCategoryDtoWithoutBooks(bookDto.getCategory()));
		return book;
	}

	public static Book fromBookDtoWithoutCategory(BookDto bookDto) {
		if(bookDto == null) return null;
		Book book = new Book();
		book.setId(bookDto.getId());
		book.setTitle(bookDto.getTitle());
		book.setTag(bookDto.getTag());
		book.setAuthors(bookDto.getAuthors());
		book.setPublisher(bookDto.getPublisher());
		book.setIsbn(bookDto.getIsbn());
		book.setStatus(bookDto.getStatus());
		book.setCreateDate(bookDto.getCreateDate());
		return book;
	}

}
