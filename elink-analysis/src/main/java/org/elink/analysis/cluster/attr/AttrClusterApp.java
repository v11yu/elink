package org.elink.analysis.cluster.attr;

import java.util.*;

import org.elink.analysis.cluster.tool.AttrClusterEvaluation;
import org.elink.analysis.utils.FileUtils;
import org.elink.database.model.cluster.AttrInfo;
import org.elink.database.utils.Log;
import org.elink.spider.baike.business.AttrInfoBusiness;
/**
 * 属性名聚类并生成评价指标
 * @author v11
 *
 */
public class AttrClusterApp {
	AttrInfoBusiness aiz = new AttrInfoBusiness();
	List<AttrInfo> getList(){
		return aiz.getAll();
	}
	List<Set<AttrInfo>> readCorrect(){
		List<Set<AttrInfo>> res = new ArrayList<>();
		FileUtils in = new FileUtils("D://wwy毕业相关//output//attr_cluster//correct.txt", "in");
		String str = null;
		while((str = in.readLine())!=null){
			String[] ls = str.split(",");
			Set<AttrInfo> tmps = new HashSet<>();
			for(String w :ls){
				AttrInfo a = new AttrInfo();
				a.setAttrName(w);
				tmps.add(a);
			}
			res.add(tmps);
		}
		return res;
		
	}
	public void work(double split_threshold,double quick_merge_threshod){
		AttrCluster ac = new AttrCluster();
		
		ac.setData(getList(), "属性名",split_threshold,quick_merge_threshod);
		ac.cluste();
		List<Set<AttrInfo>> predict = ac.initSets;
		List<Set<AttrInfo>> correct = readCorrect();
		try {
			AttrClusterEvaluation ace = new AttrClusterEvaluation(correct, predict);
			Log.info("F1值:"+ace.getF1Val());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	void test(){
		AttrCluster ac = new AttrCluster();
		ac.setData(getList(), "属性名",0.25,0.35);
		ac.cluste();
		//List<Set<AttrInfo>> predict = readCorrect();
		//ac.print(predict);
	}
	public static void main(String[] args) {
		AttrClusterApp acp = new AttrClusterApp();
		acp.test();
		
//		for(int i =1;i<8;i++){
//			AttrClusterApp acp = new AttrClusterApp();
//			acp.work(0.1+i*0.1);
//		}
//		for(int i =1;i<8;i++){
//			AttrClusterApp acp = new AttrClusterApp();
//			acp.work(0.15+i*0.01);
//		}
		
		
		
		
	}
}
