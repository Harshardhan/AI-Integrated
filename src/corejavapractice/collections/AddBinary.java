package corejavapractice.collections;

public class AddBinary {

    /**
     * Adds two binary strings and returns their sum as a binary string.
     * @param a The first binary string.
     * @param b The second binary string.
     * @return The sum of the binary strings as a binary string.
     */
    public static String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;

            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }

            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }

            result.append(sum % 2);
            carry = sum / 2;
        }

        return result.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "1010";
        String b = "1011";

        System.out.println("Sum: " + addBinary(a, b)); // Output: "10101"

        a = "11";
        b = "1";
        System.out.println("Sum: " + addBinary(a, b)); // Output: "100"
    }

}
