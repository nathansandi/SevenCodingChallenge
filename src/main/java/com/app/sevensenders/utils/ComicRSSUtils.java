package com.app.sevensenders.utils;


import static com.app.sevensenders.constants.AppConstants.POORLY_RSS;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.app.sevensenders.models.Comic;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

@Component
public class ComicRSSUtils {

	public HashSet<Comic> getComicsFromPowerDrive() throws IOException, IllegalArgumentException, FeedException {
		// TODO Auto-generated constructor stub
		SyndFeed feed= readRSS(POORLY_RSS);
		HashSet<Comic> listOfComics= new HashSet<Comic>();
		for(SyndEntry entry : (List<SyndEntry>) feed.getEntries()){
			 Comic currentEntry = parseData(entry);
			 listOfComics.add(currentEntry);
		}
		
		return listOfComics;
	}
	private static SyndFeed readRSS(String url) throws IOException, IllegalArgumentException, FeedException {	
		URL feedUrl = new URL(url);
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new InputStreamReader(feedUrl.openStream()));
		return feed;

	}
	private Comic parseData(SyndEntry entry) {
		Comic comic = new Comic();
		comic.setTitle(entry.getTitle());
		comic.setPictureUrl(getLinkFromTheContent(entry));
		comic.setPublishingDate(entry.getPublishedDate());
		comic.setWebUrl(entry.getLink());
		return comic;
	}
	private String getLinkFromTheContent(SyndEntry entry) {
		String link ="";
		for(SyndContentImpl content : (List<SyndContentImpl>) entry.getContents()){
			String aux1 = content.getValue().substring(content.getValue().indexOf("src=\"")).replace("src=\"", "");
			link = aux1.substring(0, aux1.indexOf("\""));
		}
		return link;	
	}
}
