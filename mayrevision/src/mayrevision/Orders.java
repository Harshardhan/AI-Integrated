package mayrevision;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Orders {

	public static void main(String[] args) {

		int i = 4;
		int k = 6;
		while(i != 0) {
			int rem = k %i;
			k = i;
			i = rem;
		}
		
		System.out.println(k);
}}