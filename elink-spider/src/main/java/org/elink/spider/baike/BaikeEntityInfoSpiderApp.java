package org.elink.spider.baike;

import org.elink.database.model.EntityInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.pname.model.Pname;
import org.elink.spider.baike.business.EntityInfoBusiness;
import org.elink.spider.baike.business.PnameBusiness;
import org.elink.spider.baike.business.impl.EntityInfoBusinessImpl;
import org.elink.spider.baike.business.impl.PnameBusinessImpl;
import org.elink.spider.baike.input.Inputer;
import org.elink.spider.baike.input.PnameInputer;
import org.elink.spider.utils.HttpUtils;
import org.elink.spider.utils.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 根据pname爬取百科实体信息，存入mongodb
 * @author v11
 *
 */
public class BaikeEntityInfoSpiderApp {
	
	EntityInfoBusiness entityInfoBusiness = new EntityInfoBusinessImpl();
	Inputer<Pname> inputer = new PnameInputer();
	public String getCorrectUrl(String url){
		String preUrl = "http://baike.baidu.com";
		if(!url.contains(preUrl)) return preUrl+url;
		return url;
	}
	public int checkInfoBox(Document doc){
		Elements es = doc.getElementsByClass("basic-info");
		return es.size();
	}
	public void getEntityInfo(Pname pn){
		System.out.println(pn.getUrl());
		String url = getCorrectUrl(pn.getUrl());
		EntityInfo en = new EntityInfo();
		en.setUrl(url);
		if(entityInfoBusiness.checkEntityExist(en) ){
			Log.error(en.getUrl()+" exist");
			return ;
		}
		Document doc = HttpUtils.getDocument(url);
		if(doc == null) return ;
		en.setEntity_name(pn.getName());
		en.setHasInfo(checkInfoBox(doc));
		en.setSource(doc.html());
		entityInfoBusiness.save(en);
	}
	public void work(){
		for(Pname pn:inputer.getInputData()){
			getEntityInfo(pn);
		}
	}
	public static void main(String[] args) {
		new BaikeEntityInfoSpiderApp().work();
	}
}
