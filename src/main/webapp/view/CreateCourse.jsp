<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Create Course</title>
</head>
<body>

<div> Save Course </div>

<div>
  <form:form action="saveCourse" modelAttribute="course" method="POST">
  <table>
      <tbody>
        <tr>
          <td><label>Course Name</label></td>
          <td><form:input path="name"></form:input></td>
       </tr>
       <tr>
          <td><label></label></td>
           <td><input type="submit" value="Save" class="save"></input></td>
       </tr>
      
      </tbody>
  </table>
      
  </form:form>

</div>

</body>
</html>