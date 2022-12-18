package com.BSG.BusinessGuidance.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;

import com.BSG.BusinessGuidance.model.BusinessPlaceDTO;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class denemee {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("response\n"+sendURL(-33.8587323, 151.2100055, 1500));
		List<String> a = getNames(sendURL(-33.8587323, 151.2100055, 1500));
	}
	
	public static List<String> getNames(String response)
	{
		
		JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
		
		JsonElement results = jsonObject.get("results");
		JsonArray resultArray = results.getAsJsonArray();

		int i = 0;
		while(i<resultArray.size())
		{
			
			//System.out.println("b"+resultArray.get(i)); 
			JsonObject resultObject = (JsonObject) resultArray.get(i);
			
			//System.out.println(resultObject.get("name")); 
			JsonElement photos = resultObject.get("photos");
			JsonArray photoArray = photos.getAsJsonArray();
			JsonObject photoObject = (JsonObject) photoArray.get(0);
			int end = photoObject.get("html_attributions").toString().indexOf('"',12);
			String mapURL = photoObject.get("html_attributions").toString().substring(12, end);
			BusinessPlaceDTO a = new BusinessPlaceDTO();
			a.setName(resultObject.get("name").toString());
			a.setUrl(mapURL);
		//	System.out.println("d"+mapURL);  URL
			
			i++;
		}
		
		List<BusinessPlaceDTO> a = new ArrayList<BusinessPlaceDTO>();
		
		//a.add()
		return null;
	}
	public static String sendURL(Double d,
			Double f,
			int i)
	{

		String res = null;
		try {
    		URL obj = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+d+"%2C"+f+"&radius="+i+"&type=restaurant|hospital|pharmacy|doctor&keyword=cruise&key=AIzaSyA6pHXCJgQkOSo_9rhusQRbywxpkOfjP3g");
    		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    		con.setRequestMethod("GET");
    		//con.setRequestProperty("User-Agent", USER_AGENT);
    		int responseCode = con.getResponseCode();
    		System.out.println("GET Response Code :: " + responseCode+"\n"+
    				"url:"+ obj.getFile()
    				);
    		if (responseCode == HttpURLConnection.HTTP_OK) { // success
    			BufferedReader in = new BufferedReader(new InputStreamReader(
    					con.getInputStream()));
    			String inputLine;
    			StringBuffer response = new StringBuffer();

    			while ((inputLine = in.readLine()) != null) {
    				response.append(inputLine);
    			}
    			in.close();

    			// print result
    			System.out.println(response.toString());
    			res = response.toString();
    			String json = "{ \"name\": \"Baeldung\", \"java\": true }";
    			
    		} else {
    			System.out.println("GET request not worked");
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		return res;
		
	}      
}
