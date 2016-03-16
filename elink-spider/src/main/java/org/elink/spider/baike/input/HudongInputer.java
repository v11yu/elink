package org.elink.spider.baike.input;

import java.util.ArrayList;
import java.util.List;

import org.elink.database.hudong.model.HuDongEntity;
import org.elink.spider.baike.business.PnameBusiness;
import org.elink.spider.baike.business.impl.PnameBusinessImpl;
import org.elink.spider.hudong.business.HudongBusiness;
import org.elink.spider.hudong.business.HudongBusiness2DB;

public class HudongInputer implements Inputer<String>{
	PnameBusiness pnameBusiness = new PnameBusinessImpl();
	HudongBusiness hudongBusiness = new HudongBusiness2DB();
	@Override
	public List<String> getInputData() {
		List<String> res = new ArrayList<>();
		for(HuDongEntity e :hudongBusiness.getAllList()){
			res.add(e.getName());
		}
		return res;
	}

}
