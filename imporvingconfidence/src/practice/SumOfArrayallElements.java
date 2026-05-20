package practice;


public class SumOfArrayallElements {

	public static int sumOfArrayElements(int[] number) {
		int  sum = 0;
		for(int i = 0; i< number.length; i++) {
			sum = sum+number[i];
		}
		return sum;
	}
	
	
	public static void main(String[] args) {
		
		int[] sum = {1,2,3,4,5};
		
	 int total = sumOfArrayElements(sum);
	 System.out.println("Sum OF Array Elements: "+total);
	}
}
