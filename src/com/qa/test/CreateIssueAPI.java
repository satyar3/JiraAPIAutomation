package com.qa.test;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.payload.Payloads;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import com.qa.util.TestUtil;

public class CreateIssueAPI extends TestBase{

	public CreateIssueAPI() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static String issueID;
	
	@DataProvider
	public Object[][] getIssueDetails() {
		Object ob[][] = TestUtil.getTestData("IssueData");
		return ob;
	}

	@Test(dataProvider = "getIssueDetails")
	public static void createIssue(String projectKey, String summary, String issuetypeName)
	{
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response res = 
		
		//header and body
		given().
			header("Content-Type","application/json").
			header("Cookie","JSESSIONID="+JiraAuthAPI.jiraAuthAPITest()+"").	//getting the sessionID
			body(Payloads.createIssuePayload(projectKey, summary, issuetypeName)).log().all().
		
		//end point url
		when().
			post(prop.getProperty("createIssueResource")).
		//Assertion
		then().
			assertThat().
			statusCode(201).log().all().
		extract().
			response();
		
		String response = res.asString();
		JsonPath jpath = new JsonPath(response);
		
		issueID = jpath.get("id");
		//System.out.println(issueID);
		
		
	}

}
