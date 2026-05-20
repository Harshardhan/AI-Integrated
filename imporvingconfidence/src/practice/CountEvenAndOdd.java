package practice;

import java.util.Arrays;

public class CountEvenAndOdd {

	public static void countEvenAndOdd(int[] num) {
		int evenCount = 0;
		int oddCount = 0;
		for(int i = 0; i<num.length; i++) {
			if(num[i] %2 ==0) {
				evenCount++;
			}else {
				oddCount++;
			}
			
		}
		
        System.out.println("Even count: " + evenCount);
        System.out.println("Odd count: " + oddCount);

		
	}
	
	public static void main(String[] args) {
		
		int[] result = {1,2,3,4,5,6,7};
		 countEvenAndOdd(result);
	}
}
