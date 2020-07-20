package com.tselree.extractor.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.tselree.extractor.DAO.CheckGroupDAO;
import com.tselree.extractor.DAO.CheckGroupDAOimpl;
import com.tselree.extractor.DAO.EntityHTSDAO;
import com.tselree.extractor.DAO.EntityHTSDAOimpl;
import com.tselree.extractor.DAO.EntityVTSDAO;
import com.tselree.extractor.DAO.EntityVTSDAOimpl;
import com.tselree.extractor.DAO.OmniformDAO;
import com.tselree.extractor.DAO.OmniformDAOimpl;
import com.tselree.extractor.DAO.XpathListDAO;
import com.tselree.extractor.DAO.XpathListDAOimpl;


@Configuration
@ComponentScan(basePackages="com.tselree.extractor")
public class AppConfig {
	@Bean()
    public DataSource getDataSource2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/extractor?serverTimezone=UTC&useLegacyDatetimeCode=false");
        dataSource.setUsername("pmauser");
        dataSource.setPassword("alvin147");
         
        return dataSource;
    }
	@Bean()
    public DataSource getDataSource3() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/entity?serverTimezone=UTC&useLegacyDatetimeCode=false");
        dataSource.setUsername("pmauser");
        dataSource.setPassword("alvin147");
         
        return dataSource;
    }
	@Bean()
    public OmniformDAO getOmniformDAO() {
    	return new OmniformDAOimpl(getDataSource2());
    }
	@Bean()
	public CheckGroupDAO getCheckGroupDAO() {
		return new CheckGroupDAOimpl(getDataSource2());
	}
	@Bean()
	public XpathListDAO getXpathListDAO() {
		return new XpathListDAOimpl(getDataSource2());
	}
	@Bean()
	public EntityHTSDAO getEntityHTSDAO() {
		return new EntityHTSDAOimpl(getDataSource3());
	}
	@Bean()
	public EntityVTSDAO getEntityVTSDAO() {
		return new EntityVTSDAOimpl(getDataSource3());
	}
}
