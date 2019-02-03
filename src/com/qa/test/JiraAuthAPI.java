package com.qa.test;

import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.payload.Payloads;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

public class JiraAuthAPI extends TestBase {
	
	public JiraAuthAPI() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public static String jiraAuthAPITest()
	{
		
		//Creating session
		
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response res = 
		
		//pass all the header and body
		given().																
			header("Content-Type","application/json").
			body(Payloads.loginPayload(prop.getProperty("username"), prop.getProperty("password"))).//log().all().
		//hit the end point url
		when().																	
			post(prop.getProperty("authResource")).
		
		//All assertions
		then().
			assertThat().
			statusCode(200).
			body("session.name",equalTo("JSESSIONID")).//log().all().
		extract().
			response();

	
		String response = res.asString();
		JsonPath jpath = new JsonPath(response); 
		
		String sessionID = jpath.get("session.value");
		//System.out.println(sessionID);
		
		return sessionID;			
	}

}
