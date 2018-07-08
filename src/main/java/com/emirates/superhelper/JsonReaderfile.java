package com.emirates.superhelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.JsonPath;

public class JsonReaderfile {

	static File jsonFile;
	static String value = null;

	public static String DataReader(String TCID, String property) {

		jsonFile = new File(System.getProperty("user.dir") + "/TestData/" + "TestData.json");

		try {
			value = JsonPath.read(jsonFile, "$." + "TestData." + TCID + "." + property);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;
	}

	public static String[] getLocator(String property) {

		jsonFile = new File(System.getProperty("user.dir") + "\\" + "ObjectRepository.json");
		String locatorType = null;
		String LocatorValue = null;
		String result = "";
		JSONArray Locator;

		try {

			BufferedReader br = new BufferedReader(new FileReader(jsonFile));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			result = sb.toString();
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(result);

			Locator = (JSONArray) json.get(property);
			locatorType = Locator.get(0).toString();
			LocatorValue = Locator.get(1).toString();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return new String[] { locatorType, LocatorValue };
		// return name;
	}

	public static String getTestCaseData(String property) {

		jsonFile = new File(System.getProperty("user.dir") + "/TestData/" + "TestData.json");

		try {
			value = JsonPath.read(jsonFile, "$." + "TestingData." + property);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		return value;
	}

}
