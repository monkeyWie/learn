package jvm.monitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 用JDK自带工具监视内存变化
 *-Xms100m
 *-Xmx100m
 *-XX:+UseSerialGC
 */
public class HeapMonitorTest {
	//64KB的对象
	private static class OOMObject{
		byte[] bts = new byte[64*1024];
	}
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		for (int i = 0; i < 1000; i++) {
			list.add(new OOMObject());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.gc();
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
