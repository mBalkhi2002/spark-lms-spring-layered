package com.spark.lms.service;

import com.spark.lms.common.Constants;
import com.spark.lms.model.Book;
import com.spark.lms.model.IssuedBook;
import com.spark.lms.model.dto.BookDto;
import com.spark.lms.model.dto.IssuedBookDto;
import com.spark.lms.repository.IssuedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssuedBookService {

	@Autowired
	private IssuedBookRepository issuedBookRepository;
	
	public List<IssuedBookDto> getAll() {
		return issuedBookRepository.findAll().stream().map(IssuedBookDto::fromIssuedBook).collect(Collectors.toList());
	}
	
	public IssuedBookDto get(Long id) {
		return IssuedBookDto.fromIssuedBook(issuedBookRepository.findById(id).get());
	}
	
	public Long getCountByBook(BookDto book) {
		return issuedBookRepository.countByBookAndReturned(Book.fromBookDto(book), Constants.BOOK_NOT_RETURNED);
	}
	
	public IssuedBookDto save(IssuedBookDto issuedBook) {
		return IssuedBookDto.fromIssuedBook(issuedBookRepository.save(IssuedBook.toIssuedBook(issuedBook)));
	}
	
	public IssuedBookDto addNew(IssuedBookDto issuedBook) {
		issuedBook.setReturned( Constants.BOOK_NOT_RETURNED );
		return IssuedBookDto.fromIssuedBook(issuedBookRepository.save(IssuedBook.toIssuedBook(issuedBook)));
	}

}
