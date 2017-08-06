package com.ibm.inwisey.services;


import org.springframework.jdbc.core.JdbcTemplate;



public class JdbcTemplateService {
	
	
	private static JdbcTemplate jdbcTemp;
	
	
	public JdbcTemplateService(JdbcTemplate jdbcTemplate){
		jdbcTemp = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemp() {
		return jdbcTemp;
	}
	
	
	public void setJdbcTemp(JdbcTemplate jdbcTemplate) {
		jdbcTemp = jdbcTemplate;
	}

}
