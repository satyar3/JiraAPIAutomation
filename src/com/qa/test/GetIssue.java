package com.qa.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.qa.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetIssue extends TestBase{
	
	public GetIssue() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void getIssueAPITest()
	{
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response res = 
		
		given().
			header("Accept","application/json").
			//pathParam("issueId", "TEST-30").
			log().all().
		when().
			get(prop.getProperty("getIssueResource")+"TES-30").
			//get("/rest/api/2/issue/{issueId").
		then().
			assertThat().
			statusCode(200).log().all().
		extract().
			response();
		
		String responsestring = res.asString();
		System.out.println(responsestring);
		JsonPath jpath = new JsonPath(responsestring);
		
		String id = jpath.get("id");
		System.out.println(id);
	}

}
