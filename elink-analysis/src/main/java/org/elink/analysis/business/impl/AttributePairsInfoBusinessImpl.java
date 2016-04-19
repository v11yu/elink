package org.elink.analysis.business.impl;

import java.util.List;

import org.elink.analysis.business.AttributePairsInfoBusiness;
import org.elink.database.model.AttributePairsInfo;
import org.elink.database.model.EntityInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.DBQuery;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.spider.utils.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AttributePairsInfoBusinessImpl implements AttributePairsInfoBusiness{
	ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
	BasicRepository<AttributePairsInfo> adao = (BasicRepository<AttributePairsInfo>) context.getBean("attributePairsInfoDao");
	@Override
	public void save(AttributePairsInfo attrPairs) {
		// TODO Auto-generated method stub
		try {
			adao.saveAndUpdate(attrPairs);
			Log.info("save to mongodb "+attrPairs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.info("save failure "+attrPairs);
		}
	}
	@Override
	public List<AttributePairsInfo> getAllList() {
		// TODO Auto-generated method stub
		return adao.dbobj2Entity(adao.findByAll());
	}
	@Override
	public List<AttributePairsInfo> getbyEid(EntityInfo en){
		DBQuery query = new DBQuery();
		query.equalsOperation("entityId", en.getId());
		return adao.dbobj2Entity(adao.findQuery(query));
		
	}
}
