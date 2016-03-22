package org.elink.spider.baike.business;

import org.elink.database.model.MultiEntityInfo;

public interface MultiEntityInfoBusiness {
	public void save(MultiEntityInfo e);
	public boolean checkExist(MultiEntityInfo e);
}
