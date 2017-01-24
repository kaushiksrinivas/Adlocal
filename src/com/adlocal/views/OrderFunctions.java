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
	String OrderID = null;
	String Vendor;
	String VendorId = "junk";
	int oid = 0;
	
	public OrderFunctions(String UserName,String Mobile,String Address,String OrderSummary,String Vendor){
		this.UserName = UserName;
		this.Mobile = Mobile;
		this.Address = Address;
		this.OrderSummary = OrderSummary;
		this.Vendor = Vendor;
		//this.OrderID = this.OrderID + 1;
		
		this.OrderID = UserName.substring(0,4);
		
		
		
		
	}
	
	public boolean AddOrder(){
		boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bitnami_reviewboard","root","reviewboard");
			java.sql.PreparedStatement ps = con.prepareStatement("select * from adlocal_users where Phone = ?");
			ps.setString(1, this.Mobile);
			java.sql.ResultSet rs = ps.executeQuery();
			while(rs.next()){
				this.oid = rs.getInt("orderCount");
				this.oid = this.oid + 1;
			}
		}
		catch(Exception e){
			System.out.println("OrderFunctions Exception");
		}
		
		this.OrderID = this.OrderID + this.oid;
		try {
			FileOutputStream fout = new FileOutputStream("D:/Users/kaushik.srinivas/Desktop/BackUp/Order.txt",true);
			fout.write("ORDER ID >".getBytes());
			fout.write(this.OrderID.getBytes());
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
			fout.write("\n".getBytes());
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
			ps.setString(1,this.OrderID);
			ps.setString(2, this.UserName);
			ps.setString(3, this.Mobile);
			ps.setString(4,this.Address);
			ps.setString(5, this.OrderSummary);
			ps.setString(6, this.Vendor);
			ps.setString(7,this.VendorId);
			System.out.println("Updating DataBase for Order Addition now");
			rs = ps.executeUpdate();
			ps = con.prepareStatement("update adlocal_users set orderCount = ? where Phone = ?");
			ps.setInt(1, this.oid);
			ps.setString(2, this.Mobile);
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
