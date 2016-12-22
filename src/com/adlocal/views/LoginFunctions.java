package com.adlocal.views;

import java.sql.DriverManager;

import com.mysql.jdbc.PreparedStatement;

public class LoginFunctions {
	String Mobile;
	String Password;
	
	public LoginFunctions(String Mobile,String Password){
		this.Mobile = Mobile;
		this.Password = Password;
	}
	
	public boolean userNameValidation(){
		boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bitnami_reviewboard","root","reviewboard");
			java.sql.PreparedStatement ps = con.prepareStatement("select * from adlocal_users where Phone=?");
			ps.setString(1,this.Mobile);
			java.sql.ResultSet rs = ps.executeQuery();
			
			flag = rs.next();
			if(flag){
				flag = true;
			}
			else{
				flag = false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public String passwordValidation(){
		String password = "invalid";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bitnami_reviewboard","root","reviewboard");
			java.sql.PreparedStatement ps = con.prepareStatement("select * from adlocal_users where Phone=?");
			ps.setString(1, Mobile);
			java.sql.ResultSet rs = ps.executeQuery();
			while (rs.next()){
				password = rs.getString("Passcode");
				System.out.println("DB:"+password);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return password;
	}

}
