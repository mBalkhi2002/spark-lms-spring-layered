package com.spark.lms.restcontroller;

import com.spark.lms.model.dto.MemberDto;
import com.spark.lms.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/member")
public class MemberRestController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping(value = {"/", "/list"})
	public List<MemberDto> all() {
		return memberService.getAll();
	}
	
}
