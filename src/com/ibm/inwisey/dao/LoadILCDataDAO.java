package com.ibm.inwisey.dao;

import java.util.ArrayList;
import java.util.Properties;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import com.ibm.inwisey.model.ILCData;
import com.ibm.inwisey.services.ApplicationContextService;
import com.ibm.inwisey.services.JdbcTemplateService;
import com.ibm.inwisey.services.QueryPropertiesService;
import com.ibm.inwisey.util.InwiseyUtil;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoadILCDataDAO {

	public static void loadData(ArrayList<ILCData> ilcModelList, String billCycle, String billCycleType) {

		JdbcTemplate jdbcTemp = ((JdbcTemplateService) ApplicationContextService.getApplicationContext()
				.getBean("jdbcTemplateService")).getJdbcTemp();
		Properties myProp = ((QueryPropertiesService) ApplicationContextService.getApplicationContext()
				.getBean("propertyService")).getInstance();

		jdbcTemp.batchUpdate(myProp.getProperty("INSERT_ILC_DATA"), new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, ilcModelList.get(i).getEmpID());
				ps.setString(2, ilcModelList.get(i).getEmpName());
				ps.setString(3, ilcModelList.get(i).getClaimCode());
				ps.setString(4, ilcModelList.get(i).getActivity());
				ps.setString(5, InwiseyUtil.getDate(ilcModelList.get(i).getWeekEndDate()));
				ps.setInt(6, ilcModelList.get(i).getTotHrs());
				ps.setString(7, ilcModelList.get(i).getShiftType());
				ps.setString(8, ilcModelList.get(i).getUsInd());
				ps.setString(9, ilcModelList.get(i).getOnOffShore());
				ps.setString(10, ilcModelList.get(i).getBillingType());
				ps.setString(11, ilcModelList.get(i).getCategory());
				ps.setString(12, ilcModelList.get(i).getBam());
				ps.setString(13, ilcModelList.get(i).getAppArea());
				ps.setString(14, ilcModelList.get(i).getBusinessArea());
				ps.setString(15, ilcModelList.get(i).getMonth());
				ps.setString(16, ilcModelList.get(i).getQuarter());
				ps.setString(17, ilcModelList.get(i).getDm());
				ps.setString(18, ilcModelList.get(i).getAsm());
				ps.setString(19, ilcModelList.get(i).getAsd());
				ps.setString(20, ilcModelList.get(i).getWrNo());
				ps.setString(21, ilcModelList.get(i).getIsTicket());
				ps.setString(22, ilcModelList.get(i).getStaffType());
				ps.setString(23, ilcModelList.get(i).getIsCTC());
				ps.setString(24, ilcModelList.get(i).getIsRTC());
				ps.setInt(25, ilcModelList.get(i).getPlannedHrs());
				ps.setString(26, ilcModelList.get(i).getIsBillable());
				ps.setString(27, ilcModelList.get(i).getRemarks());
				ps.setString(28, ilcModelList.get(i).getCtcOrRtc());
				ps.setString(29, ilcModelList.get(i).getWorkType());
				ps.setString(30, ilcModelList.get(i).getWrDesc());
				ps.setString(31, ilcModelList.get(i).getCostCenter());
				ps.setString(32, ilcModelList.get(i).getCategory2());
				ps.setString(33, ilcModelList.get(i).getOnOffLanded());
				ps.setString(34, ilcModelList.get(i).getTower());
				ps.setString(35, ilcModelList.get(i).getLastModified());
				ps.setString(36, ilcModelList.get(i).getAsmItwr());
				ps.setString(37, ilcModelList.get(i).getAsdItwr());
				ps.setInt(38, ilcModelList.get(i).getItwrActual());
				ps.setString(39, ilcModelList.get(i).getGroupType());
				ps.setString(40, ilcModelList.get(i).getVendorClass());
				ps.setString(41, ilcModelList.get(i).getWrIncDef());
				ps.setString(42, billCycle);
			}

			@Override
			public int getBatchSize() {
				return ilcModelList.size();
			}
		});

	}

}
