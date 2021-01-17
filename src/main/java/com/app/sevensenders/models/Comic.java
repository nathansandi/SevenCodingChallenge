package com.app.sevensenders.models;

import java.util.Date;

public class Comic {
	private String pictureUrl;
	private String title;
	private String webUrl;
	private Date publishingDate;
	
	
	public Comic() {
		// TODO Auto-generated constructor stub
	}


	public String getPictureUrl() {
		return pictureUrl;
	}


	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getWebUrl() {
		return webUrl;
	}


	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}


	public Date getPublishingDate() {
		return publishingDate;
	}


	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}
	
}
