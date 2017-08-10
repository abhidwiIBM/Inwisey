<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/user.css">
<title>Welcome to LCAT</title>
</head>
<body>
	<div class="topnav">	 
		<div class="nav-text"> Labor Claim Analysis Tool (LCAT) </div>
	</div>

	<div class="entry">
  <h1> User entry Page </h1>
  	</div>
	 <div class="form">
	 <form class="entry-form">
      <input type="text" placeholder="First Name"/>
	  <input type="text" placeholder="Middle Name"/>
      <input type="text" placeholder="Last Name"/>
	  <input type="text" placeholder="Email ID"/>
	  <input type="text" placeholder="Group ID"/>
	<h2>Role:</h2>
	
	<div class="maxl">
	<label class="radio inline"> 
      <input type="radio" name="role" value="LEAD">
      <span>LEAD </span> 
  </label>
	<label class="radio inline"> 
      <input type="radio" name="role" value="DM">
      <span>DM </span> 
  </label>
	<label class="radio inline"> 
      <input type="radio" name="role" value="BAM">
      <span>BAM </span> 
  </label>
	<label class="radio inline"> 
      <input type="radio" name="role" value="SR.BAM">
      <span>SR.BAM </span> 
  </label>
  <label class="radio inline"> 
      <input type="radio" name="role" value="PMO" checked>
      <span> PMO </span> 
   </label>
	</div>
	<br>
	<button class="button button1">Submit</button>
	</form>
	</div>
</body>
</html>