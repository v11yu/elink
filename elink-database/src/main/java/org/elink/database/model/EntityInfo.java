package org.elink.database.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.types.ObjectId;
import org.elink.database.pname.model.Pname;
import org.springframework.data.annotation.Id;

public class EntityInfo {
	@Id
	ObjectId id;
	String url;
	String entity_name;
	String source;
	Integer hasInfo;
	Integer isMulti;
	List<Pname> multiUrl;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEntity_name() {
		return entity_name;
	}
	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public Integer getHasInfo() {
		return hasInfo;
	}
	public void setHasInfo(Integer hasInfo) {
		this.hasInfo = hasInfo;
	}
	public Integer getIsMulti() {
		return isMulti;
	}
	public void setIsMulti(Integer isMulti) {
		this.isMulti = isMulti;
	}
	public List<Pname> getMultiUrl() {
		return multiUrl;
	}
	public void setMultiUrl(List<Pname> multiUrl) {
		this.multiUrl = multiUrl;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}
