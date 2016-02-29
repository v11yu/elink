package org.elink.database.mongodb.utils;

import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mysql.mapper.EntitySourceMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringBeanUtils {
	public static ApplicationContext getContext(){
		ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
		return context;
	}
}
