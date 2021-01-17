package com.app.sevensenders.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sevensenders.exceptions.GeneralException;
import com.app.sevensenders.models.Comic;
import com.app.sevensenders.services.ComicService;
import com.app.sevensenders.utils.ComicJsonUtils;
import com.app.sevensenders.utils.ComicRSSUtils;
import com.sun.syndication.io.FeedException;

@Service
public class ComicServiceImplementation implements ComicService {
	
	@Autowired
	private ComicJsonUtils comicJsonUtils;
	@Autowired
	private ComicRSSUtils comicRssUtils;


	@Override
	public ArrayList<Comic> retrieveComic()  {
		ArrayList<Comic> result = new ArrayList<Comic>(); 

		try {
			result.addAll(comicRssUtils.getComicsFromPowerDrive());
			result.addAll(comicJsonUtils.getDataFromXkcd());
		} catch (IllegalArgumentException e) {
			throw new GeneralException(e.getMessage());
		} catch (IOException e) {
			throw new GeneralException(e.getMessage());
		} catch (FeedException e) {
			throw new GeneralException(e.getMessage());
		}
		
		
		Collections.sort(result, new Comparator<Comic>() {
			  public int compare(Comic cm1, Comic cm2) {
			      return cm2.getPublishingDate().compareTo(cm1.getPublishingDate());
			  }
			});
		return result;
	}
	


}
