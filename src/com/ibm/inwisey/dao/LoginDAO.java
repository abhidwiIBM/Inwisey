package com.ibm.inwisey.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ibm.inwisey.model.UserProfile;
import com.ibm.inwisey.services.ApplicationContextService;
import com.ibm.inwisey.services.JdbcTemplateService;
import com.ibm.inwisey.services.QueryPropertiesService;

public class LoginDAO {
	
	private static JdbcTemplate jdbcTemp = ((JdbcTemplateService) ApplicationContextService.getApplicationContext()
			.getBean("jdbcTemplateService")).getJdbcTemp();
	private static Properties myProp = ((QueryPropertiesService) ApplicationContextService.getApplicationContext()
			.getBean("propertyService")).getInstance();
	
	
	public static String validateUser(String userID, String password){
		
		try{
		int count = (Integer) jdbcTemp.queryForObject(myProp.getProperty("VALIDATE_USER"), new Object [] {userID , password}, Integer.class);
		
		if (count == 0 ) {
			return "0";					
		}
		else{
			return "1";
		}
		}
		catch(EmptyResultDataAccessException e){
			return "0";
		}
		
	
		
	}
	
public static UserProfile setProfile(String userID){
	
	UserProfile appProfile = (UserProfile) jdbcTemp.queryForObject(myProp.getProperty("CREATE_USER_PROFILE"), new Object[] {userID}, new UserProfileMapper());
		
	return appProfile;
	}

}
