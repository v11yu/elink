package org.elink.spider;

import java.io.IOException;

import org.elink.spider.utils.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	public static void main(String[] args) throws IOException {
		Document doc =Jsoup.connect("http://fenlei.baike.com/%E8%AE%A1%E7%AE%97%E6%9C%BA%E5%AE%89%E5%85%A8").get();
		Log.info(doc.text());
	}
}
