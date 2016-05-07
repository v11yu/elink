package org.elink.analysis.app.other;
import java.util.*;

import org.elink.analysis.app.first.AttrValueFinderApp;
import org.elink.analysis.business.AttributeNameInfoBusiness;
import org.elink.analysis.business.AttributePairsInfoBusiness;
import org.elink.analysis.business.impl.AttributeNameInfoBusinessImpl;
import org.elink.analysis.business.impl.AttributePairsInfoBusinessImpl;
import org.elink.analysis.utils.FileUtils;
import org.elink.analysis.utils.FileWriteTools;
import org.elink.analysis.utils.TaskConfig;
import org.elink.analysis.utils.evaluation.MapUtil;
import org.elink.database.model.AttributeNameInfo;

/**
 * 仅仅用于生成属性名列表txt
 * @author v11
 *
 */
public class GenerateAttrNameApp {
	public void generateAttributeName(){
		AttributeNameInfoBusiness anb = new AttributeNameInfoBusinessImpl();
		String attrFile = TaskConfig.getValue("outputPath")+"attribute_name.txt";
		Map<String, Integer> mp = new HashMap<String, Integer>();
		List<String> attrs = new ArrayList<String>();
		for(AttributeNameInfo attr:anb.getAllList()){
			mp.put(attr.getAttrName(), attr.getAttrCount());
		}
		mp = MapUtil.sortByValue(mp);
		for(Map.Entry<String, Integer> entry : mp.entrySet()){
			attrs.add(entry.getKey());
		}
		FileWriteTools.write(attrs, attrFile);
	}
	public static void main(String[] args) {
		GenerateAttrNameApp gaa = new GenerateAttrNameApp();
		gaa.generateAttributeName();
	}
}
