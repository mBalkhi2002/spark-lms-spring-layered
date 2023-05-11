package com.spark.lms.controller;

import com.spark.lms.model.dto.BookDto;
import com.spark.lms.model.dto.CategoryDto;
import com.spark.lms.service.BookService;
import com.spark.lms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService categoryService;
	
	@ModelAttribute(name = "categories")
	public List<CategoryDto> categories() {
		return categoryService.getAllBySort();
	}
	
	@RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
	public String showBooksPage(Model model) {
		model.addAttribute("books", bookService.getAll());
		return "/book/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBookPage(Model model) {
		model.addAttribute("book", new BookDto());
		return "/book/form";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBookPage(@PathVariable(name = "id") Long id,  Model model) {
		BookDto book = bookService.get(id);
		if( book != null ) {
			model.addAttribute("book", book);
			return "/book/form";
		} else {
			return "redirect:/book/add";
		}
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@Valid BookDto book, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
//		book.setCategory(categoryService.get(book.getCategory().getId()));
		if( book.getId() == null ) {
			if( bookService.getByTag(book.getTag()) != null ) {
				bindingResult.rejectValue("tag", "tag", "Tag already exists");
				return "/book/form";
			} else {
				bookService.addNew(book);
				redirectAttributes.addFlashAttribute("successMsg", "'" + book.getTitle() + "' is added as a new Book.");
				return "redirect:/book/add";
			}
		} else {
			BookDto updatedBook = bookService.save(book);
			redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + book.getTitle() + "' are saved successfully. ");
			return "redirect:/book/edit/"+updatedBook.getId();
		}
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String removeBook(@PathVariable(name = "id") Long id, Model model) {
		BookDto book = bookService.get( id );
		if( book != null ) {
			if( bookService.hasUsage(book) ) {
				model.addAttribute("bookInUse", true);
				return showBooksPage(model);
			} else {
				bookService.delete(id);
			}
		}
		return "redirect:/book/list";
	}
}
