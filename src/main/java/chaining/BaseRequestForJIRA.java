package chaining;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseRequestForJIRA {
	
	public static String issueId;
	
	@BeforeSuite
	public void setUp()
	{
		//SetupbaseURI
		RestAssured.baseURI="https://api-mar2020.atlassian.net/rest/api/2";
		
		//SetupbaseAuthentication
		RestAssured.authentication = RestAssured.preemptive().basic("rajalakshmi.govindarajan@testleaf.com", "kEJxzmhkQzvdeP8iysWN2D1B");
		
	}

}
