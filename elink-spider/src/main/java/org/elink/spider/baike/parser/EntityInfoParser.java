package org.elink.spider.baike.parser;

import java.util.ArrayList;
import java.util.List;

import org.elink.database.pname.model.Pname;
import org.elink.spider.utils.HttpUtils;
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
	 * 统一将url变成baike.baidu.com/item/*形式
	 * @param pn
	 * @return
	 */
	public String getUrlFromPname(Pname pn){
		return "http://baike.baidu.com/item/"+HttpUtils.encode(pn.getName());
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
		Elements es = doc.getElementsByClass("main-content");
		if(es.size() == 0) return 0;
		Element main_content = es.get(0);
		//Log.info(main_content.text());
		if(main_content.text().contains("多义词") && !main_content.text().contains("词条标签")){
			Elements ees = doc.getElementsByClass("para");
			return ees.size();
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
