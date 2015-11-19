package org.elink.mysql.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class EntitySource {
	private int entityId;
	private String pageName;
	private int isEntity;
	private int goodPage;
	private String categoty;
	private String content;
	private String hudong;
	private String source;
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public int getIsEntity() {
		return isEntity;
	}
	public void setIsEntity(int isEntity) {
		this.isEntity = isEntity;
	}
	public int getGoodPage() {
		return goodPage;
	}
	public void setGoodPage(int goodPage) {
		this.goodPage = goodPage;
	}
	public String getCategoty() {
		return categoty;
	}
	public void setCategoty(String categoty) {
		this.categoty = categoty;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHudong() {
		return hudong;
	}
	public void setHudong(String hudong) {
		this.hudong = hudong;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
