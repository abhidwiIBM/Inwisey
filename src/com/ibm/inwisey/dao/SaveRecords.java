package com.ibm.inwisey.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import com.ibm.inwisey.model.Records;
import com.ibm.inwisey.services.ApplicationContextService;
import com.ibm.inwisey.services.JdbcTemplateService;
import com.ibm.inwisey.services.QueryPropertiesService;

public class SaveRecords {

	public static void loaData(ArrayList<Records> record) {

		JdbcTemplate jdbcTemp = ((JdbcTemplateService) ApplicationContextService.getApplicationContext()
				.getBean("jdbcTemplateService")).getJdbcTemp();
		Properties myProp = ((QueryPropertiesService) ApplicationContextService.getApplicationContext()
				.getBean("propertyService")).getInstance();

		jdbcTemp.batchUpdate(myProp.getProperty("UPDATE_ILC_DATA"), new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {

				String[] rowData = record.get(i).getRowData();
				int seqID = Integer.parseInt(record.get(i).getRowID());

				ps.setString(1, rowData[0]);
				ps.setString(2, rowData[1]);
				ps.setString(3, rowData[2]);
				ps.setString(4, rowData[3]);
				ps.setString(5, rowData[4]);
				ps.setInt(6, Integer.parseInt(rowData[5]));
				ps.setString(7, rowData[6]);
				ps.setString(8, rowData[7]);
				ps.setString(9, rowData[8]);
				ps.setString(10, rowData[9]);
				ps.setString(11, rowData[10]);
				ps.setString(12, rowData[11]);
				ps.setString(13, rowData[12]);
				ps.setString(14, rowData[13]);
				ps.setString(15, rowData[14]);
				ps.setString(16, rowData[15]);
				ps.setString(17, rowData[16]);
				ps.setString(18, rowData[17]);
				ps.setString(19, rowData[18]);
				ps.setString(20, rowData[19]);
				ps.setString(21, rowData[20]);
				ps.setString(22, rowData[21]);
				ps.setString(23, rowData[22]);
				ps.setString(24, rowData[23]);
				ps.setInt(25, Integer.parseInt(rowData[24]));
				ps.setString(26, rowData[25]);
				ps.setString(27, rowData[26]);
				ps.setString(28, rowData[27]);
				ps.setString(29, rowData[28]);
				ps.setString(30, rowData[29]);
				ps.setString(31, rowData[30]);
				ps.setString(32, rowData[31]);
				ps.setString(33, rowData[32]);
				ps.setString(34, rowData[33]);
				ps.setString(35, rowData[34]);
				ps.setString(36, rowData[35]);
				ps.setString(37, rowData[36]);
				ps.setInt(38, Integer.parseInt(rowData[37]));
				ps.setString(39, rowData[38]);
				ps.setString(40, rowData[39]);
				ps.setString(41, rowData[40]);
				ps.setInt(42, seqID);
			}

			@Override
			public int getBatchSize() {
				return record.size();
			}
		});
	}
}
