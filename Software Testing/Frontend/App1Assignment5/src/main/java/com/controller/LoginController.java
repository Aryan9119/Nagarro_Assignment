package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Book;
import com.service.BookService;
import com.service.LoginService;
/**
 * This class is responsible for handling the login functionality of the application.
 * @author aryanverma
 *
 */
@Controller
public class LoginController {

	@Autowired
	BookService displayBooks;
	@Autowired
	LoginService loginService;
	
	/**
	 * This method is used to redirect the user to the login page.
	 * @return A string that represents the redirect URL for the login page.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getLoginRedirect() {
		return "redirect:login.jsp";
	}
	/**

	This method is used to check the user's credentials and log them in.

	@param request An HttpServletRequest object that contains the request the client has made to the server.

	@param response An HttpServletResponse object that contains the response the server has sent to the client.

	@return A ModelAndView object that contains the view to display after the user has been logged in.
	*/
	@PostMapping("/Login") // for post method mapping
	public ModelAndView checkUser(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();

		// getting username and password
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");

		// function to check user exist or not

		if (loginService.checkLogin(uname, pass)) {
			System.out.println(uname);
			System.out.println(pass);

			HttpSession session = request.getSession();
			session.setAttribute("username", uname);
			mv.addObject("username", uname);
			List<Book> books = displayBooks.retrieveBooks();
			session.setAttribute("books", books);
			mv.setViewName("bookListing");
			return mv;

		} else {
			mv.setViewName("login");
		}
		return mv;
	}

}
