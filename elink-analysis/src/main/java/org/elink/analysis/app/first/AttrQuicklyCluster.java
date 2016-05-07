package org.elink.analysis.app.first;

import java.io.File;
import java.util.*;

import org.bson.types.ObjectId;
import org.elink.analysis.parser.CommonTools;
import org.elink.analysis.utils.FileUtils;
import org.elink.analysis.utils.Log;
import org.elink.analysis.utils.TaskConfig;
import org.elink.analysis.utils.evaluation.MapUtil;
import org.elink.database.model.AttributeNameInfo;

/**
 * 属性名快速聚类 在AttrValueFinder类生成语料之后
 * 
 * @author v11
 *
 */
public class AttrQuicklyCluster {
	int stSize = 5;// 簇进行生成模板，簇内个数的阈值
	public List<String> getInput(String fileName) {
		FileUtils in = new FileUtils(fileName, "in");
		List<String> res = new ArrayList<>();
		String str = null;
		while ((str = in.readLine()) != null) {
			str = str.replaceAll("\\s+", "");
			str = str.replaceAll(" ", "");
			res.add(str);
		}
		return res;
	}

	public void outCluster(String basePath, List<Set<String>> clu, String attr,
			int T,ObjectId attrId) {
		String dirName = "cluster-noattr/";
		//AttrTemplateApp atl = new AttrTemplateApp();
		CalConfidenceApp cca = new CalConfidenceApp();
		File tmp = new File(basePath + dirName);
		tmp.mkdir();
		String outPath = basePath + dirName + attr + "/";
		File dir = new File(outPath);
		dir.mkdir();
		int i = 0;
		for (Set<String> st : clu) {
			String filename = outPath + "T" + T + "_cluster_" + i + "_" + attr+ ".txt";
			FileUtils out = new FileUtils(filename, "out");
			for (String str : st) {
				out.writeLine(str);
			}
			out.close();
			//簇内个数的阈值
			if(st.size()>stSize) {
				String templateOutPath = outPath+"template/";
				new File(templateOutPath).mkdir();
				Map<String, Double> templates = cca.getTemplatesByslow(st);
				String tempFilename = templateOutPath + "T" + T + "_cluster_" + i + "_" + attr
						+ "_temp.txt";
				out = new FileUtils(tempFilename, "out");
				templates = MapUtil.sortByValue( templates );
				
				for(Map.Entry<String, Double> entry : templates.entrySet()) {
					out.writeLine(entry.getKey()+","+entry.getValue());
					//saveTemplate(entry,attr,attrId);
		        }
				out.close();
			}
			i++;
		}
	}
	
	void saveTemplate(Map.Entry<String, Integer> entry,String attr,ObjectId attrId){
		
	}
	public int simi(String str1, String str2) {
		return CommonTools.LCS(str1, str2);
	}

	public void cluster(AttributeNameInfo attrInfo, String basePath, int T) {
		String attr = attrInfo.getAttrName();
		//String contextPath = basePath + attr + "_context.txt";
		String contextPath = basePath + attr + "_context_noAttr.txt";
		//String contextPath = basePath + attr + ".txt";
		List<String> texts = getInput(contextPath);
		if(texts.size()>5000){
			Log.info(attrInfo.getAttrName()+" "+texts.size());
			return ;
		}
		boolean mk[] = new boolean[texts.size()];
		Log.info("开始属性名:" + attr + "...聚类...文本数量:" + texts.size());
		List<Set<String>> sts = new ArrayList<>();
		for (int i = 0; i < texts.size(); i++) {
			System.out.println(i);
			if (mk[i] == true)
				continue;
			mk[i] = true;
			Set<String> st = new HashSet<>();
			st.add(texts.get(i));
			for (int j = i + 1; j < texts.size(); j++) {
				if (mk[j] == true)
					continue;
				if (simi(texts.get(i), texts.get(j)) >= T) {
					st.add(texts.get(j));
					mk[j] = true;
				}
			}
			sts.add(st);	
		}
		Log.info("属性名:" + attr + "..在阈值为T:" + T + "时，聚类簇个数为" + sts.size());
		outCluster(basePath, sts, attr, T,attrInfo.getId());

	}

	public void work(String basePath) {
		List<AttributeNameInfo> attrs = new AttrValueFinderApp().getTopAttrList(20);
		for (AttributeNameInfo attr : attrs) {
			for(int T = 6;T>=4;T--){
				cluster(attr, basePath,T);
			}
		}
	}

	public static void main(String[] args) {
		// String outPath = "D://wwy毕业相关//output//cluster//aa//";
		// File dir = new File(outPath);
		// System.out.println(dir.mkdir());
		new AttrQuicklyCluster().work(TaskConfig.getValue("basePath"));
		//实体，数字，日期。
		//置信度,抽对多少，抽错多少
		//
	}
}
