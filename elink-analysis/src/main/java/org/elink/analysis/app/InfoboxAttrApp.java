package org.elink.analysis.app;

import java.util.List;

import org.elink.analysis.business.AttributeNameInfoBusiness;
import org.elink.analysis.business.AttributePairsInfoBusiness;
import org.elink.analysis.business.impl.AttributeNameInfoBusinessImpl;
import org.elink.analysis.business.impl.AttributePairsInfoBusinessImpl;
import org.elink.analysis.method.AbstractAttrMethod;
import org.elink.analysis.method.InfoBoxAttrMethod;
import org.elink.analysis.model.Attr;
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

/**
 * 写入InfoBox的attribute
 * @author v11
 *
 */
public class InfoboxAttrApp {
	ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
	BasicRepository<EntityInfo> edao = (BasicRepository<EntityInfo>) context.getBean("entityInfoDao");
	AttributePairsInfoBusiness attrPairsBusiness = new AttributePairsInfoBusinessImpl();
	AttributeNameInfoBusiness anb = new AttributeNameInfoBusinessImpl();
	void saveAttrInfo(AttributePairsInfo api){
		AttributeNameInfo ani = new AttributeNameInfo();
		ani.setAttrCount(1);
		ani.setAttrName(api.getAttrName());
	}
	void saveAttrPairs(EntityInfo en){
		AbstractAttrMethod attrMethod = new InfoBoxAttrMethod();
		List<Attr> attrs = attrMethod.getAttr(en.getSource());
		for(Attr at:attrs){
			AttributePairsInfo api = new AttributePairsInfo();
			api.setEntityId(en.getId());
			api.setEntityName(en.getEntity_name());
			api.setAttrName(at.getName());
			api.setAttrValue(at.getValue());
			attrPairsBusiness.save(api);
		}
	}
	
	public void work(){
		DBCursor cursor = edao.findByAll();
		int cnt = 0;
		while(cursor.hasNext()){
			EntityInfo en = edao.obj2Entity(cursor.next());
			if(en.getHasInfo() == 0) {
				Log.info(en.getEntity_name()+" 没有infobox");
				continue;
			}
			Log.info(en.getEntity_name()+" cnt "+cnt++);
			saveAttrPairs(en);
		}
	}
	public static void main(String[] args) throws Exception {
		new InfoboxAttrApp().work();
		
	}
}
