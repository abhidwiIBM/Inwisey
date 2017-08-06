package com.ibm.inwisey.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.ibm.inwisey.model.ILCData;
import com.ibm.inwisey.services.ApplicationContextService;
import com.ibm.inwisey.services.JdbcTemplateService;
import com.ibm.inwisey.services.QueryPropertiesService;

public class DisplayReportDAO {

	private static JdbcTemplate jdbcTemp = ((JdbcTemplateService) ApplicationContextService.getApplicationContext()
			.getBean("jdbcTemplateService")).getJdbcTemp();
	private static Properties myProp = ((QueryPropertiesService) ApplicationContextService.getApplicationContext()
			.getBean("propertyService")).getInstance();

	// private JdbcTemplate jdbcTemp2 = (JdbcTemplate)
	// ApplicationContextService.getApplicationContext().getBean("jdbcTemplate");

	public static ArrayList<ILCData> getReport() {

		ArrayList<ILCData> reportData;
		// reportData = (ArrayList<ILCData>)
		// jdbcTemp2.query(myProp.getProperty("REPORT_DATA"),new
		// RowMapper<ILCData>(){
		reportData = (ArrayList<ILCData>) jdbcTemp.query(myProp.getProperty("REPORT_DATA"), new RowMapper<ILCData>() {

			@Override
			public ILCData mapRow(ResultSet rs, int rownumber) throws SQLException {
				ILCData ilcModel = new ILCData();
				ilcModel.setSeqID(Integer.parseInt(rs.getString("SeqID")));
				ilcModel.setEmpID(rs.getString("EmpID"));
				ilcModel.setEmpName(rs.getString("EmpName"));
				ilcModel.setClaimCode(rs.getString("ClaimCode"));
				ilcModel.setActivity(rs.getString("Activity"));
				ilcModel.setWeekEndDate(rs.getString("WeekEndDate"));
				ilcModel.setTotHrs(Integer.parseInt(rs.getString("TotHrs")));
				ilcModel.setShiftType(rs.getString("ShiftType"));
				ilcModel.setUsInd(rs.getString("UsInd"));
				ilcModel.setOnOffShore(rs.getString("OnOffshore"));
				ilcModel.setBillingType(rs.getString("BillingType"));
				ilcModel.setCategory(rs.getString("Category"));
				ilcModel.setBam(rs.getString("Bam"));
				ilcModel.setAppArea(rs.getString("AppArea"));
				ilcModel.setBusinessArea(rs.getString("BusinessArea"));
				ilcModel.setMonth(rs.getString("Month"));
				ilcModel.setQuarter(rs.getString("Quarter"));
				ilcModel.setDm(rs.getString("Dm"));
				ilcModel.setAsm(rs.getString("Asm"));
				ilcModel.setAsd(rs.getString("Asd"));
				ilcModel.setWrNo(rs.getString("WrNo"));
				ilcModel.setIsTicket(rs.getString("IsTicket"));
				ilcModel.setStaffType(rs.getString("StaffType"));
				ilcModel.setIsCTC(rs.getString("IsCTC"));
				ilcModel.setIsRTC(rs.getString("IsRTC"));
				ilcModel.setPlannedHrs(Integer.parseInt(rs.getString("PlannedHrs")));
				ilcModel.setIsBillable(rs.getString("IsBillable"));
				ilcModel.setRemarks(rs.getString("Remarks"));
				ilcModel.setCtcOrRtc(rs.getString("CtcOrRtc"));
				ilcModel.setWorkType(rs.getString("WorkType"));
				ilcModel.setWrDesc(rs.getString("WrDesc"));
				ilcModel.setCostCenter(rs.getString("CostCenter"));
				ilcModel.setCategory2(rs.getString("Category2"));
				ilcModel.setOnOffLanded(rs.getString("OnOffLanded"));
				ilcModel.setTower(rs.getString("Tower"));
				ilcModel.setLastModified(rs.getString("LastModified"));
				ilcModel.setAsmItwr(rs.getString("AsmItwr"));
				ilcModel.setAsdItwr(rs.getString("AsdItwr"));
				ilcModel.setItwrActual(Integer.parseInt(rs.getString("ItwrActual")));
				ilcModel.setGroupType(rs.getString("GroupType"));
				ilcModel.setVendorClass(rs.getString("VendorClass"));
				ilcModel.setWrIncDef(rs.getString("WrIncDef"));
				ilcModel.setBillCycle(rs.getString("billCycle"));

				return ilcModel;
			}
		});

		return reportData;
	}
}
