package mayrevision;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoSumTarget {

	public static void main(String[] args) {

		int[] arr = { 101,102,102,102,103};


		Set<Integer> elements = new HashSet<>();

		for(int id : arr)
			if(!elements.add(id)) {

				System.out.println(elements);
			}

		}}