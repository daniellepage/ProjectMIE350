<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="memberLogged.jsp">MIE350 Sample Web
				App (Admin)</a>
				
<%
	session = request.getSession();

	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}

	
%>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a href="memberLogged.jsp">Home</a></li>
				<!-- li><a href="about.jsp">About</a></li -->
				<li><a href = "searchEvents.jsp">Find an Event</a></li>
				<li><a
					href="TaggedEventsController?action=listConfirmed" >My Events</a></li>
			</ul>
			<!-- The following code can be added to include a Login button to the right-hand side of the navbar-->
			<ul class="nav navbar-nav navbar-right">
				<li><a href="LogoutServlet"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</ul>
		</div>
	</div>
</nav>