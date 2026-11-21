package mayrevision;

public class HelloWorld {

    static {
        System.out.println("Static Block");
    }

    public static void main(String[] args) {
        System.out.println("Main Method");

        HelloWorld obj = new HelloWorld();

        obj.display();
    }

    public void display() {
        System.out.println("Display Method");
    }
}