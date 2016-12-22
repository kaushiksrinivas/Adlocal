<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/order.css">
</head>
<body>
<div id="wrap">
  <div id="regbar">
    <div id="navthing">
      <h1>  AdLocal - A local vendor</h1> 
    </div>
  </div>
</div>
<div class="modal-dialog">
	<div class="SignUp-container">
		<h2>Create Order</h2><br>
	    <form action="AddOrder" method="POST">
	        Delivery Address
	        <textarea name="address" placeholder="Delivery Address" rows="4" cols=""></textarea>
	        Order List
	        <textarea name="order_summary" placeholder="Order List" rows="10" cols=""></textarea>
			<select name="vendor" id="shopKeepers">
               <option value="Vendor1">Vendor1</option>
               <option value="Vendor1">Vendor2</option>
            </select> 
			<input type="submit" name="placeOrder" class="login loginmodal-submit" value="Place Order">
		 </form>			
	 </div>
</div>
</body>
</html>