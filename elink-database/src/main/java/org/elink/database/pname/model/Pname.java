package org.elink.database.pname.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * 临时存放name的列表
 * @author v11
 *
 */
public class Pname {
	String name;
	String url;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}
