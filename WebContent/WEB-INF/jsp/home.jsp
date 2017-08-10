<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/progress_bar.css">
<title> Welcome to LCAT</title>
<script  src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script>
	$(document).ready(function(){
		
		var arrSignOff = JSON.parse($('#group').val());
		for( var prop in arrSignOff){
			var selector1 = "#" +  prop + "Status .label";
			var selector2 = "#" +  prop + "Status";			
			if(arrSignOff[prop]==1){
				$(selector2).addClass('active');
			}
			if(arrSignOff[prop]==2){
				$(selector1).html('&#10004');
				$(selector2).addClass('done');
			}
		}
		
		$('#uploadImg').hover(function(){
			$("#upload-block").slideDown("slow");
		});
		
		$('#viewReport').click(function(){
			location.href =  '/Inwisey/Inwisey/table';
		});
		
		$('#addUser').click(function(){			
			location.href = '/Inwisey/Inwisey/addUser';
			
		});
				
		$("#uploadbtn").click(function(){
			 $(".upload-div").fadeOut("slow");
			
			var oMyForm = new FormData();
			var billCycleType = "0";
			var billCycle = "0507"; 
			
			//var reportInfo = "{" + "\"billCycleType\" : " + "\""+ billCycleType + "\"" + " , \"billCycle\" : " + "\"" +  billCycle + "\"}" ;
			
			//reportInfo = JSON.stringify(JSON.parse(reportInfo));
			
						
			for( i=0 ; i<excelFile.files.length ; i++){				
			oMyForm.append("file", excelFile.files[i]);
			}
			//oMyForm.append("reportInfo", reportInfo);			
			
			
			 $.ajax({
			    url: '/Inwisey/Inwisey/upload-Files',
			    data: oMyForm,
			    processData: false,
			    contentType: false,
			    type: 'POST',
			    success: function(data){
			    }
			  });
			 
			 $.ajax({
				    url: '/Inwisey/Inwisey/generate-ILC/?billCycle=' + billCycle + "&billCycleType=" + billCycleType,
				    dataType: false,
				    processData: false,
				    contentType: false,
				    type: 'GET',
				    success: function(data){
				      alert(data);
				    }				
				  });
		});
		
		$("#generateBox").click(function(){		
			$('#result').html('');
			$.ajax({
			    url: '/Inwisey/generate-ILC',
			    dataType: false,
			    processData: false,
			    contentType: false,
			    type: 'POST',
			    success: function(data){
			      alert(data);
			    }				
			  });
			
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


<br>
<br>

<input name="group" id="group" type="hidden" value="${fn:escapeXml(group)}" />

<div class="progress">
  
  <div class="circle" id="dmSignOffStatus">
    <span class="label"></span>
    <span class="title">DM</span>
  </div>
  <span class="bar"></span>
  
  <div class="circle" id="bamSignOffStatus">
    <span class="label"></span>
    <span class="title">BAM</span>
  </div>
  <span class="bar"></span>
  
  <div class="circle" id="srBamSignOffStatus">
    <span class="label"></span>
    <span class="title">SR.BAM</span>
  </div>
  <span class="bar"></span>
 
  <div class="circle" id="dpeSignOffStatus">
    <span class="label"></span>
    <span class="title">DPE</span>
  </div>
  <span class="bar"></span>
  
  <div class="circle" id="pmoSignOffStatus">
    <span class="label"></span>
    <span class="title">PMO</span>
  </div>
</div>

<br>
  		
<div class="homeImage">
		<div class="imgContainer" id="uploadImg">
			<img id="img1"  src="resources/image/upload.png"/>
			<label id="label1" class="icon-label"> Upload Files </label>
			
		</div>
		<!-- div class="imgContainer" id="generateBox">
			<img src="resources/image/generateReport.png"/>
			<label class="icon-label"> Generate Report </label>
		  
		</div--->
		<div class="imgContainer" id="viewReport">
			 <img src="resources/image/viewReport.png">
			 <label class="icon-label"> View Report </label>
		
		</div>
		<div class="imgContainer" id="addUser">
			 <img src="resources/image/addUser.png">
			 <label class="icon-label"> Add User </label>
		
		</div>
</div>
<br>
<br>



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