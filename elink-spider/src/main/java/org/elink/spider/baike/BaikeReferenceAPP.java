package org.elink.spider.baike;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.elink.database.hudong.model.HuDongEntity;
import org.elink.database.pname.model.Pname;
import org.elink.spider.baike.business.PnameBusiness;
import org.elink.spider.baike.business.impl.PnameBusinessImpl;
import org.elink.spider.baike.input.HudongInputer;
import org.elink.spider.baike.input.Inputer;
import org.elink.spider.hudong.business.HudongBusiness;
import org.elink.spider.hudong.business.HudongBusiness2DB;
import org.elink.spider.utils.HttpUtils;
import org.elink.spider.utils.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 通过已有的列表名，去爬取reference相关的实体名
 * 存储至pname中
 * @author v11
 *
 */
public class BaikeReferenceAPP {
	Inputer<String> inputer = new HudongInputer();
	HudongBusiness hudongBusiness = new HudongBusiness2DB();
	PnameBusiness pnameBusiness = new PnameBusinessImpl();
	Set<String> names = new HashSet<>();
	void dfs(String name,List<String> ls,Set<String> mp) {
		String baseUrl = "http://www.baidu.com/s?wd=";
		List<String> tmp = new ArrayList<>();
		if(ls.size()>100) return ;
		String url = baseUrl + name;
		Document doc;
		doc = HttpUtils.getDocument(url);
		if(doc == null ) return ;
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
					savePname(cname);
				}
			}
		}
		for(String c:tmp) dfs(c,ls,mp);
	}
	void savePname(String cname){
		Pname pn = new Pname();
		pn.setName(cname);
		pn.setUrl("http://baike.baidu.com/item/"+HttpUtils.encode(cname));
		pnameBusiness.savePname(pn);
	}
	/**
	 * Get input List
	 * @return
	 */
	List<String> getInputListName(){
		return inputer.getInputData();
	}
	/**
	 * get reference name to set "names"
	 * @throws Exception
	 */
	public void work() throws Exception{
		List<String> inputs = getInputListName();
		for(String input : inputs){
			List<String> ls = new ArrayList<>();
			dfs(input,ls,names);
			Log.info(input+" add the key name number of"+ls.size());
		}
		
		
	}
	public static void main(String[] args) throws Exception {
		new BaikeReferenceAPP().work();
	}
}
