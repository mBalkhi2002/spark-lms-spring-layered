package com.spark.lms.service;

import com.spark.lms.common.Constants;
import com.spark.lms.model.Member;
import com.spark.lms.model.dto.MemberDto;
import com.spark.lms.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private IssueService issueService;
	
	public Long getTotalCount() {
		return memberRepository.count();
	}
	
	public Long getParentsCount() {
		return memberRepository.countByType(Constants.MEMBER_PARENT);
	}
	
	public Long getStudentsCount() {
		return memberRepository.countByType(Constants.MEMBER_STUDENT);
	}
	
	public List<MemberDto> getAll() {
		return memberRepository.findAllByOrderByFirstNameAscMiddleNameAscLastNameAsc().stream().map(MemberDto::fromMember).collect(Collectors.toList());
	}
	
	public MemberDto get(Long id) {
		return MemberDto.fromMember(memberRepository.findById(id).get());
	}
	
	public MemberDto addNew(MemberDto member) {
		member.setJoiningDate( new Date() );
		return MemberDto.fromMember(memberRepository.save( Member.toMember(member) ));
	}
	
	public MemberDto save(MemberDto member) {
		return MemberDto.fromMember(memberRepository.save( Member.toMember(member) ));
	}
	
	public void delete(MemberDto member) {
		memberRepository.delete(Member.toMember(member));
	}
	
	public void delete(Long id) {
		memberRepository.deleteById(id);
	}
	
	public boolean hasUsage(MemberDto member) {
		return issueService.getCountByMember(member) > 0;
	}
	
}
