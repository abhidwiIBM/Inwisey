package com.ibm.inwisey.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InwiseyUtil {
	
	public static String getDate(String date){
		String destStr="";
		try{
		DateFormat srcDt  = new SimpleDateFormat("mm/dd/yyyy");
		Date midDt        = srcDt.parse(date);
		DateFormat destDf = new SimpleDateFormat("yyyy-mm-dd");
		destStr           = destDf.format(midDt);
		
		}catch(ParseException e)
		{
			System.out.println(e);
		}
		return destStr;
	}

}
