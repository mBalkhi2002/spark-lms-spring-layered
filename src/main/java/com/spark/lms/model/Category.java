package com.spark.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spark.lms.model.dto.CategoryDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "category")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotNull(message = "*Please enter category name")
	@NotBlank(message = "*Please enter category name")
	@Column(name = "name")
	private String name;
	
	@NotNull(message = "*Please enter category short name")
	@NotBlank(message = "*Please enter category short name")
	@Length(max = 4, message = "*Must not exceed 4 characters.")
	@Column(name = "short_name")
	private String shortName;
	
	@Column(name = "notes")
	@Length(max = 1000, message = "*Must not exceed 1000 characters.")
	private String notes;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Book> books;

	public static Category fromCategoryDtoWithBooks(CategoryDto categoryDto) {
		if(categoryDto == null) return null;
		Category category = new Category();
		category.setId(categoryDto.getId());
		category.setName(categoryDto.getName());
		category.setShortName(categoryDto.getShortName());
		category.setNotes(categoryDto.getNotes());
		category.setCreateDate(categoryDto.getCreateDate());
		category.setBooks(categoryDto.getBooks().stream().map(Book::fromBookDtoWithoutCategory).collect(Collectors.toList()));
		return category;
	}

	public static Category fromCategoryDtoWithoutBooks(CategoryDto categoryDto) {
		if(categoryDto == null) return null;
		Category category = new Category();
		category.setId(categoryDto.getId());
		category.setName(categoryDto.getName());
		category.setShortName(categoryDto.getShortName());
		category.setNotes(categoryDto.getNotes());
		category.setCreateDate(categoryDto.getCreateDate());
		return category;
	}
	
}
