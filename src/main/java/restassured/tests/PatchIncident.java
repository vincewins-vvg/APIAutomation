package restassured.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class PatchIncident {
	
	@DataProvider(name="Files", parallel=true)
	public String[] getFiles()
	{
		String[] files = new String[2];
		files[0]="createIncident.json";
		files[1]="createIncident2.json";
		
		return files;
	}
	
	@Test
	public void deleteWIncident()
	{
//		File file = new File("createIncident.json");
		RestAssured.baseURI="https://dev75576.service-now.com/api/now/table";
		
		RestAssured.authentication = RestAssured.basic("admin","vincewins");
		
		Response res = RestAssured.given()
		.contentType(ContentType.JSON)
		
		.log().all()
		.body("{\"type\":\"normal\",\"state\":\"New Updated\"}")
		.patch("/1c832706732023002728660c4cf6a7b9")
		.then().statusCode(200).extract().response();
		
		
		
	
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
