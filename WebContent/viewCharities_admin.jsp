<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List" %>
<html lang="en">

<!-- Check to see if the user is logged in. Otherwise, redirect back to the login page.-->
<%
	session = request.getSession();
	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}
		
%>

<head>
<title>uGive - Charities</title>
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
	Admin admin = (Admin) session.getAttribute("currentSessionUser");

	String username = (String) session.getAttribute("username");
	String firstname = (String) session.getAttribute("firstname");
	String lastname = (String) session.getAttribute("lastname"); 
	%>

	<div class="container-fluid text-center">
		<div class="row content">
			 <%@ include file="sidebar_admin.jsp"%>
			<div class="col-sm-8 text-left">
				<h1>Charities</h1>

				<br>
				 <br>

<table border=1 class="sortable">
					<thead>
						<tr>
							<th>Charity</th>
							<th>Category</th>
							<th>Location</th>
							<th>Address</th>
							<th>Hours</th>
							<th>Phone Number</th>
							<th colspan=2>See Events</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${charities}" var="charity">
							<tr>
								<td align="center"><c:out value="${charity.getCharityName()}" /></td>
								<td align="center"><c:out value="${charity.getCharityCategory()}" /></td>
								<td align="center"><c:out value="${charity.getCity()}" /></td>
								<td align="center"><c:out value="${charity.getAddress()}" /></td>
								<td align="center"><c:out value="${charity.getHours()}" /></td>
								<td align="center"><c:out value="${charity.getPhoneNum()}" /></td>
								<td align="center"><a class="btn btn-info"
									href="ViewCharitiesController?action=viewEvents&charID=<c:out value="${charity.getCharityID()}"/>">Events</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>



				<br /> <br />
			</div>
			<div class="col-sm-2 sidenav">
				<!-- You can put right sidebar links here if you want to. -->
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>


</body>
</html>