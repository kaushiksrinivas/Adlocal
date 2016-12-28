package com.adlocal.views;

import java.sql.*;
import java.sql.DriverManager;

public class SignUpFunctions {
	String UserName;
	String MobileNumber;
	String UserType;
	String Password;

	public SignUpFunctions(String UserName, String MobileNumber, String UserType, String Password) {
		this.UserName = UserName;
		this.MobileNumber = MobileNumber;
		this.UserType = UserType;
		this.Password = Password;
	}

	public String validate() {
		System.out.println("validation hit!");
		// Form fields validation
		if (!(this.UserName.length() >= 4)) {
			return "UserName Cannot Be Less Than Four Characters";
		} else if (!(this.MobileNumber.length() == 10)) {
			return "Mobile Number Is Invalid";
		} else if (!(this.Password.length() > 0)) {
			return "Password Cannot Be Left Blank";
		}
		boolean flag = false;
		String returnvalue = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("class loading done");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bitnami_reviewboard",
					"root", "reviewboard");
			PreparedStatement ps = con.prepareStatement("select * from adlocal_users where Phone=?");
			ps.setString(1, this.MobileNumber);

			ResultSet rs = ps.executeQuery();
			flag = rs.next();

			if (flag) {

				returnvalue = "User With Mobile Number Entered Already Exists";
			} else {
				returnvalue = "true";
			}

		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("returning flag>" + returnvalue);
		return returnvalue;

	}

	public synchronized boolean update() {
		int rs = 0;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bitnami_reviewboard", "root",
					"reviewboard");
			PreparedStatement ps = con.prepareStatement("insert into adlocal_users values(?,?,?,?)");

			ps.setString(1, this.UserName);
			ps.setString(2, this.MobileNumber);
			ps.setString(3, this.UserType);
			ps.setString(4, this.Password);
			System.out.println("updating db now");
			rs = ps.executeUpdate();

			System.out.println("No of rows affected:" + rs);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rs > 0) {
			return true;
		} else {
			return false;
		}
	}

}
