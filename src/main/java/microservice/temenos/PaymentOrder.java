package microservice.temenos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.Request;
import org.testng.annotations.Test;

import com.beust.jcommander.internal.Maps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasSize;
public class PaymentOrder {
	
	@Test
	public void getSpecificPO()
	{
		
		//Set Base URI
		
		RestAssured.baseURI="http://localhost:8090/ms-paymentorder-api/api/payments";
		
		
		 
		
		//Set Authentication
		//RestAssured.authentication = RestAssured.basic("admin", "vincewins");
		
		
//	       Map<String, String> params = new HashMap<String,String>();
//	        params.put("sysparam_fields", "number,sys_id");
		
		Response response = RestAssured.given()
	    .contentType(ContentType.JSON)
	    .header("roleId", "ADMIN")
	   //.accept(ContentType.XML)	
		.get("/orders/PO~100-CBE~123-ABC~USD~483")
		
		.then()
		.assertThat()
		.body("paymentOrder.exchangeRates[0]", hasSize(equalTo(3)))
		.extract().response();
		
		JsonPath json = response.jsonPath();
		System.out.println(json.get("paymentOrder.fromAccount").toString());
		System.out.println(json.get("paymentOrder.exchangeRates").toString());
//		List<String> noOfItems = json.getList("paymentOrder.exchangeRates"); 
//	
//		int size = noOfItems.size();
//		
//		System.out.println("Size of Account is "+size);
		System.out.println(response.prettyPrint());
		
//		for (int j = 0; j <sysIdCount; j++) {
//			
//			
//			System.out.println(json.getString(noOfSysId.get(j)));
//			
//		}
		
		
		//System.out.println(response.prettyPrint());
		//System.out.println(json.getString("result[0].sys_id"));
		
 
		
		System.out.println(response.getStatusCode());
		//System.out.println(response.prettyPrint());	
		
	}
	
	@Test
	public void getAllPO()
	{
		
		//Set Base URI
		
		RestAssured.baseURI="http://localhost:8090/ms-paymentorder-api/api/payments";
		
		
		 
		
		//Set Authentication
		//RestAssured.authentication = RestAssured.basic("admin", "vincewins");
		
		
//	       Map<String, String> params = new HashMap<String,String>();
//	        params.put("sysparam_fields", "number,sys_id");
		
		Response response = RestAssured.given().log().all()
		 .queryParam("currency", "INR")
	    .contentType(ContentType.JSON)
	    .header("roleId", "ADMIN")
	   //.accept(ContentType.XML)	
		.get("/orders")
		
		.then()
	
		.extract().response();
		
		JsonPath json = response.jsonPath();
//		System.out.println(json.get("paymentOrder.fromAccount").toString());
//		System.out.println(json.get("paymentOrder.exchangeRates").toString());
		List<String> noOfItems = json.getList("items.fromAccount");
	
		int size = noOfItems.size();
		
		System.out.println("Size of item is "+size);
//		System.out.println(response.prettyPrint());
		
//		for (int j = 0; j <sysIdCount; j++) {
//			
//			
//			System.out.println(json.getString(noOfSysId.get(j)));
//			
//		}
		
		
		//System.out.println(response.prettyPrint());
		//System.out.println(json.getString("result[0].sys_id"));
		
 
		
		System.out.println(response.getStatusCode());
		//System.out.println(response.prettyPrint());	
		
	}

}
