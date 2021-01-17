package com.app.sevensenders.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.app.sevensenders.models.Comic;

import static com.app.sevensenders.constants.AppConstants.XCDK_LINK;
import static com.app.sevensenders.constants.AppConstants.XCDK_INDEX_LINK;
import static com.app.sevensenders.constants.AppConstants.XCDK_PAGE;
import static com.app.sevensenders.constants.AppConstants.NUMBER_OF_DATA;

@Component
public class ComicJsonUtils {
	
	public HashSet<Comic> getDataFromXkcd() throws JSONException, IOException {
		
		HashSet<Comic> listOfComic = new HashSet<Comic>();
		//GetIndex
		JSONObject json = readJson(XCDK_LINK);
	    int currentComic = json.getInt("num");
		
	    //Get Last 10 comics 
	    for(int i = currentComic ; i > (currentComic -NUMBER_OF_DATA); i--) {
	    	JSONObject comicJson = readJson(XCDK_INDEX_LINK.replace("<index>", ""+i));
	    	Comic comic = new Comic();
	    	
	    	comic.setPictureUrl(comicJson.getString("img"));
	    	
	    	int day=Integer.parseInt(comicJson.getString("day"));
	    	int month=Integer.parseInt(comicJson.getString("month"));
	    	int year=Integer.parseInt(comicJson.getString("year"));	    	
	    	Date newDate= calendarFor(day,month,year).getTime();	    	
	    	comic.setPublishingDate(newDate);
	    	
	    	comic.setTitle(json.getString("title"));
	    	comic.setWebUrl(XCDK_PAGE+comicJson.getInt("num"));	    
	    	
	    	listOfComic.add(comic);
	    }
		return listOfComic;
	}

	private static JSONObject readJson(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	       BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	       String jsonText = readAll(rd);
	       JSONObject json = new JSONObject(jsonText.replace("\n", " "));
	       return json;
	    } finally {
	        is.close();
	    }
	}
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	       sb.append((char) cp);
	    }
	    return sb.toString();
	 }
	 private static Calendar calendarFor(int day, int month, int year) {
	        Calendar cal = Calendar.getInstance();
	        //Hour is not avaliable in the json 
	        cal.set(year, month-1, day, 00, 00, 00);
	        return cal;	   
	}

}
