package org.elink.analysis.app;

import java.util.*;

import org.elink.analysis.app.first.AttrValueFinderApp;

public class AttrValueFinderAppTest {
	void testTop(){
		AttrValueFinderApp af = new AttrValueFinderApp();
		List<String> res = af.getTopList(20);
		System.out.println(res);
	}
	void testDoc(){
		
	}
	public static void main(String[] args) {
		System.out.println(new Date().getTime());
	}
}
