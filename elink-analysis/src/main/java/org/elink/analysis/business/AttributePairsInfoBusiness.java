package org.elink.analysis.business;

import java.util.List;

import org.elink.database.model.AttributePairsInfo;
import org.elink.database.model.EntityInfo;

public interface AttributePairsInfoBusiness {
	void save(AttributePairsInfo attrPairs);
	public List<AttributePairsInfo> getAllList();
	public List<AttributePairsInfo> getbyEid(EntityInfo en);
}
