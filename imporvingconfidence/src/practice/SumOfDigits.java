package practice;

public class SumOfDigits {

	
	public static int sumOfDigits(int a , int b) {
		
		return a+b;
		
	}
	public static void main(String[] args) {
		int[] number = {1,2,3,4,5};
        System.out.println("Array elements:");

        int sum = 0;
		for(int i = 0;i<number.length;i++) {
            sum = sum + number[i];
		}
		
		
		int result = sumOfDigits(2,5);
		System.out.println("The sum of Digits: "+result);
        System.out.println(sum);

	}
}
