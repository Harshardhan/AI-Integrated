package practice;

import java.util.Arrays;

public class ReverseAnArray {

	public static int[] reverseAnArray(int[] num) {
		int left = 0;
		int right = num.length-1;
		
		while(left<right) {
			int temp = num[left];
			num [left] = num[right];
			num[right] = temp;
			left++;
			right--;
		}
		return num;

	}
	public static void main(String[] args) {
		
		int[] result ={1,2,3,4,5};
		int[] reversed = reverseAnArray(result);
		System.out.println(Arrays.toString(reversed));
		
		
}
}
