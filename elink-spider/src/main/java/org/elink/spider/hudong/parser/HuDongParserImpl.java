package org.elink.spider.hudong.parser;

import java.util.ArrayList;
import java.util.List;

import org.elink.database.hudong.model.Entity;
import org.elink.database.hudong.model.Tag;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HuDongParserImpl implements HuDongParser{

	

	@Override
	public List<String> getSubTagClassUrl(Document doc) {
		List<String> curl =new ArrayList<String>();
		
		return curl;
	}
	@Override
	public List<Tag> getSubTagClass(Document doc) {
		List<Tag> curl =new ArrayList<Tag>();
		
		return curl;
	}

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
	public List<String> getSuperClass(Document doc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getEntityName(Document doc) {
		Elements es = doc.getElementsByClass("link_blue");
		Elements tags = es.get(0).getElementsByTag("dd");
		
		List<String> res = new ArrayList<String>();
		for(Element e :tags){
			Tag tag = new Tag();
			String url = e.getElementsByTag("a").attr("href");
			if(url.equals("javascript:void(0);")) continue;
			res.add(e.text().trim());
		}
		return res;
	}

	@Override
	public List<String> getEntityUrl(Document doc) {
		Elements es = doc.getElementsByClass("link_blue");
		Elements tags = es.get(0).getElementsByTag("dd");
		
		List<String> res = new ArrayList<String>();
		for(Element e :tags){
			Tag tag = new Tag();
			String url = e.getElementsByTag("a").attr("href");
			if(url.equals("javascript:void(0);")) continue;
			res.add(url);
		}
		return res;
	}

	@Override
	public List<Entity> getEntity(Document doc) {
		// TODO Auto-generated method stub
		Elements es = doc.getElementsByClass("link_blue");
		Elements tags = es.get(0).getElementsByTag("dd");
		
		List<Entity> res = new ArrayList<Entity>();
		for(Element e :tags){
			Entity en = new Entity();
			String url = e.getElementsByTag("a").attr("href");
			if(url.equals("javascript:void(0);")) continue;
			en.setName(e.text().trim());
			en.setUrl(url);
			res.add(en);
		}
		return res;
	}

	

	

}
