package org.elink.database.model;

import java.util.List;

import org.elink.database.pname.model.Pname;

public class MultiEntityInfo {
	String name;
	List<Pname> cnames;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Pname> getCnames() {
		return cnames;
	}
	public void setCnames(List<Pname> cnames) {
		this.cnames = cnames;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
