package com.adlocal.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adlocal.views.OrderFunctions;

@Controller
public class OrderServlet {
	@RequestMapping(value="/AddOrder")
	public ModelAndView AddAnOrder(HttpServletRequest req,HttpServletResponse res,@CookieValue("Mobile") String mobile){
		synchronized (this){
			
			String address = req.getParameter("address");
			String order_summary = req.getParameter("order_summary");
			String vendor = req.getParameter("vendor");
			
			String user_name = null;
//			Cookie[] c = req.getCookies();
//			
//			String mobile = c[0].getValue();
			String msg = null;
			System.out.println("In Order servlet");
			System.out.println("mobile Cookie value:"+mobile);
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bitnami_reviewboard","root","reviewboard");
				java.sql.PreparedStatement ps = con.prepareStatement("select User_Name from adlocal_users where Phone=?");
				ps.setString(1, mobile);
				java.sql.ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					user_name = rs.getString("User_Name");
				}
			}
			catch(Exception e){
				System.out.println("OrderServletException");
				e.printStackTrace();
			}
			
			OrderFunctions of = new OrderFunctions(user_name, mobile, address, order_summary, vendor);
			boolean flag = of.AddOrder();
			
			if(flag){
				Cookie cook = new Cookie("Mobile", mobile);
				res.addCookie(cook);
				msg = "Order Placed Successfully!";
//				req.setAttribute("DisplayString", msg);
//				RequestDispatcher rd1 = req.getRequestDispatcher("/OrderProfile.jsp");
//				try {
//					rd1.forward(req, res);
//				} catch (Exception e){
//					System.out.println("OrderServlet_Success Exception");
//					e.printStackTrace();
//				}
				return new ModelAndView ("OrderProfile","response",msg);
			}
			else{
				msg = "Order Placing Failed! Try Again";
//				req.setAttribute("DisplayString", msg);
//				RequestDispatcher rd2 = req.getRequestDispatcher("/OrderProfile.jsp");
//				try {
//					rd2.forward(req, res);
//				} catch (Exception e){
//					System.out.println("OrderServlet_Failed Exception");
//					e.printStackTrace();
//				}
				return new ModelAndView("OrderProfile","response",msg);
				
			}
		}
		
	}
}