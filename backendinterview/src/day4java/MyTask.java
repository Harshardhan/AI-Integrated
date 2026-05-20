package day4java;

public class MyTask implements Runnable {

	@Override
	public void run() {
		System.out.println("Running Task: " + Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new MyTask());
		Thread t2 = new Thread(new MyTask());

		t1.start();
		t2.start();
	}
}
