package org.elink.spider.hudong;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.elink.database.hudong.model.HuDongEntity;
import org.elink.database.hudong.model.HudongTag;
import org.elink.spider.hudong.business.HudongBusiness;
import org.elink.spider.hudong.business.HudongBusiness2File;
import org.elink.spider.hudong.parser.*;
import org.elink.spider.utils.FileUtils;
import org.elink.spider.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HuDongSpiderApp {
	String baseClassUrl = "http://fenlei.baike.com/";
	HuDongParser hp = new HuDongParserImpl();
	HudongBusiness hbs = new HudongBusiness2File();
	void dfs(String tagName,ObjectId father,List<String> preTags) throws IOException{
		List<String> nowTags = getNowTags(preTags,tagName);
		// build tag
		String tagUrl = baseClassUrl+HttpUtils.encode(tagName);
		System.out.println(tagUrl);
		Document doc  = HttpUtils.getDocument(tagUrl);
		HudongTag tag = new HudongTag();
		tag.setFatherId(father);
		tag.setName(tagName);
		tag.setUrl(tagUrl);
		List<String> childs = hp.getSubTagClassName(doc);
		tag.setCname(childs);
		/*
		 * save tag and get the ObjectId
		 */
		tag = saveTag(tag);
		System.out.println(tagName + " "+childs+" "+nowTags);
		//get tag list & build entity
		String listUrl = getListUrl(tagName);
		Document listDoc = HttpUtils.getDocument(listUrl);
		List<HuDongEntity> entitys = hp.getEntity(listDoc);
		for(HuDongEntity e:entitys){
			e.setPreTags(nowTags);
			e.setTagNames(hp.getEntityTag(listDoc));
			buildAndSaveEntity(e);
		}
		
		
		//dfs child
		for(String curl:childs) dfs(curl,tag.getId(),nowTags);
	}
	void buildAndSaveEntity(HuDongEntity e){
		hbs.saveEntity(e);
	}
	/**
	 * get url of fenlei list page
	 * @param tagName
	 * @return
	 */
	String getListUrl(String tagName){
		return "http://fenlei.baike.com/"+HttpUtils.encode(tagName)+"/list/";
	}
	/**
	 * save the tag and return the tag with ObjectID
	 * @param tag
	 * @return
	 */
	HudongTag saveTag(HudongTag tag){
		return hbs.saveTag(tag);
	}
	List<String> getNowTags(List<String> preTags,String tagName){
		List<String> nowTags = new ArrayList<String>(preTags);
		nowTags.add(tagName);
		return nowTags;
		
	}
	public static void main(String[] args) throws IOException {
		new HuDongSpiderApp().dfs("计算机安全",null,new ArrayList<String>());
	}
}
