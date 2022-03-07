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

<title>Materials</title>
</head>
<body style="background-color:LightGray">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">MATERIALS</a>
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
   
   
  <div style="margin: 25px"> 
	  <security:authorize access="hasRole('TUTOR')">
		   <form:form method="POST" enctype="multipart/form-data" action="uploadMAT" modelAttribute="task" >
					<table>
						<tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
						<tr><td><form:input  type="hidden" path="course.id" value="${task.course.id}"></form:input></td></tr>
						<tr><td><form:input type="hidden" path="id" value="${task.id}"></form:input></td></tr>
						<tr><td></td><td><input class="btn btn-secondary" type="submit" value="Upload" /></td></tr>
					</table>
					
		 </form:form>
 <br><br>
    
      <a style="color:black" href="${pageContext.request.contextPath}/tutor/taskDetails">Back to task</a>
    </security:authorize>
    
    <security:authorize access="hasRole('STUDENT')">
       <a style="color:black" href="${pageContext.request.contextPath}/student/taskDetails">Back to task</a>
    </security:authorize>
 </div>


  <div style="margin: 25px;background-color:white">
	<table class="table table-hover table-bordered rounded">
	     <thead style="background-color:#2B6670;color: white;">
		     <tr>
		        <th>Files</th>
		        
		     </tr>
		     </thead>
		     <c:forEach var="file" items="${files}">
		     
		       <tr>
			     <td><a href="${file}" target="_blank" >${file}</a></td>
			   </tr>
			     
		     </c:forEach>
		     
	     </table>
     </div>
</body>
</html>