<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>Welcome to Adlocal</title>
  <link rel="stylesheet" href="CSS/loginprofile.css">
</head>
<body>
<div id="wrap">
  <div id="regbar">
    <div id="navthing">
    <div id="home-container">
      <h1>  AdLocal - A local vendor</h1> 
      <%Object o=request.getAttribute("response");
      	String display ="";
      	if(o==null){
      		
      	}
      	else{
      		 display = o.toString();
      	}
      %>
      <h2 id="DisplayString"> <%=display %></h2>
       <h2 style = "align:center; vertical-align:middle;"><a href="LoginHome.jsp">Login Home</a> | <a href="Order.jsp">New order</a></h2>
      </div>
      </div>
    </div>
    </div>
</body>
</html>