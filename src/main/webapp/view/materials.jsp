<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Materials</title>
</head>
<body>
   <h2>Materials</h2>
   
   
   <form:form method="POST" enctype="multipart/form-data" action="uploadMAT" modelAttribute="task" >
			<table>
				<tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
				<tr><td><form:input type="hidden" path="course.id" value="${task.course.id}"></form:input></td></tr>
				<tr><td><form:input type="hidden" path="id" value="${task.id}"></form:input></td></tr>
				<tr><td></td><td><input type="submit" value="Upload" /></td></tr>
			</table>
			
</form:form>

 <a href="${pageContext.request.contextPath}/tutor/taskDetails">Back to courses</a>
 
<table>
	     <tr>
	        <th>Files</th>
	        
	     </tr>
	     <c:forEach var="file" items="${files}">
	     
	       <tr>
		     <td><a href="${file}" target="_blank" >${file}</a></td>
		   </tr>
		     
	     </c:forEach>
	     
     </table>
</body>
</html>