package com.ibm.inwisey.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ibm.inwisey.model.GroupSignOff;
import com.ibm.inwisey.model.RoleSignOff;
import com.ibm.inwisey.model.UserProfile;
import com.ibm.inwisey.services.ApplicationContextService;
import com.ibm.inwisey.services.JdbcTemplateService;
import com.ibm.inwisey.services.QueryPropertiesService;

public class SignOff {
	
	
	@Autowired
	UserProfile user;

	static JdbcTemplate jdbcTemp = ((JdbcTemplateService) ApplicationContextService.getApplicationContext()
			.getBean("jdbcTemplateService")).getJdbcTemp();
	static Properties myProp = ((QueryPropertiesService) ApplicationContextService.getApplicationContext()
			.getBean("propertyService")).getInstance();
	
	public static int getGroupSignOff(String roleType, String billCycle){
		
		String sql = "Select " + roleType + " from ilc.reportinfo where billCycle = ?";		
		Integer groupSignOffStatus = (Integer) jdbcTemp.queryForObject(sql, new Object[] { billCycle } ,Integer.class);
		return groupSignOffStatus;
	}
	
	public static ArrayList<RoleSignOff> getSignOffByRole(String billCycle, int roleID){
		
		ArrayList<RoleSignOff> signOffStatus = (ArrayList<RoleSignOff>) jdbcTemp.query(myProp.getProperty("INDIVIDUAL_SIGN_OFF"), new Object[] {billCycle,  roleID }, new RowMapper <RoleSignOff>(){
			
			@Override
			public RoleSignOff mapRow(ResultSet rs, int rowNum) throws SQLException {
				RoleSignOff sign = new RoleSignOff();
				sign.setUserID(rs.getString("empID"));
				sign.setUserName(rs.getNString("Name"));
				sign.setRoleID(Integer.parseInt(rs.getString("roleid")));
				sign.setBillCycle(rs.getString("billCycle"));				
				return sign;				
			}
			
		});
		
		
		return  signOffStatus;
	}
	
	public static int getPendingGroupSignOffCount(String billCycle, int roleID){
		
		Integer count = (Integer) jdbcTemp.queryForObject(myProp.getProperty("COUNT_ROLE_SIGN_OFF"), new Object[] {billCycle, roleID} ,Integer.class);
		return count;
	}
	
	public static GroupSignOff getAllGroupSignOff(String billCycle){
		
		
		
		GroupSignOff allGroupStatus = new GroupSignOff();
		try{
		allGroupStatus = (GroupSignOff) jdbcTemp.queryForObject(myProp.getProperty("GET_ALLGROUP_SIGN_OFF"), new Object[] {billCycle}, new GroupSignOffMapper());
		}
		catch(EmptyResultDataAccessException e){
			allGroupStatus.setDmSignOff(0);
			allGroupStatus.setBamSignOff(0);
			allGroupStatus.setSrBamSignOff(0);
			allGroupStatus.setDpeSignOff(0);
			allGroupStatus.setPmoSignOff(0);
			
		}
		
		return allGroupStatus;
	}
	
	public static void updateGroupSignOff(String billCycle, int roleID, String column ){
		
		int count = getPendingGroupSignOffCount(billCycle, roleID);
		
		String sql = "update ilc.reportinfo set " +column +  "= ? where billCycle = ?";
		if(count == 0){			
			jdbcTemp.update(sql, new Object[] {2, billCycle });
		}
		if(count > 0){			
			jdbcTemp.update(sql, new Object[] {1, billCycle });
		}
		
	}
	
	public static void insertReportInfo(String billCycle, String userID , String billCycleType){		
				
			jdbcTemp.update(myProp.getProperty("INSERT_REPORT_INFO"), new Object[] { billCycle, userID, billCycleType });
		}
	
	
}
