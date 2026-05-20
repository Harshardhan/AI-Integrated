package practice;

public class PrintNumbers {

	public static Thread printNumber(int[] number) {
		return new Thread(() -> { for(int num: number) {
			System.out.println("Number: "+num+ "| Thread: "+Thread.currentThread().getName());
		}
		
	});
	}
	
	public static void main(String[] args) {
		
		int[] number = {2,3,4,5,6,7};
		
		Thread t1 = printNumber(number);
		
		t1.start();
		
	}
}
