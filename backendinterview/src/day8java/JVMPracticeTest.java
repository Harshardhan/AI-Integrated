package day8java;

public class JVMPracticeTest {

    public static void main(String[] args) throws Exception {

        int age = 30;

        JVMPractice jvm = new JVMPractice();
        jvm.name = "Java Virtual Machine";

        printJVMPractice(jvm);

        // Keep program running for 60 seconds
        Thread.sleep(60000);
    }

    private static void printJVMPractice(JVMPractice jvm) {

        int salary = 200000;

        System.out.println("Name: " + jvm.name);
    }
}