package com.tselree.extractor.DAO;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class OmniformDAOimpl implements OmniformDAO{
	JdbcTemplate jdbcTemplate;
	
	public OmniformDAOimpl(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public String getMinID() {
		// TODO Auto-generated method stub
		try {
		String sql = "SELECT MIN(seq) FROM omniform WHERE stage = 'col'";
		return jdbcTemplate.queryForObject(sql, String.class);
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public String getPayload(String id) {
		// TODO Auto-generated method stub
		try {
		String sql = "SELECT payload FROM omniform WHERE seq = '"+id+"'";
		return jdbcTemplate.queryForObject(sql, String.class);
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
