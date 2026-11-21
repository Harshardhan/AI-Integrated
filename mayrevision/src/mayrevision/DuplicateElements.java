package mayrevision;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicateElements {

	public static void main(String[] args) {

		int[] arr = { 5, 8, 3, 5, 2, 8 };

		findFirstDuplicate(arr);

	}

	private static void findFirstDuplicate(int[] arr) {

		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			if (set.add(arr[i]) == false) {
				System.out.println("First duplicate element is: " + arr[i]);
				return;

			}
		}
	}
}