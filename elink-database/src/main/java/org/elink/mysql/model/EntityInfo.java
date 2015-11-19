package org.elink.mysql.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class EntityInfo {
	private int entityInfoId;
	private String name;
	private String disambiguation;
	private String text;
	private String image;
	private int hasInfoBox;
	private int attriNum;
	public int getEntityInfoId() {
		return entityInfoId;
	}
	public void setEntityInfoId(int entityInfoId) {
		this.entityInfoId = entityInfoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisambiguation() {
		return disambiguation;
	}
	public void setDisambiguation(String disambiguation) {
		this.disambiguation = disambiguation;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
