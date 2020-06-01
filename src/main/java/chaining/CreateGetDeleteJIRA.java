package chaining;


import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.Matchers.containsString;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateGetDeleteJIRA extends BaseRequestForJIRA {
	
	
	
	@DataProvider(name="Files")
	public String[] getFiles()
	{
		String[] files = new String[1];
		files[0]="createIssue.json";
		
		
		return files;
	}
	
	@Test
	public void createJIRAIssue()
	{
		
		File createIssueJson = new File("createIssue.json");
        
		
		Response res = RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.body(createIssueJson)
		.when()
		.post("/issue")
		.then()
		.statusCode(201)
		.assertThat()
		.body("id", notNullValue()).extract().response();
		
		String selfPath = res.path("self").toString();
		issueId= res.jsonPath().get("id").toString();
		System.out.println(issueId);
		
		RestAssured.get(selfPath).then().assertThat().statusCode(200);
		
		
		
	}
	@Test(dependsOnMethods= {"chaining.CreateGetDeleteJIRA.createJIRAIssue"})
	public void getJIRAToday() throws ParseException
	{
		System.out.println(issueId);
	   
	       
		
		Response res = RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		
		.get("/search")
		 .then()
		 .assertThat()
		 .body(containsString(issueId))
		 .statusCode(200)
		 .extract().response();
		
		Assert.assertTrue(res.getBody().asString().isEmpty());
		
		
		//System.out.println(res.prettyPrint());
	
		JsonPath json = res.jsonPath();
		
		List<String> dateList = json.getList("issues.fields.created");
		List<String> idList = json.getList("issues.id");
		int listSize = dateList.size();
		
		//System.out.println(listSize);
        System.out.println(dateList.toString());
        for (int i = 0; i <listSize; i++) {
        	
        	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
  	         	Date dateobj = df.parse(dateList.get(i));
  	         	long createdTime = dateobj.getTime();
  	         	System.out.println(createdTime);
  	         	long currentTime = new Date().getTime();
  	         	
  	         	
        	
//        	String issueDates = dateList.get(i).toString().substring(0,10);
//        	System.out.println(issueDates);
//        	System.out.println(CreateGetDeleteJIRA.getDate());
//        	syso
        	if((currentTime-createdTime<=(24 * 60 * 60 * 1000)))
        	{
        		
        		System.out.println("Issues created in last 24 hrs: ");
        		System.out.println(idList.get(i).toString()+" created at "+dateList.get(i));
        	}
			
		}
	}
	

}
