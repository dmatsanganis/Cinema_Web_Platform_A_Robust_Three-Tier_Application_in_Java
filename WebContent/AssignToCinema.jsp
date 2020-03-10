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
<title>Assign To Cinema</title>
</head>
<body>
<%
	if(session.getAttribute("username")==null){
		response.sendRedirect("login.html");
	}
%>
<div class="container login-container">
            <div class="row">
                <div class="col-md-6 login-form-1">
                    <h3>Assign Movie To Cinema</h3>
                    <form action="AssignToCinema" method="post">
                        <div class="form-group">
                            <input type="number" class="form-control" placeholder="Movie ID" value="" name="movie_id"/>
                        </div>
                        <div class="form-group">
                            <input type="date" class="form-control" placeholder="Start Date" value="" name="start_date"/>
                        </div>
                        <div class="form-group">
                            <input type="date" class="form-control" placeholder="End Date" value="" name="end_date"/>
                        </div>
                        <input type="hidden" name="username" value="${username}">
                        <div class="form-group">
                            <input type="number" class="form-control" placeholder="Cinema ID" value="" name="cinema_id"/>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="add" value="Add" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
</body>
</html>