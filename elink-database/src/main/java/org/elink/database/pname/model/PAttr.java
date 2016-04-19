package org.elink.database.pname.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 存放临时属性名列表
 * @author v11
 *
 */
public class PAttr {
	String name;
	Integer count;
	String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}
