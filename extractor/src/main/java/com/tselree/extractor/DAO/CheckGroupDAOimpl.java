package com.tselree.extractor.DAO;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tselree.extractor.model.XpathConfig;

public class CheckGroupDAOimpl implements CheckGroupDAO{
	JdbcTemplate jdbcTemplate;
	
	public CheckGroupDAOimpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public XpathConfig checkGroup(String type) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM conf_xpath WHERE omniform_type = '"+type+"'";
		XpathConfig xpathConfig = jdbcTemplate.queryForObject(sql, new Object[] {},BeanPropertyRowMapper.newInstance(XpathConfig.class));
		return xpathConfig;
	}

	
}
