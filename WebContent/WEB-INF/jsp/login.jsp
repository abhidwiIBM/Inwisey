<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/style.css">
<title>Welcome to LCAT</title>
<script  src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script type="text/javascript" src="./js/jquery-1.11.3.min.js" ></script>
<script>
	$(document).ready(function(){
	
		$("#SignIn").on('click', function(){
            var Email = $("#Email").val();
            var Password = $("#Password").val();
            if(Email == ""){
                $('#messageDiv').css("display","none");
                alert("Email ID is required");
                return;
            }
            if(Password == ""){
                $('#messageDiv').css("display","none");
                alert("Password is required");
                return;
            }
            $.ajax({
                url : "Inwisey/validateUser",
                method : get,
                data : {
                    Email : Email,
                    Password : Password,
            		success : function(results){
                	if(results != null && results != ""){
                    showMessage(results);
                    $('#messageDiv').css("display","block");
                	}else{
                    $('#messageDiv').css("display","none");
                    $('#messageDiv').html("");
                    alert("Some exception occurred! Please try again.");
                		 }
            									}
                		}
                });
            });
		function ShowMessage(results){
            if(results == 'SUCCESS'){
                $('#messageDiv').html("<font color='green'>You are successfully logged in. </font>")
            }else if(results == 'FAILURE'){
                $('#messageDiv').html("<font color='red'>Email ID or password incorrect </font>")
            }
        }
		$('#Email').on('blur', function(){
            if($(this).val().length < 7){
              alert('Email ID should be atleast 7 characters!');
            }
        });
		
		$('#Password').on('blur', function(){
            if($(this).val().length < 8){
              alert('Password should be atleast 8 characters!');
            }
        });
	});
    </script>
<body>
	<center>
           <div class="login-page">
  <p> Welcome to LCAT, please login to continue. </p>
  <div class="form">
    <form class="login-form" id="divId">
      <input type="text" placeholder="Email" id="Email"/>
      <input type="password" placeholder="Password" id="Password"/>
      <button name="SignIn" id="SignIn"> Sign In </button>
      <p class="message">Not registered? Please contact admin. </p>
    </form>
  </div>
</div>
            <div id="messageDiv" style="display:none;"></div>
        </center>    
    </body>
</html>