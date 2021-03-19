package com.Gorest.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Gorest.Base.BaseClass;
import com.Gorest.apiConfig.APIPath;
import com.Gorest.apiConfig.HeaderConfig;
import com.Gorest.utils.Getdata;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class GetAllUserDataApi extends BaseClass{

	@Test
	public void getAlluser() {
	  RequestSpecification requestSpecification=RestAssured.given().headers(HeaderConfig.getDefaultHeader())
			                                    .auth().oauth2(Getdata.getPropertyData().get("Token"));
	  Response response=requestSpecification.get(APIPath.ApiPaths.GET_ALL_USERS);
	  JsonPath jsonPath=response.jsonPath();
	  
      String codeval=jsonPath.getString("code");
      System.out.println("Value of code node:"+codeval);
      
      String metavalString=jsonPath.getString("meta.pagination.total");
      System.out.println("Value of total:"+metavalString);
      
      String totalpagesvalString=jsonPath.getString("meta.pagination.pages");
      System.out.println("Count of pages:"+totalpagesvalString);
      
      String pagecount=jsonPath.getString("meta.pagination.page");
      System.out.println("Count of one page:"+pagecount);
      
      String limit=jsonPath.getString("meta.pagination.limit");
      System.out.println("Count of limit:"+limit);
      
      String userid=jsonPath.getString("data.id");
      System.out.println("value of id:"+userid);
      
      String name=jsonPath.getString("data.name");
      System.out.println("Value of name:"+name);
	}
	
	@Test
	public void validateHeader() {
       RestAssured.given().headers(HeaderConfig.getDefaultHeader())
		.auth().oauth2(Getdata.getPropertyData().get("Token"))
		.when()
		
		   .get(APIPath.ApiPaths.GET_ALL_USERS)
		  
		.then()
		   .assertThat()
		   .log().all()
		   .contentType("application/json")
		   .statusLine("HTTP/1.1 200 OK");
	}
	
	@Test
	public void reqWithoutAuth() {
		RequestSpecification requestSpecification=RestAssured.given().headers(HeaderConfig.getDefaultHeader());
		Response response=requestSpecification.get(APIPath.ApiPaths.GET_ALL_USERS);
		
		JsonPath jsonPath=response.jsonPath();
		
		System.out.println(response.asString());
		String totalrecord=jsonPath.getString("meta.pagination.total");
		System.out.println("Total records are :"+totalrecord);
		
		int code=response.getStatusCode();
		if (code==200) {
			System.out.println("Response code is right when request send with out auth means no auth needed with get request");
		} else {
            System.out.println("Response code is not returning as expected");
		}
	}
	
}
