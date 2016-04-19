package org.elink.analysis.utils.evaluation;
import java.util.*;
public abstract class MapEvaluation {

	List<Map<Long, Integer>> correct;
	List<Map<Long, Double>> predict;
	static double getMap(List<Map<Long, Integer>> correct,List<Map<Long, Double>> predict){
		double res = 0;
		if(correct == null || predict == null || correct.size()!=predict.size()) {
			System.out.println("error");
			return -1;
		}
		for(int i=0;i<correct.size();i++){
			res += getAp(correct.get(i),predict.get(i));
		}
		return res/correct.size();
	}
	static double getAp(Map<Long, Integer> c,Map<Long, Double> p){
		int R = 0;
		p = MapUtil.sortByValue(p);
		int cnt = 0;
		double sum = 0;
		for(Map.Entry<Long, Double> entry : p.entrySet()) {
        	Long id = entry.getKey();
        	int t = c.get(id);
        	R+=t;
        	cnt++;
        	double pi = 1.0*R/cnt;
        	sum += pi * t;
        }
		if(R ==0) return 0;
		return sum / R;
	}
}

