package corejavapractice.oops;

import java.time.LocalDateTime;

public class Car {

	private String model;
	
	private LocalDateTime dateandTime;
	
	
	private String colour;
	
	private String manufacturer;

	
	
	public void start() {
		System.out.println("The car is starting");
	}
	
	/**
	 * @param model
	 * @param colour
	 * @param manufacturer
	 */
	public Car(String model, String colour, String manufacturer) {
		super();
		this.model = model;
		this.dateandTime = LocalDateTime.now();
		this.colour = colour;
		this.manufacturer = manufacturer;
	}

	public void stop() {
		System.out.println("The car is stopping");
	}
	
	
	public void displayDetails() {
        System.out.println("Car Details:");
        System.out.println("manufacturer: " + manufacturer);
        System.out.println("Model: " + model);
        System.out.println("dateandTime: " + dateandTime);
        System.out.println("Color: " + colour);
    }
	
	
	public static void main(String args[] ){
        Car myCar = new Car("Toyota", "Red", "Toyota Motor Corporation");
        
        // Displaying car details
        myCar.displayDetails();
        
        // Starting and stopping the car
        myCar.start();
        myCar.stop();
	}
	}
