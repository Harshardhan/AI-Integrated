package mayrevision;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Threads {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService executor = Executors.newFixedThreadPool(2);

		Future<Integer> f1 = executor.submit(() -> {
		    Thread.sleep(2000);
		    return 10;
		});

		Future<Integer> f2 = executor.submit(() -> {
		    Thread.sleep(1000);
		    return 20;
		});

		System.out.println(f1.get());
		System.out.println(f2.get());
	}
}
