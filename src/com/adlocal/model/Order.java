package com.adlocal.model;

import java.sql.Timestamp;

public class Order {
	private String DeliveryAddress;
	private String OrderSummary;
	private String UserPhone;
	private String VendorName;
	private Timestamp time;	
	private String DeliveryWindowFromTime;
	private String DeliveryWindowToTime;
	private String Status;
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getDeliveryWindowFromTime() {
		return DeliveryWindowFromTime;
	}
	public void setDeliveryWindowFromTime(String deliveryWindowFromTime) {
		DeliveryWindowFromTime = deliveryWindowFromTime;
	}
	public String getDeliveryWindowToTime() {
		return DeliveryWindowToTime;
	}
	public void setDeliveryWindowToTime(String deliveryWindowToTime) {
		DeliveryWindowToTime = deliveryWindowToTime;
	}

	
	
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Order(){
		
	}
	public String getUserPhone() {
		return UserPhone;
	}

	public void setUserPhone(String userPhone) {
		UserPhone = userPhone;
	}
	
	public String getDeliveryAddress() {
		return DeliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		DeliveryAddress = deliveryAddress;
	}
	public String getOrderSummary() {
		return OrderSummary;
	}
	public void setOrderSummary(String orderSummary) {
		OrderSummary = orderSummary;
	}
	public String getVendorName() {
		return VendorName;
	}
	public void setVendorName(String vendorName) {
		VendorName = vendorName;
	}
	
}
