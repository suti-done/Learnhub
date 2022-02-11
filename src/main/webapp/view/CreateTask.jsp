<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Create Task</title>
</head>
<body>

<h2>Create Or Update Task</h2>  
<div>
  <form:form action="saveTask" modelAttribute="task" method="POST">
  <table>
      <tbody>
        <tr>
          <td><label>Title</label></td>
          <td><form:input path="title" /></td>
       </tr>
       <tr>
          <td><label>Type</label></td>
          <td><form:input path="type" /></td>
       </tr>
        
       <tr>
          <td><label>Due Date</label></td>
          <td><form:input type="date" path="due_date" /></td>
       </tr>
       
       <tr>
			<td><form:input type="hidden" path="course.id" value="${task.course.id}"></form:input></td>
					           
	  </tr>
	  <tr>
			<td><form:input type="hidden" path="on_date" value="${task.on_date}"></form:input></td>
					           
	  </tr>
       <tr>
          <td><label></label></td>
           <td><input type="submit" value="Save" class="save"></input></td>
       </tr>
      
      </tbody>
  </table>
      
  </form:form>

</div>
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