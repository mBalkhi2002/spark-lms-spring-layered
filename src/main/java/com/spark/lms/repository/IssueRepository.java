package com.spark.lms.repository;

import com.spark.lms.model.Issue;
import com.spark.lms.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
	List<Issue> findByReturned(Integer returned);
	Long countByMemberAndReturned(Member member, Integer returned);
}
