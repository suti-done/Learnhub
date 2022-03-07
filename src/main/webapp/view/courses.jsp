<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib  prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<title>Courses</title>
<style>
form { display: inline-block; }
</style>
</head>
<body style="background-color:LightGray">
 <nav class="navbar navbar-expand-lg navbar-dark bg-dark">  
  
  <div class="container-fluid">
    <a class="navbar-brand" href="#">COURSES</a>
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

 <div style="background-color:LightGray;padding: 50px">  

  <div style="border-style:inset;border-width: 5px;padding: 50px;background-color:white;">
 
  <security:authorize access="hasRole('TUTOR')">
	  <div style="margin: 25px">
	  <input class="btn btn-secondary" type="button" value="Add Course"
	           onclick="window.location.href='/tutor/CreateCourse';return false;"
	           />
	     
	   </div>
   </security:authorize>
   
   <div style="margin: 25px;border-radius: 50px; ">
     <table class="table table-striped table-hover table-bordered rounded">
     <thead style="background-color:#2B6670;color: white;">
	     <tr>
	        <th scope="col">Name</th>
	        <th scope="col">Task</th>
	        <th scope="col">Tutor</th>
	        <security:authorize access="hasRole('TUTOR')">
	         <th scope="col">Action</th>
	        </security:authorize>
	     </tr>
	  </thead>
	     <c:forEach var="courses" items="${courses}">
	     
	       <tr>
		     <td>${courses.name}</td>
		     <td>
		     <form:form action="taskDetails" modelAttribute="course" method="POST">
					  <table>
					      
					       <tr>
					          <form:input type="hidden" path="id" value="${courses.id}"></form:input></td> 
					           <td><input class="btn btn-secondary" type="submit" value="task details" class="save"></input></td>
					       </tr>
					      
					  </table>
      
             </form:form>
             </td>
		     <td>${courses.tutor.name}</td>
		     <security:authorize access="hasRole('TUTOR')">
			      <td><form:form action="updateCourse" modelAttribute="course" method="POST">
						  <table>
						      
						       <tr>
						               <td><form:input type="hidden" path="id" value="${courses.id}"></form:input></td>
						           <td><input class="btn btn-secondary" type="submit" value="Update" class="save"></input></td>
						       </tr>
						      
						  </table>
	      
	             </form:form>
	             |
	             <form:form id="delete" action="deleteCourse" modelAttribute="course" method="POST" onsubmit="check()" >
						  <table>
						      
						       <tr>
						              <td><form:input type="hidden" path="id" value="${courses.id}"></form:input></td>
						           <td><input class="btn btn-secondary" type="submit" value="Delete" class="save"  ></input></td>
						       </tr>
						      
						  </table>
	      
	             </form:form></td>
             </security:authorize>
		   </tr>
		     
	     </c:forEach>
	     
     </table>
     
     </div>
  
  </div>
  
    
     
     <script>
  
  function check()
  {
	         const form  = document.getElementById('delete');
            if(!(confirm("Do you want to delete the task")))
            	{
            	  event.preventDefault();
            	}
            else
            	{
            	   form.submit();
            	}

  }
 /* const form  = document.getElementById('delete');
  form.addEventListener("submit", function ()
		  {
	                if(!(confirm("Do you want to delete the task")))
	                	{
	                	  event.preventDefault();
	                	}
	                else
	                	{
	                	   form.submit();
	                	}
	  
		  }); */
  
  </script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </div>
</body>
</html>