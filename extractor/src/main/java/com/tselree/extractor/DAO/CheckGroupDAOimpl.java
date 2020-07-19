package com.tselree.extractor.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.tselree.extractor.model.XpathConfig;

public class CheckGroupDAOimpl implements CheckGroupDAO{
	JdbcTemplate jdbcTemplate;
	
	public CheckGroupDAOimpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<XpathConfig> checkGroup(String type) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM conf_xpath WHERE omniform_type = '"+type+"'";
		List<XpathConfig> xpathConfig = jdbcTemplate.query(sql, new RowMapper<XpathConfig>() {

			@Override
			public XpathConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				XpathConfig xpathConfig = new XpathConfig();
				xpathConfig.setColumn_key(rs.getString("column_key"));
				xpathConfig.setEntity_table(rs.getString("entity_table"));
				xpathConfig.setTb_structure(rs.getString("tb_structure"));
				xpathConfig.setXgroup(rs.getString("xgroup"));
				return xpathConfig;
			}});
		return xpathConfig;
	}

	
}
