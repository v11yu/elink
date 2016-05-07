package org.elink.database.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.types.ObjectId;

public class TemplateInfo {
	String attrName;
	ObjectId attrId;
	Integer count;
	Integer textCount;
	Double confident;
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public ObjectId getAttrId() {
		return attrId;
	}
	public void setAttrId(ObjectId attrId) {
		this.attrId = attrId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getTextCount() {
		return textCount;
	}
	public void setTextCount(Integer textCount) {
		this.textCount = textCount;
	}
	public Double getConfident() {
		return confident;
	}
	public void setConfident(Double confident) {
		this.confident = confident;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}
