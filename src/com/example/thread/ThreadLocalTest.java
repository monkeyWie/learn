package com.example.thread;

public class ThreadLocalTest {
	static ThreadLocal<String> tls = new ThreadLocal<String>();
	
	public static void main(String[] args) {
		new EatThread("555").start();
		new EatThread("666").start();
	}
	
}


class EatThread extends Thread{

	private String a;
	
	public EatThread(String a){
		this.a=a;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			ThreadLocalTest.tls.set(a+"!"+i);
			System.out.println(Thread.currentThread().getName()+":"+ThreadLocalTest.tls.get());
		}
	}
	
}