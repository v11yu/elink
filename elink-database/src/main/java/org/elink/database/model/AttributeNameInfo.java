package org.elink.database.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 用于存放属性名
 * 去重过滤后对应唯一的id
 */
public class AttributeNameInfo {
	String name;
	Integer count;
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}
