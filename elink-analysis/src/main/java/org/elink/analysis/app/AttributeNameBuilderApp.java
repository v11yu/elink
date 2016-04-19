package org.elink.analysis.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.elink.analysis.business.AttributePairsInfoBusiness;
import org.elink.analysis.business.impl.AttributePairsInfoBusinessImpl;
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

public class AttributeNameBuilderApp {
	
	public void buildAttrName(){
		ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
		BasicRepository<AttributeNameInfo> andao = (BasicRepository<AttributeNameInfo>) context.getBean("attributeNameInfoDao");
		BasicRepository<AttributePairsInfo> adao = (BasicRepository<AttributePairsInfo>) context.getBean("attributePairsInfoDao");
		AttributePairsInfoBusiness attrPairsBusiness = new AttributePairsInfoBusinessImpl();
		Map<String, Integer> mp = new HashMap<>();
		Map<String, Set<String>> mpSets = new HashMap<>();
		for(AttributePairsInfo attr:attrPairsBusiness.getAllList()){
			String key = attr.getAttrName();
			if(!mp.containsKey(key)){
				mp.put(key, 0);
				mpSets.put(key, new HashSet<String>());
			}
			mpSets.get(key).add(attr.getAttrValue());
			mp.put(key,mp.get(key)+1);
		}
		Log.info("set size is "+mp.size());
		Iterator<Entry<String, Integer>> iter = mp.entrySet().iterator();
		while(iter.hasNext()){
			Entry<String, Integer> en = iter.next();
			AttributeNameInfo an = new AttributeNameInfo();
			an.setAttrCount(en.getValue());
			an.setAttrName(en.getKey());
			List<String> values = new ArrayList<>();
			values.addAll(mpSets.get(en.getKey()));
			an.setAttrValues(values);
			Log.info(en.getKey() +" size :"+mpSets.get(en.getKey()).size());
			try {
				andao.saveAndUpdate(an);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws Exception {
		
		new AttributeNameBuilderApp().buildAttrName();
		
	
	}
}
