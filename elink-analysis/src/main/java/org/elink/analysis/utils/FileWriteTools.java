package org.elink.analysis.utils;

import java.util.List;

public class FileWriteTools {
	/**
	 * 将数组值写入path路径中
	 * @param ls
	 * @param path
	 */
	public static void write(List<String> ls,String path){
		FileUtils out = new FileUtils(path, "append");
		for(String str:ls){
			out.writeLine(str);
		}
		out.close();
	}
}
