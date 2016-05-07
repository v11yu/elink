package org.elink.analysis.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.elink.analysis.app.first.AttrTemplateApp;
import org.elink.analysis.app.first.CalConfidenceApp;
import org.elink.analysis.utils.FileUtils;
import org.elink.analysis.utils.evaluation.MapUtil;

public class CalConfidenceAppTest {
	void testPR(){
		List<Integer> edge[] = new ArrayList[6];
		for(int i=0;i<6;i++){
			edge[i] = new ArrayList<Integer>();
		}
//		edge[0].add(1);
//		edge[0].add(2);
//		edge[1].add(2);
//		edge[2].add(0);
//		edge[3].add(2);
		addEdge(0,3,edge);
		addEdge(0,4,edge);
		addEdge(1,4,edge);
		addEdge(2,4,edge);
		addEdge(2,5,edge);
		
		double val[] = new CalConfidenceApp().calRank(edge, 10);
		for(int i=0;i<val.length;i++) System.out.println(val[i]);
	}
	void addEdge(int a,int b,List<Integer> edge[]){
		edge[a].add(b);
		edge[b].add(a);
	}
	void testTemplate(){
		FileUtils in = new FileUtils("/Users/v11/Documents/毕业文档/output/attr_content/cluster-noattr/毕业院校/T4_cluster_1_毕业院校.txt"
				, "in");
		Set<String> st = new HashSet<String>();
		String str = null;
		while((str=in.readLine())!=null){
			st.add(str);
		}
		System.out.println("text size :"+st.size());
		in.close();
		Map<String, Double> mp = new CalConfidenceApp().getTemplatesByslow(st);
		mp = MapUtil.sortByValue(mp);
		System.out.println(mp);
	}
	public static void main(String[] args) {
		CalConfidenceAppTest t = new CalConfidenceAppTest();
		//t.testPR();
		t.testTemplate();
	}
}
