package org.elink.spider.baike;

import java.util.ArrayList;
import java.util.List;

import org.elink.database.pname.model.Pname;
import org.elink.spider.baike.business.PnameBusiness;
import org.elink.spider.baike.business.impl.PnameBusinessImpl;
import org.elink.spider.baike.parser.BaikeTagParser;
import org.elink.spider.baike.parser.EntityInfoParser;
import org.elink.spider.utils.HttpUtils;
import org.elink.spider.utils.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 从百科分类中爬取相关信息
 * http://baike.baidu.com/fenlei/娱乐人物
 * @author v11
 *
 */
public class BaikeTagSpiderApp {
	PnameBusiness pnameBusiness = new PnameBusinessImpl();
	EntityInfoParser eip = new EntityInfoParser();
	BaikeTagParser bp = new BaikeTagParser();
	void spiderTag(String key,List<String> tags){
		for (int idx = 1; idx < 100; idx++) {
			String url = "http://baike.baidu.com/fenlei/" + HttpUtils.encode(key)
					+ "?limit=50&index="+idx+"#gotoList";
			Log.info(key+" "+url);
			Document doc = HttpUtils.getDocument(url);
			Elements es = doc.getElementsByClass("grid-list").get(0).getElementsByClass("title");
			if(es.size() == 0) break;
			for (Element e : es) {
				Pname pn = new Pname();
				String entityUrl = eip.getCorrectUrl(e.attr("href"));
				pn.setName(e.text());
				pn.setUrl(entityUrl);
				pn.setTags(tags);
				pnameBusiness.savePname(pn);
				Log.info(entityUrl + " " + e.text());
			}
		}
	}
	void dfsTag(String tagName,List<String> superTags){
		String url = "http://baike.baidu.com/fenlei/"+ HttpUtils.encode(tagName);
		Document doc = HttpUtils.getDocument(url);
		List<String> subTags = bp.getSubTag(doc);
		Log.info(tagName+" "+subTags);
		try{
			spiderTag(tagName,superTags);
		}catch(Exception e){
			Log.info(tagName + " "+e);
		}
		for(String subTag:subTags){
			List<String> nowTags = new ArrayList<>();
			nowTags.addAll(subTags);
			nowTags.add(subTag);
			dfsTag(subTag,nowTags);
		}
	}
	void work(String beginTags[]){
		for(String tag:beginTags){
			dfsTag(tag,new ArrayList<String>());
		}
	}
	public static void main(String[] args) {
		String beginTags[] = {"娱乐人物","文化人物","体育人物","热点人物","自然科学人物","社会科学人物","政治人物"};
		new BaikeTagSpiderApp().work(beginTags);;
	}
}
