package org.elink.spider.baike.business;

import java.util.ArrayList;
import java.util.List;

import org.elink.database.model.EntityInfo;
import org.elink.database.model.cluster.AttrInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.DBQuery;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.spider.utils.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class AttrInfoBusiness {
	ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
	BasicRepository<AttrInfo> adao = (BasicRepository<AttrInfo>) context.getBean("attrInfoDao");
	public void save(AttrInfo attr){
		try {
			if(checkEntityExist(attr)) return ;
			adao.saveAndUpdate(attr);
			Log.info("save AttrInfo to mongodb "+attr.getAttrName());
		} catch (Exception e1) {
			Log.error(attr+" "+e1);
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public boolean checkEntityExist(AttrInfo attr) {
		DBQuery query = new DBQuery();
		query.equalsOperation("attrName",attr.getAttrName());
		DBCursor cursor = adao.findQuery(query);
		if(cursor.size()>0) return true;
		return false;
	}
	public List<AttrInfo> getAll(){
		List<AttrInfo> ls = new ArrayList<>();
		DBCursor cursor = adao.findByAll();
		while(cursor.hasNext()){
			AttrInfo obj = adao.obj2Entity(cursor.next());
			ls.add(obj);
		}
		return ls;
	}
}
