package com.spark.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spark.lms.model.dto.IssuedBookDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "issued_book")
public class IssuedBook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id")
	@NotNull
	private Book book;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "issue_id")
	@NotNull
	private Issue issue;
	
	@Column(name = "returned")
	private Integer returned;

	public static IssuedBook toIssuedBook(IssuedBookDto issuedBookDto) {
		IssuedBook issuedBook = new IssuedBook();
		issuedBook.setId(issuedBookDto.getId());
		issuedBook.setBook(Book.fromBookDto(issuedBookDto.getBook()));
		if(issuedBookDto.getIssue()!=null)
			issuedBook.setIssue(Issue.toIssueWithoutIssuedBooks(issuedBookDto.getIssue()));
		issuedBook.setReturned(issuedBookDto.getReturned());
		return issuedBook;
	}

	public static IssuedBook toIssuedBookWithoutIssue(IssuedBookDto issuedBookDto) {
		IssuedBook issuedBook = new IssuedBook();
		issuedBook.setId(issuedBookDto.getId());
		issuedBook.setBook(Book.fromBookDto(issuedBookDto.getBook()));
		issuedBook.setReturned(issuedBookDto.getReturned());
		return issuedBook;
	}
}
