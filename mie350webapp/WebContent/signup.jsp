<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@ include file="navbar.jsp"%>

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav">
				<!-- You can put left sidebar links here if you want to. -->
			</div>
			<div class="col-sm-8 text-left">
				<h1>Administrator Login</h1>

				<form action="LoginController">

					Username: <input type="text" name="user" />
					<br> Password: <input type="password" name="pass" />
					<br> Email: <input type="password" name="email" />
					<br> First Name: <input type="password" name="fname" />
					<br> Last Name: <input type="password" name="lname" />
					<br> Age: <input type="password" name="age" />
					<br> Address: <input type="password" name="address" />
					<br> Phone Number: <input type="password" name="phonenum" /> 
					
					
					<br> <input type="submit" class="btn btn-info" value="Submit">

				</form>
				<br /> 
\
			</div>
			<div class="col-sm-2 sidenav">
				<!-- You can put right sidebar links here if you want to. -->
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>


</body>
</html>