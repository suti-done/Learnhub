<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<title>Add Course For Student</title>
</head>
<body>
<body style="background-color:LightGray">
 <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Learnhub</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/home">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#">About Us</a>
	        </li>
	        <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            Dropdown
	          </a>
	          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	            <li><a class="dropdown-item" href="#">Action</a></li>
	            <li><a class="dropdown-item" href="#">Another action</a></li>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="#">Something else here</a></li>
	          </ul>
	        </li>
	         <li class="nav-item">
	          <a class="nav-link" href="#">Contact Us</a>
	        </li>
	      </ul>
	       <a class="nav-link" style="color:white" href="/logout">Logout</a>
	    </div>
	  </div>
	</nav>
	<div style="padding-top: 25px;padding-right: 200px;padding-left: 200px;">
	      <h2 style="text-align: center;background-color:#2B6670;color: white;">Add Course For Student</h2>  
	</div>

	<div style="  border: 6px solid black ; border-radius: 50px;padding: 50px; margin: 50px 200px 50px 200px;background-color:white">
		<form action="/tutor/saveCouseforStudent" method="post">
		
		    <div class="mb-3">
				<label for="s_name">Student</label><br>
				<input type="text"  name="s_name"><br>
			</div>
			<div class="mb-3">
			   <label for="c_name">Choose a course:</label><br>
				<select  name="c_id" >
				  <c:forEach var="course" items="${courses}">
				      <option value="${course.id}">${course.name}</option>
				  </c:forEach>
				</select><br><br>
			</div>	
			<div class="mb-3">
			  <input type="submit" value="Submit">
			 </div>
		</form>
	</div>



</body>
</html>