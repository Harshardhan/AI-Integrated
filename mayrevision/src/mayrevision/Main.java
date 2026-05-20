package mayrevision;

public class Main {

	public static void main(String[] args) {
		MyThread t1 = new MyThread();
		t1.start();
		
		
		Thread thread = new Thread(new MyTask());
		thread.start();
		
		System.out.println("Main thread is running");
	}
}
