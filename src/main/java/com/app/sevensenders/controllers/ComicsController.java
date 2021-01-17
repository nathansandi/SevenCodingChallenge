package com.app.sevensenders.controllers;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.app.sevensenders.services.ComicService;
import com.sun.syndication.io.FeedException;



@Controller
public class ComicsController {

	@Autowired 
	private ComicService service;
	
	@RequestMapping("/getComics")
	@GetMapping
	public ResponseEntity<Object> getComics() throws JSONException, IOException, IllegalArgumentException, FeedException{		
				return new ResponseEntity<Object>(service.retrieveComic(), HttpStatus.OK);
	
	}
}
