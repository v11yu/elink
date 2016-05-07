package org.elink.analysis.app.first;

import java.util.*;

import org.elink.analysis.parser.CommonTools;
/**
 * 用于计算模板置信度的
 * 类似于AttrTemplateApp
 * 只是生成的map中带double的置信度
 * @author v11
 *
 */
public class CalConfidenceApp {
	public double[] calRank(List<Integer> edge[],int T){
		double fl = 0.15;
		double val[][] = new double[2][edge.length];
		for(int i=0;i<edge.length;i++){
			val[0][i] = 1;
			val[1][i] = fl;
		}
		for(int t=0;t<T;t++){
			//pagerank 
			for(int i=0;i<edge.length;i++){
				if(edge[i].size() == 0) continue;
				double v = 1.0*val[t%2][i]/edge[i].size();
				for(Integer to : edge[i]){
					val[(t+1)%2][to] +=v*(1-fl);
				}
			}
			for(int i=0;i<edge.length;i++){
				val[t%2][i] = fl;
			}
		}
		return val[T%2];
	}
	/**
	 * 返回map,string表示模板,double表示置信度
	 * @param st
	 * @return
	 */
	public Map<String, Double> getTemplatesByslow(Set<String> st){
		List<String> strs = new ArrayList<String>(st);
		Map<String, Integer> numSet = new HashMap<String, Integer>();
		Map<String, Integer> res = new HashMap<String, Integer>();
		Map<String, Double> confidence = new HashMap<String, Double>();
		List<String> edgeStr = new ArrayList<String>();
	
		int numZ = st.size();
		for(int i=0;i<strs.size();i++){
			for(int j=i+1;j<strs.size();j++){
				String str1 = strs.get(i);
				String str2 = strs.get(j);
				String lcs = CommonTools.longestCommonSubsequence4Str(str1, str2);
				String lcsT = getTemplate(str1,lcs);
				int idx = addOne2Mp(res,lcsT,numSet,numZ);
				if(idx == numZ) numZ++;
				edgeStr.add(i+"#"+idx);
				edgeStr.add(j+"#"+idx);
				
			}
		}
		/*
		 * build graph
		 */
		List<Integer> edge[] = new ArrayList[numZ];
		for(int i=0;i<numZ;i++) edge[i] = new ArrayList<Integer>();
		for(String str : edgeStr){
			String ls[] = str.split("#");
			int a = Integer.parseInt(ls[0]);
			int b = Integer.parseInt(ls[1]);
			addEdge(a,b,edge);
		}
		/*
		 * cal graph PR
		 */
		double val[] = calRank(edge,15);
		for(Map.Entry<String,Integer> entry: numSet.entrySet()){
			confidence.put(entry.getKey(), val[entry.getValue()]);
		}
		return confidence;
	}
	void addEdge(int a,int b,List<Integer> edge[]){
		edge[a].add(b);
		edge[b].add(a);
	}
	int addOne2Mp(Map<String, Integer> mp,String str,Map<String, Integer> numSet,int numZ){
		int num = 0;
		if(!mp.containsKey(str)){
			mp.put(str, 1);
			num = numZ;
			numSet.put(str, num);
		}else{
			mp.put(str,mp.get(str)+1);
			num = numSet.get(str);
		}
		return num;
	}
	String getTemplate(String text,String lcs){
		char t2[] = text.toCharArray();
		char t1[] = lcs.toCharArray();
		String res = "";
		for(int i=0,j=0;i<t1.length&&j<t2.length;){
			if(t1[i] == t2[j]){
				res += t1[i];
				i++;
				j++;
			}else{
				while(t1[i]!=t2[j]&&j<t2.length){
					j++;
				}
				res+='*';
			}
		}
		return res;
	}
	public static void main(String[] args) {
		
	}
}
