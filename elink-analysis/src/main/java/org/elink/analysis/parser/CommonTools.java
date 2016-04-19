package org.elink.analysis.parser;

public class CommonTools {
	public static int LCS(String str1,String str2){
		if(str2.length() == 0 || str1.length() == 0) return 0;
		char s1[] = str1.toCharArray();
		char s2[] = str2.toCharArray();
		int len = Math.max(s1.length, s2.length);
		int table[][] = new int [len][len];
		int size2 = str2.length(),size1=str1.length();
		for (int j = 0; j < size2; ++j){
	        table[0][j] = (s1[0] == s2[j] ? 1 :0);
	    }
	    for (int i = 1; i < size1; ++i){
	        table[i][0] = (s1[i] == s2[0] ? 1 :0);
	        for (int j = 1; j < size2; ++j)
	        {
	            if (s1[i] == s2[j])
	            {
	                table[i][j] = table[i-1][j-1]+1;
	            }
	        }
	    }
	    int ans = 0;
		for(int i=0;i<s1.length;i++){
			for(int j=0;j<s2.length;j++){
				ans = Math.max(ans, table[i][j]);
			}
		}
		return ans;
		
	}
	public static void main(String[] args) {
		System.out.println(CommonTools.LCS("jhgooooooooo", "aaaaaaaaaaaaaaaaaaaaaajhgo"));
	}
}
