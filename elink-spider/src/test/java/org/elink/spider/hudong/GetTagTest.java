package org.elink.spider.hudong;

import java.io.IOException;

import org.elink.spider.hudong.parser.HuDongParser;
import org.elink.spider.hudong.parser.HuDongParserImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class GetTagTest {
	String fenleiList = "http://fenlei.baike.com/%E8%AE%A1%E7%AE%97%E6%9C%BA%E5%AE%89%E5%85%A8/list/";
	HuDongParser hp = new HuDongParserImpl();
	//@Test
	public void testClassList() throws IOException{
		Document doc = Jsoup.connect(fenleiList).get();
		Elements es = doc.getElementsByClass("link_blue");
		Elements tags = es.get(0).getElementsByTag("dd");
		for(Element e :tags) System.out.println(e.text()+" "+e.getElementsByTag("a").attr("href")) ;
		System.out.println(es.size());
		System.out.println(doc.text());
	}
	@Test
	public void testGetTag() throws IOException{
		Document doc = Jsoup.connect("http://fenlei.baike.com/%E8%AE%A1%E7%AE%97%E6%9C%BA%E5%AE%89%E5%85%A8/list/").get();
		System.out.println(hp.getEntity(doc));
		String name = "Backdoor/D";
		name = name.replace('/', '-');
		System.out.println(name);
	}
}
