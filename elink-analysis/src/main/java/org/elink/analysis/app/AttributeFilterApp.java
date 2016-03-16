package org.elink.analysis.app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.elink.analysis.utils.Log;
import org.elink.database.model.AttributeNameInfo;
import org.elink.database.model.AttributePairsInfo;
import org.elink.database.model.EntityInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class AttributeFilterApp {
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
		BasicRepository<AttributeNameInfo> andao = (BasicRepository<AttributeNameInfo>) context.getBean("attributeNameInfoDao");
		BasicRepository<AttributePairsInfo> adao = (BasicRepository<AttributePairsInfo>) context.getBean("attributePairsInfoDao");
		Log.info(adao.findByAll().size());
		Map<String, Integer> mp = new HashMap<>();
		DBCursor cursor = adao.findByAll();
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			AttributePairsInfo a = adao.obj2Entity(obj);
			String key = a.getName();
			if(!mp.containsKey(key)){
				mp.put(key, 0);
			}
			mp.put(key,mp.get(key)+1);
		}
		Log.info("set size is "+mp.size());
		
		Iterator<Entry<String, Integer>> iter = mp.entrySet().iterator();
		while(iter.hasNext()){
			Entry<String, Integer> en = iter.next();
			AttributeNameInfo an = new AttributeNameInfo();
			an.setName(en.getKey());
			an.setCount(en.getValue());
			andao.saveAndUpdate(an);
		}
	
	}
}
