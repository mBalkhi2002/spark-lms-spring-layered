package com.spark.lms.model.dto;

import com.spark.lms.model.IssuedBook;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssuedBookDto {

    private Long id;
    private BookDto book;
    private IssueDto issue;
    private Integer returned;

    public static IssuedBookDto fromIssuedBook(IssuedBook issuedBook) {
        IssuedBookDto issuedBookDto = new IssuedBookDto();
        issuedBookDto.setId(issuedBook.getId());
        issuedBookDto.setBook(BookDto.fromBookWithCategory(issuedBook.getBook()));
        if(issuedBook.getIssue()!= null)
            issuedBookDto.setIssue(IssueDto.fromIssueWithoutIssuedBooks(issuedBook.getIssue()));
        issuedBookDto.setReturned(issuedBook.getReturned());
        return issuedBookDto;
    }

    public static IssuedBookDto fromIssuedBookWithoutIssue(IssuedBook issuedBook) {
        IssuedBookDto issuedBookDto = new IssuedBookDto();
        issuedBookDto.setId(issuedBook.getId());
        issuedBookDto.setBook(BookDto.fromBookWithCategory(issuedBook.getBook()));
        issuedBookDto.setReturned(issuedBook.getReturned());
        return issuedBookDto;
    }
}