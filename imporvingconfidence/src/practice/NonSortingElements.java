package practice;

import java.util.Arrays;

public class NonSortingElements {

	public static int secondElement(int[] element) {
		if(element.length < 2) {
			throw new IllegalArgumentException("Array must have 2 elements.");
		}
		
		//int largest = Integer.MIN_VALUE;
		//int secondLargest = Integer.MIN_VALUE;
		
		int smallest = Integer.MAX_VALUE;
		int secondSmallest = Integer.MAX_VALUE;

		for(int num :element) {
			if (num < smallest) {
				secondSmallest = smallest ;
				smallest = num;
			}else if( num < secondSmallest && num != smallest) {
				secondSmallest = num ;
			}
		}
		
		return secondSmallest;
	}
	public static void main(String[] args) {
		
		int [] elements = {2,3,4,5,6,7};
		int result = secondElement(elements);
		System.out.println("secondLarestElement: "+result);

	}
}
