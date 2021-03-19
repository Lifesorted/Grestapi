package com.Gorest.Base;

import org.testng.annotations.BeforeClass;

import com.Gorest.utils.Getdata;

import io.restassured.RestAssured;

public class BaseClass {

	@BeforeClass
	public void baseClass() {
		RestAssured.baseURI=Getdata.getPropertyData().get("ServerUrl");
	}
	
}
