package practice;

public class SecondLargest {

	private static Integer secondLargestElement(int[] array) {
		
		if(array == null || array.length < 2)
		return null;
		
		int largest = Integer.MIN_VALUE;
		
		int secondLargest = Integer.MIN_VALUE;
		
		for(int num : array) {
			if(num > largest) {
				secondLargest = largest;
				largest = num;
			}else if(num > secondLargest && num != largest) {
				secondLargest = num;
			}
		}
		return secondLargest  == Integer.MIN_VALUE ? null : secondLargest;
	}
	
	public static void main(String[] args) {
		
		int[] arr = {1,2,3,4,5,6,7,8,9,10,9,-5,-9};
		
		Integer secondLargest = secondLargestElement(arr);
		
		System.out.println(secondLargest);
	}
}
