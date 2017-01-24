package com.adlocal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adlocal.model.User;
import com.adlocal.model.UserDAO;
import com.adlocal.views.LoginFunctions;

@Controller
public class LoginServlet {
	@RequestMapping(value="/Login")
	public ModelAndView Login(HttpServletRequest req,HttpServletResponse res){
		
		String Mobile = req.getParameter("Mobile");
		String Password = req.getParameter("Password");
		String UserType = req.getParameter("UserType");
		PrintWriter pw = null;
		boolean MobileNoTest = false;
		String DB_password = null;
		String response_message;
//		try{
//			pw  = res.getWriter();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
		
		
		
/*		LoginFunctions obj = new LoginFunctions(Mobile, Password);
//c		MobileNoTest = obj.userNameValidation();
		if(MobileNoTest){
			DB_password = obj.passwordValidation();
			System.out.println(DB_password+":"+Password);
			if(Password.equals(DB_password)){
				System.out.println("Setting cookie:"+Mobile);
				Cookie c = new Cookie("Mobile",Mobile);
				res.addCookie(c);
				response_message = Mobile;
				return new ModelAndView("LoginHome","response",response_message);
//				req.setAttribute("User",Mobile);
//				RequestDispatcher rd2 = req.getRequestDispatcher("/LoginHome.jsp");
//				try{
//					rd2.forward(req, res);
//				}
//				catch(IOException e){
//					e.printStackTrace();
//				}
//				catch(ServletException e){
//					e.printStackTrace();
//				}				
				
			}
			else{
				response_message = "Invalid Password";
//				pw.write("Invalid Password");
//				RequestDispatcher rd1 = req.getRequestDispatcher("/Login.html");
//				try{
//					rd1.include(req, res);
//				}
//				catch(IOException e){
//					e.printStackTrace();
//				}
//				catch(ServletException e){
//					e.printStackTrace();
//				}
				return new ModelAndView("Login","response",response_message);
			}
		}
		else{
			response_message = "Mobile Number is Not Registered!,Please SignUp";
//			pw.write("Mobile Number Is Not Registered!,Please Complete SignUp!");
//			RequestDispatcher rd = req.getRequestDispatcher("/SignUp.html");
//			try{
//				rd.include(req, res);
//			}
//			catch(IOException e){
//				e.printStackTrace();
//			}
//			catch(ServletException e){
//				e.printStackTrace();
//			}
			return new ModelAndView("SignUp","response",response_message);
		}
		*/
		
		//get the bean factory to get the user dao object.
		Resource resource = new ClassPathResource("ApplicationContext.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		
		//get user DAO objec and default user object for the current user login.
		UserDAO userdao = (UserDAO)factory.getBean("UserDao");
		User user = (User)factory.getBean("user");
		
		//set the Mobile and Password to the user object got from the login page.
		user.setMobile(Mobile);
		user.setPassword(Password);
		user.setUserType(UserType);
		
		HashMap<String,String> result = new HashMap<String,String>();
		//get the returned hashmap object after authentication done for login.
		result = userdao.AuthenticateUser(user);
		String flag = null;
		String message = null;
		
		for(Map.Entry map: result.entrySet()){
			if(map.getKey().equals("flag")){
				flag = (String)map.getValue();
			}
			else if(map.getKey().equals("message")){
				message = (String)map.getValue();
			}
		}
		
		
		//perform the corresponding redirection based on the result obtained.
		//success case
		if(flag.contains("true")){
			String viewName = null;
			if(UserType.equals("Consumer")){
				viewName = "LoginHome";
			}
			else{
				viewName = "VendorHome";
			}
			System.out.println("Setting cookie:"+Mobile);
			Cookie c = new Cookie("Mobile",Mobile);
			c.setMaxAge(1800);
			res.addCookie(c);
			response_message = Mobile;
			return new ModelAndView(viewName,"response",response_message);
		}
		//failure cases
		else{
			
			//Wrong password case
			if(message.contains("Invalid Password!")){
				return new ModelAndView("Login","response",message);
			}
			//user not registered case
			else{
				return new ModelAndView("SignUp","response",message);
			}
		}
				
	}
}
