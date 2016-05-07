package org.elink.analysis.parser;

public class CommonTools {
	public static int LCS(String str1, String str2) {
		if (str2.length() == 0 || str1.length() == 0)
			return 0;
		char s1[] = str1.toCharArray();
		char s2[] = str2.toCharArray();
		int len = Math.max(s1.length, s2.length);
		int table[][] = new int[len][len];
		int size2 = str2.length(), size1 = str1.length();
		for (int j = 0; j < size2; ++j) {
			table[0][j] = (s1[0] == s2[j] ? 1 : 0);
		}
		for (int i = 1; i < size1; ++i) {
			table[i][0] = (s1[i] == s2[0] ? 1 : 0);
			for (int j = 1; j < size2; ++j) {
				if (s1[i] == s2[j]) {
					table[i][j] = table[i - 1][j - 1] + 1;
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < s1.length; i++) {
			for (int j = 0; j < s2.length; j++) {
				ans = Math.max(ans, table[i][j]);
			}
		}
		return ans;

	}
	public static int longestCommonSubsequence(String str1,String str2) {
		char[] string1 = str1.toCharArray();
		char[] string2 = str2.toCharArray();
		int m = string1.length;
		int n = string2.length;
		
		int[][] c = new int[m][n];
		int max = 0;
		int maxPosX = 0;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (string1[i] == string2[j]) {
					if (i == 0 || j == 0) {
						c[i][j] = 1;
						
					} else {
						c[i][j] = c[i - 1][j - 1] + 1;
						
					}

				} else {
					if(i>0&&c[i-1][j]>c[i][j]){
						c[i][j] = c[i-1][j];
						
					}
					if(j>0&&c[i][j-1]>c[i][j]){
						c[i][j] = c[i][j-1];
						
					}
					if(i>0&&j>0&&c[i-1][j-1]>c[i][j]){
						c[i][j] = c[i-1][j-1];
						
					}
				}
				if (c[i][j] > max) {
					max = c[i][j];
					
				}
			}
		}
		return max;
	}
	public static String longestCommonSubsequence4Str(String str1,String str2) {
		char[] string1 = str1.toCharArray();
		char[] string2 = str2.toCharArray();
		int m = string1.length;
		int n = string2.length;
		String ans[][] = new String[m][n];
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				ans[i][j] = "";
			}
		}
		int[][] c = new int[m][n];
		int max = 0;
		int maxPosX = 0;
		String resString = "";
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (string1[i] == string2[j]) {
					if (i == 0 || j == 0) {
						c[i][j] = 1;
						ans[i][j] += string1[i];
					} else {
						c[i][j] = c[i - 1][j - 1] + 1;
						ans[i][j] = ans[i-1][j-1] + string1[i];
					}

				} else {
					if(i>0&&c[i-1][j]>c[i][j]){
						c[i][j] = c[i-1][j];
						ans[i][j] = ans[i-1][j];
					}
					if(j>0&&c[i][j-1]>c[i][j]){
						c[i][j] = c[i][j-1];
						ans[i][j] = ans[i][j-1];
					}
					if(i>0&&j>0&&c[i-1][j-1]>c[i][j]){
						c[i][j] = c[i-1][j-1];
						ans[i][j] = ans[i-1][j-1];
					}
				}
				if (c[i][j] > max) {
					max = c[i][j];
					resString = ans[i][j];
				}
			}
		}
		return resString;
	}

	public static void main(String[] args) {
		//System.out.println(CommonTools.LCS("jhgooooooooo","aaaaaaaaaaaaaaaaaaaaaajhgo"));
		System.out.println(CommonTools.longestCommonSubsequence4Str(
				"Mi）又译：河由美，韩国资深演员，毕业于日本大谷女子大学，代表作《我男人的女人》《逆转女王》。",
				"吴飞霞，出生于美国，毕业于瑞士弗拉克林大学，演员。导演吴宇森的女儿。2001年大"));
	}
}
