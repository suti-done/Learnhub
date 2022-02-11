<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Create Task</title>
</head>
<body>

<h2>Create Or Update user</h2>  
<div>
  <form:form action="/saveUser" modelAttribute="user" method="POST">
  <table>
      <tbody>
        <tr>
          <td><label>User name</label></td>
          <td><form:input path="username" /></td>
       </tr>
       <tr>
          <td><label>Password</label></td>
          <td><form:input path="password" /></td>
       </tr>
        
       <tr>
          <td><label>Name</label></td>
          <td><form:input  path="name" /></td>
       </tr>
       
        <tr>
          <td><label>Phone Number</label></td>
          <td><form:input  path="phone_number" /></td>
       </tr>
       
       <tr>
          <td><label>Profile</label></td>
          <td><form:input  path="profile" /></td>
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