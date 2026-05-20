package practice;

public class CountDigitsInANumber {

	public static void main(String[] args) {
		
		int[] num= {1,22,333,4444,55555};
		
		for(int i = 0;i< num.length; i++ ) {
			int number = num[i];
			int count = 0;
			
			if(number ==0) {
				count =1;
			}else {
				while(number >0) {
					number = number/10;
					count ++;
				}
				
			}
		
        System.out.println("Digits in " + num[i] + " = " + count);
		}
	}
}
