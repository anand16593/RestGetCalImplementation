package com.dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class DataClass {

	@org.testng.annotations.DataProvider(name="apidata")
	public static String[][] data() throws IOException
	{
		DataClass ds=new DataClass();
		String[][] data=ds.data2();
		return data;		
	}

	
	public String[][] data2() throws IOException 
	{
		FileInputStream fis=new FileInputStream(new File("Data.xls"));
	
		
		HSSFWorkbook wb=new HSSFWorkbook(fis);
		HSSFSheet sh=wb.getSheet("abc");
		int noofrows=sh.getLastRowNum();
		int noofcolumn=4;//Fixed for now
		
		String data[][]=new String[noofrows][noofcolumn];
		
		for(int i=1;i<=noofrows;i++)
		{
				HSSFRow rowdata=sh.getRow(i);
				
				String url=rowdata.getCell(0).toString();
				String rescode= rowdata.getCell(1).toString();
				String resdata= rowdata.getCell(2).toString();
				String apiname= rowdata.getCell(3).toString();
				
				data[i-1][0]=url;
				//System.out.println("api is "+url);
				data[i-1][1]=rescode;
				data[i-1][2]=resdata.trim().replaceAll("\\s+", "");
				//System.out.println(data[i-1][2]);
				data[i-1][3]=apiname;
		}
		return data;		
	}	
}
