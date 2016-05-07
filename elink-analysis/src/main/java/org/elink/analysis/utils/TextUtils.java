package org.elink.analysis.utils;

public class TextUtils {
	public static String getClearText(String str){
		str = str.replaceAll("\\s+", "");
		str = str.replaceAll(" ", "");
		return str;
	}
}
