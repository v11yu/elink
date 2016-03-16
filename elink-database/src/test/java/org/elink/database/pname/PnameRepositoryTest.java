package org.elink.database.pname;

import org.elink.database.model.PageInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.pname.model.Pname;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

public class PnameRepositoryTest {
	@Test
	public void test1() throws Exception{
		ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
		BasicRepository<Pname> pdao = (BasicRepository<Pname>) context.getBean("pnameDao");
		Pname pn = new Pname();
		pn.setName("jay");
		pn.setUrl("ww.bbbb");
		pdao.saveAndUpdate(pn);
	}
}
