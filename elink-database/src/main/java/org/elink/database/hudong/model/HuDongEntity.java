package org.elink.database.hudong.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.types.ObjectId;

public class HuDongEntity {
	ObjectId id;
	String name;
	List<String> preTags;
	List<String> tagNames;
	String url;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getPreTags() {
		return preTags;
	}
	public void setPreTags(List<String> preTags) {
		this.preTags = preTags;
	}
	public List<String> getTagNames() {
		return tagNames;
	}
	public void setTagNames(List<String> tagNames) {
		this.tagNames = tagNames;
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
