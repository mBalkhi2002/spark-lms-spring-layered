package com.spark.lms.repository;

import com.spark.lms.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	List<Member> findAllByOrderByFirstNameAscMiddleNameAscLastNameAsc();
	Long countByType(String type);
}
