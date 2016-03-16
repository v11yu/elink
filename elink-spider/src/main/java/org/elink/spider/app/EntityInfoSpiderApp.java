package org.elink.spider.app;

import java.io.IOException;

import org.elink.database.model.EntityInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.DBQuery;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.pname.model.Pname;
import org.elink.spider.utils.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
/**
 * 用于采集数据库中含有pname的字段
 * @author v11
 *
 */
public class EntityInfoSpiderApp {
	public static String getCorrectUrl(String url){
		String preUrl = "http://baike.baidu.com";
		if(!url.contains(preUrl)) return preUrl+url;
		return url;
	}
	public static int checkInfoBox(Document doc){
		Elements es = doc.getElementsByClass("basic-info");
		return es.size();

	}
	/**
	 * exist return true
	 * @param edao
	 * @param url
	 * @return
	 */
	public static boolean check(BasicRepository<EntityInfo> edao,String url){
		DBQuery query = new DBQuery();
		query.equalsOperation("url",url);
		DBCursor cursor = edao.findQuery(query);
		if(cursor.size()>0) return true;
		return false;
		
	}
	//(๑• . •๑)
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
		BasicRepository<Pname> pdao = (BasicRepository<Pname>) context.getBean("pnameDao");
		DBCursor cursor = pdao.findByAll();
		BasicRepository<EntityInfo> edao = (BasicRepository<EntityInfo>) context.getBean("entityInfoDao");
		int cnt = 0;
		while(cursor.hasNext()){
			
			DBObject obj = cursor.next();
			Pname pn = pdao.obj2Entity(obj);
			System.out.println(pn.getUrl());
			String url = getCorrectUrl(pn.getUrl());
			
			if(check(edao,url) ){
				System.out.println("exist");
				continue;
			}
			Document doc = null;
			for(int i = 0;i<3;i++){
				try{
					doc = Jsoup.connect(url).get();
				}catch(Exception e){
					Thread.sleep(1000);
				}
				if(doc != null) break;
			}
			
			if(doc == null) continue;
			EntityInfo en = new EntityInfo();
			en.setUrl(url);
			en.setEntity_name(pn.getName());
			en.setHasInfo(checkInfoBox(doc));
			en.setSource(doc.html());
			edao.saveAndUpdate(en);
	
			//if(cnt++>500) break;
		}
		//System.out.println(checkInfoBox(Jsoup.connect("http://baike.baidu.com/view/6499.htm").get()));
		//System.out.println(checkInfoBox(Jsoup.connect("http://baike.baidu.com/item/%E4%B9%9D%E9%BC%8E%E8%AE%B0").get()));
	}
}
