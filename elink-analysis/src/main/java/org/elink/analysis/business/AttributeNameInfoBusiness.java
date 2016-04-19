package org.elink.analysis.business;

import org.elink.database.model.AttributeNameInfo;
import java.util.*;
public interface AttributeNameInfoBusiness {
	public void save(AttributeNameInfo attr);
	public List<AttributeNameInfo> getAllList();
	public List<AttributeNameInfo> getList(String attrName);
}
