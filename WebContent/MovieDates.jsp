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
<title>Movies For Your Dates</title>
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
                    <h3>Movies For Your Dates</h3>
                    <h6>(Shows movies with start date between your dates)</h6>
                    <form action="MovieDates" method="post">
                        <div class="form-group">
                            <input type="date" class="form-control" placeholder="Start Date" value="" name="start_date"/>
                        </div>
                        <div class="form-group">
                            <input type="date" class="form-control" placeholder="End Date" value="" name="end_date"/>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="add" value="Check" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
</body>
</html>