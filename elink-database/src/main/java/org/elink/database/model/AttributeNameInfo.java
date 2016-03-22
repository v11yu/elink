package org.elink.database.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * 用于存放属性名
 * 去重过滤后对应唯一的id
 */
public class AttributeNameInfo {
	
	String attrName;
	Integer attrCount;
	@Id
	ObjectId id;
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public Integer getAttrCount() {
		return attrCount;
	}

	public void setAttrCount(Integer attrCount) {
		this.attrCount = attrCount;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}
