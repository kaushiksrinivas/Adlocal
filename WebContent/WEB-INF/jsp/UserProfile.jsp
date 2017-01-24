<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String UserName = null;
	String MobileNumber = null;
	String UserType = null;
	String OrderCount = null;
	
	Object o = request.getAttribute("UserName");

	if(o == null){
		
	}else{
		UserName = o.toString();
	}
	
	o = request.getAttribute("MobileNumber");
	if(o == null){
		
	}else{
		MobileNumber = o.toString();
	}
	
	o = request.getAttribute("UserType");
	if(o == null){
		
	}else{
		UserType = o.toString();
	}
	
	o = request.getAttribute("OrderCount");
	if(o == null){
		
	}else{
		OrderCount = o.toString();
	}
	

%>

<h1> User Profile Display </h1> <br/>

<a href="logout">Logout</a> <br/>

<h2>User Name :</h2> <%= UserName%><br/> 
<form action="ChangeUserName" method="POST">
	<input type="text" name="UserName" placeholder="New User Name">
	<input type="hidden" name="UserType" value=<%= UserType %>>
	<input type="submit" name="ChangeUserName" class="ChangeUserName" value="ChangeUserName">
</form>	

<h2>Mobile Number :</h2> <%= MobileNumber %><br/>
<form action="ChangeMobileNumber" method="POST">
	<input type="text" name="MobileNumber" placeholder="New Mobile Number">
	<input type="hidden" name="UserType" value=<%= UserType %>>
	<input type="submit" name="ChangeMobileNumber" class="ChangeMobileNumber" value="ChangeMobileNumber">
</form>	

<h2>User Type : </h2><%= UserType %> <br/>	

<h2>Total Orders Placed/Received : </h2> <%= OrderCount %>

<h2> Change Password </h2>
<form action="ChangePassword" method="POST">
	<input type="password" name="NewPassword" placeholder="Enter New Password">
	<input type="text" name="NewPasswordDup" placeholder="Re enter the Password">
	<input type="hidden" name="UserType" value=<%= UserType %>>
	<input type="submit" name="ChangePassword" class="ChangePassword" value="ChangePassword">
</form>



</body>
</html>