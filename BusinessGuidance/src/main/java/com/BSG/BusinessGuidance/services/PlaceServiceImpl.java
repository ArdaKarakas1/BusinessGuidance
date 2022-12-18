/*package com.BSG.BusinessGuidance.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CODEXNEARST.model.Place;
import com.example.CODEXNEARST.model.PlaceInfoDTO;
import com.example.CODEXNEARST.repository.PlaceRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class PlaceServiceImpl implements PlaceService{

	@Autowired
	PlaceRepository placeRep;
	
	@Override// 3 parametre ve Google requestinin tamamı 
	public List<PlaceInfoDTO> getPlaces(Double longitude,
			Double latitude,
			int radius) {
		Place place = 	placeRep.findPlaceByLongitude(longitude);
		Place place1 = placeRep.findPlaceByLatitude(latitude);
		Place place2 = placeRep.findPlaceByRadius(radius);
		// TODO Auto-generated method stub
		List<PlaceInfoDTO> list = null;	
		if(place != null & place1 != null & place2 != null  )
		{
			if(place.getLatitude()==place1.getLatitude() && place1.getLatitude()==place2.getLatitude()&& place.getLatitude()==place2.getLatitude() )
			{
				if(place.getLongitude()==place1.getLongitude() && place1.getLongitude()==place2.getLongitude() && place.getLongitude()==place2.getLongitude())
				{
					if(place.getRadius()==place1.getRadius() && place1.getRadius()==place2.getRadius() && place.getRadius()==place2.getRadius())
					{
						
					 list =  getNames(place.getRequest());
					}
				}
			}
		}
		else {
			String req = sendURL(longitude, latitude, radius);
			 list =  getNames(req);
			Place a = new Place();
			a.setLatitude(latitude);
			a.setLongitude(longitude);
			a.setRadius(radius);
			a.setRequest(req);
			placeRep.save(a);
		}
		return list;
		
		
		
	}
	public List<Place> getDataFromGoogle()
	{
		return null;
	}
	public static List<PlaceInfoDTO> getNames(String response)
	{
		
		JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
		
		JsonElement results = jsonObject.get("results");
		JsonArray resultArray = results.getAsJsonArray();

		int i = 0;

		List<PlaceInfoDTO> allplaces = new ArrayList<PlaceInfoDTO>();
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
			PlaceInfoDTO a = new PlaceInfoDTO();
			a.setName(resultObject.get("name").toString());
			a.setUrl(mapURL);
			allplaces.add(a);
		//	System.out.println("d"+mapURL);  URL
			
			i++;
		}
		
		
		//a.add()
		return allplaces;
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
	
}*/
