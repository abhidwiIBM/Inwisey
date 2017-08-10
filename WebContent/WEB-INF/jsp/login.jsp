<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/style.css">
<title>Welcome to LCAT</title>
<script  src="https://code.jquery.com/jquery-3.2.1.js"></script>
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
                url: "Inwisey/validateUser?userID=" + Email + "&password=" + Password,
                dataType: false,
                type: 'GET',                
            	success: function(results){
                	if(results == "0"){                		
                    $('#messageDiv').css("display","block");
                    $('#messageDiv').html("<font color='red'>User ID or password incorrect </font>");
                    return;
                	}else if(results =="8"){                    
                		location.href = 'Inwisey/admin';
                	}else{                		
                		location.href = 'Inwisey/table';
                	}
            	}
                		 
                });
            });
		
		$('#Email').on('blur', function(){
            if($(this).val().length < 3){
              alert('User ID should be atleast 3 characters!');
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
    <div class="login-form" id="divId" accept-charset=utf-8>
      <input type="text" placeholder="User ID" id="Email"/>
      <input type="password" placeholder="Password" id="Password"/>
      <button name="SignIn" id="SignIn"> Sign In </button>
      <p class="message">Not registered? Please contact admin. </p>
    </div>
  </div>
</div>
            <div id="messageDiv" style="display:none;"></div>
        </center>    
    </body>
</html>