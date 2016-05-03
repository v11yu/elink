package org.elink.analysis.cluster;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.elink.analysis.model.Attr;
import org.elink.analysis.utils.FileUtils;
import org.elink.analysis.utils.FileWriteTools;
import org.elink.analysis.utils.Log;
import org.elink.database.model.cluster.AttrInfo;

public abstract class HierarchicalCluster<T> {
	double threshold ;
	String name;
	public List<Set<T>> initSets = new ArrayList<>();
	void buildInitSet(List<T> elements){
		for(T e : elements){
			Set<T> st = new HashSet<>();
			st.add(e);
			initSets.add(st);
		}
	}
	public HierarchicalCluster(){
		
	}
	public HierarchicalCluster(List<T> elements){
		this(elements,"",0.7);
	}
	/**
	 * 文件名
	 * @param elements
	 * @param name
	 */
	public HierarchicalCluster(List<T> elements,String name,double threshold){
		buildInitSet(elements);
		this.name = name;
		this.threshold = threshold;
	}
	public void setData(List<T> elements,String name,double threshold){
		buildInitSet(elements);
		this.name = name;
		this.threshold = threshold;
	}
	void merge(Set<T> a,Set<T> b){
		//Log.info(a+" "+b);
		//Set<T> c = new HashSet<>(a);
		initSets.remove(a);
		b.addAll(a);
		//Log.info("merge success");
	}
	public abstract double getElementSimilar(T a,T b);
	double getMeansClusterSimilar(Set<T> a,Set<T> b){
		double res = 0;
		for(T i:a){
			for(T j : b){
				res += getElementSimilar(i, j);
			}
		}
		return res/(a.size()*b.size());
	}
	double getClosedClusterSimilar(Set<T> a,Set<T> b){
		double res = 0;
		for(T i:a){
			for(T j : b){
				res = Math.max(getElementSimilar(i, j), res);
			}
		}
		return res;
	}
	double getFarClusterSimilar(Set<T> a,Set<T> b){
		double res = 100;
		for(T i:a){
			for(T j : b){
				res = Math.min(getElementSimilar(i, j), res);
			}
		}
		return res;
	}
	abstract public Set<String> set2str(Set<T> st);
	public void print(List<Set<T>> initSets){
		String path =  "D:\\wwy毕业相关\\output\\attr_cluster\\";
		String filePath = path+this.name+"_"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date())+".txt";
		FileUtils out = new FileUtils(filePath,"out");
		for(Set<T> t : initSets){
			Set<String> tt = set2str(t);
			out.writeLine(tt+"");
		}
		out.close();
	}
	public void cluste(){
		int tim = 0;
		int size = initSets.size();
		while(true){
			double minDis = -1;
			Set<T> a = null;
			Set<T> b = null;
			for(int i=0;i<initSets.size();i++){
				for(int j=i+1;j<initSets.size();j++){
					Set<T> si = initSets.get(i);
					Set<T> sj = initSets.get(j);
					double tmpDis = getMeansClusterSimilar(si,sj);
					if(tmpDis > minDis) {
						minDis = tmpDis;
						a = si;
						b = sj;
					}
				}
			}
			if(minDis > threshold)merge(a,b);
			else break;
			tim++;
		}
		Log.info("clust size"+size+" spend time "+tim);
		print(initSets);
		
	}
	public static void main(String[] args) {
		
	}
}
