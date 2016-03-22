package org.elink.spider.baike;

import java.util.List;

import org.elink.database.model.EntityInfo;
import org.elink.database.model.MultiEntityInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.pname.model.Pname;
import org.elink.spider.baike.business.EntityInfoBusiness;
import org.elink.spider.baike.business.MultiEntityInfoBusiness;
import org.elink.spider.baike.business.PnameBusiness;
import org.elink.spider.baike.business.impl.EntityInfoBusinessImpl;
import org.elink.spider.baike.business.impl.MultiEntityInfoBusinessImpl;
import org.elink.spider.baike.business.impl.PnameBusinessImpl;
import org.elink.spider.baike.input.Inputer;
import org.elink.spider.baike.input.PnameInputer;
import org.elink.spider.baike.parser.EntityInfoParser;
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
	EntityInfoParser ea = new EntityInfoParser();
	//PnameBusiness pnameBusiness = new PnameBusinessImpl();
	EntityInfoBusiness entityInfoBusiness = new EntityInfoBusinessImpl();
	MultiEntityInfoBusiness multiEntityInfoBusiness = new MultiEntityInfoBusinessImpl();
	Inputer<Pname> inputer = new PnameInputer();

	public void getEntityInfo(Pname pn,boolean isSub){
		
		Log.info("处理实体信息 do get entity info" + pn);
		
		String url = ea.getUrlFromPname(pn);
		EntityInfo en = new EntityInfo();
		en.setUrl(url);
		if(entityInfoBusiness.checkEntityExist(en) ){
			Log.error(en.getUrl()+" exist");
			return ;
		}
		Document doc = HttpUtils.getDocument(url);
		if(doc == null) return ;
		en.setEntity_name(pn.getName());
		en.setHasInfo(ea.checkInfoBox(doc));
		en.setSource(doc.html());
		if(!isSub){
			en.setIsMulti(ea.checkMulti(doc));
			// add multi entity info
			if(en.getIsMulti()>0){
				List<Pname> pns = ea.getMulti(doc);
				MultiEntityInfo me = new MultiEntityInfo();
				me.setName(en.getEntity_name());
				me.setCnames(pns);
				Log.info("出现多义词 "+pns);
				multiEntityInfoBusiness.save(me);
				
				en.setMultiUrl(pns);
				for(Pname p : pns){
					//不对pname再改动
					//pnameBusiness.savePname(p); 
					getEntityInfo(p,true);
				}
			}
		}
		entityInfoBusiness.save(en);
	}
	public void work(){
		for(Pname pn:inputer.getInputData()){
			try{
				getEntityInfo(pn,false);
			}catch(Exception e){
				Log.info(e);
			}
		}
	}
	public static void main(String[] args) {
		new BaikeEntityInfoSpiderApp().work();
	}
}
