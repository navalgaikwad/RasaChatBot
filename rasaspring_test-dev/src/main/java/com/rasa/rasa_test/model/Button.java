package com.rasa.rasa_test.model;

public class Button {
	
	private String title;
	private String payload;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "Button [title=" + title + ", payload=" + payload + "]";
	}
	
	

}
