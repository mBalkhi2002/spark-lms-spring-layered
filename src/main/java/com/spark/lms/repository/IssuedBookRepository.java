package com.spark.lms.repository;

import com.spark.lms.model.Book;
import com.spark.lms.model.IssuedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuedBookRepository extends JpaRepository<IssuedBook, Long> {
	Long countByBookAndReturned(Book book, Integer returned);
}
