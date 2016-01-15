package org.elink.database.hudong.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.types.ObjectId;

public class Tag {
	ObjectId id;
	ObjectId fatherId;
	List<ObjectId> child;
	String name;
	String url;
	List<String> curl;
	
	public List<String> getCurl() {
		return curl;
	}
	public void setCurl(List<String> curl) {
		this.curl = curl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ObjectId getFatherId() {
		return fatherId;
	}
	public void setFatherId(ObjectId fatherId) {
		this.fatherId = fatherId;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public List<ObjectId> getChild() {
		return child;
	}
	public void setChild(List<ObjectId> child) {
		this.child = child;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}
