package org.elink.analysis.method;

import java.util.ArrayList;
import java.util.List;

import org.elink.analysis.model.Attr;
import org.elink.analysis.utils.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 从百度百科html页面的infobox中抽取属性和属性名
 * @author v11
 *
 */
public class InfoBoxAttrMethod extends AbstractAttrMethod{

	@Override
	public List<Attr> getAttr(String text) {
		// TODO Auto-generated method stub
		List<Attr> ls = new ArrayList<>();
		Document doc = Jsoup.parse(text);
		Elements es = doc.getElementsByClass("basic-info");
		if(es.size() == 0){
			Log.error("没有infobox");
		}
		System.out.println(es.text());
		Elements es2 = es.get(0).select("dd.basicInfo-item.value");
		Elements es3 = es.get(0).select("dt.basicInfo-item.name");
		System.out.println("es3 "+es3.size());
		System.out.println("es2 "+es2.size());
		if(es2.size() == es3.size()) System.out.println("equal");
		for(int i=0;i<es2.size();i++){
			Attr attr = new Attr();
			attr.setName(es3.get(i).text());
			attr.setValue(es2.get(i).text());
			ls.add(attr);
		}
		System.out.println(ls);
		return ls;
	}

}
