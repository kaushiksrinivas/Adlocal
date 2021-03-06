package com.adlocal.model;

import java.util.HashMap;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class OrderDAO {
	
	private JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public boolean AddOrder(Order obj){
		String table_name = "adlocal_users";
		String orderId = null;
		String query = "SELECT UserId,orderCount,User_Name FROM "+table_name+" WHERE Phone="+obj.getUserPhone();
		SqlRowSet rs = this.template.queryForRowSet(query);
		
		//store the user id and order count from adlocal_users table.
		int uid = 0;
		int orderCount = 0;
		String UserName = null;
		int vendorOrdersCount = 0;
		
		while(rs.next()){
			uid = rs.getInt("UserId");
			orderCount = rs.getInt("orderCount");
			UserName = rs.getString("User_Name");
		}
		orderCount+=1;
		orderId = UserName.substring(0, 4)+orderCount;
		
		table_name = "adlocal_vendors";
		query = "SELECT VendorId,orderCount FROM "+table_name+" WHERE User_Name='"+obj.getVendorName()+"'";
		
		rs = this.template.queryForRowSet(query);
		
		//store the vendor id from the adlocal_vendors table.
		int vid = 0;
		while(rs.next()){
			vid = rs.getInt("VendorId");
			vendorOrdersCount = rs.getInt("orderCount");
		}
		vendorOrdersCount+= 1;
		
		table_name = "adlocal_orders";
		query = "insert into "+table_name+" values(?,?,?,?,?,?,?,?,?)";
		int status = this.template.update(query, orderId ,obj.getDeliveryAddress(),obj.getOrderSummary(),obj.getTime(),obj.getDeliveryWindowFromTime(),obj.getDeliveryWindowToTime(),obj.getStatus(), uid, vid);
		
		if(status > 0){
			boolean checkflag = true;
			query = "UPDATE adlocal_users SET orderCount = ? WHERE Phone = ?";
			int update_result = this.template.update(query,orderCount,obj.getUserPhone());
			if(!(update_result>0)){
				checkflag = false;
			}
			
			query ="UPDATE adlocal_vendors SET orderCount = ? WHERE VendorId = ?";
			update_result = this.template.update(query,vendorOrdersCount,vid);
			if(!(update_result>0)){
				checkflag = false;
			}
			
			return true;
		}
		else{
			return false;
		}
		
	
	}
	
	public HashMap<String,String> GetOrderData(String OrderId){
		HashMap<String,String> map = new HashMap<String,String> ();
		int vendorId = 0;
		String query = "SELECT DeliveryAddress,OrderSummary,Status,vendorid FROM adlocal_orders WHERE OrderId = '"+OrderId+"'";
		SqlRowSet rs = this.template.queryForRowSet(query);
		
		while(rs.next()){
			map.put("DeliveryAddress",rs.getString("DeliveryAddress") );
			map.put("OrderSummary",rs.getString("OrderSummary") );
			map.put("Status", rs.getString("Status"));
			vendorId = rs.getInt("vendorid");
		}
		
		query = "SELECT User_Name FROM adlocal_vendors WHERE VendorId = "+vendorId;
		rs = this.template.queryForRowSet(query);
		while(rs.next()){
			map.put("Vendor", rs.getString("User_Name"));
		}
		
		map.put("OrderId",OrderId);
		return map;
		
		
		
	}
	
	
}
