package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorTest {
	public static void main(String[] args) {
		ExecutorService executorServices = Executors.newSingleThreadExecutor();
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				System.out.println(1111);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		executorServices.execute(runnable);
		executorServices.execute(runnable);
		executorServices.execute(runnable);
		executorServices.execute(runnable);
		executorServices.shutdown();
	}
}
