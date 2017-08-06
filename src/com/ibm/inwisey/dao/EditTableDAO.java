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

	public static String checkTableLock() {

		try {
			String lockedBy = (String) jdbcTemp.queryForObject(myProp.getProperty("CHECK_TABLE_LOCK"), String.class);
			return lockedBy;
		} catch (EmptyResultDataAccessException e) {
			return "";
		}
	}

	public static void lockTable(String userID) {

		jdbcTemp.update(myProp.getProperty("INSERT_TABLE_LOCK"), new Object[] { userID, 1 });
	}

	public static void releaseLock() {
		jdbcTemp.execute(myProp.getProperty("DELETE_TABLE_LOCK"));
	}
}
