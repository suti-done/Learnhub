<%@ taglib  prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	
<title>HOME</title>
</head>
<body >
   <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Learnhub</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="#">Home</a>
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
    
<br><br>
	<security:authorize access="hasRole('TUTOR')">
		<div>
		    <input type="button" value="My Courses"
		           onclick="window.location.href='/tutor/courses';return false;"
		           />
		    <!-- <a href="${pageContext.request.contextPath}/tutor/courses">courses</a> -->
		    
		    <br><br>
		    <input type="button" value="Update Courses for Student"
		           onclick="window.location.href='/tutor/addCourseForStudent';return false;"
		           />
		    
		</div>
	</security:authorize>
	
	 <security:authorize access="hasRole('STUDENT')">
		<div>
		    <input type="button" value="My Courses"
		           onclick="window.location.href='/student/courses';return false;"
		           />
		    <!-- <a href="${pageContext.request.contextPath}/tutor/courses">courses</a> -->
		    
		</div>
	</security:authorize>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
 
 
 
</body>
</html>