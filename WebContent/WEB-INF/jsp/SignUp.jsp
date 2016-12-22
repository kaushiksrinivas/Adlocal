<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SignUp- AdLocal</title>
<link rel="stylesheet" href="CSS/signupstyle.css">
</head>
<body>
	<div id="wrap">
		<div id="regbar">
			<div id="navthing">
				<h1>AdLocal - A local vendor</h1>
			</div>
		</div>
	</div>
	<div class="modal-dialog">
		<div class="SignUp-container">
			<h2>SignUp</h2>
			<br>
			<form action="SignUp" method="POST">
				<input type="text" name="UserName" placeholder="Username_"> 
				<input type="text" name="MobileNumber" placeholder="+91"> 
				<input type="password" name="Password" placeholder="Password"> 
				<select name="UserType" id="customertype">
					<option value="Consumer">Consumer</option>
					<option value="ShopKeeper">ShopKeeper</option>
				</select> 
				<input type="submit" name="register" class="login loginmodal-submit" value="SignUp">
			</form>

		</div>
	</div>
</body>
</html>