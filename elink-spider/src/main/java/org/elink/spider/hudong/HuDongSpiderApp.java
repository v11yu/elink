package org.elink.spider.hudong;

import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;
import org.elink.database.hudong.model.Entity;
import org.elink.database.hudong.model.Tag;
import org.elink.spider.hudong.parser.*;
import org.elink.spider.utils.FileUtils;
import org.elink.spider.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HuDongSpiderApp {
	String baseClassUrl = "http://fenlei.baike.com/";
	HuDongParser hp = new HuDongParserImpl();
	void dfs(String tagName,ObjectId father) throws IOException{
		// build tag
		String tagUrl = baseClassUrl+HttpUtils.encode(tagName);
		Document doc  = HttpUtils.getDocument(tagUrl);
		Tag tag = new Tag();
		tag.setFatherId(father);
		tag.setName(tagName);
		tag.setUrl(tagUrl);
		List<String> childs = hp.getSubTagClassName(doc);
		tag.setCurl(childs);
		saveTag(tag);
		System.out.println(tagName + " "+childs);
		//get tag list & build entity
		String listUrl = getListUrl(tagName);
		Document listDoc = HttpUtils.getDocument(listUrl);
		List<Entity> entitys = hp.getEntity(listDoc);
		for(Entity e:entitys){
			buildAndSaveEntity(e);
		}
		
		//dfs child
		for(String curl:childs) dfs(curl,tag.getId());
	}
	void buildAndSaveEntity(Entity e) throws IOException{
		String name = e.getName();
		name = name.replace('\\', '-');
		name = name.replace('/', '-');
		String filename = "F:\\v11-test-space\\hudong\\"+name+".html";
		FileUtils out = new FileUtils(filename, "out");
		Document doc = HttpUtils.getDocument(e.getUrl());
		out.writeLine(doc.html());
		out.close();
	}
	String getListUrl(String tagName){
		return "http://fenlei.baike.com/"+HttpUtils.encode(tagName)+"/list/";
	}
	void saveTag(Tag tag){
		
	}
	public static void main(String[] args) throws IOException {
		new HuDongSpiderApp().dfs("计算机安全",null);
	}
}
