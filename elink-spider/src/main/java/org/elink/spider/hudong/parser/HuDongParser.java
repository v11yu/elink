package org.elink.spider.hudong.parser;

import java.util.List;

import org.elink.database.hudong.model.Entity;
import org.elink.database.hudong.model.Tag;
import org.jsoup.nodes.Document;

public interface HuDongParser {
	List<Entity> getEntity(Document doc);
	List<String> getEntityName(Document doc);
	List<String> getEntityUrl(Document doc);
	List<String> getSubTagClassUrl(Document doc);
	List<String> getSubTagClassName(Document doc);
	List<Tag> getSubTagClass(Document doc);
	List<String> getSuperClass(Document doc);
}
