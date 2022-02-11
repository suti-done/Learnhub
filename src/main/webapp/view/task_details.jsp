<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title></title>
<style>
form { display: inline-block; }
</style>
</head>
<body>
<div>
   <div>
     <h2>TASK</h2>
   </div>
   
   <div>
   <form:form action="CreateTask" modelAttribute="course" method="POST">
	  <table>
	      
	       <tr>
	      
	           <td><input type="submit" value="Add Task" class="save"></input></td>
	       </tr>
	      
	  </table>
      
     </form:form>
     
   </div>
  <br> <br>
     <table>
	     <tr>
	        <th>title</th>
	        <th>type</th>
	        <th>on_date</th>
	        <th>due_date</th>
	        <th>material</th>
	        <th>submission</th>
	        <th>Action</th>
	     </tr>
	     <c:forEach var="task_v" items="${tasks}">
	     
	       <tr>
		     <td>${task_v.title}</td>
		     <td>${task_v.type}</td>
		     <td>${task_v.on_date}</td>
		     <td>${task_v.due_date}</td>
		     <td><form:form action="materials" modelAttribute="task" method="POST">
					  <table>
					      
					       <tr>
					          
					           <td><form:input type="hidden" path="id" value="${task_v.id}"></form:input></td> 
					           <td><input type="submit" value="materials" class="save"></input></td>
					       </tr>
					      
					  </table>
      
             </form:form></td>
              <td><form:form action="submission" modelAttribute="task" method="POST">
					  <table>
					      
					       <tr>
					           <td><form:input type="hidden" path="id" value="${task_v.id}"></form:input></td> 
					           <td><input type="submit" value="submissions" class="save"></input></td>
					       </tr>
					      
					  </table>
      
             </form:form></td>
             <td><form:form action="taskUpdate" modelAttribute="task" method="POST">
					  <table>
					      
					       <tr>
					            <td><form:input type="hidden" path="id" value="${task_v.id}"></form:input></td>  
					           <td><input type="submit" value="Update" class="save"></input></td>
					       </tr>
					      
					  </table>
      
             </form:form>
             |
             <form:form id="delete" action="taskDelete" modelAttribute="task" method="POST" onsubmit="check()" >
					  <table>
					      
					       <tr>
					            <td><form:input type="hidden" path="id" value="${task_v.id}"></form:input></td>  
					           <td><input type="submit" value="Delete" class="save"  ></input></td>
					       </tr>
					      
					  </table>
      
             </form:form></td>
		     
		   </tr>
		     
	     </c:forEach>
	     
     </table>
  
  </div>
  <br><br>
  <a href="${pageContext.request.contextPath}/tutor/courses">Back to courses</a>
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