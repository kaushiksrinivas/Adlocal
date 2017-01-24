<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
  <title>Welcome to Adlocal</title>
  <link rel="stylesheet" href="CSS/loginprofile.css">
</head>
<body>
  <%
  	Object o = request.getAttribute("response");
    String display = null;
  	if(o == null){
  		
  	}
  	else{
  		display = o.toString();
  	}
  %>
  <div id="wrap">
  <div id="regbar">
    <div id="navthing">
    <div id="home-container">
      <h1> Vendor Home Page </h1> 
      <p> Welcome Vendor: <%=display %>
      <a href = "logout.html">Logout </a>
      <h2 style = "align:center; vertical-align:middle;"><a href="ViewProfile">Profile</a>
      </div>
      </div>
    </div>
    </div>
</body>
</html>