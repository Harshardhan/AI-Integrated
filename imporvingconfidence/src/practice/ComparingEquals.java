package practice;

import java.util.Objects;

public class ComparingEquals {


	public static void main(String[] args) {
		
		String s1 = "Hello";
		String s2 = "hello";
		String s3 = "Hello";
		String str4 = new String("Hello");
		
		System.out.println(s1==s3);
		System.out.println(s1.equals(s3));
		System.out.println(s1 == str4);
		System.out.println(s3.equals(str4));
	}
}
