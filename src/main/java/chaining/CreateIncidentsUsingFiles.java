package chaining;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class CreateIncidentsUsingFiles extends BaseRequest {
	
	@DataProvider(name="Files", parallel=true)
	public String[] getFiles()
	{
		String[] files = new String[2];
		files[0]="createIncident.json";
		files[1]="createIncident2.json";
		
		return files;
	}
	
	@Test
	public void createNewIncident() 
	{
		File file = new File("createIncident.json");

		
		Response res = RestAssured.given()
		.contentType(ContentType.JSON)
		
		.body(file).log().all()
		.post("/incident");
		
		JsonPath json = res.jsonPath();
		sys_id = json.getString("result.sys_id");
		System.out.println(sys_id);
		
		Assert.assertEquals(res.statusCode(), 201);
	}
	
	@Test
	public void getCreatedIncident() 
	{
		
		System.out.println(sys_id);
		
		ValidatableResponse res = RestAssured.given()
		.accept(ContentType.JSON)
		.log().all()
		.get("/incident/"+sys_id)
		.then()
		   .statusCode(200)
		 .and()
		 .body("result.sys_id", equalTo(sys_id));
		
	
	}
	
	@Test(dataProvider="Files")
	public void createNewIncidentFromDataProvider(String fileName)
	{
		
		File filesFromDP = new File(fileName);
		
	
		
		Response res = RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.body(filesFromDP)
		.post("/incident");
		
		Assert.assertEquals(res.statusCode(), 201);
	}

}
