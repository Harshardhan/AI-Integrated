package mayrevision;

import java.util.HashMap;

public class FirstNonRepeatingElement {

	public static void main(String[] args) {
		int[] arr = {4, 5, 1, 2, 1, 4, 5};
		findFirstNonRepeatingElement(arr);
		
		
	}

	private static Integer findFirstNonRepeatingElement(int[] arr) {

		HashMap<Integer, Integer> frequencyMap = new HashMap<>();
		
		// Count the frequency of each element
		for(int num :arr) {
			frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
		}
		
		// Find the first non-repeating element with frequency 1
		for(int num : arr) {
			if(frequencyMap.get(num) == 1) {
				System.out.println("First non-repeating element is: " + num);
				return num ;
			}
		}
		
		return null;
	}}