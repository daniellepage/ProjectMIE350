<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.mie.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	session = request.getSession();
	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}
		
%>
<head>
<title>uGive - Add a Charity</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- Date Picker Javascript -->
<!-- https://jqueryui.com/datepicker/ -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet" type="text/css" href="css/mystyle.css">
</head>
<body>

<%@ include file="navbar_admin.jsp"%>

	<% 
	Admin user = (Admin) session.getAttribute("currentSessionUser");

	String username = (String) session.getAttribute("username");
	String firstname = (String) session.getAttribute("firstname");
	String lastname = (String) session.getAttribute("lastname"); 
	%>

	<div class="container-fluid text-center">
		<div class="row content">

			<%@ include file="sidebar_admin.jsp"%>

			<div class="col-sm-8 text-left">
				<h1>Add a Charity</h1>

				<form action="AddCharityController">

					Charity Name: <input type="text" name="charname" />
					<br> Charity Type: <select name = "chartype">
						<option value="Food Bank">Food Bank</option>
						<option value="Religious">Religious</option>
						<option value="Environmental">Environmental</option>
						<option value="Orphanage">Orphanage</option>
						<option value="Shelter">Shelter</option>
					</select> 
					<br> Location: <select name = "city">
						<option value="York">York</option>
						<option value="Durham">Durham</option>
						<option value="Peel">Peel</option>
						<option value="Halton">Halton</option>
						<option value="Toronto">Toronto</option>
					</select> 
					<br> Hours: <input type="text" name="hours" />
					<br> Address: <input type="text" name="address" />
					<br> Phone Number: <input type="text" name="phonenum" /> 
					
					
					<br> <input type="submit" class="btn btn-info" value="Submit">

				</form>
				
				<br /> 

			</div>
			<div class="col-sm-2 sidenav">
				<!-- You can put right sidebar links here if you want to. -->
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>


</body>
</html>