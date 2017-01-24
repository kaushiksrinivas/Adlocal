package com.adlocal.controller;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adlocal.model.User;
import com.adlocal.model.UserDAO;

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
	
	@RequestMapping(value="/ViewProfile")
	public ModelAndView ProfileViewer(HttpServletRequest req,HttpServletResponse res,@CookieValue(value="Mobile",defaultValue="invalid_cookie")String mobile){
		
		if(mobile.equals("invalid_cookie")){
			Cookie c = new Cookie("Mobile","invalid_cookie");
			c.setMaxAge(0);
			res.addCookie(c);
			return new ModelAndView("Login","response","User Session Has Timed Out! Please Login To Place The Order.");
		}
		
		
		//get the bean factory to get the user dao object.
		Resource resource = new ClassPathResource("ApplicationContext.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
				
		//get user DAO objec and default user object for the current user login.
		UserDAO userdao = (UserDAO)factory.getBean("UserDao");
		User user = (User)factory.getBean("user");
		
		user.setMobile(mobile);
		user = userdao.GenerateProfile(user);
		
		ModelAndView mv = new ModelAndView("UserProfile");
		mv.addObject("UserName", user.getUserName());
		mv.addObject("MobileNumber", user.getMobile());
		mv.addObject("UserType",user.getUserType());
		mv.addObject("OrderCount", user.getOrderCount());
		
		// reset the cookie timeout to 30 minutes again.
		Cookie c = new Cookie("Mobile",mobile);
		c.setMaxAge(1800);
		res.addCookie(c);
		
		//return the ModelAndView Object with profile attributes set of a user.
		return mv;
	}
	
	@RequestMapping(value="/ChangeUserName")
	public ModelAndView ChangeUserName(HttpServletRequest req, HttpServletResponse res,@CookieValue(value="Mobile",defaultValue="invalid_cookie")String mobile){
		
		if(mobile.equals("invalid_cookie")){
			Cookie c = new Cookie("Mobile","invalid_cookie");
			c.setMaxAge(0);
			res.addCookie(c);
			return new ModelAndView("Login","response","User Session Has Timed Out! Please Login to Update the profile.");
		}
		
		Resource resource = new ClassPathResource("ApplicationContext.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
				
		//get user DAO objec and default user object for the current user login.
		UserDAO userdao = (UserDAO)factory.getBean("UserDao");
		User user = (User)factory.getBean("user");
		
		user.setMobile(mobile);
		
		String NewUserName = req.getParameter("UserName");
		String UserType = req.getParameter("UserType");
		user.setUserName(NewUserName);
		user.setUserType(UserType);
		
		HashMap<String,String> result = userdao.ChangeUserName(user);
		String flag = result.get("flag");
		String message = result.get("message");
		ModelAndView mv = null;
		//reset the cookie timer back to 30 minutes.
		Cookie c = new Cookie("Mobile",mobile);
		c.setMaxAge(1800);
		res.addCookie(c);
		//check if the user name change is done or failed and proceed further.
	
		mv = new ModelAndView("LoginHome");
		mv.addObject("failures", message);
		mv.addObject("response", mobile);
		return mv;			
			
		
	}
	
	@RequestMapping(value="/ChangeMobileNumber")
	public ModelAndView ChangeMobileNumber(HttpServletRequest req, HttpServletResponse res,@CookieValue(value="Mobile",defaultValue="invalid_cookie")String mobile){
		// check if the user is still in the logged in session.
		if(mobile.equals("invalid_cookie")){
			Cookie c = new Cookie("Mobile","invalid_cookie");
			c.setMaxAge(0);
			res.addCookie(c);
			return new ModelAndView("Login","response","User Session Has Timed Out! Please Login to Update the profile.");
		}
		
		// if the user is in session continue.
		
		
		Resource resource = new ClassPathResource("ApplicationContext.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
				
		//get user DAO objec and default user object for the current user login.
		UserDAO userdao = (UserDAO)factory.getBean("UserDao");
		User user = (User)factory.getBean("user");
		
		user.setMobile(req.getParameter("MobileNumber"));
		user.setUserType(req.getParameter("UserType"));
		
		String oldMobileNumber = mobile;
		
		HashMap<String,String> result = userdao.ChangeMobile(user,oldMobileNumber);
		String flag = result.get("flag");
		String message = result.get("message");
		ModelAndView mv = null;
		//reset the cookie timer back to 30 minutes.
		//check if the user name change is done or failed and proceed further.	
		if(flag.contains("false")){
			Cookie c = new Cookie("Mobile",oldMobileNumber);
			c.setMaxAge(1800);
			res.addCookie(c);
			mv = new ModelAndView("LoginHome");
			mv.addObject("failures", message);
			mv.addObject("response", oldMobileNumber);
			return mv;
		}else{
			Cookie c = new Cookie("Mobile",user.getMobile());
			c.setMaxAge(1800);
			res.addCookie(c);
			mv = new ModelAndView("LoginHome");
			mv.addObject("failures", message);
			mv.addObject("response", user.getMobile());
			return mv;
		}		
		
	}
	
}


