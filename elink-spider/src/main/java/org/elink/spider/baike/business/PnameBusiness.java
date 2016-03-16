package org.elink.spider.baike.business;

import java.util.List;

import org.elink.database.pname.model.Pname;

public interface PnameBusiness {
	void savePname(Pname p);
	/**
	 * check the pname exist in mongodb
	 * @param p
	 * @return exist return true, or return false
	 */
	boolean check(Pname p);
	List<Pname> getAllList();
}
