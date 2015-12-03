package com.clevelandbarscene.android;

public class VenueInfo {
	public VenueInfo(String webcam, String map) {
		this.webcam = webcam;
		this.map = map;
	}
	public String getWebcam() {
		return webcam;
	}
	public void setWebcam(String webcam) {
		this.webcam = webcam;
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
	private String webcam;
	private String map;
	
}
