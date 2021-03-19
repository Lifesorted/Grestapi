package com.Gorest.Test;
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.core.IsEqual;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.Gorest.Base.BaseClass;
import com.Gorest.apiConfig.APIPath;
import com.Gorest.apiConfig.HeaderConfig;
import com.Gorest.utils.GenerateUniqueValues;
import com.Gorest.utils.Getdata;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class CreateUserApiTest extends BaseClass {

	@Test
	public void postDataApi() {
		Map<String, String> createuserpayload= new HashMap<>();
		createuserpayload.put("name", GenerateUniqueValues.getUniqueName());
		createuserpayload.put("gender", "Female");
		createuserpayload.put("email", GenerateUniqueValues.getUniqueName()+"@gmail.com");
		createuserpayload.put("status", "Active");
		RequestSpecification requestSpecification=RestAssured.given().headers(HeaderConfig.getDefaultHeader())
				.auth().oauth2(Getdata.getPropertyData().get("Token")).body(createuserpayload);
		Response response=requestSpecification.post(APIPath.ApiPaths.CREATE_USER);
		
		JsonPath responsejsondata=response.jsonPath();
		System.out.println(responsejsondata.prettyPrint());
		String codeval=responsejsondata.getString("code");
		System.out.println("Response code value:"+codeval);
		Assert.assertEquals("201", codeval);
		String metaval=responsejsondata.getString("meta");
		System.out.println("Response meta value:"+metaval);
		Assert.assertEquals(null, metaval);
		String id=responsejsondata.getString("data.id");
		System.out.println("Response id value:"+id);
		
	}
	
	@Test
	public void createApiwithoutAuth() {
		Map<String, String> createuserpayload= new HashMap<>();
		createuserpayload.put("name", GenerateUniqueValues.getUniqueName());
		createuserpayload.put("gender", "Female");
		createuserpayload.put("email", GenerateUniqueValues.getUniqueName()+"@gmail.com");
		createuserpayload.put("status", "Active");
		RequestSpecification requestSpecification=RestAssured.given().headers(HeaderConfig.getDefaultHeader()).body(createuserpayload);
		Response response =requestSpecification.post(APIPath.ApiPaths.CREATE_USER);
	
		JsonPath jsonPath=response.jsonPath();
		System.out.println(response.body().asString());
		String codevalString= jsonPath.getString("code");
		System.out.println("Value of code without auth:"+codevalString);
		Assert.assertEquals(codevalString, "401");
		
		String metaString=jsonPath.getString("meta");
		System.out.println("Value of meta withour auth:"+metaString);
		
		String messageString=jsonPath.getString("data.message");
		System.out.println("Value of message:"+messageString);
	}
	
	@Test
	public void validateResponsseHeaders() {
		Map<String, String> createuserpayload= new HashMap<>();
		createuserpayload.put("name", GenerateUniqueValues.getUniqueName());
		createuserpayload.put("gender", "Female");
		createuserpayload.put("email", GenerateUniqueValues.getUniqueName()+"@gmail.com");
		createuserpayload.put("status", "Active");
		RequestSpecification requestSpecification=RestAssured.given().headers(HeaderConfig.getDefaultHeader())
				.auth().oauth2("Token").body(createuserpayload);
		Response response =requestSpecification.post(APIPath.ApiPaths.CREATE_USER);
	
		JsonPath jsonPath=response.jsonPath();
		System.out.println(response.body().asString());
		String contenttype= response.contentType();
				
		System.out.println("Content type is:"+contenttype);
		Assert.assertEquals(contenttype, "application/json");
		
		int statuscode=response.getStatusCode();
		System.out.println("Value of status code:"+statuscode);
		Assert.assertEquals(statuscode, 200);
		
		long statusline=response.getTime();
		System.out.println("Value of time taken in response:"+statusline);
	}
}
