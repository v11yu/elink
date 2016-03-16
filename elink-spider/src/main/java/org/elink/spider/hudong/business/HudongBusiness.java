package org.elink.spider.hudong.business;

import java.util.List;

import org.elink.database.hudong.model.HuDongEntity;
import org.elink.database.hudong.model.HudongTag;

public interface HudongBusiness {
	/**
	 * save the tag
	 * @param tag
	 * @return
	 */
	HudongTag saveTag(HudongTag tag);
	/**
	 * save hudong entity
	 * @param en
	 */
	void saveEntity(HuDongEntity en);
	public List<HuDongEntity> getAllList();
}
