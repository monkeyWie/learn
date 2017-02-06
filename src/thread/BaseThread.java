package thread;

public class BaseThread {
	public static void main(String[] args) throws InterruptedException {
		Thread a =	new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						System.out.println(1111);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
		Thread b = new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("BBBBBB");
				}
			});
		a.setDaemon(true);
		a.start();
		Thread.sleep(5000);
	}
}
