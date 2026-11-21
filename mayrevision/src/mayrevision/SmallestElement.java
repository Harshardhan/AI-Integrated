package mayrevision;

public class SmallestElement {

	public static void main(String[] args) {

		int [] arr = { 5, 8, 3, 5, 2, 8 };
		
		int min = arr[0];
		
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] < min) {
				min = arr[i];
			}
		}
		System.out.println(min);
	}
}
