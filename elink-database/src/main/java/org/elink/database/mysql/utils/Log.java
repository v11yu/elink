package org.elink.database.mysql.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
	static final Logger log = LoggerFactory.getLogger(Log.class);
	public static void info(String s){
		log.info(s);
	}
}
