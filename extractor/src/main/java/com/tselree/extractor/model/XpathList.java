package com.tselree.extractor.model;

public class XpathList {
	private String column;
	private String path;
	private Boolean multiplevalue;
	
	public XpathList() {
	}

	

	public XpathList(String column, String path, Boolean multiplevalue) {
		super();
		this.column = column;
		this.path = path;
		this.multiplevalue = multiplevalue;
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



	public Boolean getMultiplevalue() {
		return multiplevalue;
	}



	public void setMultiplevalue(Boolean multiplevalue) {
		this.multiplevalue = multiplevalue;
	}
	
	
}
