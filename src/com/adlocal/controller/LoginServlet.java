package com.adlocal.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adlocal.views.LoginFunctions;

@Controller
public class LoginServlet {
	@RequestMapping(value="/Login")
	public ModelAndView Login(HttpServletRequest req,HttpServletResponse res){
		
		String Mobile = req.getParameter("Mobile");
		String Password = req.getParameter("Password");
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
		
		LoginFunctions obj = new LoginFunctions(Mobile, Password);
		MobileNoTest = obj.userNameValidation();
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
	}
}
