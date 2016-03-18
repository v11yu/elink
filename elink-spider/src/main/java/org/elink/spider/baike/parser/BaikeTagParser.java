package org.elink.spider.baike.parser;

import java.util.ArrayList;
import java.util.List;

import org.elink.spider.utils.Log;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BaikeTagParser {
	/**
	 * 获取下级分类名称
	 * @return
	 */
	public List<String> getSubTag(Document doc){
		Elements es = doc.getElementsByClass("category-title");
		List<String> tags = new ArrayList<>();
		int idx = -1;
		for(int i=0;i<es.size();i++){
			Element e = es.get(i);
			if(e.text().contains("下级分类")) idx = i;
		}
		if(idx == -1) return tags;
		Elements esTags = es.get(idx).getElementsByTag("a");
		for(Element e : esTags){
			tags.add(e.text());
		}
		return tags;
		
	}
}
