package org.elink.analysis.app.first;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.elink.analysis.parser.CommonTools;
import org.elink.analysis.utils.FileUtils;
/**
 * 用于生成语料模板txt
 * @author v11
 *
 */
public class AttrTemplateApp {
	/**
	 * 返回map,string表示模板,integer表示出现次数
	 * @param st
	 * @return
	 */
	public Map<String, Integer> getTemplates(Set<String> st){
		List<String> strs = new ArrayList<String>(st);
		Map<String, Integer> mp = new HashMap<String, Integer>();
		Map<String, Integer> res = new HashMap<String, Integer>();
		String str1 = strs.get(0);
		for(int i=1;i<strs.size();i++){
			String str2 = strs.get(i);
			String lcs = CommonTools.longestCommonSubsequence4Str(str1, str2);
			addOne2Mp(mp,lcs);
		}
		for(Map.Entry<String, Integer> entry:mp.entrySet()){
			String lcsT = getTemplate(str1,entry.getKey());
			addOne2Mp(res,lcsT);
		}
		return res;
		
	}
	/**
	 * 返回map,string表示模板,integer表示出现次数
	 * @param st
	 * @return
	 */
	public Map<String, Integer> getTemplatesByslow(Set<String> st){
		List<String> strs = new ArrayList<String>(st);
		Map<String, Integer> mp = new HashMap<String, Integer>();
		Map<String, Integer> res = new HashMap<String, Integer>();
		for(int i=0;i<strs.size();i++){
			for(int j=i+1;j<strs.size();j++){
				String str1 = strs.get(i);
				String str2 = strs.get(j);
				String lcs = CommonTools.longestCommonSubsequence4Str(str1, str2);
				
				String lcsT = getTemplate(str1,lcs);
				addOne2Mp(res,lcsT);
			}
		}
		return res;
	}
	void addOne2Mp(Map<String, Integer> mp,String str){
		if(!mp.containsKey(str)){
			mp.put(str, 1);
		}else mp.put(str,mp.get(str)+1);
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
		FileUtils in = new FileUtils("/Users/v11/Documents/毕业文档/output/attr_content/cluster-attr/身    高/T2_cluster_0_身    高.txt"
				, "in");
		Set<String> st = new HashSet<String>();
		String str = null;
		while((str=in.readLine())!=null){
			st.add(str);
		}
		in.close();
		Map<String, Integer> mp = new AttrTemplateApp().getTemplatesByslow(st);
		System.out.println(mp);
	}
}
