package mayrevision;

import java.util.Arrays;
import java.util.HashSet;

public class SearchingUnSortedElement {

	public static void main(String[] args) {

		HashSet<Integer> numbers = new HashSet(Arrays.asList(50, 10, 40, 20, 30));
		int target = 60;

		boolean found = numbers.contains(target);
		
		System.out.println(found);
	}
}
