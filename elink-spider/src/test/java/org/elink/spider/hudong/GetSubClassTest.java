package org.elink.spider.hudong;

import java.io.IOException;

import org.elink.spider.hudong.parser.HuDongParser;
import org.elink.spider.hudong.parser.HuDongParserImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class GetSubClassTest {
	/*
	 * 计算机安全
	 */
	String url = "http://fenlei.baike.com/%E8%AE%A1%E7%AE%97%E6%9C%BA%E5%AE%89%E5%85%A8";
	HuDongParser hp = new HuDongParserImpl();
	//@Test
	public void testGetSub() throws IOException{
		Document doc = Jsoup.connect(url).get();
		Elements es = doc.getElementsByClass("sort");
		System.out.println(es.size());
		Elements h3s = es.get(0).getElementsByTag("h3");
		System.out.println("h3 size "+ h3s.size());
		for(Element e : h3s) System.out.println(e.text());
		
		Elements ps = es.get(0).getElementsByTag("p");
		if(ps.size() == 0) return ; // none
		for(Element e :ps){
			Elements links = e.getElementsByTag("a");
			for(Element ee : links) System.out.println(ee.text()+" "+ee.attr("href"));
		}
	}
	@Test
	public void testGetSubTagList() throws IOException{
		Document doc = Jsoup.connect(url).get();
		System.out.println(hp.getSubTagClassName(doc));
	}
}
