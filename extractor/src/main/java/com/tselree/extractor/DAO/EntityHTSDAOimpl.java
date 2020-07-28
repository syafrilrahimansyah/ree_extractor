package com.tselree.extractor.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

public class EntityHTSDAOimpl implements EntityHTSDAO{
	JdbcTemplate jdbcTemplate;
	
	public EntityHTSDAOimpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(String table, String id, String column, String value) {
		String sql = "UPDATE "+table+" SET "+column+" = '"+value+"' WHERE id = '"+id+"'";
		jdbcTemplate.update(sql);
	}

	@Override
	public Integer insert_key(String table, String key_col, String key_val) {
		String sql = "INSERT INTO "+table+"("+key_col+") VALUES('"+key_val+"')";
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
		    @Override
		    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		        return statement;
		    }
		}, holder);
		Integer id = holder.getKey().intValue();
		return id;
	}

	@Override
	public void insert2(String table, String column, String value, String key_col, String key_val) {
		try {
			String sql = "INSERT INTO "+table+"("+column+") VALUES("+value+")";
			jdbcTemplate.update(sql);
		}catch(DuplicateKeyException e) {
			String sql_del = "DELETE FROM "+table+" WHERE "+key_col+" = "+key_val;
			String sql_insert = "INSERT INTO "+table+"("+column+") VALUES("+value+")";
			jdbcTemplate.update(sql_del);
			jdbcTemplate.update(sql_insert);
		}
	}

	@Override
	public void delete_old(String table, String key_col, String key_val) {
		String sql_del = "DELETE FROM "+table+" WHERE "+key_col+" = "+key_val;
		jdbcTemplate.update(sql_del);		
	}

}
