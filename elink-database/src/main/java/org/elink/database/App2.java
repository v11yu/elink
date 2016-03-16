package org.elink.database;

import java.util.Date;
import java.util.List;

import org.elink.database.model.MySQLEntityInfo;
import org.elink.database.model.EntitySource;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mysql.mapper.EntityInfoMapper;
import org.elink.database.mysql.mapper.EntitySourceMapper;
import org.elink.database.utils.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App2 {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
		EntityInfoMapper entityInfoMapper = (EntityInfoMapper) context.getBean("entityInfoMapper");
		Date tim = new Date();
		List<MySQLEntityInfo> ls = entityInfoMapper.getByPage(0, 1);
		//List ls = entityInfoMapper.getAll();
		System.out.println(ls.size());
		for(MySQLEntityInfo e : ls){Log.info(e+"");}
		System.out.println((new Date().getTime() - tim.getTime()));
//		BasicRepository<EntitySource> esdao = (BasicRepository<EntitySource>) context.getBean("entitySourceDao");
//		for(EntitySource e : ls){
//			try {
//				esdao.saveAndUpdate(e);
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
	}
}
