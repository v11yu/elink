package org.elink.analysis.app;

import java.util.List;

import org.elink.analysis.method.AbstractAttrMethod;
import org.elink.analysis.method.InfoBoxAttrMethod;
import org.elink.analysis.model.Attr;
import org.elink.analysis.utils.Log;
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
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
		BasicRepository<EntityInfo> edao = (BasicRepository<EntityInfo>) context.getBean("entityInfoDao");
		BasicRepository<AttributePairsInfo> adao = (BasicRepository<AttributePairsInfo>) context.getBean("attributePairsInfoDao");
		AbstractAttrMethod attrMethod = new InfoBoxAttrMethod();
		DBCursor cursor = edao.findByAll();
		int cnt = 0;
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			EntityInfo e = edao.obj2Entity(obj);
			if(e.getHasInfo() == 0) {
				Log.info(e.getEntity_name()+" 没有infobox");
				continue;
			}
			List<Attr> attrs = attrMethod.getAttr(e.getSource());
			for(Attr at:attrs){
				AttributePairsInfo api = new AttributePairsInfo();
				api.setEntity_id(e.getId());
				api.setEntity_name(e.getEntity_name());
				api.setName(at.getName());
				api.setValue(at.getValue());
				adao.saveAndUpdate(api);
			}
			Log.info("cnt "+cnt++);
		}
		
	}
}
