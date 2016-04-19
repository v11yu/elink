package org.elink.database.model.cluster;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;
/**
 * 记录属性外部信息，用于扩展特征进行聚类
 * @author v11
 *
 */
public class AttrInfo {
	String attrName;
	String baiduSearchText;
	List<String> baiduRecommends;
	List<String> baikeList;
	String weiboSearchText;
	String bingSearchText;
	
	
	public List<String> getBaiduRecommends() {
		return baiduRecommends;
	}
	public void setBaiduRecommends(List<String> baiduRecommends) {
		this.baiduRecommends = baiduRecommends;
	}
	public List<String> getBaikeList() {
		return baikeList;
	}
	public void setBaikeList(List<String> baikeList) {
		this.baikeList = baikeList;
	}
	public String getWeiboSearchText() {
		return weiboSearchText;
	}
	public void setWeiboSearchText(String weiboSearchText) {
		this.weiboSearchText = weiboSearchText;
	}
	public String getBingSearchText() {
		return bingSearchText;
	}
	public void setBingSearchText(String bingSearchText) {
		this.bingSearchText = bingSearchText;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getBaiduSearchText() {
		return baiduSearchText;
	}
	public void setBaiduSearchText(String baiduSearchText) {
		this.baiduSearchText = baiduSearchText;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}
