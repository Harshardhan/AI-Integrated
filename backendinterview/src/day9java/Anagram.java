package day9java;

public class Anagram {

	public static void main(String[] args) {
		
		String str1 = "Animal";
		String str2 = "lamina";
		
        if (checkAnagram(str1, str2)) {
            System.out.println("The given string is a Anagram");
        } else {
            System.out.println("The given string is Not Anagram");
        }
    }

	private static boolean checkAnagram(String str1, String str2) {
		str1 = str1.replaceAll("\\s", "").toLowerCase();
		str2 = str2.replaceAll("\\s", "").toLowerCase();
		
		if(str1.length() != str2.length()) {
			return false;
		}
		
		int[] count = new int[26];
		
		for(int i = 0; i< str1.length();i++) {
			count[str1.charAt(i) -'a']++;
			count[str2.charAt(i) - 'a']--;
		}
		
		for(int c : count) {
			if( c != 0) 
				return false;
			
		}
		return true;
	}
	

		
}
