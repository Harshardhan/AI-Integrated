package practice;

public class CountVowelsAndConsonants {

	public static void countVowelsAndConsonants(String str) {

		int countVowels = 0;
		int countConsonants = 0;
		str = str.toLowerCase(); // handle uppercase letters

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch >= 'a' && ch <= 'z') { // check it's a letter
				if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
					countVowels++;
				} else {
					countConsonants++;
				}
			}
		}
			System.out.println("Number of Vowels in a given string: " + countVowels);
			System.out.println("Number of Consonants in a given string: " + countConsonants);
		
	}

	public static void main(String[] args) {
		String input = "harshavardhan";
		countVowelsAndConsonants(input);
	}
}
