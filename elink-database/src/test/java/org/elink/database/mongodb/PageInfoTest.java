package org.elink.database.mongodb;

import java.util.HashMap;
import java.util.Map;

import org.elink.database.model.PageInfo;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PageInfoTest {
	@Test
	public void insert() throws Exception{
		ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
		BasicRepository<PageInfo> pdao = (BasicRepository<PageInfo>) context.getBean("pageInfoDao");
		PageInfo pi = new PageInfo();
		pi.setContent("hello world1");
		Map<String, String> mp = new HashMap<String, String>();
		mp.put("v1", "k1");
		mp.put("v2","k2");
		pi.setAttribute(mp);
		pdao.saveAndUpdate(pi);
	}
}
