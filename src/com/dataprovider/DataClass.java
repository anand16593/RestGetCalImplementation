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
		HSSFRow row=sh.getRow(0);
		
		int noofrows=sh.getLastRowNum();
		
		int noofcolumn=row.getLastCellNum();
		String newurl = null;
		
		String data[][]=new String[noofrows][noofcolumn];
		for(int i=1;i<=noofrows;i++)
		{
			HSSFRow rowdata=sh.getRow(i);
		    String url=rowdata.getCell(0).toString();
		
				String paramcount=rowdata.getCell(1).toString();
				
				if(!paramcount.equals("1.0"))
				{
					
				}
				else
				{
				  	String param=rowdata.getCell(2).toString();
				  	
				  	int len=param.length();
				  	String s=param.substring(1,len-1);
				    newurl=url.replaceAll("var1",s);//This is complete URl
				}
		
		      //Get response code and response data to compare.
				String rescode= rowdata.getCell(3).toString();
				String resdata= rowdata.getCell(5).toString();
				
				data[i-1][0]=newurl;
				data[i-1][1]=rescode;
				data[i-1][2]=resdata;
				
		}
		return data;
		
	}	
}
