package org.elink.spider.baike.business;

import org.elink.database.model.EntityInfo;

public interface EntityInfoBusiness {
	public void save(EntityInfo e);
	/**
	 * check whether entity exist
	 * @param e
	 * @return exist return true;
	 */
	public boolean checkEntityExist(EntityInfo e);
}
