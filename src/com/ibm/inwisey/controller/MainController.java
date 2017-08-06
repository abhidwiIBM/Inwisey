package com.ibm.inwisey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.inwisey.dao.DisplayReportDAO;
import com.ibm.inwisey.dao.EditTableDAO;
import com.ibm.inwisey.dao.SaveRecords;
import com.ibm.inwisey.dao.SignOffDAO;
import com.ibm.inwisey.model.ILCData;
import com.ibm.inwisey.model.Records;
import com.ibm.inwisey.services.LoadILCDataDAOService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


@Controller
public class MainController {
	
	@RequestMapping(value="/")
	public String renderLogin(){
		return "login";
	}
   
   
   @RequestMapping(value="/table")
   public ModelAndView renderTable() {
	  List<ILCData> ilcData = new ArrayList<ILCData>();	   
	  ilcData = DisplayReportDAO.getReport();	   
	   ModelAndView mv = new ModelAndView("/tableData"); 
      mv.addObject("ilcDataList", ilcData);
       return mv;
   }
 
   
   @RequestMapping(value="/editReport")
   public @ResponseBody String editTable(
		   @RequestParam("userID") String userID
		   	) {
	   
	   String lockedBy = EditTableDAO.checkTableLock();	  
	   if( lockedBy.equals("")){
		   EditTableDAO.lockTable(userID);
		   return "success";
	   }
	   else{
		   System.out.println("Table is locked by " + lockedBy);
		   return lockedBy;
		}
	   }
   
   @RequestMapping(value="/Inwisey/Save")
   public @ResponseBody String saveData(@RequestBody  ArrayList<Records> record){
	   SaveRecords.loaData(record);
	   EditTableDAO.releaseLock();
	   return "success";
   }
   
   @RequestMapping(value="/Inwisey/SignOff")
   public @ResponseBody String signOff(@RequestParam("userID")  String userID, @RequestParam("billCycle")  String billCycle){
	   
	   SignOffDAO.signOff(billCycle, userID);	   
	   return "success";
   }
   
   @RequestMapping("/upload-Files")
   public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response) {
   
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

     
       return "Files Uploaded Successfully. Click on Generate Report to generate ILC Files.";
   }
    
   
   
   @RequestMapping(value = "/admin")
   public ModelAndView showAdminPage() {
       ModelAndView mv = new ModelAndView("/home");
       return mv;
   }
   
   @RequestMapping(value = "/generate-ILC")
   public String generateILC() {
	   try {
           String[] command = {"cmd.exe", "/C", "Start", "C:\\Users\\IBM_ADMIN\\Desktop\\Workbench\\trigger.bat"};
           @SuppressWarnings("unused")
			Process p =  Runtime.getRuntime().exec(command);
            Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
            LoadILCDataDAOService.loadData();
            
       } catch (IOException ex) {
    	   System.out.println(ex);
       }	
       return "ILC Report generated successfully";
   }
}
	   
	   
   
