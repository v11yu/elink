package org.elink.spider.baike.business.impl;

import org.elink.database.model.EntityInfo;
import org.elink.database.model.MultiEntityInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.DBQuery;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.spider.baike.business.MultiEntityInfoBusiness;
import org.elink.spider.utils.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mongodb.DBCursor;

public class MultiEntityInfoBusinessImpl implements MultiEntityInfoBusiness{
	ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
	BasicRepository<MultiEntityInfo> medao = (BasicRepository<MultiEntityInfo>) context.getBean("multiEntityInfoDao");
	@Override
	public void save(MultiEntityInfo e) {
		// TODO Auto-generated method stub
		if(checkExist(e)) return ;
		try {
			medao.saveAndUpdate(e);
			Log.info("save to mongodb "+e);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			Log.error("can not save "+e);
			e1.printStackTrace();
		}
		return ;
	}

	@Override
	public boolean checkExist(MultiEntityInfo e) {
		// TODO Auto-generated method stub
		try{
			DBQuery query = new DBQuery();
			query.equalsOperation("name",e.getName());
			DBCursor cursor = medao.findQuery(query);
			if(cursor.size()>0) return true;
			return false;
		}catch(Exception e1){
			Log.info(e +" "+e1);
		}
		return true;
	}

}
