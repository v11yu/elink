package org.elink.database;

import java.util.Date;
import java.util.List;

import org.elink.database.model.EntitySource;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.mysql.MySqlRootConfiguration;
import org.elink.database.mysql.mapper.EntityInfoMapper;
import org.elink.database.mysql.mapper.EntitySourceMapper;
import org.elink.database.utils.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
		EntitySourceMapper entityInfoMapper = (EntitySourceMapper) context.getBean("entitySourceMapper");
		Date tim = new Date();
		List<EntitySource> ls = entityInfoMapper.getByPage(0, 10);
		//List ls = entityInfoMapper.getAll();
		System.out.println(ls.size());
		//for(EntitySource e : ls){Log.info(e+"");}
		System.out.println((new Date().getTime() - tim.getTime()));
		BasicRepository<EntitySource> esdao = (BasicRepository<EntitySource>) context.getBean("entitySourceDao");
		for(EntitySource e : ls){
			try {
				esdao.saveAndUpdate(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
/*
 * 100/20s
 * 500/70s
 */