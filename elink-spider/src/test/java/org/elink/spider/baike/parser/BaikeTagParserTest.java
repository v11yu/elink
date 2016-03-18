package org.elink.spider.baike.parser;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class BaikeTagParserTest {
	BaikeTagParser bp = new BaikeTagParser();

	@Test
	public void testTag() throws IOException{
		String url = "http://baike.baidu.com/fenlei/%E5%A8%B1%E4%B9%90%E4%BA%BA%E7%89%A9";
		Document doc = Jsoup.connect(url).get();
		List<String> res = bp.getSubTag(doc);
		System.out.println(res);
	}
	
}
