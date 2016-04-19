package org.elink.analysis.cluster;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.elink.database.model.cluster.AttrInfo;

public class SetTest {
	public void testAdd(){
		AttrInfo ai = new AttrInfo();
		ai.setAttrName("aa");
		ai.setBaiduSearchText("hello");
		Set<AttrInfo> st = new HashSet<>();
		st.add(ai);
		st.add(ai);
		System.out.println(st.size());
		AttrInfo bi = new AttrInfo();
		bi.setAttrName("aa");
		bi.setBaiduSearchText("hello");
		st.add(bi);
		System.out.println(st.size());
		//ai.setAttrName("gogo");
		
	//	st.add(ai);
		System.out.println(st.size());
		
		for(AttrInfo a:st){
			System.out.println(a);
		}
	}
	public void testMerge(){
		List<Set<String>> initSets = new ArrayList<>();
		Set<String> st = new HashSet<>();
		st.add("h");
		st.add("h1");
		initSets.add(st);
		Set<String> st1 = new HashSet<>();
		st1.add("h11");
		st1.add("h1111");
		initSets.add(st1);
		
		Set<String> st2 = new HashSet<>();
		st2.add("22");
		st2.add("222");
		initSets.add(st2);
		for(Set<String> t:initSets){
			System.out.println(t);
		}
		initSets.remove(st);
		st2.addAll(st);
		for(Set<String> t:initSets){
			System.out.println(t);
		}
	}
	public static void main(String[] args) {
		new SetTest().testMerge();
	}
}
