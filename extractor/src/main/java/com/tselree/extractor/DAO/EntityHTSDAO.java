package com.tselree.extractor.DAO;

public interface EntityHTSDAO {
	public void insert_key(String table, String key_col, String key_val);
	public void insert(String table, String key_col, String key_val, String column, String value);
}
