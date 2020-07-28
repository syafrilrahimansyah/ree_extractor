package com.tselree.extractor.model;

public class XpathConfig {
	private String xgroup;
	private String entity_table;
	private String column_key;
	private String tb_structure;
	private String key_path;
	private String loop_path;
	
	public XpathConfig() {
	}

	public XpathConfig(String xgroup, String entity_table, String column_key, String tb_structure, String key_path,
			String loop_path) {
		super();
		this.xgroup = xgroup;
		this.entity_table = entity_table;
		this.column_key = column_key;
		this.tb_structure = tb_structure;
		this.key_path = key_path;
		this.loop_path = loop_path;
	}

	public String getXgroup() {
		return xgroup;
	}

	public void setXgroup(String xgroup) {
		this.xgroup = xgroup;
	}

	public String getEntity_table() {
		return entity_table;
	}

	public void setEntity_table(String entity_table) {
		this.entity_table = entity_table;
	}

	public String getColumn_key() {
		return column_key;
	}

	public void setColumn_key(String column_key) {
		this.column_key = column_key;
	}

	public String getTb_structure() {
		return tb_structure;
	}

	public void setTb_structure(String tb_structure) {
		this.tb_structure = tb_structure;
	}

	public String getKey_path() {
		return key_path;
	}

	public void setKey_path(String key_path) {
		this.key_path = key_path;
	}

	public String getLoop_path() {
		return loop_path;
	}

	public void setLoop_path(String loop_path) {
		this.loop_path = loop_path;
	}	
}
