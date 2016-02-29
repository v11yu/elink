package org.elink.spider.hudong.business;

import java.util.ArrayList;
import java.util.List;

import org.elink.database.hudong.model.HuDongEntity;
import org.elink.database.hudong.model.HudongTag;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.mongodb.utils.SpringBeanUtils;
import org.elink.spider.utils.HttpUtils;
import org.elink.spider.utils.Log;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HudongBusiness2DB implements HudongBusiness{
	BasicRepository hudongEntityDao = (BasicRepository) SpringBeanUtils.getContext().getBean("HudongEntityDao");
	@Override
	public HudongTag saveTag(HudongTag tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveEntity(HuDongEntity en) {
		// TODO Auto-generated method stub
		String name = en.getName();
		name = name.replace('\\', '-');
		name = name.replace('/', '-');
		try {
			
			Document doc = HttpUtils.getDocument(en.getUrl());
			/*
			 * write tag to file
			 */
			Elements es = doc.getElementsByClass("h27");
			Elements htags = es.get(0).getElementsByTag("a");
			List<String> tags = new ArrayList<String>();
			for (Element e : htags) {
				tags.add(e.text().trim());
			}
			en.setTagNames(tags);
			System.out.println(tags);
			

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e);
		}finally{

		}
	}


}
