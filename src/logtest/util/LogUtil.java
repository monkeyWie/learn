package logtest.util;

import logtest.log.Test;

import org.apache.log4j.Logger;

public class LogUtil {
	
	private static Logger logger = Logger.getLogger(Test.class);
	
	public static void info(Object message){
		logger.info(message);
	}
	
	public static void debug(Object message){
		logger.debug(message);
	}
}
