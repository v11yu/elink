package org.elink.spider.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class FileTest {
	@Test
	public void test() throws IOException{
		FileUtils out = new FileUtils("F:\\v11-test-space\\hudong\\a.html", "out");
		Document doc = Jsoup.connect("http://www.baike.com/wiki/AVG[%E5%AE%89%E5%85%A8%E8%BD%AF%E4%BB%B6]").get();
		out.writeLine(doc.html());
		out.close();
	}
}
