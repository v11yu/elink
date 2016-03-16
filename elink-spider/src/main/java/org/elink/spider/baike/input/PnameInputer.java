package org.elink.spider.baike.input;

import java.util.List;

import org.elink.database.pname.model.Pname;
import org.elink.spider.baike.business.PnameBusiness;
import org.elink.spider.baike.business.impl.PnameBusinessImpl;

public class PnameInputer implements Inputer<Pname>{
	PnameBusiness pnameBusiness = new PnameBusinessImpl();
	@Override
	public List<Pname> getInputData() {
		// TODO Auto-generated method stub
		return pnameBusiness.getAllList();
	}

}
