package com.tselree.extractor.model;

public class Check {
	private String EntityID;
	private String type;
	
	public Check() {
		
	}
	
	public Check(String EntityID, String type) {
		super();
		this.EntityID = EntityID;
		this.type = type;
	}

	public String getEntityID() {
		return EntityID;
	}

	public void setEntityID(String EntityID) {
		this.EntityID = EntityID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
