package org.elink.mysql;

import java.util.List;

import org.elink.mysql.mapper.EntityInfoMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(MySqlRootConfiguration.class);
		EntityInfoMapper entityInfoMapper = (EntityInfoMapper) context.getBean("entityInfoMapper");
		List ls = entityInfoMapper.getById(1657);
		System.out.println(ls.size());
	}
}
