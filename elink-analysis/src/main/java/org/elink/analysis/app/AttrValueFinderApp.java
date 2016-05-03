package org.elink.analysis.app;

import java.util.List;

import org.elink.analysis.business.AttributeNameInfoBusiness;
import org.elink.analysis.business.AttributePairsInfoBusiness;
import org.elink.analysis.business.impl.AttributeNameInfoBusinessImpl;
import org.elink.analysis.business.impl.AttributePairsInfoBusinessImpl;
import org.elink.analysis.method.AbstractAttrMethod;
import org.elink.analysis.method.InfoBoxAttrMethod;
import org.elink.analysis.model.Attr;
import org.elink.analysis.parser.TextParser;
import org.elink.analysis.utils.FileWriteTools;
import org.elink.analysis.utils.Log;
import org.elink.database.model.AttributeNameInfo;
import org.elink.database.model.AttributePairsInfo;
import org.elink.database.model.EntityInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.spider.baike.business.EntityInfoBusiness;
import org.elink.spider.baike.business.impl.EntityInfoBusinessImpl;
import org.jsoup.Jsoup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mongodb.DBCursor;

/**
 * 用于文本中发现属性值上下文
 * @author v11
 *
 */
public class AttrValueFinderApp {
	AttributeNameInfoBusiness anb = new AttributeNameInfoBusinessImpl();
	EntityInfoBusiness entityInfoBusiness = new EntityInfoBusinessImpl();
	ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
	BasicRepository<EntityInfo> edao = (BasicRepository<EntityInfo>) context.getBean("entityInfoDao");
	AttributePairsInfoBusiness attrPairsBusiness = new AttributePairsInfoBusinessImpl();
	TextParser testParser = new TextParser();
	String basePath = "D:\\wwy毕业相关\\output\\";
	String contextPath ;
	String upPath;
	String downPath;
	int trainNum;
	int windowSize;
	public AttrValueFinderApp(){
		this(200,10);
	}
	public AttrValueFinderApp(int trainNum,int windowSize){
		this.trainNum = trainNum;
		this.windowSize = windowSize;
		
	}
	
	public void findContext(String keyword,int windowSize){
		
	}
	public List<Attr> getAttrs(EntityInfo en){
		AbstractAttrMethod attrMethod = new InfoBoxAttrMethod();
		List<Attr> attrs = attrMethod.getAttr(en.getSource());
		return attrs;
	}
	public List<AttributePairsInfo> getAttrPairsInfos(EntityInfo en){
		
		return attrPairsBusiness.getbyEid(en);
	}
	public void work(String attrName){
		contextPath = basePath + attrName+"_context.txt";
		upPath = basePath + attrName+"_up.txt";
		downPath = basePath + attrName+"_down.txt";
		// get [trainNum] entity
		int cnt = 0;
		DBCursor cursor = edao.findByAll();
		while(cursor.hasNext()){
			if(cnt> trainNum) break;
			EntityInfo en = edao.obj2Entity(cursor.next());
			if(en.getHasInfo() == 0) {
				Log.info(en.getEntity_name()+" 没有infobox");
				continue;
			}
			String value = null;
			List<AttributePairsInfo> attrs = getAttrPairsInfos(en);
			for(AttributePairsInfo attr : attrs){
				if(attr.getAttrName().equals(attrName)){
					value = attr.getAttrValue();
					break;
				}
			}
			if(value == null) continue;
			List<String> res = testParser.getContext(Jsoup.parse(en.getSource()).text(), value,windowSize);
			FileWriteTools.write(res, contextPath);
			res = testParser.getContextByUp(Jsoup.parse(en.getSource()).text(), value,windowSize);
			FileWriteTools.write(res, upPath);
			res = testParser.getContextByDown(Jsoup.parse(en.getSource()).text(), value,windowSize);
			FileWriteTools.write(res, downPath);
			cnt++;
			
		}
		
	}
	public static void main(String[] args) {
		new AttrValueFinderApp().work("民    族");
	}
}
