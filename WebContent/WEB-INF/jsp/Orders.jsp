<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	// Jsp code to get the object being received.
	String OrderId = "";
	String OrderSummary = "";
	String DeliveryAddress = "";
	String Vendor = "";
	String Status = "";
	
	Object o1 = request.getAttribute("OrderId");
	if(o1 == null){
		
	}else{
		OrderId = o1.toString();
	}
	
	o1 = request.getAttribute("OrderSummary");
	if(o1 == null){
		
	}else{
		OrderSummary = o1.toString();
	}
	
	o1 = request.getAttribute("DeliveryAddress");
	if(o1 == null){
		
	}else{
		DeliveryAddress = o1.toString();
	}
	
	o1 = request.getAttribute("Status");
	if(o1 == null){
		
	}else{
		Status = o1.toString();
	}
	
	o1  = request.getAttribute("Vendor");
	if(o1 == null){
		
	}else{
		Vendor = o1.toString();
	}

%>

<%
	//Jsp code to update the drop downs showing the orders placed by the user logged in.
	Object obj = request.getAttribute("response");
	String mobile = "";
	if(obj == null){
		
	}else{
		mobile = obj.toString();
	}
	
	//Get the uid of the user from adlocal_users table
	String table_name = "adlocal_users";
	ResultSet rs = null;
	Connection con = null;
	PreparedStatement ps = null;
	int uid = 0;
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bitnami_reviewboard", "root",	"reviewboard");
			ps = con.prepareStatement("SELECT UserId FROM "+table_name+" WHERE Phone = '"+mobile+"'");
			rs = ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	while(rs.next()){
		uid = rs.getInt("UserId");
	}
	
	//Get the orders placed by the logged in user from the adlocal_orders table.
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bitnami_reviewboard", "root",	"reviewboard");
			ps = con.prepareStatement("SELECT * FROM adlocal_orders WHERE userid = "+uid);
			rs = ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
%>	

      <h1>  AdLocal - A local vendor</h1> <br/>        
      <p>User: <%= mobile %> </p> &nbsp  <p><a href="logout">LogOut</a> </p> <br/>
		
		<label> Select the Order ID &nbsp</label> <br/>
		<form action="OrderOperation" method="POST" style="text-align:center">
		<h2>
		<select name="OrderId" id="OrderId">
			<%
				while (rs.next()) {
			%>
			
			<option value=<%=rs.getString("OrderId")%>><%=rs.getString("OrderId")%></option>
			
			<%
				}
			%>
		</select>
		<br>
		<br>
		<input type="radio" name="operation" value="Display"> Show Details <br>
  		<input type="radio" name="operation" value="Delete"> Cancel Order <br>
  		<br>
		<input type="submit" value="Lets go">
		</h2>
		</form>
	
	
		<h2><p style="text-align:center" >Order ID >  </p></h2> <p style="text-align:center" ><%= OrderId %> </p>
		<h2><p style="text-align:center" >Order Summary >  </p></h2> <p style="text-align:center" > <%= OrderSummary %> </p>
		<h2><p style="text-align:center" >Delivery Address >  </p></h2> <p style="text-align:center" > <%= DeliveryAddress %></p>
		<h2><p style="text-align:center" >Vendor Name >  </p></h2> <p style="text-align:center" > <%= Vendor %> </p>
		<h2><p style="text-align:center" >Status >  </p></h2> <p style="text-align:center" > <%= Status %> </p>
		
		<p style="text-align:center"> END OF DOCUMENT </p>


</body>
</html>