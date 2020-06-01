package restassured.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import chaining.BaseRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class DeleteIncidents extends BaseRequest {
	

	
	@Test
	public void deleteWIncident()
	{
//		File file = new File("createIncident.json");
		RestAssured.baseURI="https://dev75576.service-now.com/api/now/table";
		
		RestAssured.authentication = RestAssured.basic("admin","vincewins");
		
		ValidatableResponse res = RestAssured.given()
		.contentType(ContentType.JSON)
		
		.log().all()
		.delete("/incident/1c741bd70b2322007518478d83673af3")
		.then().statusCode(204);
		
		
		
	
	}
	
//	@Test(dataProvider="Files")
//	public void createNewIncidentFromDataProvider(String fileName)
//	{
//		
//		File filesFromDP = new File(fileName);
//		
//		RestAssured.baseURI="https://dev75576.service-now.com/api/now/table";
//		
//		RestAssured.authentication = RestAssured.basic("admin","vincewins");
//		
//		Response res = RestAssured.given().log().all()
//		.contentType(ContentType.JSON)
//		.body(filesFromDP)
//		.post("/incident");
//		
//		Assert.assertEquals(res.statusCode(), 201);
//	}

}
