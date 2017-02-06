package jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆溢出
 * -Xms20m-Xmx20m-XX：+HeapDumpOnOutOfMemoryError
 * @author Administrator
 *
 */
public class HeapOutTest {
	static class HeapOOM{}
	public static void main(String[] args) {
		List<HeapOOM> list = new ArrayList<HeapOOM>();
		while(true){
			list.add(new HeapOOM());
		}
	}
}
