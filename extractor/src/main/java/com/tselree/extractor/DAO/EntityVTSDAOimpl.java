package com.tselree.extractor.DAO;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class EntityVTSDAOimpl implements EntityVTSDAO{
	JdbcTemplate jdbcTemplate;
	
	public EntityVTSDAOimpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(String table, String key_val, String column, String value) {
		String sql = "INSERT INTO "+table+"(KEY_ID, NAME, VALUE) VALUES('"+key_val+"','"+column+"','"+value+"')";
		jdbcTemplate.update(sql);
	}

	@Override
	public void del_existing(String table, String key_val) {
		String sql = "DELETE FROM "+table+" WHERE key_id = '"+key_val+"'";
		jdbcTemplate.update(sql);
	}

}
