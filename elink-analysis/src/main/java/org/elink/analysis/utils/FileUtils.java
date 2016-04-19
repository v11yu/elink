package org.elink.analysis.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.elink.spider.utils.Log;
/**
 * 文件读写工具类
 * @author v11
 *
 */
public class FileUtils {
	BufferedReader cin;
	BufferedWriter out;
	public FileUtils(String FileName, String type) {
		File file = new File(FileName);
		try {
			if (type.equals("in")) {
				cin = new BufferedReader(new FileReader(file));
			} else if (type.equals("out")) {
				out = new BufferedWriter(new FileWriter(file, false));
			} else if(type.equals("append")){
				out = new BufferedWriter(new FileWriter(file,true));
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void append(String filepath,String line){
		try {
		    Files.write(Paths.get(filepath), line.getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
			Log.info(e+" ");
		}
	}
	public void close() {
		try {
			if(cin!=null) cin.close();
			if(out!=null){
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeLine(String string) {
		// TODO Auto-generated method stub
		try {
			out.write(string);
			out.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	public String readLine() {
		// TODO Auto-generated method stub
		try {
			return cin.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return null;
	}
}
