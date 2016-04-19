package org.elink.analysis.app;

import java.util.List;

import org.elink.analysis.business.AttributePairsInfoBusiness;
import org.elink.analysis.business.impl.AttributePairsInfoBusinessImpl;
import org.elink.analysis.parser.CommonTools;
import org.elink.analysis.parser.TextParser;
import org.elink.analysis.utils.Log;
import org.elink.database.model.AttributePairsInfo;
import org.elink.database.model.EntityInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.jsoup.Jsoup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mongodb.DBCursor;

public class AttrExtraction {
	int windowSize = 15;
	int acNum = 0; // ac num
	int pSum = 0; // predict num
	int cSum = 0;// correct num
	ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
	BasicRepository<EntityInfo> edao = (BasicRepository<EntityInfo>) context.getBean("entityInfoDao");
	AttributePairsInfoBusiness attrPairsBusiness = new AttributePairsInfoBusinessImpl();
	TextParser testParser = new TextParser();
	public List<AttributePairsInfo> getAttrPairsInfos(EntityInfo en){
		return attrPairsBusiness.getbyEid(en);
	}
	String getAttr(EntityInfo en,String name){
		List<AttributePairsInfo> attrs = attrPairsBusiness.getbyEid(en);
		for(AttributePairsInfo attr:attrs){
			if(attr.getAttrName().equals(name)) return attr.getAttrValue();
		}
		return null;
	}
	String extractAttr(EntityInfo en,String pt,String pattern,String target){
		
		List<String> t = testParser.getContext(Jsoup.parse(en.getSource()).text(), pt,2*windowSize);
		List<String> ut = testParser.getContextByUp(Jsoup.parse(en.getSource()).text(), pt,windowSize);
		List<String> dt = testParser.getContextByDown(Jsoup.parse(en.getSource()).text(), pt,windowSize);
		
		
		List<String> res = null;
		if(pattern.equals("down")) res = ut;
		else res = dt;
		
		Log.info(en.getEntity_name()+" 抽取获得实体属性个数:"+res.size());
		pSum += res.size();
		int tmp = 0;
		for(int i=0;i<res.size();i++){
			Log.info(" 实体属性 :"+res.get(i));
			int acc = CommonTools.LCS(target, res.get(i));
			if(acc>1){
				acNum++;
				tmp++;
			}
		}
		if(tmp >1) cSum += (tmp-1);
		return null;
	}
	public void work(String name,String pt,String pattern){
		DBCursor cursor = edao.findByAll();
		int cnt = 0;
		while(cursor.hasNext()){
			EntityInfo en = edao.obj2Entity(cursor.next());
			String value = getAttr(en,name);
			if(value == null) continue;
			Log.info(cnt+" "+en.getEntity_name()+" 正确的"+name+" :"+value);
			cnt++;
			extractAttr(en,pt,pattern,value);
			cSum++;
			if(cnt > 120) break;
		}
		
		Log.info("正确个数："+acNum+" 总预测数"+pSum+" 总正确个数"+cSum);
	}
	// 出生日期 出生地 毕业院校
	public static void main(String[] args) {
		String name = "国    籍";
		new AttrExtraction().work(name,"出生于","up");
		
	}
}
