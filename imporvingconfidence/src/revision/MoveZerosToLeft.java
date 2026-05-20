package revision;

import java.util.Arrays;

public class MoveZerosToLeft {

	public static int[] zerosToLeft(int[] num) {
	    int insertPos = num.length - 1; // position to insert non-zero

	    // Step 1: Move non-zero elements to the end
	    for (int i = num.length - 1; i >= 0; i--) {
	        if (num[i] != 0) {
	            num[insertPos] = num[i];
	            insertPos--;
	        }
	    }

	    // Step 2: Fill remaining positions with zeros
	    while (insertPos >= 0) {
	        num[insertPos] = 0;
	        insertPos--;
	    }

	    return num;
	}

	public static void main(String[] args) {
		int[] result = {1,2,3,45,0,0,0,0,};
		int[] rotateLeft = zerosToLeft(result);
		System.out.println("Zeros start with Left: "+Arrays.toString(rotateLeft));
	}

}
