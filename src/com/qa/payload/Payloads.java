package com.qa.payload;

public class Payloads {
	
	public static String loginPayload(String uname, String password)
	{
		String payload = "{ \"username\": \""+uname+"\", \"password\": \""+password+"\" }";
		
		return payload;
	}
	
	public static String createIssuePayload(String projectKey, String summary, String issuetypeName)
	{
		String payload = "{\r\n" + 
				"      \"fields\": {\r\n" + 
				"        \"project\": {\r\n" + 
				"          \"key\": \""+projectKey+"\"\r\n" + 
				"        },\r\n" + 
				"        \"summary\": \""+summary+"\",\r\n" + 
				"        \"issuetype\": {\r\n" + 
				"          \"name\": \""+issuetypeName+"\"\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"}";
		
		return payload;
	}
	public static String updateIssuePayload()
	{
		String payload = "{\r\n" + 
				"      \"update\": {\r\n" + 
				"        \"summary\": [\r\n" + 
				"          {\r\n" + 
				"            \"set\": \"New Rest API Defect 3\"\r\n" + 
				"          }]\r\n" + 
				"      }\r\n" + 
				"}";
		
		return payload;
	}

}
