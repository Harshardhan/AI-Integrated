package corejavapractice.oops;

public class Rectangle extends Shape{

	private double length;
	private double width;
	
	
	
	/**
	 * @param length
	 * @param width
	 */
	public Rectangle(double length, double width) {
		super();
		this.length = length;
		this.width = width;
	}



	@Override
	public double calculateArea() {
		// TODO Auto-generated method stub
		return length*width;
	}

}
