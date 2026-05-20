package practice;

public class CountingSameElements {

	public static int countRepeatElements(int[] num, int target) {
		
		int count = 0;
		for(int i =0; i< num.length; i++) {

			if(num[i]==target) {
				count ++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		
        int[] result = {1,2,2,3,3,3,4,4,4,5,5};
        int target = 3;

        int repeatingElements = countRepeatElements(result, target);
        System.out.println("Number " + target + " appears " + repeatingElements + " times");
	}
}
