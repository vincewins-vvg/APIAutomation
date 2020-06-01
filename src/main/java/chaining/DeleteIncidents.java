package chaining;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class DeleteIncidents extends BaseRequest {
	
	
	@Test(dependsOnMethods = {"chaining.CreateIncidentsUsingFiles.createNewIncident"})
	public void deleteIncidentChain()
	{
//		
		
		ValidatableResponse res = RestAssured.given()
		.contentType(ContentType.JSON)
		
		.log().all()
		.delete("/incident/"+sys_id)
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
