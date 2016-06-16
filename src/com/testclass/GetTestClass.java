package com.testclass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.dataprovider.DataClass;
import com.token.TokenClass;

public class GetTestClass {
	
	@Test(dataProvider="apidata",dataProviderClass=DataClass.class)
	public void getCallAPI(String url1, String stcode, String res,String apiname) throws ParseException 
	{
		
		String acctoken=TokenClass.getToken();
		
		
		double k=Double.parseDouble(stcode);
		int statuscode=(int) k;
		 try {
				URL url = new URL(url1);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Authorization","Bearer "+acctoken);

				if (conn.getResponseCode() != statuscode) 
				{
					System.out.println("Received status Code does not macth with excel status code");
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output=null;
				System.out.println("Response for API:"+apiname+" is ");
				String op=null;
				
				while ((output = br.readLine()) != null) 
				{
					op=output;
					System.out.println(output);
				}
							
				if(op.equalsIgnoreCase(res))
					System.out.println("API Response matches successfully");
				else
					System.out.println("API Response fails to match");
				
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
