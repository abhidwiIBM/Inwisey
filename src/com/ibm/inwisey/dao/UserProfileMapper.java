package com.ibm.inwisey.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ibm.inwisey.model.UserProfile;

public class UserProfileMapper implements RowMapper {
	
	@Override
	public UserProfile mapRow(ResultSet rs, int row) throws SQLException {
		
		UserProfile appProfile = new UserProfile();
		
		appProfile.setUserID(rs.getString("empID"));
		appProfile.setUserName(rs.getString("Name"));
		appProfile.setRoleID(rs.getInt("roleID"));
		return appProfile;
		
	}

}
