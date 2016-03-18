package org.elink.spider.baike.business.impl;

import java.util.List;

import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.DBQuery;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.pname.model.Pname;
import org.elink.spider.baike.business.PnameBusiness;
import org.elink.spider.baike.parser.EntityInfoParser;
import org.elink.spider.utils.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mongodb.DBCursor;

public class PnameBusinessImpl implements PnameBusiness{
	ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
	BasicRepository<Pname> pdao = (BasicRepository<Pname>) context.getBean("pnameDao");
	
	@Override
	public void savePname(Pname p) {
		// TODO Auto-generated method stub
		try {
			if(check(p)) return ;
			pdao.saveAndUpdate(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(p + " "+e);
		}
	}
	@Override
	public boolean check(Pname p) {
		// TODO Auto-generated method stub
		DBQuery query = new DBQuery();
		query.equalsOperation("url",p.getUrl());
		DBCursor cursor = pdao.findQuery(query);
		return cursor.size() == 0?false:true;
	}
	@Override
	public List<Pname> getAllList() {
		// TODO Auto-generated method stub
		List<Pname> res = pdao.dbobj2Entity(pdao.findByAll());
		return res;
	}

}
