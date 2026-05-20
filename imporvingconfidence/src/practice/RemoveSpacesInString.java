package practice;

public class RemoveSpacesInString {

	public static String removeSpaces(String str) {
	    return str.replaceAll("\\s", "");
	}
	
	public static void main(String[] args) {
		
		String input = "I am a Java Developer";
		String result = removeSpaces(input);
		System.out.println("Remove Spaces in a given string: "+result);
		
	}
}
