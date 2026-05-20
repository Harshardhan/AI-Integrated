package interviewpreparation;

public class MajorityElement {

 public static void main(String[] args) {
	
	 int[] array = {1,3,3,2,2,2,2};
	 
	 int majorityElement = findMajorityElement(array);
	 System.out.println("The majority element is: " + majorityElement);
}

 private static int findMajorityElement(int[] array) {
	 int count = 1;
	 int candidate = array[0];
	 
	 for (int i = 1; i < array.length; i++) {
		 if (array[i] == candidate) {
			 count++;
		 } else {
			 count--;
			 if (count == 0) {
				 candidate = array[i];
				 count = 1;
			 }
		 }
	 }
	 
	 // Verify if the candidate is indeed the majority element
	 count = 0;
	 for (int num : array) {
		 if (num == candidate) {
			 count++;
		 }
	 }
	 
	 return count > array.length / 2 ? candidate : -1; // Return -1 if no majority element exists
 }
 }