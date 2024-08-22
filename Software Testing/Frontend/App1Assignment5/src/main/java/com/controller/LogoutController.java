package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * This class is responsible for handling the logout functionality of the application.
 * @author aryanverma
 *
 */
@Controller
public class LogoutController {
	/**
	 * POST method to logout the user and invalidate the session
	 * @param request the HTTP servlet request
	 * @param response the HTTP servlet response
	 * @return ModelAndView object containing the view name for the login page
	 */
	@PostMapping("/Logout")
	public ModelAndView checkUser(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.invalidate();
		mv.setViewName("login");

		return mv;
	}

}
