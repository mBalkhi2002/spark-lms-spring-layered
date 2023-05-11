package com.spark.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spark.lms.model.dto.IssueDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "issue")
public class Issue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Column(name = "issue_date")
	private Date issueDate;
	
	@Column(name = "notes")
	private String notes;
	
	@Column(name = "expected_return_date")
	private Date expectedReturnDate;
	
	@Column(name = "returned")
	private Integer returned;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@JsonIgnore
	@OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
	private List<IssuedBook> issuedBooks;

	public static Issue toIssue(IssueDto issueDto) {
		Issue issue = new Issue();
		issue.setId(issueDto.getId());
		issue.setIssueDate(issueDto.getIssueDate());
		issue.setNotes(issueDto.getNotes());
		issue.setExpectedReturnDate(issueDto.getExpectedReturnDate());
		issue.setReturned(issueDto.getReturned());
		issue.setMember(Member.toMember(issueDto.getMember()));
		if(issueDto.getIssuedBooks()!=null) {
			issue.setIssuedBooks(issueDto.getIssuedBooks().stream().map(IssuedBook::toIssuedBookWithoutIssue).collect(Collectors.toList()));
			for(IssuedBook ib : issue.getIssuedBooks()){
				ib.setIssue(issue);
			}
		}
		return issue;
	}

	public static Issue toIssueWithoutIssuedBooks(IssueDto issueDto) {
		Issue issue = new Issue();
		issue.setId(issueDto.getId());
		issue.setIssueDate(issueDto.getIssueDate());
		issue.setNotes(issueDto.getNotes());
		issue.setExpectedReturnDate(issueDto.getExpectedReturnDate());
		issue.setReturned(issueDto.getReturned());
		issue.setMember(Member.toMember(issueDto.getMember()));
		return issue;
	}
}
