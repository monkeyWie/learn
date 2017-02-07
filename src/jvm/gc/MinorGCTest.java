package jvm.gc;

/**
 * 新生代内存不足发生Minor GC,当虚拟机回收新生代内存(对象存活不能回收)后不足于放下新对象时，通过担保机制将新生代内存提前转移至老年代
 * VM参数:
 * -verbose:gc
 * -Xms20M	堆大小20M
 * -Xmx20M	堆大小20M
 * -Xmn10M	新生代10M
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8	Eden:Survivor = 8:1
 */
public class MinorGCTest {
	private static final int _1MB = 1024 * 1024;

	public static void testAllocation() {
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
//		allocation1=null;
//		allocation2=null;
//		allocation3=null;
		allocation4 = new byte[4 * _1MB];// 出现一次Minor GC
	}
	
	public static void main(String[] args) {
		testAllocation();
	}
}
