package mayrevision;

public class CountVowelsAndConsonants {

	public static void main(String[] args) {
		
		String str = "Harsha";
		int vowels = 0;
		int consonants = 0;
		
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (isVowel(ch)) {
				vowels++;
			} else if (Character.isLetter(ch)) {
				consonants++;
			}
		}
		
		System.out.println("Number of vowels: " + vowels);
		System.out.println("Number of consonants: " + consonants);
	}

	private static boolean isVowel(char ch) {

		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
			ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
			return true;
		}
		return false;
	}
}
