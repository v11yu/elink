package org.elink.spider.pname;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ListSpiderApp {
	
	public static void main(String[] args) throws IOException {
		String url = "http://baike.baidu.com/fenlei/%E6%BC%94%E5%91%98?limit=100&index=1&offset=30#gotoList";
		Document doc = Jsoup.connect(url).get();
		doc.getElementsByClass("list-right");
	}
}
