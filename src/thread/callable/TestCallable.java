package thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallable implements Callable<String>{

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return "test";
	}

	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorPool = Executors.newFixedThreadPool(3);
		Future<String> future = executorPool.submit(new TestCallable());
		System.out.println(future.get());
	}
}
