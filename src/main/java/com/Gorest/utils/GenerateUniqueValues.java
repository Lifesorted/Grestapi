package com.Gorest.utils;
import org.apache.commons.lang3.RandomStringUtils;

public class GenerateUniqueValues {

	public static String getUniqueName() {
		String uniquename= RandomStringUtils.randomAlphabetic(2);
		return ("TestnameSample"+uniquename);
	}
	
}
