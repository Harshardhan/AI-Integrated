package day9java;

public class LargestThreeNumbers {

	public static void main(String[] args) {

		int num1 = -25;
		int num2 = -32;
		int num3 = -18;

		int result = checkLargestThreeNumbers(num1, num2, num3);
		System.out.println("LargestThreeNumbers: " + result);
	}

	private static int checkLargestThreeNumbers(int num1, int num2, int num3) {

		if (num1 >= num2 && num1 >= num3) {
			return num1;
		} else if (num2 >= num1 && num2 >= num3) {
			return num2;
		} else if (num3 >= num1 && num3 >= num2) {
			return num3;
		}
		return 0;
	}
}

