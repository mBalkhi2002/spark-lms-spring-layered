package com.spark.lms.model.dto;

import com.spark.lms.model.Issue;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueDto {

    private Long id;
    private Date issueDate;
    private String notes;
    private Date expectedReturnDate;
    private Integer returned;
    private MemberDto member;
    private List<IssuedBookDto> issuedBooks;

    public static IssueDto fromIssue(Issue issue) {
        IssueDto issueDto = new IssueDto();
        issueDto.setId(issue.getId());
        issueDto.setIssueDate(issue.getIssueDate());
        issueDto.setNotes(issue.getNotes());
        issueDto.setExpectedReturnDate(issue.getExpectedReturnDate());
        issueDto.setReturned(issue.getReturned());
        issueDto.setMember(MemberDto.fromMember(issue.getMember()));
        if(issue.getIssuedBooks()!=null) {
            issueDto.setIssuedBooks(issue.getIssuedBooks().stream().map(IssuedBookDto::fromIssuedBook).collect(Collectors.toList()));

        }
        return issueDto;
    }

    public static IssueDto fromIssueWithoutIssuedBooks(Issue issue) {
        IssueDto issueDto = new IssueDto();
        issueDto.setId(issue.getId());
        issueDto.setIssueDate(issue.getIssueDate());
        issueDto.setNotes(issue.getNotes());
        issueDto.setExpectedReturnDate(issue.getExpectedReturnDate());
        issueDto.setReturned(issue.getReturned());
        issueDto.setMember(MemberDto.fromMember(issue.getMember()));
        return issueDto;
    }
}