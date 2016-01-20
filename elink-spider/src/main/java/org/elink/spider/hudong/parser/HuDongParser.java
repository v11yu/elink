package org.elink.spider.hudong.parser;

import java.util.List;

import org.elink.database.hudong.model.HuDongEntity;
import org.elink.database.hudong.model.HudongTag;
import org.jsoup.nodes.Document;

public interface HuDongParser {
	/**
	 * build entity with name&url attribute
	 * @param doc
	 * @return
	 */
	List<HuDongEntity> getEntity(Document doc);

	/**
	 * get all sub tag name in List
	 * @param doc
	 * @return list of all sub tag name
	 */
	List<String> getSubTagClassName(Document doc);
	/**
	 * get entity page contain tags
	 * @param doc
	 * @return
	 */
	List<String> getEntityTag(Document doc);
}
