package thread;

public class Motk{
	private byte[] lock = new byte[0];
	
	public void t1(){
		synchronized (lock) {
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName()+"\ti:"+i);
			}
		}
	}
	
	public void t2(){
		synchronized (lock) {
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName()+"\ti:"+i);
			}
		}
	}
	
	public static void main(String[] args) {
		final Motk motk = new Motk();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				motk.t1();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				motk.t2();
			}
		});
		
		t1.start();
		t2.start();
	}
}
