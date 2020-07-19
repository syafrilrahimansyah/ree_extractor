package com.tselree.extractor.model;

public class XpathList {
	private String column;
	private String path;
	
	public XpathList() {
	}

	public XpathList(String column, String path) {
		super();
		this.column = column;
		this.path = path;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
