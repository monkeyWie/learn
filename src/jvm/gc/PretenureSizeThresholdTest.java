package jvm.gc;

/**
 * 大对象直接进入老年代
 * VM参数:
 * -verbose:gc
 * -Xms20M	堆大小20M
 * -Xmx20M	堆大小20M
 * -Xmn10M	新生代10M
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8	Eden:Survivor = 8:1
 * -XX:PretenureSizeThreshold=3145728	大于3M的对象直接进入老年代避免新生代Eden区及两个Survivor区发生大量的内存复制
 * @author Administrator
 *
 */
public class PretenureSizeThresholdTest {
	private static final int _1MB = 1024 * 1024;

	public static void testPretenureSizeThreshold() {
		byte[] allocation4 = new byte[4 * _1MB];// 直接分配在老年代里
	}
	
	public static void main(String[] args) {
		testPretenureSizeThreshold();
	}
}
