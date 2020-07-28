package com.tselree.extractor.model;

public class XpathList {
	private String column;
	private String path;
	private String parent;
	private Boolean multiplevalue;
	
	
	public XpathList() {
	}


	public XpathList(String column, String path, String parent, Boolean multiplevalue) {
		super();
		this.column = column;
		this.path = path;
		this.parent = parent;
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


	public String getParent() {
		return parent;
	}


	public void setParent(String parent) {
		this.parent = parent;
	}


	public Boolean getMultiplevalue() {
		return multiplevalue;
	}


	public void setMultiplevalue(Boolean multiplevalue) {
		this.multiplevalue = multiplevalue;
	}

	
}
