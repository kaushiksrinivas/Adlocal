<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>

  <title>Login- AdLocal</title>
  <link rel="stylesheet" href="CSS/loginstyle.css">
</head>
<body>
<div id="background">
<div id="wrap">
  <div id="regbar">
    <div id="navthing">
      <h1>  AdLocal - A local vendor</h1> 
    </div>
  </div>
</div>
<div>
	<div class="loginmodal-container">
		<h2>Login to Your Account</h2><br>
		<form action="Login" method="POST">
			<input type="text" name="Mobile" placeholder="+91">
			<input type="password" name="Password" placeholder="Password">
			<input type="submit" name="login" class="login loginmodal-submit" value="Login">
		</form>			
		<div class="login-help">
			<a href="#">Register</a> - <a href="#">Forgot Password</a>
		</div>
	</div>
</div>
</div>
 </body>
</html>