package com.ibm.inwisey.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ibm.inwisey.model.GroupSignOff;

public class GroupSignOffMapper implements RowMapper {
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		GroupSignOff group = new GroupSignOff();
		group.setDmSignOff(Integer.parseInt(rs.getString("dmSignOff")));
		group.setBamSignOff(Integer.parseInt(rs.getString("bamSignOff")));
		group.setSrBamSignOff(Integer.parseInt(rs.getString("srBamsignOff")));
		group.setDpeSignOff(Integer.parseInt(rs.getString("dpeSignOff")));
		group.setPmoSignOff(Integer.parseInt(rs.getString("pmoSignOff")));
		return group;			
		
	}

}
