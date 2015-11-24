package org.elink.database;

import java.util.Date;
import java.util.List;

import org.elink.database.model.EntitySource;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.mysql.mapper.EntitySourceMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestFindOne {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
		
		BasicRepository<EntitySource> esdao = (BasicRepository<EntitySource>) context.getBean("entitySourceDao");
		esdao.dropCollection();
	}
}
