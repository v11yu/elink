package org.elink.analysis.cluster.tool;
import java.util.*;

public abstract class ClusterEvaluation<T> {
	List<Set<T>> L;
	List<Set<T>> C;
	public abstract boolean equal(T a,T b);
	public ClusterEvaluation(List<Set<T>> fact,List<Set<T>> predict) throws Exception{
		this.L = fact;
		this.C = predict;
		for(Set<T> i:L){
			if(i.size() == 0){
				throw new Exception("Error!!face set have element size is 0");
			}
		}
		for(Set<T> i:C){
			if(i.size() == 0){
				throw new Exception("Error!!predict set have element size is 0");
			}
		}
	}
	int getSameNum(Set<T> a,Set<T> b){
		int num = 0;
		for(T i:a){
			for(T j:b){
				if(equal(i, j)){
					num++;
					break;
				}
			}
		}
		return num;
	}
	private double getP(Set<T> l,Set<T> c){
		return 1.0*getSameNum(l, c) / c.size();
	}
	private double getR(Set<T> l,Set<T> c){
		return 1.0*getSameNum(l, c) / l.size();
	}
	private double getF(Set<T> a,Set<T> b){
		double p = getP(a, b);
		double r = getR(a, b);
		if(p+r < 1e-7) return 0;
		return 2*p*r/(p+r);
	}
	private double getF4L(Set<T> a){
		double f = 0;
		for(Set<T> c:C){
			f = Math.max(f, getF(a, c));
		}
		return f;
	}
	public double getF1Val(){
		double res = 0;
		double num = 0;
		for(Set<T> l:L){
			res += getF4L(l)*l.size();
			
			num += l.size();
		}
		return res/num;
	}
	public static void main(String[] args) {
		
	}
}
