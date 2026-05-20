package corejavapractice.oops;

public class ShapeTest {

	public static void main(String[] args) {
		Shape circle = new Circle(5.00);
		Shape rectangle =  new Rectangle(40.00, 60.00);
		
		
		System.out.println("Area of the circle: "+circle.calculateArea());
		System.out.println("Area of the rectangle: "+rectangle.calculateArea());
	}
}
