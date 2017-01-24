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
<% 
	Object o = request.getAttribute("response");
	String display = "";
	
	if(o==null){
		
	}else{
		display = o.toString();
	}
%>

<div id="wrap">
  <div id="regbar">
    <div id="navthing">
      <h1>  AdLocal - A local vendor</h1> 
    </div>
  </div>
</div>
<div class="modal-dialog">
	<div class="SignUp-container">
		<p>User: <%= display %></p>         <p><a href="logout.html">LogOut</a> </p>
		<h2>Create Order</h2><br> 
		
	    <form action="AddOrder" method="POST">
	        Delivery Address
	        <textarea name="address" placeholder="Delivery Address" rows="4" cols=""></textarea>
	        &nbsp
	        Order List
	        <textarea name="order_summary" placeholder="Order List" rows="10" cols=""></textarea>
	        &nbsp
	        Vendor    
			<select name="vendor" id="shopKeepers">
               <option value="shreeharsha">shreeharsha enterprises</option>
               <option value="kumar enterprises">kumar enterprises</option>
            </select> 
            &nbspDelivery Time Window:&nbsp
            
            From
            <select name="FromTime" id="FromTime">
            	<option value="10:00:00">10:00:00</option>
            	<option value="10:30:00">10:30:00</option>
            	<option value="11:00:00">11:00:00</option>
            	<option value="11:30:00">11:30:00</option>
            	<option value="12:00:00">12:00:00</option>
            	<option value="12:30:00">12:30:00</option>
            	<option value="13:00:00">13:00:00</option>
            	<option value="13:30:00">13:30:00</option>
            	<option value="14:00:00">14:00:00</option>
            	<option value="14:30:00">14:30:00</option>
            	<option value="15:00:00">15:00:00</option>
            	<option value="15:30:00">15:30:00</option>
            	<option value="16:00:00">16:00:00</option>
            	<option value="16:30:00">16:30:00</option>
            	<option value="17:00:00">17:00:00</option>
            	<option value="17:30:00">17:30:00</option>
            	<option value="18:00:00">18:00:00</option>
            	<option value="18:30:00">18:30:00</option>
            	<option value="19:00:00">19:00:00</option>
            </select>
            To 
            <select name="ToTime" id="ToTime">
            	<option value="10:00:00">10:00:00</option>
            	<option value="10:30:00">10:30:00</option>
            	<option value="11:00:00">11:00:00</option>
            	<option value="11:30:00">11:30:00</option>
            	<option value="12:00:00">12:00:00</option>
            	<option value="12:30:00">12:30:00</option>
            	<option value="13:00:00">13:00:00</option>
            	<option value="13:30:00">13:30:00</option>
            	<option value="14:00:00">14:00:00</option>
            	<option value="14:30:00">14:30:00</option>
            	<option value="15:00:00">15:00:00</option>
            	<option value="15:30:00">15:30:00</option>
            	<option value="16:00:00">16:00:00</option>
            	<option value="16:30:00">16:30:00</option>
            	<option value="17:00:00">17:00:00</option>
            	<option value="17:30:00">17:30:00</option>
            	<option value="18:00:00">18:00:00</option>
            	<option value="18:30:00">18:30:00</option>
            	<option value="19:00:00">19:00:00</option>
            </select>
			<input type="submit" name="placeOrder" class="login loginmodal-submit" value="Place Order">
		 </form>			
	 </div>
</div>
</body>
</html>