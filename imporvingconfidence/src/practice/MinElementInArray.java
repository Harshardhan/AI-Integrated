package practice;

import java.util.Arrays;

public class MinElementInArray {
	
	public static int findMinElement(int[] num) {
		int min = num[0];
		for(int i =0; i < num.length; i++) {
			if(num[i] < min) {
				min = num[i];
			}
		}
		return min;
		
	}
	
	public static void main(String[] args) {
		
		int [] maxValue = {2,3,4,5,6,7};
		int element = findMinElement(maxValue);
        System.out.println("Minimum element is: " + element);
	}

}
