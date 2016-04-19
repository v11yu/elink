package org.elink.database.business;

import java.util.List;


import org.elink.database.model.AttributeNameInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.DBQuery;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.utils.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mongodb.DBCursor;

public class AttributeNameInfoBusiness {
	ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
	BasicRepository<AttributeNameInfo> andao = (BasicRepository<AttributeNameInfo>) context.getBean("attributeNameInfoDao");
	public void save(AttributeNameInfo attr) {
		// TODO Auto-generated method stub
		
		try {
			andao.saveAndUpdate(attr);
			Log.info("save to mongodb" + attr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.info("save faliure" + attr);
		}
	}
	public List<AttributeNameInfo> getAllList() {
		// TODO Auto-generated method stub
		return andao.dbobj2Entity(andao.findByAll());
	}

	public List<AttributeNameInfo> getList(String attrName){
		DBQuery query = new DBQuery();
		query.equalsOperation("attrName", attrName);
		DBCursor cursor = andao.findQuery(query);
		return andao.dbobj2Entity(cursor);
		
	}
}
