package org.elink.spider.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.pname.model.Pname;
import org.elink.spider.utils.HttpUtils;
import org.elink.spider.utils.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * 根据人名进行DFS递归求相关人名
 * @author v11
 *
 */
public class PnameSpiderApp {
	static Set<String> mp = new HashSet<String>();
	static String baseUrl = "http://www.baidu.com/s?wd=";
	static String[] Bg = {"周杰伦","刘翔","李宗盛","姚明","李娜"};
	static List<String> ls = new ArrayList<>();
	static ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
	static BasicRepository<Pname> pdao = (BasicRepository<Pname>) context.getBean("pnameDao");
	public static void dfs(String name) throws Exception{
		List<String> tmp = new ArrayList<>();
		if(ls.size()>1000) return ;
		String url = baseUrl + name;
		Document doc = Jsoup.connect(url).get();
		Elements es = doc.getElementsByClass("opr-recommends-merge-panel");
		for (Element ei : es) {
			Elements es2 = ei.getElementsByClass("c-span4");
			for (Element e : es2) {
				String cname = e.text().trim();
				if(!mp.contains(cname)){
					ls.add(e.text().trim());
					tmp.add(e.text().trim());
					mp.add(e.text().trim());
					Log.info(cname);
					Pname pn = new Pname();
					pn.setName(cname);
					pn.setUrl("http://baike.baidu.com/item/"+HttpUtils.encode(cname));
					pdao.saveAndUpdate(pn);
				}
			}
		}
		for(String c:tmp) dfs(c);
	}
	public static void main(String[] args) throws Exception {	
		
		for(String bg:Bg){
			mp.clear();
			ls.clear();
			dfs(bg);
			System.out.println(bg+" "+mp.size());
			for(String cname:ls){
				Log.info(cname + " "+"http://baike.baidu.com/item/"+HttpUtils.encode(cname));
			}
		}
		
	}
}
