package com.adlocal.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Controllers {
	@RequestMapping(value="/logout")
	public ModelAndView Logout(HttpServletRequest req,HttpServletResponse res,@CookieValue(value="Mobile",defaultValue="invalid_cookie") String mobile){
		
		if(mobile.equals("invalid_cookie")){
			Cookie c = new Cookie("Mobile","invalid_cookie");
			c.setMaxAge(0);
			res.addCookie(c);
			return new ModelAndView("Login","response","Successfully signed out");
		}
		
		Cookie c = new Cookie("Mobile","invalid_cookie");
		c.setMaxAge(0);
		res.addCookie(c);
		return new ModelAndView("Login","response","Successfully signed out");
		
	}
	
	@RequestMapping(value="/Order")
	public ModelAndView OrderPageRedirection(HttpServletRequest req, HttpServletResponse res,@CookieValue(value="Mobile",defaultValue="invalid_cookie")String mobile){
		
		if(mobile.equals("invalid_cookie")){
			Cookie c = new Cookie("Mobile","invalid_cookie");
			c.setMaxAge(0);
			res.addCookie(c);
			return new ModelAndView("Login","response","User Session Has Timed Out! Please Login To Place The Order.");
		}
		
		Cookie c = new Cookie("Mobile",mobile);
		c.setMaxAge(1800);
		res.addCookie(c);
		return new ModelAndView("Order","response",mobile);
	}

}


