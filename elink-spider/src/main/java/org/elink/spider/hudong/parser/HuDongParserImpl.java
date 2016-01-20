package org.elink.spider.hudong.parser;

import java.util.ArrayList;
import java.util.List;

import org.elink.database.hudong.model.HuDongEntity;
import org.elink.database.hudong.model.HudongTag;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HuDongParserImpl implements HuDongParser{

	

	@Override
	public List<String> getSubTagClassName(Document doc) {
		List<String> curl =new ArrayList<String>();
		Elements es = doc.getElementsByClass("sort");
		Elements h3s = es.get(0).getElementsByTag("h3");
		if(h3s.size() == 2){
			Elements ps = es.get(0).getElementsByTag("p");
			Elements links = ps.get(1).getElementsByTag("a");
			for(Element ee : links){
				curl.add(ee.text().trim());
			}
			
		}else if(h3s.size() == 1){
			if(!h3s.get(0).text().trim().equals("下一级微百科")) return curl;
			Elements ps = es.get(0).getElementsByTag("p");
			Elements links = ps.get(0).getElementsByTag("a");
			for(Element ee : links){
				curl.add(ee.text().trim());
			}
		}
		return curl;
	}


	@Override
	public List<HuDongEntity> getEntity(Document doc) {
		// TODO Auto-generated method stub
		Elements es = doc.getElementsByClass("link_blue");
		Elements tags = es.get(0).getElementsByTag("dd");
		
		List<HuDongEntity> res = new ArrayList<HuDongEntity>();
		for(Element e :tags){
			HuDongEntity en = new HuDongEntity();
			String url = e.getElementsByTag("a").attr("href");
			if(url.equals("javascript:void(0);")) continue;
			en.setName(e.text().trim());
			en.setUrl(url);
			res.add(en);
		}
		return res;
	}


	@Override
	public List<String> getEntityTag(Document doc) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
