package org.elink.analysis.method;

import java.util.List;

import org.elink.analysis.model.Attr;

public abstract class AbstractAttrMethod {
	public abstract List<Attr> getAttr(String text);
}
