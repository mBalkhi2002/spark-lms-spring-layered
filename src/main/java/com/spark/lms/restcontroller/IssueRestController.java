package com.spark.lms.restcontroller;

import com.spark.lms.common.Constants;
import com.spark.lms.model.dto.BookDto;
import com.spark.lms.model.dto.IssueDto;
import com.spark.lms.model.dto.IssuedBookDto;
import com.spark.lms.model.dto.MemberDto;
import com.spark.lms.service.BookService;
import com.spark.lms.service.IssueService;
import com.spark.lms.service.IssuedBookService;
import com.spark.lms.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/issue")
public class IssueRestController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private BookService bookService;

	@Autowired
	private IssueService issueService;

	@Autowired
	private IssuedBookService issuedBookService;

	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@RequestParam Map<String, String> payload) {

		String memberIdStr = payload.get("member");
		String[] bookIdsStr = payload.get("books").split(",");

		Long memberId = null;
		List<Long> bookIds = new ArrayList<Long>();
		try {
			memberId = Long.parseLong( memberIdStr );
			for(int k=0 ; k<bookIdsStr.length ; k++) {
				bookIds.add( Long.parseLong(bookIdsStr[k]) );
			}
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			return "invalid number format";
		}

		MemberDto member = memberService.get( memberId );
		List<BookDto> books = bookService.get(bookIds);

		IssueDto issue = new IssueDto();
		issue.setMember(member);
		issue = issueService.addNew(issue);

		for( int k=0 ; k<books.size() ; k++ ) {
			BookDto book = books.get(k);
			book.setStatus( Constants.BOOK_STATUS_ISSUED );
			book = bookService.save(book);

			IssuedBookDto ib = new IssuedBookDto();
			ib.setBook( book );
			ib.setIssue( issue );
			issuedBookService.addNew( ib );
		}

		return "success";
	}

	@RequestMapping(value = "/{id}/return/all", method = RequestMethod.GET)
	public String returnAll(@PathVariable(name = "id") Long id) {
		IssueDto issue = issueService.get(id);
		if( issue != null ) {
			List<IssuedBookDto> issuedBooks = issue.getIssuedBooks();
			for( int k=0 ; k<issuedBooks.size() ; k++ ) {
				IssuedBookDto ib = issuedBooks.get(k);
				ib.setReturned( Constants.BOOK_RETURNED );

				issuedBookService.save( ib );

				BookDto book = ib.getBook();
				book.setStatus( Constants.BOOK_STATUS_AVAILABLE );
				bookService.save(book);
			}

			issue.setReturned( Constants.BOOK_RETURNED );
			issueService.save(issue);

			return "successful";
		} else {
			return "unsuccessful";
		}
	}

	@RequestMapping(value="/{id}/return", method = RequestMethod.POST)
	public String returnSelected(@RequestParam Map<String, String> payload, @PathVariable(name = "id") Long id) {
		IssueDto issue = issueService.get(id);
		String[] issuedBookIds = payload.get("ids").split(",");
		if( issue != null ) {

			List<IssuedBookDto> issuedBooks = issue.getIssuedBooks();
			IssueDto issue2 = new IssueDto();
			issue2.setId(issue.getId());
			issue2.setIssueDate(issue.getIssueDate());
			issue2.setReturned(issue.getReturned());
			issue2.setMember(issue.getMember());
			issue2.setExpectedReturnDate(issue.getExpectedReturnDate());
			for( int k=0 ; k<issuedBooks.size() ; k++ ) {
				IssuedBookDto ib = issuedBooks.get(k);
				if( Arrays.asList(issuedBookIds).contains( ib.getId().toString() ) ) {
					ib.setReturned( Constants.BOOK_RETURNED );
					ib.setIssue(issue2);
					issuedBookService.save( ib );

					BookDto book = ib.getBook();
					book.setStatus( Constants.BOOK_STATUS_AVAILABLE );
					bookService.save(book);
				}
			}

			return "successful";
		} else {
			return "unsuccessful";
		}
	}

}
