package practice;

import java.util.Arrays;

public class MoveZerosToEnd {

	private static int temp;

	public static int[] zeroToEnd(int[] num) {
		int index = 0;
		
		for(int i = 0; i< num.length; i++) {
			if(num[i] != 0) {
				num[index] = num[i];
				index ++;
			}
		}
		
		while(index < num.length) {
			num[index] = 0;
			index++;
		}
		return num;
	}	
	public static void main(String[] args) {
		int[] numbers = {1,0,2,0,3,0,4,0};
		int[] rotateZeros = zeroToEnd(numbers);
		System.out.println(Arrays.toString(rotateZeros));
	}
}
