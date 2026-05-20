package corejavapractice.oops;

public class Circle extends Shape {
	
	
	private double radius;
	
	/**
	 * @param radius
	 */
	public Circle(double radius) {
		super();
		this.radius = radius;
	}



	@Override
	public double calculateArea() {
		// TODO Auto-generated method stub
		return Math.PI*radius*radius;
	}

}
