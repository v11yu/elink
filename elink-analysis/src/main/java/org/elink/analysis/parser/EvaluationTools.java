package org.elink.analysis.parser;

import org.elink.database.utils.Log;

public class EvaluationTools {
	public static double getP(int ac,int pnum,int cnum){
		return 1.0*ac/pnum;
	}
	public static double getR(int ac,int pnum,int cnum){
		return 1.0*ac/cnum;
	}
	public static double getF(int ac,int pnum,int cnum){
		return 2.0*getP(ac,pnum,cnum)*getR(ac,pnum,cnum)/(getP(ac,pnum,cnum)+getR(ac,pnum,cnum));
		
	}
	static void work(int ac,int pnum,int cnum){
		Log.info("正确率:"+getP(ac, pnum, cnum));
		Log.info("召回率:"+getR(ac, pnum, cnum));
		Log.info("F1:"+getF(ac, pnum, cnum));
	}
	public static void main(String[] args) {
		int ac = 60;
		int pnum = 105;
		int cnum = 126;
		work(ac,pnum,cnum);
	}
}
