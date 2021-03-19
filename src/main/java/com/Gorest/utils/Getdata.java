package com.Gorest.utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Getdata {
	
	public static Properties prop=new Properties();
	
	public static Map<String, String> getPropertyData() {
		Map<String, String> mapdata=new HashMap<String, String>();
		
		try {
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/TestDataFiles/Data.properties");
			prop.load(fis);
			mapdata.put("ServerUrl", prop.getProperty("serverurl"));
			mapdata.put("Token", prop.getProperty("token"));
			mapdata.put("Port", prop.getProperty("port"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapdata;
	}
	
}
