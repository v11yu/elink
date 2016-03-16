package org.elink.analysis.method;

import org.elink.database.model.EntityInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.pname.model.Pname;
import org.elink.database.utils.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class String2DocTest {
	AbstractAttrMethod am = new InfoBoxAttrMethod();
	@Test
	public void testStr2doc(){
		ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
		BasicRepository<EntityInfo> edao = (BasicRepository<EntityInfo>) context.getBean("entityInfoDao");
		
		EntityInfo e = edao.findOne();
		
		Document doc = Jsoup.parse(e.getSource());
		am.getAttr(e.getSource());
		
	}
}
