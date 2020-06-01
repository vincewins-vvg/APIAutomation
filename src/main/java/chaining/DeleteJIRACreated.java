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

public class DeleteJIRACreated extends BaseRequestForJIRA {
	
	@Test(dependsOnMethods={"chaining.CreateGetDeleteJIRA.createJIRAIssue"})
	public void deleteIssue()
	{
		System.out.println(issueId);
		
		Response res = RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.when()
		.delete("/issue/"+issueId)
		.then()
		.assertThat()
		
		.statusCode(204).extract().response();
		
		Assert.assertTrue(res.getBody().asString().isEmpty());
		
	}

}
