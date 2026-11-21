package mayrevision;

public class Anagram {

	public static void main(String[] args) {

		String str1 = "aabb";
		String str2 = "abbb";
		if (isAnagram(str1, str2)) {
			System.out.println(str1 + " and " + str2 + " are anagrams.");
		} else {
			System.out.println(str1 + " and " + str2 + " are not anagrams.");
		}
	}

	private static boolean isAnagram(String str1, String str2) {

		str1 = str1.replaceAll("\\s+", "").toLowerCase();
		str2 = str2.replaceAll("\\s+", "").toLowerCase();
		
		if (str1.length() != str2.length()) {
			return false;
		}
		int[] count = new int[256]; // Assuming ASCII character set
		for (int i = 0; i < str1.length(); i++) {
			count[str1.charAt(i)]++;
			count[str2.charAt(i)]--;
		}
		
		for(int counts : count) {
			if(counts != 0) {
				return false;
			}
			
		}
		return true;

	}
}
