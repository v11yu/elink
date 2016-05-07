package org.elink.analysis.app.second;
import java.io.IOException;
import java.util.*;

import javax.swing.plaf.TextUI;

import org.elink.analysis.utils.TaskConfig;
import org.elink.analysis.utils.TextUtils;
import org.elink.database.business.AttributeNameInfoBusiness;
import org.elink.database.model.AttributeNameInfo;
import org.elink.database.model.cluster.AttrInfo;
import org.elink.spider.baike.business.AttrInfoBusiness;
import org.elink.spider.utils.FileUtils;
import org.elink.spider.utils.HttpUtils;
import org.elink.spider.utils.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 用于获取实体属性名特征
 * @author v11
 *
 */
public class AttrCharacSpiderApp {
	AttributeNameInfoBusiness attrNameBz = new AttributeNameInfoBusiness();
	AttrInfoBusiness attrBz = new AttrInfoBusiness();
	List<String> getInput(String path){
		List<String> res = new ArrayList<>();
		//String path = "D://wwy毕业相关//input//attrList.txt";
		FileUtils in = new FileUtils(path, "in");
		String str = null;
		while((str = in.readLine())!=null){
			res.add(str);
		}
		in.close();
		return res;
	}
	String getBaiduSearch(Document doc){
		String res = "";
		Elements es = doc.getElementsByClass("result");
		int cnt = 0;
		for(Element e:es){
			res += e.text()+"#";
			if(cnt ++ < 5) break;
		}
		Log.info(res);
		return res;
	}
	String getBingSearch(Document doc){
		String res = "";
		Elements es = doc.getElementsByClass("b_algo");
		int cnt = 0;
		for(Element e:es){
			res += e.text()+"#";
			if(cnt ++ < 5) break;
		}
		Log.info(res);
		return res;

	}
	List<String> getBaiduSearchRecommend(Document doc){
		List<String> res = new ArrayList<>();
		Elements es = doc.getElementsByClass("opr-recommends-merge-content");
		for (Element ei : es) {
			Elements es2 = ei.getElementsByClass("c-span4");
			for (Element e : es2) {
				String cname = e.text().trim();
				res.add(cname);
			}
		}
		Log.info(res);
		return res;
	}
	List<String> getBaikeList(Document doc){
		List<String> res = new ArrayList<>();
		Elements es = doc.getElementsByClass("result-title");
		for(Element e:es){
			String str = e.text().trim();
			res.add(str.substring(0, Math.max(0, str.length())));
		}
		Log.info(res);
		return res;
	}
	AttrInfo build(String name) throws IOException{
		AttrInfo attrs = new AttrInfo();
		String baikeSearchUrl = "http://www.baidu.com/s?wd="+HttpUtils.encode(name);
		Log.info(baikeSearchUrl);
		String bingSearchUrl = "http://cn.bing.com/search?q="+HttpUtils.encode(name);
		String baikeUrl = "http://baike.baidu.com/search/none?word="+HttpUtils.encode(name);
		Document baiduDoc = HttpUtils.getDocument(baikeSearchUrl);
		Document bingDoc = HttpUtils.getDocument(bingSearchUrl);
		Document baikeDoc = HttpUtils.getDocument(baikeUrl);
		
		List<String> recommends = getBaiduSearchRecommend(baiduDoc);
		String baiduSearchText = getBaiduSearch(baiduDoc);
		
		attrs.setAttrName(name);
		attrs.setBaiduRecommends(recommends);
		attrs.setBaiduSearchText(baiduSearchText);
		attrs.setBingSearchText(getBingSearch(bingDoc));
		attrs.setBaikeList(getBaikeList(baikeDoc));
		
		
		return attrs;
		
	}
	void save(AttrInfo attr){
		attrBz.save(attr);
	}
	void work() throws IOException{
		
		for(String name : getInput(TaskConfig.getValue("attr_cluster_path")+"attribute_name.txt")){
			name = TextUtils.getClearText(name);
			Log.info(name);
			AttrInfo attr = build(name);
			save(attr);
	
		}
	}
	public static void main(String[] args) throws IOException {
		//new AttrCharacSpiderApp().build("层次聚类");
		//new AttrCharacSpiderApp().build("周杰伦");
		//new AttrCharacSpiderApp().work();
		AttrInfoBusiness attrBz = new AttrInfoBusiness();
		int cnt = 0;
		for(AttrInfo attr:attrBz.getAll()){
			Log.info(attr.getAttrName()+" "+cnt++);
		}
		
	}
}
