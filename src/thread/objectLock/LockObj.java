package thread.objectLock;

public class LockObj {
	synchronized public static void doSomeThing1() throws InterruptedException{
		System.out.println("静态类：搞事搞事1!");
		Thread.sleep(5000);
	}
	synchronized public void doSomeThing2() throws InterruptedException{
		System.out.println("实例类：搞事搞事2!");
		Thread.sleep(5000);
	}
	synchronized public static void doSomeThing3() throws InterruptedException{
		System.out.println("静态类：搞事搞事3!");
		Thread.sleep(5000);
	}
	synchronized public void doSomeThing4() throws InterruptedException{
		System.out.println("实例类：搞事搞事4!");
		Thread.sleep(5000);
	}
	public static void main(String[] args) throws InterruptedException {
		LockObj lockObj = new LockObj();
		new DoObjectThread(lockObj).start();
		lockObj.doSomeThing4();
		//LockObj lockObj = new LockObj();
		/*new DoClassThread().start();*/
		/*Thread.sleep(10);
		new DoObjectThread(lockObj).start();
		Thread.sleep(10);
		lockObj.doSomeThing2();*/
		//LockObj.doSomeThing1();
	//	System.out.println(LockObj.class);
	}
}
class DoObjectThread extends Thread{

	private LockObj lockObj;

	public DoObjectThread(LockObj lockObj) {
		this.lockObj = lockObj;
	}

	@Override
	public void run() {
		/*synchronized (lockObj) {
			System.out.println("线程实例类:搞事搞事3");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		try {
			lockObj.doSomeThing2();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
class DoClassThread extends Thread{

	@Override
	public void run() {
		/*synchronized (LockObj.class) {
			System.out.println("线程静态类:搞事搞事4");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		try {
			LockObj.doSomeThing1();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}