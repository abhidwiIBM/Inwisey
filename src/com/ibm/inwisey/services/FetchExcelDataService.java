package com.ibm.inwisey.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.ibm.inwisey.model.ILCData;



public class FetchExcelDataService {

	
	public static FileInputStream ilcInput;
	public static XSSFWorkbook ilcBook;
	public static XSSFSheet ilcSheet;
	public static XSSFRow row;
	public static ArrayList<ILCData> ilcModelList;
	public static Map<String,String> rowData;
	public static Iterator<Row> rowIterator;
	public static Iterator<Cell> cellIterator;
	public static ILCData ilcModel;
	public static Cell cell;
	public static DataFormatter formatter;
	public static String colName;
	public static String colVal;
	public static String numColName;
	public static String numColVal;
	public static CellType BLANK;
	public static boolean dataExists;
	public static String curRowData;
	public static int curRow;
	public static CellType cellType;
	
	
	public static ArrayList<ILCData> getDataFromExcel(){

		try{
				ilcInput        = new FileInputStream(new File("C:\\ILCData.xlsx"));	
				ilcBook         = new XSSFWorkbook(ilcInput);
				ilcSheet        = ilcBook.getSheet("WNPPT");	
				rowIterator     = ilcSheet.iterator();				
				ilcModelList    = new ArrayList<ILCData>();
				dataExists      = true;
				curRow          = 1;
				
		
			while(dataExists){		
				row          = (XSSFRow) rowIterator.next();		
				cellIterator = row.cellIterator();
				rowData         = new HashMap<String,String>();
				ilcModel     = new ILCData();			
				
				while(cellIterator.hasNext()){
					cell = cellIterator.next();
					cellType = ilcSheet.getRow(curRow).getCell(cell.getColumnIndex()).getCellTypeEnum();
					switch (cellType){
					case STRING:					
						colName = (ilcSheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue()).trim();					
						colVal  = (ilcSheet.getRow(curRow).getCell(cell.getColumnIndex()).getStringCellValue()).trim();
						System.out.println(colName + " - " + colVal );
						rowData.put(colName, colVal);					
						break;
					case NUMERIC:
						numColName = ilcSheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue();
						numColVal  =  Long.toString(Math.round(ilcSheet.getRow(curRow).getCell(cell.getColumnIndex()).getNumericCellValue()));					
						rowData.put(numColName, numColVal);					
						break;			
					default:
						break;
					}				
				}
					curRow++;
					curRowData = ilcSheet.getRow(curRow).getCell(0).getStringCellValue();			
					dataExists = (curRowData != "");
					PrepareILCData.setILCData(rowData,ilcModel);				
					ilcModelList.add(ilcModel);
					rowData = null;
					ilcModel=null;
				}
			
				return ilcModelList;
				
		}catch(FileNotFoundException fe){
			System.out.println("File can not be located: " + fe.getStackTrace());
			return ilcModelList;
		}catch(Exception e){
			System.out.println("Error occured:" + e);
			return ilcModelList;
		}finally{	
			try{
			ilcBook.close();	
			ilcInput.close();
			}catch(Exception e){
				System.out.println("Exception occured while closing connection");
			}
		}
		
	}
}
