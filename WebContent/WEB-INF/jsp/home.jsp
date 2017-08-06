<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/style.css">
<title> Welcome to LCAT</title>
<script  src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script>
	$(document).ready(function(){
		
				
		$('#uploadImg').click(function(){
			$("#upload-block").slideDown("slow");
			//$(".upload-div").css('display','block');
			
		});
				
		
		$("#uploadbtn").click(function(){
			$(".upload-div").fadeOut("slow");
			var oMyForm = new FormData();			
			for( i=0 ; i<excelFile.files.length ; i++){				
			oMyForm.append("file", excelFile.files[i]);
			}
			
			$.ajax({
			    url: '/Inwisey/upload-Files',
			    data: oMyForm,
			    dataType: 'text',
			    processData: false,
			    contentType: false,
			    type: 'POST',
			    success: function(data){
			      $('#result').html(data);
			    }
			  });
		});
		
		$("#generateBox").click(function(){		
			$('#result').html('');
			$.ajax({
			    url: '/ILCApp/generate-ILC',
			    dataType: false,
			    processData: false,
			    contentType: false,
			    type: 'POST',
			    success: function(data){
			      $('#result').html(data);
			    }				
			  });
			//alert("returened from Controller");
			$('#result').html("ILC Report generated successfully");
		});
		
	});
</script>
</head>
<body>
<div class="topnav">	 
		<div class="nav-text"> Labor Claim Analysis Tool (LCAT) </div>
		<div class="nav-image"> <img src="resources/image/user.png"></img> 
			<div id="usersetting" class="nav-dropdown">
				<ul>
					<li> <a href=#> Logout </a></li>
				</ul>
			</div>		
		</div>
		
</div>

<!--div class="user-options">
		<div class="user-box">
			<div class="box-icon"><img src="resources/images/upload.png"> </img> </div>
			<div class="box-text"> Upload Files </div>
		</div>
		<div class="user-box"></div>
		<div class="user-box"></div>
		<div class="user-box"> </div>
	
	</div>
</div-->

		
<div class="homeImage">
		<div class="imgContainer" id="uploadImg">
			<img id="img1"  src="resources/image/upload.png"/>
			<label id="label1" class="icon-label"> Upload Files </label>
			
		</div>
		<div class="imgContainer" id="generateBox">
			<img src="resources/image/generateReport.png"/>
			<label class="icon-label"> Generate Report </label>
		  
		</div>
		<div class="imgContainer">
			 <img src="resources/image/viewReport.png">
			 <label class="icon-label"> View Report </label>
		
		</div>
		<div class="imgContainer">
			 <img src="resources/image/addUser.png">
			 <label class="icon-label"> Add User </label>
		
		</div>
</div>



<br>
<br>
<!-- div class="upload-div">
			<form id="uploadForm" method="post" action="ILCApp/upload-Files" enctype="multipart/form-data">
			 <div>                
                 <input type="file" name="excelFile" id="excelFile" multiple="multiple"/>
             </div>
             
             <div class="upload-btn-wrapper">
				   <input id="uploadbtn" type="button" value="Upload Files">
				  
			 </div>
             <div id="buttons">                     
                
             </div>
             
             <div class="drop-down">
             	<label> Billing Cycle </label>
             	<div>
             	<select>  </select>
             	</div>
             	<div>
             	<select> </select>
             	</div>
             	
             
             </div>
             </form>
             

</div-->

<div class="message" id="result">	
</div>

<div class="upload-div" id="upload-block">
			<form id="uploadForm" method="post" action="ILCApp/upload-Files" enctype="multipart/form-data">
				 <div class="upload-div-block">
							<div class="upload-element upld-label">					
								<label> Report Type </label>
							</div>
				 
						  <div class="upload-element">
						  <label> 
							  <input type="radio" name="Report" value="ILC Report" checked>
							  <span>ILC Report </span> 
						   </label>
						  <label > 
							  <input type="radio" name="Report" value="SLA Report">
							  <span>SLA Report </span> 
						  </label>
						 </div>
				 </div>
				 <div class="upload-div-block">
					<div class="upload-element upld-label ">
					<label> Browse Files </label>
					</div>
					<div class="upload-element">
					 <input type="file" name="excelFile" id="excelFile" multiple="multiple"/>
					</div>
				 </div>
				 
				 
				 <div class="upload-div-block">
					<div class="upload-element upld-label">
					<label> Billing Cycle </label>
					</div>
					<div class="upload-drop-down">
						<select >
						<option value="volvo"> Jan</option>
							  <option value="saab">Feb</option>
							  <option value="opel">March</option>
							  <option value="audi">April</option>
						</select>
					</div>
					<div class="upload-drop-down">
					<select >
						<option value="volvo">2014</option>
							  <option value="saab">2015</option>
							  <option value="opel">2016</option>
							  <option value="audi">2017</option>
						</select>
					</div>				 
				 </div>
				 
				 <div class="upload-div-block">
					
					<div class="upload-element upld-sub">
					  <input type="button" id="uploadbtn" value="Submit"> </input>
					</div>
					  
				 </div>
				 
             </form>
             

</div>
<div class="footer">	 
		<div class="footer-text"> Copyright: IBM India Pvt Ltd. </div>
		
</div>
</body>
</html>