package logtest.log;

import logtest.util.LogUtil;

public class Test {
	
	public static void main(String[] args) {
		LogUtil.info("info");
		LogUtil.debug("debug");
	}
	
	public static void test(){
		LogUtil.info("info");
		LogUtil.debug("debug");
	}
}
