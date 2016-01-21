package org.elink.spider.hudong;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class GetEntityTagTest {
	String url = "http://www.baike.com/wiki/%E6%9C%A8%E9%A9%AC%E7%97%85%E6%AF%92";
	@Test
	public void testget() throws IOException{
		Document doc = Jsoup.connect(url).get();
		Elements es = doc.getElementsByClass("h27");
		System.out.println(es.text());
		Elements as = es.get(0).getElementsByTag("a");
		for(Element e :as){
			System.out.println(e.text());
		}
	}
}
