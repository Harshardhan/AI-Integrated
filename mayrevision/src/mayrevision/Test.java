package mayrevision;

class Employers {

	String name;

	Employers(String name) {
		this.name = name;
	}

	void changeName(String name) {
		this.name = name;
	}

}

public class Test {

	public static void main(String[] args) {

		Employers e = new Employers("Harsha");

		e.changeName("Rahul");

		System.out.println(e.name);	}
}