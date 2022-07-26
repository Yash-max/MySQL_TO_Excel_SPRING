package com.example.report.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {
	
	private static final String FILE_NAME = "/home/auriga/report.xlsx";
	String []PageNames = new String[] {"Payment Page", "QR Scanner Page", "Enter Amount Page", "Confirm Payment Page", "Enter UPI Page"};
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() throws InvalidFormatException, IOException {
		System.out.println("Home");
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("All Time In Seconds");
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		
		Map<String, Object[]> data = new TreeMap<>();
		
		data.put("1", new Object[] { "All Time In Seconds" });
		data.put("2", new Object[] { "Configuration", "Paytm" });
		data.put("3", PageNames);
//		data.put("3", new Object[] { "Network", "Sufficient/Insufficient Balance", "Start Time", "Link Generate", "Link Send To SMS", "PSP(Payment Selection Page)", "Bank Page", "UPI", "Success Page", "Get Status Of Payment" });
//		data.put("4", new Object[] { "ID", "NAME", "LASTNAME" });
		
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			  
            // Creating a new row in the sheet
            Row row = sheet.createRow(rownum++);
  
            Object[] objArr = data.get(key);
  
            int cellnum = 0;
  
            for (Object obj : objArr) {
  
                // This line creates a cell in the next
                //  column of that row
                Cell cell = row.createCell(cellnum++);
                cell.setCellStyle(cellStyle);
                
                if (obj instanceof String)
                    cell.setCellValue((String)obj);
  
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
                
                
            }
            sheet.autoSizeColumn(rownum-1);
//            sheet.autoSizeColumn(row.getRowNum());
        }
		
		
		sheet.addMergedRegion(CellRangeAddress.valueOf("A1:J1"));
		
//		File file = new File(FILE_NAME);
//		if(file.exists() == false) {
//			file = new File(FILE_NAME);
//		}
		
		FileOutputStream out = new FileOutputStream(FILE_NAME);
        workbook.write(out);
        out.close();
		
		return "home";
	}
}
