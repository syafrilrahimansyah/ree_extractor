package com.tselree.extractor.DAO;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class EntityHTSDAOimpl implements EntityHTSDAO{
	JdbcTemplate jdbcTemplate;
	
	public EntityHTSDAOimpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(String table, String key_col, String key_val, String column, String value) {
		String sql = "UPDATE "+table+" SET "+column+" = '"+value+"' WHERE "+key_col+" = '"+key_val+"'";
		jdbcTemplate.update(sql);
	}

	@Override
	public void insert_key(String table, String key_col, String key_val) {
		String sql = "INSERT INTO "+table+"("+key_col+") VALUES('"+key_val+"')";
		jdbcTemplate.update(sql);		
	}

}
