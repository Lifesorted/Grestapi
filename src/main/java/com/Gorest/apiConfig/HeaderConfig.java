package com.Gorest.apiConfig;

import java.util.HashMap;
import java.util.Map;

import com.Gorest.utils.Getdata;

public class HeaderConfig {
	
	public static Map<String, String> getDefaultHeader() {
		Map<String, String> defHeader= new HashMap<String, String>();
		defHeader.put("Content-Type", "application/json");
		defHeader.put("Authorization", "Bearer"+Getdata.getPropertyData().get("Token"));
		defHeader.put("Accept", "*/*");
		return defHeader;	
	}
	
}
