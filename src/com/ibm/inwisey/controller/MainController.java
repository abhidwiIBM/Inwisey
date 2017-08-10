package com.ibm.inwisey.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.ibm.inwisey.dao.BillCycleInfo;
import com.ibm.inwisey.dao.DisplayReportDAO;
import com.ibm.inwisey.dao.EditTableDAO;
import com.ibm.inwisey.dao.LoginDAO;
import com.ibm.inwisey.dao.SaveRecords;
import com.ibm.inwisey.dao.SignOff;
import com.ibm.inwisey.dao.SignOffDAO;
import com.ibm.inwisey.model.UserProfile;
import com.ibm.inwisey.model.GroupSignOff;
import com.ibm.inwisey.model.ILCData;
import com.ibm.inwisey.model.Records;
import com.ibm.inwisey.model.RoleSignOff;
import com.ibm.inwisey.services.LoadILCDataDAOService;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;


@Controller
public class MainController {
	
	
	@Autowired UserProfile userProfile;
	
	
	@RequestMapping(value="/")
	public String renderLogin(){
		return "login";
	}
	
	@RequestMapping(value="/Inwisey/validateUser")
	
	public @ResponseBody String renderView(
			@RequestParam("userID") String userID,
			@RequestParam("password") String password){
		
		String userExists = LoginDAO.validateUser(userID, password);
		
		if(userExists == "0") {
			return "0";
		}
		else{
		userProfile = LoginDAO.setProfile(userID);
		
		return Integer.toString(userProfile.getRoleID());
		}
		
	}
   
   
   @RequestMapping(value="/Inwisey/table")
   public ModelAndView renderTable() {
	   
	   //String name = userProfile.getUserID();
	   
	  List<ILCData> ilcData = new ArrayList<ILCData>();	   
	  ilcData = DisplayReportDAO.getReport();	   
	   ModelAndView mv = new ModelAndView("/tableData"); 
      mv.addObject("ilcDataList", ilcData);
       return mv;
   }
   
   @RequestMapping(value="/checkQueries")
   public @ResponseBody String checkTable() {
	   
	   String billCycle = "0507";
	   int roleID = 2;
	   String roleType = "dmSignOff";
	   String column = "dmSignOff";
	   int dmSignOff;
	   int count;
	   try{
	   dmSignOff = SignOff.getGroupSignOff(roleType, billCycle);
	   	
	   }
	   catch(EmptyResultDataAccessException e){		 
		   dmSignOff = 0;
	   }
	  
	   try{
	   count = SignOff.getPendingGroupSignOffCount(billCycle, roleID);
	   }
	   catch(EmptyResultDataAccessException e){
		   count = 0;
	   }
	   ArrayList<RoleSignOff> myArr = SignOff.getSignOffByRole(billCycle, roleID);
	   SignOff.updateGroupSignOff(billCycle, roleID, column);
	   
	   
	   
       return "success";
   }
 
   
   @RequestMapping(value="/Inwisey/editReport")
   public @ResponseBody String editTable() {
	   
	   String billCycle = "0507";
	   String userID = userProfile.getUserID();
	   
	   String lockedBy = EditTableDAO.checkTableLock(billCycle);	  
	   if( lockedBy.equals("")){
		   EditTableDAO.lockTable(billCycle, userID);
		   return "success";
	   }
	   else{
		   System.out.println("Table is locked by " + lockedBy);
		   return lockedBy;
		}
	   }
   
   @RequestMapping(value="/Inwisey/Save")
   public @ResponseBody String saveData(@RequestBody  ArrayList<Records> record){

	   String userID = userProfile.getUserID();
	   String billCycle = "0507";
	   SaveRecords.loaData(record);
	   EditTableDAO.releaseLock(userID, billCycle);
	   return "success";
   }
   
   @RequestMapping(value="/Inwisey/SignOff")
   public @ResponseBody String signOff(){
	   
	   String userID = userProfile.getUserID();
	   String billCycle = "0507";
	   int roleID = userProfile.getRoleID() ;
	   String column="";
	   
	   switch(roleID){
	   case 2:
		  column = "dmSignOff";
		  break;		  
	   case 3:
		   column = "bamSignOff";
		   break;
	   case 4:
		   column = "srBamSignOff";
		   break;
	   case 5:
	   case 6:
		   column = "srBamSignOff";
		   break;
	   case 7:
	   case 8:
		   column = "pmoSignOff";
		   break;
		}
	   
	   
	   SignOffDAO.signOff(billCycle, userID);	   
	   SignOff.updateGroupSignOff(billCycle, roleID, column);
	   
	   GroupSignOff group = SignOff.getAllGroupSignOff(billCycle);
	   
	   if ( group.getDmSignOff() == 1 && group.getBamSignOff() == 1 && group.getSrBamSignOff() == 1 && group.getDpeSignOff() == 1 && group.getPmoSignOff() == 1){
		   BillCycleInfo.markReportAsBilled(billCycle);
	   }
	   
	   return "success";
   }
   
   @RequestMapping("/Inwisey/upload-Files")
   public @ResponseBody void upload(MultipartHttpServletRequest request, HttpServletResponse response) {
	   
	   
   
   /*@RequestMapping("/upload-Files")
   public @ResponseBody String upload(@RequestPart("reportInfo") String reportInfo, @RequestPart("file") MultipartHttpServletRequest request	) {
	   
	 
	   System.out.println(reportInfo);
   */
	   Iterator<String> itr =  request.getFileNames();
       List<MultipartFile> files = request.getFiles(itr.next());
       List<String> fileNames = new ArrayList<String>();
       if (null != files && files.size() > 0) 
       {
           for (MultipartFile multipartFile : files) {

               String fileName = multipartFile.getOriginalFilename();
               fileNames.add(fileName);               
              
               File excelFile = new File("C:\\Users\\IBM_ADMIN\\Desktop\\Workbench", fileName);        
               
               try
               {
                   multipartFile.transferTo(excelFile);
               } catch (IOException e) 
               {
                   e.printStackTrace();
               }
           }
       }
       return;
   }
    
   
   
   @RequestMapping(value = "/Inwisey/admin")
   public ModelAndView showAdminPage() {
	   
	  
	   
	   
	   String billCycle = "0507";
	   
	   GroupSignOff group = SignOff.getAllGroupSignOff(billCycle);
	   
	   
	   Gson gson = new Gson();
	   String json = gson.toJson(group);
	   
       ModelAndView mv = new ModelAndView("/home","group", json);
       return mv;
   }
   
   @RequestMapping(value = "/Inwisey/generate-ILC")
   public @ResponseBody String generateILC(
		   @RequestParam("billCycle") String billCycle,
		   @RequestParam("billCycleType") String billCycleType
		   
		   ) {
	   
	   System.out.println(billCycle);
	   System.out.println(billCycleType);
	   
	   String userID = userProfile.getUserID();
	   try {
           String[] command = {"cmd.exe", "/C", "Start", "C:\\Users\\IBM_ADMIN\\Desktop\\Workbench\\trigger.bat"};
           @SuppressWarnings("unused")
			Process p =  Runtime.getRuntime().exec(command);
            Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
            LoadILCDataDAOService.loadData(billCycle,billCycleType);
            SignOff.insertReportInfo(billCycle, userID, billCycleType);
            
	   } 
        catch (IOException ex) {
    	   System.out.println(ex);
       }	
       return "ILC Report generated successfully";
   }
   
   @RequestMapping(value = "/Inwisey/addUser")
   public ModelAndView addUser(){
	   
	   ModelAndView mv = new ModelAndView("/addUser");
	   return mv;
   }
   
   
}
	   
	   
   
;