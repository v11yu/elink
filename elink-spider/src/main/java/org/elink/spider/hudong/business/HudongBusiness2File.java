package org.elink.spider.hudong.business;

import java.util.ArrayList;
import java.util.List;

import org.elink.database.hudong.model.HuDongEntity;
import org.elink.database.hudong.model.HudongTag;
import org.elink.spider.utils.FileUtils;
import org.elink.spider.utils.HttpUtils;
import org.elink.spider.utils.Log;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HudongBusiness2File implements HudongBusiness {
	/**
	 * do nothing with tag.Because tag is useless in file.
	 */
	@Override
	public HudongTag saveTag(HudongTag tag) {
		// TODO Auto-generated method stub
		return tag;
	}

	@Override
	public void saveEntity(HuDongEntity en) {
		String name = en.getName();
		name = name.replace('\\', '-');
		name = name.replace('/', '-');
		String filename = "F:\\v11-test-space\\hudong\\" + name + ".html";
		String tagFilePath = "F:\\v11-test-space\\test\\tag.txt";
		FileUtils out = new FileUtils(filename, "out");
		FileUtils tagFile = new FileUtils(tagFilePath, "append");
		try {
			
			Document doc = HttpUtils.getDocument(en.getUrl());
			out.writeLine(doc.html());
			/*
			 * write tag to file
			 */
			Elements es = doc.getElementsByClass("h27");
			Elements htags = es.get(0).getElementsByTag("a");
			List<String> tags = new ArrayList<String>();
			for (Element e : htags) {
				tags.add(e.text().trim());
			}
			System.out.println(tags);
			tagFile.writeLine(name + "	" + tags);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e);
		}finally{
			out.close();
			tagFile.close();
		}

	}
}
