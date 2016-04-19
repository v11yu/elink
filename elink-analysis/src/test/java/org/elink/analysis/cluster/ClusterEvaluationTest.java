package org.elink.analysis.cluster;

import java.util.*;

import org.elink.analysis.cluster.tool.ClusterEvaluation;

public class ClusterEvaluationTest extends ClusterEvaluation<String>{

	public ClusterEvaluationTest(List<Set<String>> fact, List<Set<String>> predict) throws Exception {
		super(fact, predict);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equal(String a, String b) {
		// TODO Auto-generated method stub
		return a.equals(b);
	}
	public static void addSetElement(Set<String> st,String[] ls){
		for(String s:ls) st.add(s);
	}
	public static void main(String[] args) {
		String[] resa =  {"a","b","c"};
		String[] resb = {"a","bb","c"};
		Set<String> sa = new HashSet<>();
		Set<String> saa = new HashSet<>();
		Set<String> sb = new HashSet<>();
		Set<String> sbb = new HashSet<>();

		addSetElement(sa,resa);
		addSetElement(saa,resb);
		addSetElement(sb,resa);
		addSetElement(sbb,resb);
		
		List<Set<String>> a = new ArrayList<>();
		List<Set<String>> b = new ArrayList<>();
		a.add(sa);
		a.add(saa);
		b.add(sb);
		b.add(sbb);
		double f;
		try {
			f = new ClusterEvaluationTest(a, b).getF1Val();
			System.out.println(f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
