package org.elink.spider.hudong.business;

import org.elink.database.hudong.model.HuDongEntity;
import org.elink.database.hudong.model.HudongTag;
import org.elink.spider.utils.FileUtils;
import org.elink.spider.utils.HttpUtils;
import org.jsoup.nodes.Document;

public class HudongBusiness2File implements HudongBusiness{
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
		String filename = "F:\\v11-test-space\\hudong\\"+name+".html";
		FileUtils out = new FileUtils(filename, "out");
		Document doc = HttpUtils.getDocument(en.getUrl());
		out.writeLine(doc.html());
		out.close();
	}
}
