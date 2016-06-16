package com.token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TokenClass 
{
	public static String access_token;
	public static String access_token2;
	public static String getToken() throws ParseException
	{	
		Get_access_token();
		Generate_OTP();
		Get_me_in_by_OTP();
		return access_token2;
		
	}


	public static void Get_access_token() throws ParseException
	{  
		String newurl="http://tstapi.bankoncube.com/v1/api/oauth/token?grant_type=client_credentials";
		HttpURLConnection conn;
		try {
			URL url = new URL(newurl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Basic Y3ViZWFwcDpjdWJlYXBwJDEyMw==");


			//System.out.println("response code: "+conn.getResponseCode() );

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));


			String output=null;
			//System.out.println("Output from Server .... \n");
			String op=null;

			while ((output = br.readLine()) != null) 
			{
				op=output;
				//System.out.println(output);
			}			JSONParser parser=new JSONParser();
			JSONObject jso=(JSONObject)parser.parse(op);
			access_token = (String) jso.get("access_token");

			//System.out.println("access token value is: "+access_token);

		} catch (IOException e) {
			System.out.println("Exception :"+e);
			e.printStackTrace();
		}	
	}  


	public static void Generate_OTP() throws ParseException
	{
		String newurl="http://tstapi.bankoncube.com/v1/user/otp?identifier=8412121212";
		HttpURLConnection conn;
		try {
			URL url = new URL(newurl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");			 
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Authorization","Bearer "+access_token);


			//System.out.println("response code: "+conn.getResponseCode() );

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output=null;
			//System.out.println("Output from Server .... \n");
			String op=null;

			while ((output = br.readLine()) != null) 
			{
				op=output;
				//System.out.println(output);
			}

			JSONParser parser=new JSONParser();
			JSONObject jso=(JSONObject)parser.parse(op);
			String msg = (String) jso.get("message");
			//System.out.println("Message is: "+msg);
			
			
			conn.disconnect();

		} catch (IOException e) {
			System.out.println("Exception :"+e);
			e.printStackTrace();
		}	
	}  
	

	public static void Get_me_in_by_OTP() throws ParseException
	{
		String newurl="http://tstapi.bankoncube.com/v1/api/oauth"
				+ "/token?identifier=8412121212&passcode=1234&grant_type=onetimepass";
		HttpURLConnection conn;
		try {
			URL url = new URL(newurl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");			 
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Authorization","Basic Y3ViZWFwcDpjdWJlYXBwJDEyMw==");


			//System.out.println("response code: "+conn.getResponseCode() );

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output=null;
			//System.out.println("Output from Server .... \n");
			String op=null;

			while ((output = br.readLine()) != null) 
			{
				op=output;
				//System.out.println(output);
			}

			JSONParser parser=new JSONParser();
			JSONObject jso=(JSONObject)parser.parse(op);
			access_token2 = (String) jso.get("access_token");

			//System.out.println("your access toekn is: "+access_token2);
			conn.disconnect();

		} catch (IOException e) {
			System.out.println("Exception :"+e);
			e.printStackTrace();
		}	

	} 


} 