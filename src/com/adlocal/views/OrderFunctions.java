package com.adlocal.views;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class OrderFunctions {
	String UserName;
	String Mobile;
	String Address;
	String OrderSummary;
	int OrderID = 0;
	String Vendor;
	String VendorId = "junk";
	
	public OrderFunctions(String UserName,String Mobile,String Address,String OrderSummary,String Vendor){
		this.UserName = UserName;
		this.Mobile = Mobile;
		this.Address = Address;
		this.OrderSummary = OrderSummary;
		this.Vendor = Vendor;
		this.OrderID = this.OrderID + 1;
	}
	
	public boolean AddOrder(){
		boolean flag = false;
		try {
			FileOutputStream fout = new FileOutputStream("D:/Users/kaushik.srinivas/Desktop/BackUp/Order.txt",true);
			fout.write("ORDER>".getBytes());
			fout.write(this.OrderID);
			fout.write(":".getBytes());
			fout.write("\n".getBytes());
			fout.write("Mobile:".getBytes());
			fout.write(this.Mobile.getBytes());
			fout.write("\n".getBytes());
			fout.write("UserName:".getBytes());
			fout.write(this.UserName.getBytes());
			fout.write("\n".getBytes());
			fout.write("Address:".getBytes());
			fout.write(this.Address.getBytes());
			fout.write("\n".getBytes());
			fout.write("OrderSummary:".getBytes());
			fout.write(this.OrderSummary.getBytes());
			fout.write("\n".getBytes());
			fout.write("-----------------------------------------".getBytes());
			fout.flush();
			fout.close();
			
			flag=true;			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("OrderFunctionFIleNotFoundException");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("OrderFunctionIOException");
			e.printStackTrace();
		}
		int rs = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bitnami_reviewboard","root","reviewboard");
			java.sql.PreparedStatement ps = con.prepareStatement("insert into orders values(?,?,?,?,?,?,?)");
			ps.setInt(1,this.OrderID);
			ps.setString(2, this.UserName);
			ps.setString(3, this.Mobile);
			ps.setString(4,this.Address);
			ps.setString(5, this.OrderSummary);
			ps.setString(6, this.Vendor);
			ps.setString(7,this.VendorId);
			System.out.println("Updating DataBase for Order Addition now");
			rs = ps.executeUpdate();
					
		}
		catch(Exception e){
			System.out.println("OrderFunctions JDBC Exception");
			e.printStackTrace();
		}
		if(rs>0){
			flag = true;
		}
		else{
			flag = false;
		}
		return flag;
	}
}
