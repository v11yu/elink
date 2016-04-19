package org.elink.analysis.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FIleTest {
	public void testWrite(){
		String path = "D:\\wwy毕业相关\\output\\context.txt";
		List<String> ls = new ArrayList<>();
		ls.add("hello");
		ls.add("world");
		FileWriteTools.write(ls, path);
	}
	public static void main(String[] args) {
		new FIleTest().testWrite();
	}
}
