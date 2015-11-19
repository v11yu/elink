package org.elink.mysql;

import java.util.Date;
import java.util.List;

import org.elink.mysql.mapper.EntityInfoMapper;
import org.elink.mysql.mapper.EntitySourceMapper;
import org.elink.mysql.model.EntitySource;
import org.elink.mysql.utils.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(MySqlRootConfiguration.class);
		EntitySourceMapper entityInfoMapper = (EntitySourceMapper) context.getBean("entitySourceMapper");
		Date tim = new Date();
		List<EntitySource> ls = entityInfoMapper.getByPage(0, 500);
		//List ls = entityInfoMapper.getAll();
		System.out.println(ls.size());
		//for(EntitySource e : ls){Log.info(e+"");}
		System.out.println((new Date().getTime() - tim.getTime()));
	}
}
/*
 * 100/20s
 * 500/70s
 */