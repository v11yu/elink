package org.elink.analysis.cluster;

import java.io.File;
import java.util.*;

import org.elink.analysis.parser.CommonTools;
import org.elink.analysis.utils.FileUtils;
import org.elink.analysis.utils.Log;
public class QuicklyCluster {
	int T = 3;
	String basePath = "D://wwy毕业相关//output//";
	public List<String> getInput(String fileName){
		FileUtils in = new FileUtils(basePath+fileName, "in");
		List<String> res = new ArrayList<>();
		String str = null;
		while((str=in.readLine())!=null){
			str = str.replaceAll("\\s+","");
			str = str.replaceAll(" ", "");
			res.add(str);
		}
		return res;
	}
	public void outCluster(List<Set<String>> clu,String fileName){
		String outPath = "D://wwy毕业相关//output//cluster//"+fileName.substring(0, fileName.length()-4)+"//";
		File dir = new File(outPath);
		dir.mkdir();
		int i = 0;
		for(Set<String> st :clu){
			String file = outPath+"cluster_"+i+"_"+fileName;
			FileUtils out = new FileUtils(file, "out");
			for(String str:st){
				out.writeLine(str);
			}
			out.close();
			i++;
		}
	}
	public int simi(String str1,String str2){
		return CommonTools.LCS(str1, str2);
	}
	public void cluster(String name){
		String fileName = name;
		List<Set<String>> sts = new ArrayList<>();
		List<String> texts = getInput(fileName);
		boolean mk[] = new boolean[texts.size()];
		System.out.println(texts.size());
		
		
		for(int i=0;i<texts.size();i++){
			if(mk[i] == true) continue ;
			mk[i] = true;
			Set<String> st = new HashSet<>();
			st.add(texts.get(i));
			for(int j=i+1;j<texts.size();j++){
				if(mk[j] == true) continue;
				if(simi(texts.get(i), texts.get(j)) >= T){
					st.add(texts.get(j));
					mk[j] = true;
				}
			}
			sts.add(st);
		}
		System.out.println(sts.size());
		outCluster(sts,fileName);
	}
	public static void main(String[] args) {
//		String outPath = "D://wwy毕业相关//output//cluster//aa//";
//		File dir = new File(outPath);
//		System.out.println(dir.mkdir());
		String name = "国    籍";
		new QuicklyCluster().cluster(name+"_down.txt");
		new QuicklyCluster().cluster(name+"_up.txt");
	}
}
