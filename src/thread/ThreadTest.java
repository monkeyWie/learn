package thread;

public class ThreadTest {
	
	public static synchronized void handel(){
		System.out.println(111);
		try {
			ThreadTest.class.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void ead(){
		System.out.println("EAT EAT!!");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Runnable run = new Runnable() {
			
			@Override
			public void run() {
				ThreadTest.handel();
			}
		};
		new Thread(run).start(); 
		new Thread(run).start();
		try {
			Thread.sleep(1000);
			synchronized (ThreadTest.class) {
				ThreadTest.class.notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
