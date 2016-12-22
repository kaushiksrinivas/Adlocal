package com.adlocal.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.adlocal.views.SignUpFunctions;

@Controller
public class SignUpServlet {
	@RequestMapping(value = "/SignUp")
	public ModelAndView SignUp(HttpServletRequest req, HttpServletResponse res) {
		synchronized (this) {
			System.out.println("SignUp servlet is hit!");
			String UserName = req.getParameter("UserName");
			System.out.println("servlet contents username:" + UserName);
			String MobileNumber = req.getParameter("MobileNumber");
			String UserType = req.getParameter("UserType");
			String Password = req.getParameter("Password");
			res.setContentType("text/html");
//			PrintWriter pw = null;
//			try {
//
//				pw = res.getWriter();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

			SignUpFunctions obj = new SignUpFunctions(UserName, MobileNumber, UserType, Password);

			String validate = obj.validate();
			System.out.println("Validate result in controller: "+validate);
			String message;
			if (validate.equals("true")) {

				boolean jdbcUpdateResult = obj.update();
				System.out.println("JDBC Update result: "+jdbcUpdateResult);
//				RequestDispatcher rd = null;
				if (jdbcUpdateResult) {
//					pw.write("Successfully registered!");
//					rd = req.getRequestDispatcher("/Login.html");
//					try {
//						rd.include(req, res);
//					} catch (IOException e) {
//						e.printStackTrace();
//					} catch (ServletException e) {
//						e.printStackTrace();
//					}
					message = "Successfully Registered!";
					System.out.println(message);
					return new ModelAndView("Login","message",message);
				} else {
//					pw.write("Sign Up Failed");
//					rd = req.getRequestDispatcher("/SignUp.html");
//					try {
//						rd.include(req, res);
//					} catch (IOException e) {
//						e.printStackTrace();
//					} catch (ServletException e) {
//						e.printStackTrace();
//					}
					message = "Sign Up Failed!";
					System.out.println(message);
					return new ModelAndView("SignUp","message",message);
					
				}

			} else {
//				pw.write(validate);
//				RequestDispatcher rd = req.getRequestDispatcher("/SignUp.html");
//				try {
//					rd.include(req, res);
//				} catch (IOException e) {
//					e.printStackTrace();
//				} catch (ServletException e) {
//					e.printStackTrace();
//				}
				message = validate;
				System.out.println(message);
				return new ModelAndView("SignUp","message",message);
			}

		}
	}

}
