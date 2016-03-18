package org.elink.spider.baike;

import java.util.ArrayList;

import org.elink.database.pname.model.Pname;
import org.elink.spider.baike.business.PnameBusiness;
import org.elink.spider.baike.business.impl.PnameBusinessImpl;
import org.junit.Test;

public class BaikeTest {
	PnameBusiness pnb = new PnameBusinessImpl();
//	@Test
//	public void testSavePname(){
//		Pname pn = new Pname();
//		pn.setName("何贵林");
//		pn.setUrl("http://baike.baidu.com/item/%E4%BD%95%E8%B4%B5%E6%9E%97");
//		pnb.savePname(pn);
//	}
	BaikeTagSpiderApp bta = new BaikeTagSpiderApp();
	@Test
	public void testDfs(){
		String tagName = "娱乐人物";
		bta.dfsTag(tagName,new ArrayList<String>());
	}
}
