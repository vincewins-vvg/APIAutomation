package chaining;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseRequest {
	
	public static String sys_id;
	
	@BeforeSuite
	public void setUp()
	{
		//SetupbaseURI
		RestAssured.baseURI="https://dev75576.service-now.com/api/now/table";
		//SetupbaseAuthentication
		RestAssured.authentication = RestAssured.basic("admin", "vincewins");
		
	}

}
