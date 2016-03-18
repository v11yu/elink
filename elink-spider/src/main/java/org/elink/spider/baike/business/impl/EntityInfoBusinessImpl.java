package org.elink.spider.baike.business.impl;

import org.elink.database.model.EntityInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.DBQuery;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.pname.model.Pname;
import org.elink.spider.baike.business.EntityInfoBusiness;
import org.elink.spider.utils.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mongodb.DBCursor;


public class EntityInfoBusinessImpl implements EntityInfoBusiness{
	ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
	BasicRepository<EntityInfo> edao = (BasicRepository<EntityInfo>) context.getBean("entityInfoDao");
	@Override
	public void save(EntityInfo e) {
		// TODO Auto-generated method stub
		try {
			if(checkEntityExist(e)) return ;
			edao.saveAndUpdate(e);
			Log.info("save EntityInfo to mongodb "+e);
		} catch (Exception e1) {
			Log.error(e+" "+e1);
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public boolean checkEntityExist(EntityInfo e) {
		DBQuery query = new DBQuery();
		query.equalsOperation("url",e.getUrl());
		DBCursor cursor = edao.findQuery(query);
		if(cursor.size()>0) return true;
		return false;
	}

}
