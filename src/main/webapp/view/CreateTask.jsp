<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<title>Create Task</title>
</head>
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
      <h2 style="text-align: center;background-color:#2B6670;color: white;">Create Or Update Task</h2>  
</div>

<div style="  border: 6px solid black ; border-radius: 50px;padding: 50px; margin: 50px 200px 50px 200px;background-color:white">
  <form:form action="saveTask" modelAttribute="task" method="POST">
  
    <div class="mb-3">
	      <label class="form-label">Title</label>
          <form:input class="form-control" path="title" />
     </div>
     
     <div class="mb-3">
	      <label class="form-label">Type</label>
          <form:select class="form-control" path="type" >
	          <form:option value="Classwork" label="Classwork"/>
		      <form:option value="Homework" label="Homework"/>
		   </form:select>    
    </div>
          
     <div class="mb-3">
	      <label class="form-label">Due Date</label>
          <form:input class="form-control" type="date" path="due_date" />
     </div>
      
       
		  <form:input type="hidden" path="course.id" value="${task.course.id}" />
					           
	      <form:input type="hidden" path="on_date" value="${task.on_date}" />
					           
	<div class="mb-3">     
	     <input class="btn btn-primary" type="submit" value="Save" class="save"></input>
    </div>
	     
  </form:form>
  
</div>
<a style="margin: 50px 200px 50px 200px;color:black" href="${pageContext.request.contextPath}/tutor/taskDetails">Back to task</a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<!--  
<form:form method="POST" enctype="multipart/form-data" action="file" modelAttribute="task" >
			<table>
				<tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
				<tr><td><form:input type="hidden" path="course.id" value="${task.course.id}"></form:input></td></tr>
				<tr><td></td><td><input type="submit" value="Upload" /></td></tr>
			</table>
			
</form:form>
-->

</body>
</html>