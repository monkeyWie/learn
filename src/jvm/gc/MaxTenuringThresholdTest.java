package jvm.gc;

/**
 * 如果对象在Eden出生并经过第一次Minor GC后仍然存活，并且能被
	Survivor容纳的话，将被移动到Survivor空间中，并且对象年龄设为1。对象在Survivor区中
	每“熬过”一次Minor GC，年龄就增加1岁，当它的年龄增加到一定程度（默认为15岁），就
	将会被晋升到老年代中。
 * VM参数:
 * -verbose:gc
 * -Xms20M	堆大小20M
 * -Xmx20M	堆大小20M
 * -Xmn10M	新生代10M
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8	Eden:Survivor = 8:1
 * -XX:MaxTenuringThreshold=1
 * -XX:+PrintTenuringDistribution
 */
public class MaxTenuringThresholdTest {
	private static final int _1MB = 1024 * 1024;

	public static void test() {
		byte[] allocation1, allocation2, allocation3;
		allocation1 = new byte[_1MB/16];	//当MaxTenuringThreshold=15时还是保留在Survivor空间
		allocation2 = new byte[4 * _1MB];
		allocation3 = new byte[4 * _1MB];
		allocation3=null;
		allocation3 = new byte[4 * _1MB];
	}
	
	/**
	 * 为了能更好地适应不同程序的内存状况，虚拟机并不是永远地要求对象的年龄必须达到
		了MaxTenuringThreshold才能晋升老年代，如果在Survivor空间中相同年龄所有对象大小的总
		和大于Survivor空间的一半，年龄大于或等于该年龄的对象就可以直接进入老年代，无须等
		到MaxTenuringThreshold中要求的年龄。
	 */
	public static void test2() {
		byte[] allocation1, allocation2, allocation3,allocation4;
		allocation1 = new byte[_1MB/16];	
		allocation4 = new byte[_1MB/16];	//当MaxTenuringThreshold=15时但是因为同年龄并且大于Survivor空间的一半则提前进入老年区
		allocation2 = new byte[4 * _1MB];
		allocation3 = new byte[4 * _1MB];
		allocation3=null;
		allocation3 = new byte[4 * _1MB];
	}
	
	public static void main(String[] args) {
		test2();
	}
}
