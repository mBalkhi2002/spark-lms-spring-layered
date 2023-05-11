package com.spark.lms.service;

import com.spark.lms.common.Constants;
import com.spark.lms.model.Issue;
import com.spark.lms.model.Member;
import com.spark.lms.model.dto.IssueDto;
import com.spark.lms.model.dto.MemberDto;
import com.spark.lms.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueService {

	@Autowired
	private IssueRepository issueRepository;
	
	public List<IssueDto> getAll() {
		return issueRepository.findAll().stream().map(IssueDto::fromIssue).collect(Collectors.toList());
	}
	
	public IssueDto get(Long id) {
		return IssueDto.fromIssue(issueRepository.findById(id).get());
	}
	
	public List<IssueDto> getAllUnreturned() {
		return issueRepository.findByReturned( Constants.BOOK_NOT_RETURNED ).stream().map(IssueDto::fromIssue).collect(Collectors.toList());
	}
	
	public IssueDto addNew(IssueDto issue) {
		issue.setIssueDate( new Date() );
		issue.setReturned( Constants.BOOK_NOT_RETURNED );
		return IssueDto.fromIssue(issueRepository.save(Issue.toIssue(issue)));
	}
	
	public IssueDto save(IssueDto issue) {
		Issue issueToSave = Issue.toIssue(issue);
		return IssueDto.fromIssue(issueRepository.save(issueToSave));
	}
	
	public Long getCountByMember(MemberDto member) {
		return issueRepository.countByMemberAndReturned(Member.toMember(member), Constants.BOOK_NOT_RETURNED);
	}
}
