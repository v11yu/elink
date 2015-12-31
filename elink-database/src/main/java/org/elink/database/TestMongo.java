package org.elink.database;

import org.elink.database.model.EntitySource;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class TestMongo {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
		BasicRepository<EntitySource> esdao = (BasicRepository<EntitySource>) context.getBean("entitySourceDao");
		System.out.println(esdao.findById(440));
		
		
	}
}
