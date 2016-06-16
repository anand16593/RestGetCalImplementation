package com.testclass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.testng.annotations.Test;

import com.dataprovider.DataClass;

public class GetTestClass {
	
	@Test(dataProvider="apidata",dataProviderClass=DataClass.class)
	public void getCallAPI(String newurl, String stcode, String res,String a1,String a2,String a3,String a4,String a5) 
	{
		
		//System.out.println("url: "+newurl);
		//System.out.println("status code: "+stcode);
		//System.out.println("response data: "+res);
		
		double k=Double.parseDouble(stcode);
		int statuscode=(int) k;
		
	
		 try {

				URL url = new URL(newurl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != statuscode) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output=null;
				System.out.println("Output from Server .... \n");
				String op=null;
				
				while ((output = br.readLine()) != null) 
				{
					op=output;
					System.out.println(output);
				}
							
				if(op.equalsIgnoreCase(res))
					System.out.println("I am right here");
				else
					System.out.println("something went wrong");
				conn.disconnect();

			  } catch (MalformedURLException e) {
				  System.out.println("in the catch block");

				e.printStackTrace();

			  } catch (IOException e) {
				  System.out.println("in the catch block");

				e.printStackTrace();

			  }
		
	}

}
