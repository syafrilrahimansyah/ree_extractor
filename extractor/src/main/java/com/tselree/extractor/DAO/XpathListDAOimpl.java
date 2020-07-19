package com.tselree.extractor.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.tselree.extractor.model.XpathList;

public class XpathListDAOimpl implements XpathListDAO {
	JdbcTemplate jdbcTemplate;
	
	public XpathListDAOimpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<XpathList> xpathList(String xgroup) {
		// TODO Auto-generated method stub
		String sql = "SELECT `column`, `path` FROM list_xpath WHERE xgroup = '"+xgroup+"'";
		
		List<XpathList> listXpath = jdbcTemplate.query(sql, new RowMapper<XpathList>() {

			@Override
			public XpathList mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				XpathList xpath = new XpathList();
				xpath.setColumn(rs.getString("column"));
				xpath.setPath(rs.getString("path"));
				return xpath;
			}
		});
		return listXpath;
		
	}
	
}
