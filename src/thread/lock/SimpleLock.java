package thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleLock {
	public static void main(String[] args) {
		final Lock lock = new ReentrantLock();
		final Condition condition = lock.newCondition();//用于等待和通知
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				lock.lock();
				System.out.println(111111);
				try {
					Thread.sleep(1000);
					condition.await();	//等待，释放对象锁
					System.out.println(444444);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock.unlock();
			}
		}).start();
		try {
			Thread.sleep(50);	//保证不会死锁
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				lock.lock();
				System.out.println(222222);
				try {
					Thread.sleep(1000);
					condition.signal();	//通知
					System.out.println(333333);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock.unlock();
			}
		}).start();
	}
}