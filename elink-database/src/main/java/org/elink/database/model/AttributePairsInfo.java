package org.elink.database.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.types.ObjectId;

public class AttributePairsInfo {
	String name;
	String value;
	String entity_name;
	ObjectId entity_id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getEntity_name() {
		return entity_name;
	}
	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}
	public ObjectId getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(ObjectId entity_id) {
		this.entity_id = entity_id;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}
