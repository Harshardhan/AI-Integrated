package mayrevision;

import java.util.HashMap;

public class Frequency {

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4, 5, 1, 2, 1, 3, 4, 5, 5 };

		countEachElementFrequency(arr);

	}

	private static void countEachElementFrequency(int[] arr) {

		HashMap<Integer, Integer> frequencyMap = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			int element = arr[i];
			if (frequencyMap.containsKey(element)) {
				frequencyMap.put(element, frequencyMap.get(element) + 1);
			} else {
				frequencyMap.put(element, 1);
			}
		}

		System.out.println("Frequency of each element: " + frequencyMap);
	}

}