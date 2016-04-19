package org.elink.analysis.cluster.simi;

public abstract class SimiMethod<T> {
	abstract public Double getSimi(T a,T b);
}
