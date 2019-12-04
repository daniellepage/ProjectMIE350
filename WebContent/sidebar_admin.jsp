<div class="col-sm-2 sidenav">
	<!-- You can put left sidebar links here if you want to. -->
	<!-- Eclipse will underline the first name, last name, and user name variables as errors
	but you can ignore them since this JSP file will be imported by another JSP 
	file that handles the variable declarations (see memberLogged.jsp for example).
	 -->
	Welcome, <b><%=firstname%> <%=lastname%></b> (username: <b><%=username%></b>).<br />
	<br />You are now logged in as an <b>administrator</b>.<br />
	<br /> The time is now <b><%=new java.util.Date()%></b>.<br /> <br />

</div>