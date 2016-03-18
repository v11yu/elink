package org.elink.spider.baike.parser;

import java.io.IOException;

import org.elink.spider.baike.parser.EntityInfoParser;
import org.jsoup.Jsoup;
import org.junit.Test;

public class EntityInfoAnalysisTest {
	EntityInfoParser ea = new EntityInfoParser();
	@Test
	public void testMulti() throws IOException{
		String url = "http://baike.baidu.com/item/%E4%BD%95%E8%B4%B5%E6%9E%97";
		int num = ea.checkMulti(Jsoup.connect(url).get());
		System.out.println(num);
	}
}
