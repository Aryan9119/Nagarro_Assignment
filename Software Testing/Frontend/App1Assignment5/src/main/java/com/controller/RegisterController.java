package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.entity.User;
import com.service.RegisterService;
/**
 *
 * Controller for handling user registration.
 * @author aryanverma
 *
 */
@Controller
public class RegisterController {
	
	@Autowired
	User user;
	
	@Autowired
	RegisterService registerService;
	/**
	 * Redirects to the registration page.
	 * @return A string representing the path to the registration page.
	 */
	@RequestMapping(value = "/Register", method = RequestMethod.GET)
	public String getRegisterRedirect() {
		return "redirect:register.jsp";
	}
	/**
	 * Adds a new user to the system.
	 * @param request The HTTP servlet request.
	 * @param response The HTTP servlet response.
	 * @return A ModelAndView object representing the view and any model data.
	 */
	@PostMapping("/Register") // for post method mapping
	public ModelAndView addUsers(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();

		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");

		user.setUname(uname);
		user.setPass(pass);
		
		User savedUser = registerService.saveUser(user);

		mv.setViewName("login");

		return mv;
	}


}
