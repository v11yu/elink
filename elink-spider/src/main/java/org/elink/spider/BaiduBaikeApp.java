package org.elink.spider;

import java.util.ArrayList;
import java.util.List;

import org.elink.spider.task.BaiduBaikeTask;
import org.elink.spider.utils.TaskConfig;

/**
 * Hello world!
 *
 */
public class BaiduBaikeApp 
{
    public static void main( String[] args ){
    	String tmps = TaskConfig.getValue("names");
        List<String> names = new ArrayList<String>();
        for(String name : tmps.split(",")) names.add(name);
        new BaiduBaikeTask().work(names);
    }
}
