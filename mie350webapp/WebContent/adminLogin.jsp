<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>uGive - Login</title>
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



<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

	<%@ include file="navbar.jsp"%>

	<div class="container-fluid text-center">
		<div class="row no-gutter">
			<div class="col-sm-2 text-left">
			</div>	
				<div class="col-sm-8 text-left">
		              <h3 class="login-heading mb-4">Welcome back! ADMIN</h3>
		              <form action="AdminLoginController">
		                <div class="form-label-group">
		                  <input type="text" name="un" class="form-control" placeholder="Username" required autofocus>
		                </div>
						<br /> 
		                <div class="form-label-group">
		                  <input type="password" name="pw" class="form-control" placeholder="Password" required>

		                </div>
		                <br /> 
		                <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit">Sign in</button>
		                
		              </form>
		              
		              <br /> 
				<a href="userLogin.jsp"> User LogIn </a>
		              
		            </div>
		          </div>
				
	<%@ include file="footer.jsp"%>

		        </div>



</body>
</html>


