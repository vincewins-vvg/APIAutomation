package restassured.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.Request;
import org.testng.annotations.Test;

import com.beust.jcommander.internal.Maps;
import static org.hamcrest.Matchers.is; 
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class JIRAScenarios {
	
//1) Get all the issues in Jira
//a) Print the count of JIRA issues
//b) Print the last issue id
	

	@Test
	public void getAllJIRA()
	{
		
		 //Set Base URI
		
		RestAssured.baseURI="https://api-mar2020.atlassian.net/rest/api/2";
				
		//Set Authentication
		RestAssured.authentication = RestAssured.preemptive().basic("rajalakshmi.govindarajan@testleaf.com", "kEJxzmhkQzvdeP8iysWN2D1B");

		
				
		Response response1 = RestAssured.given().log().all()
	   //.accept(ContentType.XML)	
		.get("/search");
		
		System.out.println(response1.getStatusCode());
		
		JsonPath json = response1.jsonPath();
		
		List<String> noOfIds = json.getList("issues.");
		List<String> noOfIdss = json.getList("total");
		
		
		
		int size = noOfIds.size();
		
		
		System.out.println("No of issues is "+size);
		
		String lastIssue = noOfIds.get(size-1);
		System.out.println("The last issue is "+lastIssue);
		
		
			
		
	}
	
	@Test
	public void getSpecificJIRA()
	{
		
		 //Set Base URI
		
		RestAssured.baseURI="https://api-mar2020.atlassian.net/rest/api/2";
				
		//Set Authentication
		RestAssured.authentication = RestAssured.preemptive().basic("rajalakshmi.govindarajan@testleaf.com", "kEJxzmhkQzvdeP8iysWN2D1B");

		
				
		Response response1 = RestAssured.given().log().all()
	   //.accept(ContentType.XML)	
		.get("/issue/11442")
		.then()
		.extract().response();
		
		System.out.println(response1.getStatusCode());
		
		JsonPath json = response1.jsonPath();

		
		
		
		
		
		//List<String> noOfIds = json.getList("issues[*]");
//		List<String> noOfIdss = json.getList("total");
//		
//		
//		
//		int size = noOfIds.size();
		
		
		
		
			
		
	}
	
	//2) Get Only the issues that are
	//- with Priority: Medium and has attachments
	//- Print all attachment names

	
	public void getMediumIssues()
	{
        //Set Base URI
		RestAssured.baseURI="https://api-mar2020.atlassian.net/rest/api/2";
				
		//Set Authentication
		RestAssured.authentication = RestAssured.preemptive().basic("rajalakshmi.govindarajan@testleaf.com", "kEJxzmhkQzvdeP8iysWN2D1B");
 
		Response response1 = RestAssured.given().log().all()
				   //.accept(ContentType.XML)	
					.get("/search");
					
					System.out.println(response1.getStatusCode());
					System.out.println(response1.prettyPrint());
//					//For traversing to JSON field
					JsonPath json = response1.jsonPath();
					
					List<String> noOfIds = json.getList("issues[*]");
					
					
					
					int size = noOfIds.size();
					
					
					System.out.println("No of issues is "+size);
					
					String lastIssue = noOfIds.get(size-1);
					System.out.println("The last issue is "+lastIssue);
		
	}

}
