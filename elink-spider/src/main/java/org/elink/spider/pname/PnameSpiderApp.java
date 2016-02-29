package org.elink.spider.pname;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

import org.elink.spider.utils.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PnameSpiderApp {
	static Set<String> mp = new HashSet<String>();
	static String baseUrl = "http://www.baidu.com/s?wd=";
	static String Bg = "周杰伦";
	static List<String> ls = new ArrayList<>();
	public static void dfs(String name) throws IOException{
		List<String> tmp = new ArrayList<>();
		if(ls.size()>1000) return ;
		String url = baseUrl + name;
		Document doc = Jsoup.connect(url).get();
		Elements es = doc.getElementsByClass("opr-recommends-merge-panel");
		for (Element ei : es) {
			Elements es2 = ei.getElementsByClass("c-span4");
			for (Element e : es2) {
				String cname = e.text().trim();
				if(!mp.contains(cname))
				Log.info(e.text());
				ls.add(e.text().trim());
				tmp.add(e.text().trim());
				mp.add(e.text().trim());
			}
		}
		
		for(String cname : tmp)	dfs(cname);
	}
	public static void main(String[] args) throws IOException {
		dfs(Bg);
		System.out.println(mp.size());
	}
}
