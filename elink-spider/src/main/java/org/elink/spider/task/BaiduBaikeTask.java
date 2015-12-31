package org.elink.spider.task;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.elink.database.model.PageInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.spider.utils.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class BaiduBaikeTask {
	String baseUrl = "http://baike.baidu.com/item/";
	public void work(List<String> names){
		ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
		BasicRepository<PageInfo> pdao = (BasicRepository<PageInfo>) context.getBean("pageInfoDao");
		
		for(String name : names){
			String key = null;
			try {
				key = URLEncoder.encode(name, "utf-8");
			} catch (UnsupportedEncodingException e) {
				Log.error(e);
			}
			Log.info("开始抓取 "+name);
			if(key == null) continue;
			try {
				Document doc = Jsoup.connect(baseUrl+key).get();
				//Log.info(doc.text());
				PageInfo pi = new PageInfo();
				pi.setName(name);
				pi.setContent(doc.text());
				
			} catch (IOException e) {
				Log.error("jsoup error "+e);
			}
		}
	}
}
