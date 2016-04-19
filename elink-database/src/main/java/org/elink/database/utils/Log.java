package org.elink.database.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
	static final Logger log = LoggerFactory.getLogger(Log.class);
	public static void info(Object msg){
		log.info(msg+"");
	}
	public static void error(Object msg){
		log.error(msg+"");
	}
	public static void debug(Object msg){
		log.debug(msg+"");
	}
	public static void warn(Object msg){
		log.warn(msg+"");
	}
}
