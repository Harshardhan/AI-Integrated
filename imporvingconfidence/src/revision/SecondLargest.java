package revision;

public class SecondLargest {

	public static Integer secondLargest(int[] array) {
		if(array == null || array.length < 2) {
			return null;
		}
			int largest = Integer.MIN_VALUE;
			
			int secondLargest = Integer.MIN_VALUE;
			
			boolean foundSecond = false;
			
			for(int number : array) {
				if( number > largest) {
					secondLargest = largest;
					largest = number;
				    foundSecond = true;
				}else if(number < largest && number > secondLargest) {
					secondLargest = number;
					foundSecond = true;
				}
			}
		
		return foundSecond ? secondLargest : null ;
		
	}
		
	public static void main(String[] args) {
		
		int[] input = {2,3,4,5,6,7,6};
		
		Integer result =secondLargest(input);
		
		System.out.println(result);
	}
}
