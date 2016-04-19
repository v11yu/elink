package org.elink.analysis.cluster.attr;

import java.text.SimpleDateFormat;
import java.util.*;

import org.elink.analysis.cluster.HierarchicalCluster;
import org.elink.analysis.parser.CommonTools;
import org.elink.analysis.utils.FileUtils;
import org.elink.database.model.cluster.AttrInfo;
import org.elink.database.utils.Log;

public class AttrCluster extends HierarchicalCluster<AttrInfo>{
	double getAttrNameSimilar(AttrInfo a,AttrInfo b){
		int lcs = CommonTools.LCS(a.getAttrName(), b.getAttrName());
		return 1.0*lcs / Math.min(a.getAttrName().length(),b.getAttrName().length());
	}
	double getListSimilar(List<String> a,List<String> b){
		double res = 0;
		Set<String> st = new HashSet<>();
		int cnt = 0;
		for(String as:a){
			st.add(as);
			for(String bs:b){
				st.add(bs);
				if(as.equals(bs)){
					cnt++;
					break;
				}
			}
		}
		if(st.size() == 0){
			return 0;
		}
		return 1.0*cnt/st.size();
	}
	List<String> getNgram(String a,int n){
		List<String> res = new ArrayList<>();
		for(int i=n;i<a.length();i++){
			res.add(a.substring(i-n,i));
		}
		return res;
	}
	double getTextSimilar(String a,String b){
		a = a.substring(0, Math.min(a.length(),500));
		b = b.substring(0, Math.min(b.length(),500));
		return getListSimilar(getNgram(a,3),getNgram(b,3));
	}
	@Override
	public double getElementSimilar(AttrInfo a, AttrInfo b) {
		// TODO Auto-generated method stub
		//baiduSearchText
		
		//string similar
		double stringNameSimi = getAttrNameSimilar(a,b);
		
		//Baike List similar
		double baikeListSimi = getListSimilar(a.getBaikeList(),b.getBaikeList());

		//Baidu recommand similar
		double baikeRecomSimi = getListSimilar(a.getBaiduRecommends(),b.getBaiduRecommends());
		
		double baiduText = getTextSimilar(a.getBaiduSearchText(),b.getBaiduSearchText());
		
		
		double res = stringNameSimi+baikeListSimi+baikeRecomSimi+baiduText;
		res /=4;
		//Log.info(stringNameSimi+" "+baikeListSimi+" "+baikeRecomSimi+" "+baiduText+" "+res);
		return res;
	}
	@Override
	public Set<String> set2str(Set<AttrInfo> st) {
		// TODO Auto-generated method stub
		Set<String> res = new HashSet<>();
		for(AttrInfo a:st){
			res.add(a.getAttrName());
		}
		return res;
	}
	
	
}
