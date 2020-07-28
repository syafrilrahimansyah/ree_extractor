package com.tselree.extractor.DAO;

public interface EntityHTSDAO {
	public Integer insert_key(String table, String key_col, String key_val);
	public void insert(String table, String id, String column, String value);
	public void insert2(String table, String column, String value, String key_col, String key_val);
	public void delete_old(String table, String key_col, String key_val);
}
