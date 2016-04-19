package org.elink.analysis.cluster.distance;

import java.util.Map;
import java.util.Set;

public interface DistantTooler<T> {
	public double dis(Set<T> a,Set<T> b,double simi[][],Map<T, Integer> idx);
}
