package org.elink.analysis.cluster.inputer;

import java.util.Map;

public abstract class AbstractInputer<T> {
	abstract public Map<String, T> getInputData();
}
