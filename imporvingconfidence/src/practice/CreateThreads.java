package practice;

public class CreateThreads {

	public static Thread createThread(Runnable task) {
		return new Thread(task);
	}

	public static void main(String[] args) {

	    Thread t1 = createThread(() ->
	        System.out.println("Thread 1: " + Thread.currentThread().getName())
	    );

	    Thread t2 = createThread(() ->
	        System.out.println("Thread 2: " + Thread.currentThread().getName())
	    );

	    t1.start();
	    t2.start();
	}
}
