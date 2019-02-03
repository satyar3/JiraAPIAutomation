package com.qa.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.payload.Payloads;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UpdateIssue extends TestBase{

	public UpdateIssue() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void editIssueAPI()
	{
		
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response res = 
		
		//Header and Body
		given().
			header("Content-Type","application/json").
			header("Cookie","JSESSIONID="+JiraAuthAPI.jiraAuthAPITest()+"").
			body(Payloads.updateIssuePayload()).log().all().
		//resource details
		when().
			put(prop.getProperty("updateIssueResource")+CreateIssueAPI.issueID).
		//assertion
		then().
		//extract details 
			assertThat().
			statusCode(204).log().all().
		extract().
			response();
		
		String response = res.asString();
		JsonPath jpath = new JsonPath(response);
		
		
	}
	

}
