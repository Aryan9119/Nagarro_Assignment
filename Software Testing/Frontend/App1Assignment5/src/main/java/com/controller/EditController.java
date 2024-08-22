package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Author;
import com.entity.Book;
import com.service.AuthorService;
import com.service.BookService;

/**
 * This class represents a controller for editing books in the library management system.
 * @author aryanverma
 *
 */
@Controller
public class EditController {
//	@Autowired
//	Book book;
//	@Autowired
	private final AuthorService authors;
//	@Autowired
	private final BookService bookService;
	
	 @Autowired
	    public EditController(BookService bookService, AuthorService authorService) {
	        this.bookService = bookService;
	        this.authors = authorService;
	    }
	
	/**
	 * Handles the HTTP POST request for editing a book and returns a ModelAndView object 
	 * that represents the EditBook view.
	 * @param request HttpServletRequest object representing the request
	 * @param response HttpServletResponse object representing the response
	 * @return ModelAndView object representing the EditBook view
	 */
	@PostMapping("/Edit") // for post method mapping
	public ModelAndView editBook(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();

		String bookcode = request.getParameter("bookcode");
		int bookCode = Integer.parseInt(bookcode);
		String bookname = request.getParameter("bookname");
		String author = request.getParameter("author");
		String addedon = request.getParameter("addedOn");

		Book book = new Book();
		book.setAddedOn(addedon);
		book.setAuthor(author);
		book.setBookCode(bookCode);
		book.setBookName(bookname);

		mv.addObject("book", book);
		List<Author> Author = authors.retrieveAuthors();

		mv.addObject("Author", Author);
		mv.setViewName("EditBook");

		return mv;
	}
	/**
	 * Handles the HTTP POST request for saving the changes made to a book and returns a ModelAndView object 
	 * that represents the bookListing view.
	 * @param request HttpServletRequest object representing the request
	 * @param response HttpServletResponse object representing the response
	 * @return ModelAndView object representing the bookListing view
	 */
	@PostMapping("/Editbook") // for post method mapping
	public ModelAndView addBook(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();

		String bookcode = request.getParameter("bookCode");
		int bookCode = Integer.parseInt(bookcode);
		String bookname = request.getParameter("bookName");
		String author = request.getParameter("author");
		String addedon = request.getParameter("addedOn");

		Book book = new Book();
		book.setAddedOn(addedon);
		book.setAuthor(author);
		book.setBookCode(bookCode);
		book.setBookName(bookname);

		System.out.println("aryannnn");
		System.out.println(book);
		Book updatedBook = bookService.saveBook(book, "PUT");
		HttpSession session = request.getSession();
		List<Book> books = bookService.retrieveBooks();
		session.setAttribute("books", books);
		mv.setViewName("bookListing");

		return mv;
	}
}
