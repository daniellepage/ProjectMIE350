<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<!-- Check to see if the user is logged in. Otherwise, redirect back to the login page.-->
<%
	session = request.getSession();
	System.out.println(session);
	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}
	
%>

<head>
<title>uGive - Confirmed Events</title>
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

	<%@ include file="navbar_loggedin.jsp"%>
	<%
		User user = (User) session.getAttribute("currentSessionUser");

		String username = (String) session.getAttribute("username");
		String firstname = (String) session.getAttribute("firstname");
		String lastname = (String) session.getAttribute("lastname");
	%>

	<div class="container-fluid text-center">
		<div class="row content">
			<%@ include file="sidebar_loggedin.jsp"%>
			<div class="col-sm-8 text-left">
				<h1>Your Confirmed Events</h1>

				The time is now <b><%=new java.util.Date()%></b>.<br> 
				
				<a class="btn btn-outline-info"
					href="TaggedEventsController?action=listInterested">View Interested Events</a> <br>
				<br /> <br /> You have confirmed your participation at <B><c:out
						value="${confevents.size()}" /> events</B>: <br>
						
				<br>
				<table border=1 class="sortable">
					<thead>
						<tr>
							<th>Event</th>
							<th>Charity</th>
							<th>Donation Type</th>
							<th>Date</th>
							<th>Location</th>
							<th colspan=2>Change Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${confevents}" var="event">
							<tr>
								<td align="center"><c:out value="${event.getEventName()}" /></td>
								<td align="center"><c:out value="${event.getCharityName()}" /></td>
								<td align="center"><c:out value="${event.getDonationType()}" /></td>
								<td align="center"><fmt:formatDate pattern="MMM-dd-yyyy"
										value="${event.getEventDate()}" /></td>
								<td align="center"><c:out value="${event.getCity()}" /></td>
								<td align="center"><a class="btn btn-warning"
									href="TaggedEventsController?action=setInterested&eventID=<c:out value="${event.getEventID()}"/>">Interested</a></td>
								<td align="center"><a class="btn btn-danger"
									href="TaggedEventsController?action=deleteEvent&eventID=<c:out value="${event.getEventID()}"/>">Remove</a></td>
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