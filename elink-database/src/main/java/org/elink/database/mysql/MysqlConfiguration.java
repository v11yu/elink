package org.elink.database.mysql;

import java.io.InputStream;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class MysqlConfiguration {
	@Bean
	MapperScannerConfigurer mapperScannerConfigurer(){
		MapperScannerConfigurer mp = new MapperScannerConfigurer();
		mp.setBasePackage("org.elink.database.mysql.mapper");
		
		return mp;
	}
	@SuppressWarnings("deprecation")
	@Bean
	DriverManagerDataSource dataSource(){
		return new DriverManagerDataSource("com.mysql.jdbc.Driver", 
				"jdbc:mysql://192.168.2.23:3306/entity?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true", 
				"contexttrec", 
				"contexttrec");
	}
	@Bean
	SqlSessionFactoryBean sqlSessionFactory(){
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		return sqlSessionFactory;
		
	}
}
