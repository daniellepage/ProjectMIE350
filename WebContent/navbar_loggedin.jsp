<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="userLogged.jsp">uGive</a>
				
<%
	session = request.getSession();

	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}

	
%>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a href="userLogged.jsp">Home</a></li>
				<li><a href="about_user.jsp">About</a></li>
				<li><a href="ViewCharitiesController?action=viewCharities&admin=false">Charity List</a></li>
				<li><a href = "searchEvents.jsp">Find an Event</a></li>
				
				<li class="dropdown"><a data-toggle="dropdown"
					href="helpful_links.jsp">My Events<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="TaggedEventsController?action=listInterested">Interested Events</a></li>
						<li><a href="TaggedEventsController?action=listConfirmed">Confirmed Events</a></li>
					</ul></li>
			</ul>
			<!-- The following code can be added to include a Login button to the right-hand side of the navbar-->
			<ul class="nav navbar-nav navbar-right">
				<li><a href="profile.jsp"><span
						class="glyphicon glyphicon-user"></span> My Profile</a></li>
				<li><a href="LogoutServlet"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</ul>
		</div>
	</div>
</nav>