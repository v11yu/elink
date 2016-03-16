package org.elink.spider.app;

import java.io.IOException;

import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.pname.model.Pname;
import org.elink.spider.utils.HttpUtils;
import org.elink.spider.utils.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 爬去人名分類下的人名列表
 * @author v11
 *
 */
public class ListSpiderApp {
	
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
		BasicRepository<Pname> pdao = (BasicRepository<Pname>) context.getBean("pnameDao");
		//String ls[] = {"演员","娱乐人物","歌手","明星"};
		String ls[] = {"体育人物","热点人物"};
		for(String key:ls){			
			for (int idx = 1; idx < 30; idx++) {
				String url = "http://baike.baidu.com/fenlei/" + HttpUtils.encode(key)
						+ "?limit=50&index="+idx+"#gotoList";
				Log.info(url);
				Document doc = Jsoup.connect(url).get();
				Elements es = doc.getElementsByClass("grid-list").get(0).getElementsByClass("title");
				if(es.size() == 0) break;
				System.out.println(es.size());
				for (Element e : es) {
					Pname pn = new Pname();
					pn.setName(e.text());
					pn.setUrl(e.attr("href"));
					pdao.saveAndUpdate(pn);
					Log.info(e.attr("href") + " " + e.text());
				}
			}
		}

	}
}
