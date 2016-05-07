package org.elink.analysis.app.first;

import java.util.*;

import org.elink.analysis.business.AttributeNameInfoBusiness;
import org.elink.analysis.business.AttributePairsInfoBusiness;
import org.elink.analysis.business.impl.AttributeNameInfoBusinessImpl;
import org.elink.analysis.business.impl.AttributePairsInfoBusinessImpl;
import org.elink.analysis.method.AbstractAttrMethod;
import org.elink.analysis.method.InfoBoxAttrMethod;
import org.elink.analysis.model.Attr;
import org.elink.analysis.parser.TextParser;
import org.elink.analysis.utils.FileWriteTools;
import org.elink.analysis.utils.Log;
import org.elink.analysis.utils.TaskConfig;
import org.elink.database.model.AttributeNameInfo;
import org.elink.database.model.AttributePairsInfo;
import org.elink.database.model.EntityInfo;
import org.elink.database.mongodb.MongoRootConfiguration;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.spider.baike.business.EntityInfoBusiness;
import org.elink.spider.baike.business.impl.EntityInfoBusinessImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mongodb.DBCursor;

/**
 * 用于文本中发现属性值上下文
 * 生成上下午语料txt
 * @author v11
 *
 */
public class AttrValueFinderApp {
	AttributeNameInfoBusiness anb = new AttributeNameInfoBusinessImpl();
	EntityInfoBusiness entityInfoBusiness = new EntityInfoBusinessImpl();
	ApplicationContext context = new AnnotationConfigApplicationContext(MongoRootConfiguration.class);
	BasicRepository<EntityInfo> edao = (BasicRepository<EntityInfo>) context.getBean("entityInfoDao");
	AttributePairsInfoBusiness attrPairsBusiness = new AttributePairsInfoBusinessImpl();
	TextParser testParser = new TextParser();
	//String basePath = "D:\\wwy毕业相关\\output\\";

	public AttrValueFinderApp(){
	}
	public List<AttributePairsInfo> getAttrPairsInfos(EntityInfo en){
		return attrPairsBusiness.getbyEid(en);
	}

	/**
	 * 生成上下午语料程序
	 * @param trainNum 抽取的百科实体数量
	 * @param windowSize 上下文窗口大小,上文下文各windowSize的长度
	 * @param topNum 排名前top数量的属性名
	 * @param basePath 存储的路径
	 */
	public void work(int trainNum,int windowSize,int topNum,String basePath){
		List<String> attrs = getTopList(topNum);
		
		Map<String, List<String>> mp = new HashMap<String, List<String>>();
		DBCursor cursor = edao.findByAll();
		int noInfobox = 0;
		int cnt = 0;
		
		while(cursor.hasNext()){
			EntityInfo en = edao.obj2Entity(cursor.next());
			if(en.getHasInfo() == 0) {
				Log.info(en.getEntity_name()+" 没有infobox");
				noInfobox++;
				continue;
			}
			String text = getText(en.getSource());//实体正文文本
			List<AttributePairsInfo> aps = getAttrPairsInfos(en);
			
			for(String attr:attrs){
				String contextPath = basePath  +attr+"_context.txt";
				String contextNoAttrPath = basePath  +attr+"_context_noAttr.txt";
				String valuePath = basePath  +attr +".txt";
				String value = null;
				for(AttributePairsInfo ap : aps){
					if(ap.getAttrName().equals(attr)){
						value = ap.getAttrValue();
						break;
					}
				}
				if(value == null) continue;
				List<String> values = new ArrayList<String>();
				values.add(value);
				try{
					//List<String> res = testParser.getContext(text, value,windowSize);
					List<String> resNoAttr = testParser.getContextWithoutAttr(text, value, windowSize);
					FileWriteTools.write(resNoAttr, contextNoAttrPath);
					//FileWriteTools.write(res, contextPath);
					FileWriteTools.write(values, valuePath);
				}catch(Exception e){
					e.printStackTrace();
					Log.error(en.getEntity_name()+" "+attr+" "+value+" "+text);
				}
				
			}
			Log.info("抽取到第"+cnt+"实体"+en.getEntity_name());
			if(cnt++> trainNum) break;	
			
		}
		
		Log.info("抽取实体总数:"+trainNum+" noInfobox没有信息框实体:"+noInfobox);
		
	}
	/**
	 * 从百科页面中获取描述性文本正文内容
	 * @param doc
	 * @return
	 */
	String getText(String doc){
		Elements es = Jsoup.parse(doc).getElementsByClass("para");
		String res = "";
		for(Element e:es){
			res+=e.text();
			//System.out.println(e.text());
		}
		//System.out.println(res.length());
		//System.out.println(Jsoup.parse(doc).text().length());
		return res;
		
	}
	/**
	 * 获取top属性名列表
	 * @param topNum top排名的数值
	 * @return
	 */
	public List<String> getTopList(int topNum){
		List<String> res = new ArrayList<String>();
		List<AttributeNameInfo> attrs = anb.getAllList();
		Collections.sort(attrs, new Comparator<AttributeNameInfo>() {
			@Override
			public int compare(AttributeNameInfo o1, AttributeNameInfo o2) {
				// TODO Auto-generated method stub
				return o2.getAttrCount()-o1.getAttrCount();
			}
		});
		int cnt = 0;
		for(AttributeNameInfo attr:attrs){
			res.add(attr.getAttrName());
			Log.info(attr);
			if(++cnt  >= topNum) break;
		}
		return res;
	}
	/**
	 * 获取top属性名列表
	 * @param topNum top排名的数值
	 * @return
	 */
	public List<AttributeNameInfo> getTopAttrList(int topNum){
		List<AttributeNameInfo> res = new ArrayList<AttributeNameInfo>();
		List<AttributeNameInfo> attrs = anb.getAllList();
		Collections.sort(attrs, new Comparator<AttributeNameInfo>() {
			@Override
			public int compare(AttributeNameInfo o1, AttributeNameInfo o2) {
				// TODO Auto-generated method stub
				return o2.getAttrCount()-o1.getAttrCount();
			}
		});
		int cnt = 0;
		for(AttributeNameInfo attr:attrs){
			res.add(attr);
			Log.info(attr);
			if(++cnt  >= topNum) break;
		}
		return res;
	}
	public static void main(String[] args) {
		Date date = new Date();
		new AttrValueFinderApp().work(2000,15,20,TaskConfig.getValue("basePath"));
		Log.info("run the app using time:"+(new Date().getTime()-date.getTime())/1000);
	}
}
