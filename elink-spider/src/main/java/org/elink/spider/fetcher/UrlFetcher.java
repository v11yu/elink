package org.elink.spider.fetcher;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



public class UrlFetcher extends BasicHttpMethod {
	
	private HttpClient client = new DefaultHttpClient();
	public String getUrl(String url){
		return url;	
	}
	public Document getUrlDocument(String url){
		HttpGet getSearch = addHttpGetWithHeader(url);
		HttpResponse res = null;
		try {
			res = client.execute(getSearch);
			String responseBodyString = getResponseBody(res);
			//getLogger().info(responseBodyString);
			Document doc = Jsoup.parse(responseBodyString);
			release(res);
			return doc;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
		}
		return null;
	}
	public static void main(String[] args) {
		UrlFetcher f = new UrlFetcher();
		Document d = f.getUrlDocument("http://baike.baidu.com/fenlei/%E6%94%BF%E6%B2%BB%E4%BA%BA%E7%89%A9");
		System.out.println(d.text());
	}
}
