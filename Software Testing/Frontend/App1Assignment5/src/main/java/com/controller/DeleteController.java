package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Book;
import com.service.BookService;
/**
 * The DeleteController class is responsible for handling HTTP requests related to deleting a book.
 * @author aryanverma
 *
 */
@Controller
public class DeleteController {

	@Autowired
	BookService bookService;
	
	/**
	 * This method handles the HTTP POST request for deleting a book. It retrieves the book code from the request,
	 * deletes the book with the corresponding book code from the database using the bookService, and then sets the
	 * list of books in the session and redirects the user to the book listing page.
	 *
	 * @param request the HttpServletRequest object representing the request
	 * @param response the HttpServletResponse object representing the response
	 * @return the ModelAndView object representing the view and model data
	 */
	@PostMapping("/Delete") // for post method mapping
	public ModelAndView deleteBook(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();

		String bookCode = request.getParameter("bookCode");
		int bookcode = Integer.parseInt(bookCode);
		bookService.deleteBook(bookcode);
		HttpSession session = request.getSession();
		List<Book> books = bookService.retrieveBooks();
		session.setAttribute("books", books);
		mv.setViewName("bookListing");
		return mv;
	}

}
