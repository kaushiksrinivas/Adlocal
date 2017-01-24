package com.adlocal.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet; 

public class UserDAO {
	
	private JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public HashMap<String,String> validate(User u){
		//Result HashMap <status(false/true),error/success message> return collection.
		HashMap<String,String> result = new HashMap<String,String>();
		
		//UserName Length Needs to be atleast four chars.
		if(!(u.UserName.length()>=4)){
			result.put("flag", "false");
			result.put("message", "UserName Cannot be less than four characters!");
			return result;
		}
		//Mobile number length should be exactly ten chars.
		if(!(u.Mobile.length()== 10)){
			result.put("flag", "false");
			result.put("message", "Mobile number is invalid!");
			return result;
		}
		//Password length validation for security purposes.
		if(!(u.Password.length()>=4)){
			result.put("flag", "false");
			result.put("message", "Password cannot be less than four characters!");
			return result;
		}
		
		result.put("flag", "true");
		result.put("message", "Validation Done");
		return result;
	}
	
	public HashMap<String,String> UserExistsCheck(User u){
		//create the hashmap which holds the result.
		HashMap<String,String> result = new HashMap<String,String>();
		// refer to the correct db tables adlocal_users/adlocal_vendors based on the user type.
		String db_name = null;
		if(u.UserType.equals("Consumer")){
			db_name = "adlocal_users";
		}
		else{
			db_name = "adlocal_vendors";
		}
		//check for the unique mobile number validation from the database.
		String query = "SELECT COUNT(*) FROM "+db_name+" WHERE Phone="+u.getMobile();
		int count = this.template.queryForInt(query);
		
		//If an entry found in the database for mobile number, return with error message.
		if(count>0){
			result.put("flag", "false");
			result.put("message", "mobile number already registered, Please login!");
			return result;
		}
		
		//If mobile number validation is successfull, perform user name uniqueness validation.
		query = "SELECT COUNT(*) FROM "+db_name+" WHERE User_Name='"+u.getUserName()+"'";
		count = this.template.queryForInt(query);
		
		//If an entry found in the database for user name, return with error message.
		if(count > 0){
			result.put("flag", "false");
			result.put("message", "user name already registered, please choose a different user name!");
			return result;
		}
		
		result.put("flag", "true");
		result.put("message", "No user exists!");
		return result;
		
	}
	
	public HashMap<String,String> AddUser(User u){
		//step1 : validate the user for the fields correctness.
		//variables to check the success of each step.
		String flag = null;
		String message = null;
		String query = null;
		
		HashMap<String,String> result = this.validate(u);
		for(Map.Entry map : result.entrySet()){
				if(map.getKey().equals("flag")){
					flag = (String)map.getValue();
				}
				else if(map.getKey().equals("message")){
					message = (String)map.getValue();
				}
		}
		//de reference the HashMap object for future usage.
		result.clear();
		if(flag.equals("false")){
			result.put("flag", "false");
			result.put("message", message);
			return result;
		}
		
		//step2 : check if the user with same username/phone number already exists.
		
		result = this.UserExistsCheck(u);
		for(Map.Entry map : result.entrySet()){
			if(map.getKey().equals("flag")){
				flag = (String) map.getValue();
			}
			else if(map.getKey().equals("message")){
				message = (String) map.getValue();
			}
		}
		result.clear();
		if(flag.equals("false")){
			result.put("flag", "false");
			result.put("message", message);
			return result;
		}
		
		//step3 : add the user and update the data base.
		// select the table as per the user type.
		String table_name = null;
		if(u.UserType.equals("Consumer")){
			table_name = "adlocal_users";
		}
		else{
			table_name = "adlocal_vendors";
		}
		
		query = "insert into "+table_name+" (User_Name,Phone,User_Type,Passcode,orderCount) values(?,?,?,?,?)";
		int status = this.template.update(query,u.UserName,u.Mobile,u.UserType,u.Password,u.OrderCount);
		
		if(status > 0){
			result.put("flag", "true");
			result.put("message", "Registered Successfully!");
			return result;
		}
		else{
			result.put("flag", "false");
			result.put("message", "Registration Failed! Please try again");
			return result;
		}
	}
	
	public HashMap<String,String> AuthenticateUser(User u){
		// select the table based on the user type being authenticated.
		String table_name = null;
		if(u.UserType.equals("Consumer")){
			table_name = "adlocal_users";
		}
		else{
			table_name = "adlocal_vendors";
		}
		
		String query = "SELECT COUNT(*) FROM "+table_name+" WHERE Phone = "+u.getMobile();
		String passcode = null;
		HashMap<String,String> result = new HashMap<String,String>();
		int count = this.template.queryForInt(query);
	
		if(count<=0){
			result.put("flag", "false");
			result.put("message", "User Does Not Exist !, Sign Up First Please");
			return result;
		}
		
		query = "SELECT Passcode FROM "+table_name+" WHERE Phone="+u.getMobile();
				
		SqlRowSet rs = this.template.queryForRowSet(query);
		while (rs.next()){
			passcode = rs.getString("Passcode");
		}
		
		if(u.Password.equals(passcode)){
			result.put("flag", "true");
			result.put("message", "Login Success!");
			return result;
		}
		else{
			result.put("flag", "false");
			result.put("message", "Invalid Password!");
			return result;
		}
		
	}
	

}
