package com.tselree.extractor.DAO;

public interface EntityVTSDAO {
	public void insert(String table, String key_val, String column, String value);
	public void del_existing(String table, String key_val);
}
