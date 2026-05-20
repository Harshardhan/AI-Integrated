package practice;

public class EvenOddWithoutMod {
    public static void checkEvenOdd(int num) {
        if ((num / 2) * 2 == num) {
            System.out.println(num + " is Even");
        } else {
            System.out.println(num + " is Odd");
        }
    }

    public static void main(String[] args) {
        checkEvenOdd(8);
        checkEvenOdd(7);
    }
}
