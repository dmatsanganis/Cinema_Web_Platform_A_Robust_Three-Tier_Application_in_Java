<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Welcome</title>
</head>
<body>

<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires","0");

	if(session.getAttribute("username")==null){
		response.sendRedirect("login.html");
	}
%>
<div class="container login-container">
            <div class="row">
                <div class="col-md-6 login-form-1">
                    <h3>Welcome ${username}</h3>
                    <form method="post">
                        <div class="form-group">
                            <input type="submit" class="btnSubmit" formaction="CreateUser.jsp" value="Create New User" />
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btnSubmit" formaction="UpdateUser.jsp" value="Update User" />
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btnSubmit" formaction="DeleteUser.jsp" value="Delete User" />
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btnSubmit" formaction="SearchUser.jsp" value="Search For User" />
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btnSubmit" formaction="ShowUsers" value="Show All Users" />
                        </div>
                        <div class="form-group">
                            <input type="submit" class="logout" formaction="Logout" value="Logout" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
</body>
</html>