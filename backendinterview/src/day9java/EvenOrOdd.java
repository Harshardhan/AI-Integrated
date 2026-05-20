package day9java;

public class EvenOrOdd {

	public static void main(String[] args) {
		
		int num = 24;
		
		checkEvenOrOdd(num);
	}

	private static void checkEvenOrOdd(int num) {
		
		if(num % 2 == 0) {		
			System.out.println("Given number is Odd:  "+num);
		}else {
			System.out.println("Given number is even: "+num);
		}
		
	}
}
