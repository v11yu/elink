package org.elink.database.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.ibatis.annotations.MapKey;

public class EntityInfo {

	private int entity_id;
	private String entity_name;
	private String disambiguation;
	private String abstact;
	private String image;
	private int hasInfoBox;
	private int attriNum;
	
	public int getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(int entity_id) {
		this.entity_id = entity_id;
	}

	public String getEntity_name() {
		return entity_name;
	}

	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}

	public String getDisambiguation() {
		return disambiguation;
	}

	public void setDisambiguation(String disambiguation) {
		this.disambiguation = disambiguation;
	}

	public String getAbstact() {
		return abstact;
	}

	public void setAbstact(String abstact) {
		this.abstact = abstact;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getHasInfoBox() {
		return hasInfoBox;
	}

	public void setHasInfoBox(int hasInfoBox) {
		this.hasInfoBox = hasInfoBox;
	}

	public int getAttriNum() {
		return attriNum;
	}

	public void setAttriNum(int attriNum) {
		this.attriNum = attriNum;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
