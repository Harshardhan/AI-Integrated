package day9java;

import java.util.ArrayList;
import java.util.List;

public class GCPractice {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			list.add("String " + i);

			if (i % 10000 == 0) {

				System.out.println("Created " + (i + 1) + " strings");
			}
		}

		list.clear();
	}
}