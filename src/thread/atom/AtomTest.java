package thread.atom;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomTest {
	public static void main(String[] args) {
//		AtomicInteger num = new AtomicInteger(1);
//		System.out.println(num.incrementAndGet());
		test2();
	}
	
	static int num = 0;
	
	static AtomicInteger atomicInteger = new AtomicInteger(0);
	
	/*
	 * 非线程安全,num++不符合原子性
	 * 分为以下三部
	 * 1.从内存取出num的值
	 * 2.计算num的值
	 * 3.把numb的值写入内存
	 */
	static void test1(){
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
						num++;
					}
					System.out.println(num);
				}
			}).start();
		}
	}
	
	/*
	 * 线程安全,使用AtomicInteger类确保原子性
	 * incrementAndGet()
	 */
	static void test2(){
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
						atomicInteger.incrementAndGet();
					}
					System.out.println(atomicInteger.get());
				}
			}).start();
		}
	}
}

