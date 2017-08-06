package com.ibm.inwisey.dao;


import java.util.Properties;
import org.springframework.jdbc.core.JdbcTemplate;
import com.ibm.inwisey.services.ApplicationContextService;
import com.ibm.inwisey.services.JdbcTemplateService;
import com.ibm.inwisey.services.QueryPropertiesService;

public class SignOffDAO {
	
	static JdbcTemplate jdbcTemp = ((JdbcTemplateService) ApplicationContextService.getApplicationContext()
			.getBean("jdbcTemplateService")).getJdbcTemp();
	static Properties myProp = ((QueryPropertiesService) ApplicationContextService.getApplicationContext()
			.getBean("propertyService")).getInstance();
	
	public static void signOff(String billCycle, String userID) {
		jdbcTemp.update(myProp.getProperty("INSERT_SIGN_OFF"), new Object[] { billCycle, userID });

	}
	
}
