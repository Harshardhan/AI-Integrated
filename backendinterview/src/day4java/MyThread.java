package day4java;

public class MyThread extends Thread{

	public void run() {
		
		for(int i=1; i<=5; i++) {
			System.out.println(Thread.currentThread().getName()+" : "+i);
			
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Thread running: "+Thread.currentThread().getName());
	}
	public static void main(String[] args) {
		
		MyThread t1 = new MyThread();
		MyThread t2 = new MyThread();
		MyThread t3 = new MyThread();

		t1.start();
		t2.start();
		t3.start(); 
		
	} 
}
