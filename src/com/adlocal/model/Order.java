package com.adlocal.model;

public class Order {
	private String DeliveryAddress;
	private String OrderSummary;
	private String UserPhone;
	private String VendorName;
	
	
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
