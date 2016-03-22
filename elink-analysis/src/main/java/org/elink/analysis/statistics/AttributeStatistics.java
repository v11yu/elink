package org.elink.analysis.statistics;

import java.util.*;
import java.util.Map.Entry;

import org.elink.analysis.business.AttributeNameInfoBusiness;
import org.elink.analysis.business.AttributePairsInfoBusiness;
import org.elink.analysis.business.impl.AttributeNameInfoBusinessImpl;
import org.elink.analysis.business.impl.AttributePairsInfoBusinessImpl;
import org.elink.analysis.utils.Log;
import org.elink.database.model.AttributeNameInfo;
import org.elink.database.model.AttributePairsInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.common.collect.Lists;

public class AttributeStatistics {
	ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
	AttributePairsInfoBusiness attrPairsBusiness = new AttributePairsInfoBusinessImpl();
	BasicRepository<AttributeNameInfo> andao = (BasicRepository<AttributeNameInfo>) context.getBean("attributeNameInfoDao");
	AttributeNameInfoBusiness anb = new AttributeNameInfoBusinessImpl();
	public void writeAttr() throws Exception{
		Map<String, Integer> mp = new HashMap<>();
		for(AttributePairsInfo attr:attrPairsBusiness.getAllList()){
			String key = attr.getAttrName();
			if(!mp.containsKey(key)){
				mp.put(key, 0);
			}
			mp.put(key,mp.get(key)+1);
		}
		Log.info(mp.size());
		Iterator<Entry<String, Integer>> iter = mp.entrySet().iterator();
		while(iter.hasNext()){
			Entry<String, Integer> en = iter.next();
			AttributeNameInfo an = new AttributeNameInfo();
			an.setAttrCount(en.getValue());
			an.setAttrName(en.getKey());
			andao.saveAndUpdate(an);
		}
	}
	public void sortCount(){
		List<AttributeNameInfo> res = anb.getAllList();
		res.sort(new Comparator<AttributeNameInfo>() {

			@Override
			public int compare(AttributeNameInfo o1, AttributeNameInfo o2) {
				// TODO Auto-generated method stub
				return o2.getAttrCount()-o1.getAttrCount();
			}
		});
		for(AttributeNameInfo attr : res){
			Log.info(attr);
		}
	}
	public static void main(String[] args) throws Exception {
		new AttributeStatistics().sortCount();
	}
}
