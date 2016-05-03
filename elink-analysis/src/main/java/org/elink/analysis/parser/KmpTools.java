package org.elink.analysis.parser;

import java.util.ArrayList;
import java.util.List;

public class KmpTools {
	static int MAX = 100000;
	int next[] = new int[MAX];
	String context,value;
	public KmpTools(String context,String value){
		this.context = context;
		this.value = value;
	}
	void getNext(){
		next[0]=-1;
		int i=0,j=-1;
		int length = value.length();
		char t[] = value.toCharArray();
		while(i<length){
			if(j==-1||t[i]==t[j]){
				i++;j++;
				next[i]=j;
			}else j = next[j];
		}
	}
	public List<Integer> kmpGetIndex(){
		getNext();
		List<Integer> res = new ArrayList<>();
		int length = context.length()-1;
		int matLen = value.length()-1;
		if(length<matLen) return res;
		int i=0,j=0,count = 0;
		char s[] = context.toCharArray();
		char t[] = value.toCharArray();
		while(i<=length){
			if(j==-1||s[i]==t[j]){
				i++;
				j++;
			}
			else j = next[j];
			if(j==matLen+1){
				res.add(i - matLen-1);
				j = next[j]; 
			}
		}
		return res;
	}
	public static void main(String[] args) {
		String context = "中国人在中国";
		String value = "中国人";
		KmpTools kt = new KmpTools(context, value);
		System.out.println(kt.kmpGetIndex());
	}
}
