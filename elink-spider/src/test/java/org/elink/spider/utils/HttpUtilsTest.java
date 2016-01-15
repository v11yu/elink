package org.elink.spider.utils;

import org.junit.Test;

public class HttpUtilsTest {
	@Test
	public void test(){
		String str = "gt哈";
		System.out.println(HttpUtils.encode(str));
	}
}
