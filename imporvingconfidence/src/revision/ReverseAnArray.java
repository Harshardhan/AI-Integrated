package revision;

import java.util.Arrays;

public class ReverseAnArray {

	public static int[] reverseAnArray(int[] number) {
		
	    for (int left = 0, right = number.length - 1; left < right; left++, right--) {
			if(left < right) {
				int temp =  number[left];
				number[left] = number[right];
				number[right]= temp;
			}
			
		}
		return number;
	}
	
	public static void main(String[] args) {
		int[] result = {1,22,3,4,5};
		int[] reverseArray = reverseAnArray(result);
		System.out.println(Arrays.toString(reverseArray));
	}
}
