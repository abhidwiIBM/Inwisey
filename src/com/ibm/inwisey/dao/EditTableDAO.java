package com.ibm.inwisey.dao;

import java.util.Properties;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ibm.inwisey.services.ApplicationContextService;
import com.ibm.inwisey.services.JdbcTemplateService;
import com.ibm.inwisey.services.QueryPropertiesService;

public class EditTableDAO {

	private static JdbcTemplate jdbcTemp = ((JdbcTemplateService) ApplicationContextService.getApplicationContext()
			.getBean("jdbcTemplateService")).getJdbcTemp();
	private static Properties myProp = ((QueryPropertiesService) ApplicationContextService.getApplicationContext()
			.getBean("propertyService")).getInstance();

	public static String checkTableLock(String billCycle) {

		try {
			String lockedBy = (String) jdbcTemp.queryForObject(myProp.getProperty("CHECK_TABLE_LOCK"), new Object[] { billCycle } ,String.class);
			return lockedBy;
		} catch (EmptyResultDataAccessException e) {
			return "";
		}
	}

	public static void lockTable(String billCycle , String userID) {

		jdbcTemp.update(myProp.getProperty("UPDATE_TABLE_LOCK"), new Object[] {userID, billCycle });
	}

	public static void releaseLock(String userID, String billCycle) {
		jdbcTemp.update(myProp.getProperty("DELETE_TABLE_LOCK"), new Object [] {userID, billCycle});
	}
}
