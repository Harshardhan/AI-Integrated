package practice;

public class CountWordsInSentence {

	public static void countWords(String str) {
		int words = 0;
		str = str.trim();

		for (int i = 0; i < str.length(); i++) {
			char s = str.charAt(i);
			if (str.charAt(i) != ' ' && (i == 0 || str.charAt(i - 1) == ' ')) {
				words++;
			}
		}
		System.out.println("No. of words in a sentence: " + words);
	}

	public static void main(String[] args) {

		String input = "I am a JavaDeveloper at Rakuten.";

		countWords(input);
	}
}
