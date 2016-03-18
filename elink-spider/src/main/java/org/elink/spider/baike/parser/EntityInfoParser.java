package org.elink.spider.baike.parser;

import java.util.ArrayList;
import java.util.List;

import org.elink.database.pname.model.Pname;
import org.elink.spider.utils.Log;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EntityInfoParser {
	/**
	 * pname列表中有url，返回有效的url
	 * @param url
	 * @return
	 */
	public String getCorrectUrl(String url){
		String preUrl = "http://baike.baidu.com";
		if(!url.contains(preUrl)) return preUrl+url;
		return url;
	}
	/**
	 * 判断是否有infobox信息
	 */
	
	public int checkInfoBox(Document doc){
		Elements es = doc.getElementsByClass("basic-info");
		return es.size();
	}
	/**
	 * 是否有多义词
	 * @param doc
	 * @return
	 */
	public int checkMulti(Document doc){
		if(checkInfoBox(doc)>0) return 0;
		
		if(doc.text().contains("多义词")){
			Elements es = doc.getElementsByClass("para");
			for(Element e : es){
				Log.info(e.text());
				Log.info(e.getElementsByTag("a").attr("href"));
			}
			return es.size();
		}
		return 0;
	}
	/**
	 * 返回多义词pname列表
	 * @param doc
	 * @return
	 */
	public List<Pname> getMulti(Document doc){
		List<Pname> pns = new ArrayList<>();
		Elements es = doc.getElementsByClass("para");
		for(Element e : es){
			Pname pn = new Pname();
			pn.setName(e.text().trim());
			pn.setUrl(getCorrectUrl(e.getElementsByTag("a").attr("href").trim()));
			pns.add(pn);
		}
		return pns;
		
	}
}
