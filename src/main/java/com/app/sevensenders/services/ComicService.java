package com.app.sevensenders.services;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.springframework.stereotype.Service;

import com.app.sevensenders.models.Comic;
import com.sun.syndication.io.FeedException;

@Service
public interface ComicService {
	ArrayList<Comic> retrieveComic() throws JSONException, IOException, IllegalArgumentException, FeedException;
}
