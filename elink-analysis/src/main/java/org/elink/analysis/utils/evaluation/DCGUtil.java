package org.elink.analysis.utils.evaluation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DCGUtil {
	//List<Map<Long, Integer>> correct;
	public static double getNNDCG(List<List<Double>> predict){
		double sum = 0;
		for(int i=0;i<predict.size();i++){
			sum += getNDCG(predict.get(i));
		}
		return sum / predict.size();
		
	}
	public static double getNDCG(List<Double> predict){
		double s1 = getDCG(predict);
		predict.sort(new Comparator<Double>() {
			@Override
			public int compare(Double o1, Double o2) {
				// TODO Auto-generated method stub
				if(Math.abs(o1-o2) < 1e-6) return 0;
				return o2>o1?1:-1;
			}
		});
		System.out.println(predict);
		double s2 = getDCG(predict);
		System.out.println(s1+" "+s2);
		return s1/(s2+1e-6);
		
	}
	public static double getDCG(List<Double> predict){
		double res = 0;
		for(int i=1;i<=predict.size();i++){
			double re = predict.get(i-1);
			res += 1.0*(Math.pow(2, re)-1) / Math.log(i+1);
		}
		return res /predict.size();
		
	}
	public static void main(String[] args) {
		List<Double> predict = new ArrayList<>();
		predict.add(2.0);
		predict.add(4.0);
		predict.add(1.0);
		DCGUtil.getNDCG(predict);
	}
}
