<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      <title>Spring MVC Form Handling</title>
   </head>

   <body>
      <h2>Student Information</h2>
      <form:form method = "POST" action = "/ILCApp/addStudent">
         <table>
            <tr>
               <td><form:label path = "name">Name</form:label></td>
               <td><form:input path = "name" /></td>
            </tr>
            <tr>
               <td><form:label path = "age">Age</form:label></td>
               <td><form:input path = "age" /></td>
            </tr>
            <tr>
               <td><form:label path = "id">id</form:label></td>
               <td><form:input path = "id" /></td>
            </tr>
            <tr>
               <td colspan = "2">
                  <input type = "submit" value = "Submit"/>
               </td>
            </tr>
         </table>  
      </form:form>
   </body>
   <br>
   <br>
   <form:form method="POST" action="/ILCApp/table">
   <input type = "submit" value = "Get Table Data"/>
   </form:form>
   
   <form:form method="POST" action="/ILCApp/editReport">
   <input type = "text" name="userID" value = "adwivedi"/>
   <input type = "text" name="billcyle" value = "0517"/>
   <input type = "submit" value = "Edit Report"/>
   </form:form>
   
</html>