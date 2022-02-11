<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Courses</title>
<style>
form { display: inline-block; }
</style>
</head>
<body>

  <div>
   <div>
     <h2>COURSES</h2>
   </div>
   <div>
  <input type="button" value="Add Course"
           onclick="window.location.href='/tutor/CreateCourse';return false;"
           />
     
   </div>
     <table>
	     <tr>
	        <th>Name</th>
	        <th>Task</th>
	        <th>Tutor</th>
	        <th>Action</th>
	     </tr>
	     <c:forEach var="courses" items="${courses}">
	     
	       <tr>
		     <td>${courses.name}</td>
		     <td>
		     <form:form action="taskDetails" modelAttribute="course" method="POST">
					  <table>
					      
					       <tr>
					          <form:input type="hidden" path="id" value="${courses.id}"></form:input></td> 
					           <td><input type="submit" value="task details" class="save"></input></td>
					       </tr>
					      
					  </table>
      
             </form:form>
             </td>
		     <td>${courses.tutor.name}</td>
		      <td><form:form action="updateCourse" modelAttribute="course" method="POST">
					  <table>
					      
					       <tr>
					               <td><form:input type="hidden" path="id" value="${courses.id}"></form:input></td>
					           <td><input type="submit" value="Update" class="save"></input></td>
					       </tr>
					      
					  </table>
      
             </form:form>
             |
             <form:form id="delete" action="deleteCourse" modelAttribute="course" method="POST" onsubmit="check()" >
					  <table>
					      
					       <tr>
					              <td><form:input type="hidden" path="id" value="${courses.id}"></form:input></td>
					           <td><input type="submit" value="Delete" class="save"  ></input></td>
					       </tr>
					      
					  </table>
      
             </form:form></td>
		   </tr>
		     
	     </c:forEach>
	     
     </table>
  
  </div>
  <br><br>
     <a href="${pageContext.request.contextPath}/">Back to home</a>
     
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
</body>
</html>