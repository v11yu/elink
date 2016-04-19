package org.elink.analysis.cluster.tool;

import java.util.List;
import java.util.Set;

import org.elink.database.model.cluster.AttrInfo;

public class AttrClusterEvaluation extends ClusterEvaluation<AttrInfo> {

	public AttrClusterEvaluation(List<Set<AttrInfo>> fact, List<Set<AttrInfo>> predict) throws Exception {
		super(fact, predict);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equal(AttrInfo a, AttrInfo b) {
		// TODO Auto-generated method stub
		return a.getAttrName().equals(b.getAttrName());
	}

}
