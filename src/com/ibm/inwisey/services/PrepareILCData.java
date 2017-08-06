package com.ibm.inwisey.services;

import java.util.Map;

import com.ibm.inwisey.model.ILCData;

public class PrepareILCData {
	
	public static void setILCData(Map<String,String> rowData, ILCData ilcModel){
		
		ilcModel.setEmpID(rowData.get("Emp Ser Num"));
		ilcModel.setEmpName(rowData.get("EMPLOYEE_NAME"));
		ilcModel.setClaimCode(rowData.get("Work Item"));
		ilcModel.setActivity(rowData.get("Activity"));
		ilcModel.setWeekEndDate(rowData.get("Week Ending Date"));
		ilcModel.setTotHrs(Integer.parseInt(rowData.get("Total Hrs")));
		ilcModel.setShiftType(rowData.get("ShiftDetails"));
		ilcModel.setUsInd(rowData.get("US/INDIA"));
		ilcModel.setOnOffShore(rowData.get("On/Offshore"));
		ilcModel.setBillingType(rowData.get("Billing Type"));
		ilcModel.setCategory(rowData.get("Category"));
		ilcModel.setBam(rowData.get("BAM"));
		ilcModel.setAppArea(rowData.get("APP AREA"));
		ilcModel.setBusinessArea(rowData.get("Business Area"));
		ilcModel.setMonth(rowData.get("Month"));
		ilcModel.setQuarter(rowData.get("Quarter"));
		ilcModel.setDm(rowData.get("DM"));
		ilcModel.setAsm(rowData.get("ASM"));
		ilcModel.setAsd(rowData.get("ASD"));
		ilcModel.setWrNo(rowData.get("WR #"));
		ilcModel.setIsTicket(rowData.get("Is Ticket ?"));
		ilcModel.setStaffType(rowData.get("BASE/Staff Aug"));
		ilcModel.setIsCTC(rowData.get("CTC WR"));
		ilcModel.setIsRTC(rowData.get("RTC WR"));
		ilcModel.setPlannedHrs(Integer.parseInt(rowData.get("Planned Hours")));
		ilcModel.setIsBillable(rowData.get("Billable?"));
		ilcModel.setRemarks(rowData.get("Remarks"));
		ilcModel.setCtcOrRtc(rowData.get("CTC/RTC"));
		ilcModel.setWorkType(rowData.get("Work Type"));
		ilcModel.setWrDesc(rowData.get("WR Description"));
		ilcModel.setCostCenter(rowData.get("Cost Center"));
		ilcModel.setCategory2(rowData.get("Category 2"));
		ilcModel.setOnOffLanded(rowData.get("OnOffLanded"));
		ilcModel.setTower(rowData.get("Tower"));
		ilcModel.setLastModified(rowData.get("Latest Modified Time"));
		ilcModel.setAsmItwr(rowData.get("ASM (ITWR)"));
		ilcModel.setAsdItwr(rowData.get("ASD (ITWR)"));
		ilcModel.setItwrActual(Integer.parseInt(rowData.get("ITWR Actuals")));
		ilcModel.setGroupType(rowData.get("Group"));
		ilcModel.setVendorClass(rowData.get("Vendor Classification"));
		ilcModel.setWrIncDef(rowData.get("WR/INC/DEF"));
	}

}
