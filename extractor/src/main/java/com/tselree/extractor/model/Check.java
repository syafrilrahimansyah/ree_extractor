package com.tselree.extractor.model;

public class Check {
	private String BusinessID;
	private String type;
	
	public Check() {
		
	}
	public Check(String businessID, String type) {
		super();
		BusinessID = businessID;
		this.type = type;
	}
	public String getBusinessID() {
		return BusinessID;
	}
	public void setBusinessID(String businessID) {
		BusinessID = businessID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
