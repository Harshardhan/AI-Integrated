package revision;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SecondLargestElement {

	public static int secondLargeElements(int[] element) {
		int largest  = Integer.MAX_VALUE;
		int secondLargest = Integer.MAX_VALUE;
		
		for(int num :element) {
			if(num < largest) {
				secondLargest = largest;
				largest = num;
			}else if(num < secondLargest && num != largest) {
				secondLargest = num;
			}
		}
		return secondLargest;
		
	}
	
	public static void main(String[] args) {
		int[] result =  {1,2,3,4,5,6};
		int secondElement = secondLargeElements(result);
		System.out.println("Second Largest Element: "+secondElement);
		
	}
}
