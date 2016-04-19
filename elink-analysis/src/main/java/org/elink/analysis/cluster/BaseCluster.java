package org.elink.analysis.cluster;

import java.util.HashMap;
import java.util.*;

import org.elink.analysis.cluster.inputer.AbstractInputer;
import org.elink.analysis.cluster.simi.AttrInfoMethod;
import org.elink.analysis.cluster.simi.SimiMethod;
import org.elink.database.model.cluster.AttrInfo;

/**
 * 用于基础聚类
 * @author v11
 *
 */
public class BaseCluster<T> {
	int N = 3000;
	Map<String, T> mp = new HashMap<>();
	SimiMethod<AttrInfo> simi = new AttrInfoMethod();
	List<T> data;
	double simiVal[][] = new double[N][N];
	List<Set<T>> cluster;
	public BaseCluster(SimiMethod<T> simiMethod,AbstractInputer<T> inputer){
		
	}
	public void setData(List<T> data){
		this.data = data;
	}
	public void calSimiVal(){
		for(int i=0;i<data.size();i++){
			for(int j=i+1;j<data.size();j++){
				
			}
		}
	}
	public void cluste(){
		
		Map<String, Map<String,String>> mp = new HashMap<>();
		
		
	}
	public static void main(String[] args) {
		
		//base.setData(data);
	}
}
