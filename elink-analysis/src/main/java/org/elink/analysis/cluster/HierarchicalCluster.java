package org.elink.analysis.cluster;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.elink.analysis.model.Attr;
import org.elink.analysis.utils.FileUtils;
import org.elink.analysis.utils.FileWriteTools;
import org.elink.analysis.utils.Log;
import org.elink.analysis.utils.TaskConfig;
import org.elink.database.model.cluster.AttrInfo;
/**
 * 用于层次聚类的超级虚类
 * @author v11
 *
 * @param <T>
 */
public abstract class HierarchicalCluster<T> {
	double split_threshold;//孤立点分裂阈值,同时也是合并最低阈值
	double quick_merge_threshold; //快速融合的阈值，一般会设定一个比较高的阈值
	final String dir = TaskConfig.getValue("attr_cluster_path");//目录
	String fileName;//文件名
	public List<Set<T>> initSets = new ArrayList<>();
	public List<Set<T>> singleSets = new ArrayList<>();
	void buildInitSet(List<T> elements){
		for(T e : elements){
			Set<T> st = new HashSet<>();
			st.add(e);
			initSets.add(st);
		}
	}
	public HierarchicalCluster(){
		
	}
	/**
	 * 配置数据集，每次聚类前必须做得
	 * @param elements 带聚类元素
	 * @param name 
	 * @param merge_threshold 合并阈值
	 * @param split_threshold 孤立点分裂阈值
	 */
	public void setData(List<T> elements,String fileName,
			double split_threshold,
			double quick_merge_threshold){
		buildInitSet(elements);
		this.fileName = fileName;
		this.split_threshold = split_threshold;
		this.quick_merge_threshold = quick_merge_threshold;
	}
	/**
	 * 
	 * @param remove need to remove
	 * @param b merge {remove.element} to b
	 */
	void merge(Set<T> remove,Set<T> b){
		//Log.info(a+" "+b);
		//Set<T> c = new HashSet<>(a);
		initSets.remove(remove);
		b.addAll(remove);
		//Log.info("merge success");
	}
	public abstract double getElementSimilar(T a,T b);
	//abstract String T2Str(T a);
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
		String filePath = dir+this.fileName+"_"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date())+".txt";
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
		for(int i=initSets.size()-1;i>=0;i--){
			Double maxSimiliar = 0.0;
			Set<T> si = initSets.get(i);
			Set<T> sa = null;
			System.out.println("initSets th:"+i);
			Date pre = new Date();
			for(int j=i-1;j>=0;j--){
				Set<T> sj = initSets.get(j);
				Double v = getClosedClusterSimilar(si, sj);
				if(v > maxSimiliar){
					maxSimiliar = v;
					sa = sj;
				}
				maxSimiliar = Math.max(maxSimiliar, v);
			}
			Log.info("一个用时:"+(new Date().getTime() - pre.getTime())+" ms");
			System.out.println(maxSimiliar+" "+si);
			if(maxSimiliar > quick_merge_threshold){
				merge(si,sa);
				Log.info(si+"##############"+sa);
			}
			if(maxSimiliar<split_threshold){
				singleSets.add(si);
				initSets.remove(si);
			}
			System.out.println("singleSets size: "+singleSets.size());
			if(i<1600) break;
		}
		Log.info("split 孤立点个数:"+singleSets.size());
//		while(true){
//			double minDis = -1;
//			Set<T> a = null;
//			Set<T> b = null;
//			for(int i=0;i<initSets.size();i++){
//				for(int j=i+1;j<initSets.size();j++){
//					Set<T> si = initSets.get(i);
//					Set<T> sj = initSets.get(j);
//					double tmpDis = getMeansClusterSimilar(si,sj);
//					if(tmpDis > minDis) {
//						minDis = tmpDis;
//						a = si;
//						b = sj;
//					}
//				}
//				Log.info("cluster size:"+initSets.size());
//			}
//			if(minDis > merge_threshold) merge(a,b);
//			else break;
//			tim++;
//		}
		Log.info("clust size"+size+" spend time "+tim);
		//print(initSets);
		print(singleSets);
		print(initSets);
		
	}
	public static void main(String[] args) {
		
	}
}
