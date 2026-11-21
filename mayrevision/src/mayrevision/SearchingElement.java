package mayrevision;

import java.util.Arrays;

public class SearchingElement {

	public static void main(String[] args) {

		int[] numbers = { 10, 20, 30, 40, 50 };

		int target = 30;
		
		int index = Arrays.binarySearch(numbers, target);
		
		if(index >=0) {
			System.out.println("Found at index: "+index);
		}else {
			System.out.println("Number not found");
		}
	}
}
