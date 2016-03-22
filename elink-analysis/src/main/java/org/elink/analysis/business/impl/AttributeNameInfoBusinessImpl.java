package org.elink.analysis.business.impl;

import java.util.List;

import org.elink.analysis.business.AttributeNameInfoBusiness;
import org.elink.analysis.utils.Log;
import org.elink.database.model.AttributeNameInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.DBQuery;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class AttributeNameInfoBusinessImpl implements AttributeNameInfoBusiness{
	ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
	BasicRepository<AttributeNameInfo> andao = (BasicRepository<AttributeNameInfo>) context.getBean("attributeNameInfoDao");
	@Override
	public void save(AttributeNameInfo attr) {
		// TODO Auto-generated method stub
		DBQuery query = new DBQuery();
		query.equalsOperation("attrName", attr.getAttrName());
		DBCursor cursor = andao.findQuery(query);
		if(cursor.size() > 0){
			attr = andao.findOne(cursor);
			attr.setAttrCount(attr.getAttrCount()+1);
		}
		try {
			andao.saveAndUpdate(attr);
			Log.info("save to mongodb" + attr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.info("save faliure" + attr);
		}
	}
	@Override
	public List<AttributeNameInfo> getAllList() {
		// TODO Auto-generated method stub
		return andao.dbobj2Entity(andao.findByAll());
	}
	

}
