package com.ibm.inwisey.dao;

import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ibm.inwisey.services.ApplicationContextService;
import com.ibm.inwisey.services.JdbcTemplateService;
import com.ibm.inwisey.services.QueryPropertiesService;

public class BillCycleInfo {
	
	static JdbcTemplate jdbcTemp = ((JdbcTemplateService) ApplicationContextService.getApplicationContext()
			.getBean("jdbcTemplateService")).getJdbcTemp();
	static Properties myProp = ((QueryPropertiesService) ApplicationContextService.getApplicationContext()
			.getBean("propertyService")).getInstance();
	
	
	public static int getBillCycleStatus(String billCycle){
		
		Integer billCycleStatus = (Integer) jdbcTemp.queryForObject(myProp.getProperty("GET_BILLCYCLE_STATUS"),new Object[] {billCycle}, Integer.class);		
		return billCycleStatus;
	}
	
	public static void markReportAsSLA(String billCycle){
		
		jdbcTemp.update(myProp.getProperty("UPDATE_BILLCYLE_STATUS"), new Object[] {billCycle, 1});
		
	}
	
    public static void markReportAsBilled(String billCycle){
		
		jdbcTemp.update(myProp.getProperty("UPDATE_BILLCYLE_STATUS"), new Object[] {billCycle , 2});
		
	}

}
