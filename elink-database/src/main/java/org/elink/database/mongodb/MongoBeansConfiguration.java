package org.elink.database.mongodb;

import org.elink.database.model.EntitySource;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.mongodb.repository.impl.DBConvertor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MongoBeansConfiguration {
	/*
	 * topic
	 */
	@Bean
	DBConvertor<EntitySource> entitySourceConvertor(){
		return new DBConvertor<>(EntitySource.class);
	}
	@Bean
	BasicRepository<EntitySource> entitySourceDao(){
		return new BasicRepository<EntitySource>(EntitySource.class,entitySourceConvertor());
	}

}
