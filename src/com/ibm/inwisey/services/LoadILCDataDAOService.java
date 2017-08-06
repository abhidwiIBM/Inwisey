package com.ibm.inwisey.services;

import java.util.ArrayList;


import com.ibm.inwisey.dao.LoadILCDataDAO;
import com.ibm.inwisey.model.ILCData;


public class LoadILCDataDAOService {
			
			private static ArrayList<ILCData> ilcModelList;
			
			public static void loadData(){
			
			ilcModelList = FetchExcelDataService.getDataFromExcel();
			LoadILCDataDAO.loadData(ilcModelList);
			System.out.println("Data Loaded successfully");
			
			}
	
}


	
	

