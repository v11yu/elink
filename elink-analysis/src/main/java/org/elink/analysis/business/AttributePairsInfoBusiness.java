package org.elink.analysis.business;

import java.util.List;

import org.elink.database.model.AttributePairsInfo;

public interface AttributePairsInfoBusiness {
	void save(AttributePairsInfo attrPairs);
	public List<AttributePairsInfo> getAllList();
}
