package org.elink.analysis.parser;

import java.util.ArrayList;
import java.util.List;

import org.elink.analysis.utils.Log;

public class TextParser {
	public List<String> getContext(String context,String value,int windowSize){
		//Log.info("context "+context);
		//Log.info("value "+value);
		List<Integer> idxs = new KmpTools(context, value).kmpGetIndex();
		List<String> res = new ArrayList<>();
		for(Integer idx :idxs){
			res.add(context.substring(Math.max(0, idx-windowSize), Math.min(context.length(), idx+value.length()+windowSize)));
		}
		return res;
	}
	public List<String> getContextByUp(String context,String value,int windowSize){
		List<Integer> idxs = new KmpTools(context, value).kmpGetIndex();
		List<String> res = new ArrayList<>();
		for(Integer idx :idxs){
			res.add(context.substring(Math.max(0, idx-windowSize), Math.min(context.length(), idx)));
		}
		return res;
	}
	public List<String> getContextByDown(String context,String value,int windowSize){
		List<Integer> idxs = new KmpTools(context, value).kmpGetIndex();
		List<String> res = new ArrayList<>();
		for(Integer idx :idxs){
			res.add(context.substring(Math.max(0, idx+value.length()), Math.min(context.length(), idx+value.length()+windowSize)));
		}
		return res;
	}
}
