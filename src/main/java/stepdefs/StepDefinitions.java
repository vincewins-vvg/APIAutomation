package stepdefs;

import java.io.File;

import org.testng.Assert;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefinitions {
	
	private RequestSpecification reqspec;
	private Response res;

    @Given("^end point is loaded$")
    public void initEndPoint() throws Throwable {
        RestAssured.baseURI="https://dev75576.service-now.com/api/now/table";
        
    }

    @When("^i do a POST request$")
    public void doAPOST() throws Throwable {
      res = reqspec.post("/incident");
      
    }
    
    

    @Then("^the response code should be 201$")
    public void checkResponseCode201() throws Throwable {
       if (res.getStatusCode()==201) {
		System.out.println("Successfully created");
//		System.out.println(res.);
	}
       else{
    	   
    	   System.err.println("Failed");
       }
    }

    @And("^basic auth is set$")
    public void setBasicAuth() throws Throwable {
        RestAssured.authentication = RestAssured.basic("admin", "vincewins");
    }

    @And("^set Content-Type as JSON$")
    public void setContentTypeJSON() throws Throwable {
       reqspec = RestAssured.given().contentType(ContentType.JSON);
    }
    
    @And("^send body as JSON$")
    public void sendJSONB0dy() throws Throwable {
       reqspec = reqspec.body("{\"type\":\"normal\",\"state\":\"New\"}");
    }

    @And("^send body from  file (.*)$")
    public void sendJSONFromFile(String file) throws Throwable {
       reqspec = reqspec.body(new File(file));
    }
}