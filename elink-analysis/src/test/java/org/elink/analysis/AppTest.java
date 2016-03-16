package org.elink.analysis;

import org.elink.analysis.method.AbstractAttrMethod;
import org.elink.analysis.method.InfoBoxAttrMethod;
import org.elink.analysis.utils.Log;
import org.elink.database.model.AttributePairsInfo;
import org.elink.database.model.EntityInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest {
	@Test
	public void testNum(){
		ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
		BasicRepository<EntityInfo> edao = (BasicRepository<EntityInfo>) context.getBean("entityInfoDao");
		BasicRepository<AttributePairsInfo> adao = (BasicRepository<AttributePairsInfo>) context.getBean("attributePairsInfoDao");
		Log.info(adao.findByAll().size());
		
	}
}
