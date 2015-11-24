package org.elink.database.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class EntitySource {
	@Id
	private int entity_id;
	private String page_Name;
	private int is_entity;
	private int good_page;
	private String categoty_name;
	private String content;
	private String hudong;
	private String source;
	public int getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(int entity_id) {
		this.entity_id = entity_id;
	}
	public String getPage_Name() {
		return page_Name;
	}
	public void setPage_Name(String page_Name) {
		this.page_Name = page_Name;
	}
	public int getIs_entity() {
		return is_entity;
	}
	public void setIs_entity(int is_entity) {
		this.is_entity = is_entity;
	}
	public int getGood_page() {
		return good_page;
	}
	public void setGood_page(int good_page) {
		this.good_page = good_page;
	}
	public String getCategoty_name() {
		return categoty_name;
	}
	public void setCategoty_name(String categoty_name) {
		this.categoty_name = categoty_name;
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
